package gov.acwi.wqp.etl.monitoringLocation;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.acwi.wqp.etl.biodata.domain.BiodataStation;
import gov.acwi.wqp.etl.biodata.domain.BiodataStationRowMapper;


@Configuration
public class TransformMonitoringLocation {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
    
    @Autowired
	@Qualifier("wqpDataSource")
	private DataSource wqpDataSource;
    
    @Autowired
	@Qualifier("biodataDataSource")
	private DataSource biodataDataSource;

	@Autowired
	@Qualifier("setupMonitoringLocationSwapTableFlow")
	private Flow setupMonitoringLocationSwapTableFlow;

	@Autowired
	@Qualifier("buildMonitoringLocationIndexesFlow")
	private Flow buildMonitoringLocationIndexesFlow;

	@Bean
	public JdbcCursorItemReader<BiodataStation> monitoringLocationReader() {
		return new JdbcCursorItemReaderBuilder<BiodataStation>()
				.dataSource(this.biodataDataSource)
				.name("monitoringLocationReader")
				//TODo cleanup for PostgreSQL
//				.sql("select biodata_station.*, biodata_org_project.*, biodata_site_type_to_primary.primary_site_type resolved_monitoring_location_type_name from biodata_station join biodata_org_project on 1=1 left join biodata_site_type_to_primary on biodata_station.monitoring_location_type_name = biodata_site_type_to_primary.site_type")
				
				// THIS IS THE TRANSFORM_STATION.sql SELECT
				.sql("select happy_bunnies.*, biodata_org_project.*, biodata_site_type_to_primary.primary_site_type resolved_monitoring_location_type_name from happy_bunnies join biodata_org_project on 1=1 left join biodata_site_type_to_primary on happy_bunnies.monitoring_location_type_name = biodata_site_type_to_primary.site_type")
				.rowMapper(new BiodataStationRowMapper())
				.build();
	}

	@Bean
	public ItemWriter<MonitoringLocation> monitoringLocationWriter() {
		JdbcBatchItemWriter<MonitoringLocation> itemWriter = new JdbcBatchItemWriter<MonitoringLocation>();
		itemWriter.setDataSource(wqpDataSource);
		
		// THIS IS THE TRANSFORM_STATION.sql INSERT
		itemWriter.setSql("insert "
				+ " into station_swap_biodata (data_source_id, data_source, station_id, site_id, organization, site_type, huc, governmental_unit_code,"
					+ " geom, station_name, organization_name, description_text, station_type_name, latitude, longitude, map_scale,"
					+ " geopositioning_method, hdatum_id_code, elevation_value, elevation_unit, elevation_method, vdatum_id_code,"
					+ " drain_area_value, drain_area_unit, contrib_drain_area_value, contrib_drain_area_unit,"
					+ " geoposition_accy_value, geoposition_accy_unit, vertical_accuracy_value, vertical_accuracy_unit,"
					+ " nat_aqfr_name, aqfr_name, aqfr_type_name, construction_date, well_depth_value, well_depth_unit,"
					+ " hole_depth_value, hole_depth_unit)"
				+ " values (:dataSourceId, :dataSource, :stationId, :siteId, :organization, :siteType, :huc, :governmentalUnitCode,"
					+ " :geom::geometry, :stationName, :organizationName, :descriptionText, :stationTypeName, :latitude, :longitude, :mapScale,"
					+ " :geopositioningMethod, :hdatumIdCode, :elevationValue, :elevationUnit, :elevationMethod, :vdatumIdCode,"
					+ " :drainAreaValue, :drainAreaUnit, :contribDrainAreaValue, :contribDrainAreaUnit,"
					+ " :geopositionAccyValue, :geopositionAccyUnit, :verticalAccuracyValue, :verticalAccuracyUnit,"
					+ " :natAqfrName, :aqfrName, :aqfrTypeName, :constructionDate, :wellDepthValue, :wellDepthUnit,"
					+ " :holeDepthValue, :holeDepthUnit)");
		ItemSqlParameterSourceProvider<MonitoringLocation> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}

	@Bean
	public Step transformMonitoringLocationStep() {
		return stepBuilderFactory
				.get("transformMonitoringLocationStep")
				.<BiodataStation, MonitoringLocation>chunk(10)
				.reader(monitoringLocationReader())
				.processor(new MonitoringLocationProcessor())
				.writer(monitoringLocationWriter())
				.build();
	}

	@Bean
	public Flow monitoringLocationFlow() {
		return new FlowBuilder<SimpleFlow>("monitoringLocationFlow")
				.start(setupMonitoringLocationSwapTableFlow)
				.next(transformMonitoringLocationStep())
				.next(buildMonitoringLocationIndexesFlow)
				.build();
	}

}
