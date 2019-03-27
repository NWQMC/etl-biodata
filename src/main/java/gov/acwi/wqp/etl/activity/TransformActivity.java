package gov.acwi.wqp.etl.activity;

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

import gov.acwi.wqp.etl.biodata.domain.BiodataResult;
import gov.acwi.wqp.etl.biodata.domain.BiodataResultActivityRowMapper;


@Configuration
public class TransformActivity {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("setupActivitySwapTableFlow")
	private Flow setupActivitySwapTableFlow;

	@Autowired
	@Qualifier("buildActivityIndexesFlow")
	private Flow buildActivityIndexesFlow;

	@Bean
	public JdbcCursorItemReader<BiodataResult> activityReader() {
		return new JdbcCursorItemReaderBuilder<BiodataResult>()
				.dataSource(this.dataSource)
				.name("organizationReader")
				//TODo cleanup for PostgreSQL
				.sql("select row_number() over () activity_id, a.* from (select distinct "
						+ "  biodata_result.activity_start_date,"
						+ "  biodata_result.activity_identifier,"
						+ "  biodata_result.activity_media_name,"
						+ "  biodata_result.activity_type_code,"
						+ "  biodata_result.activity_start_time,"
						+ "  biodata_result.activity_start_time_zone_code,"
						+ "  biodata_result.project_identifier,"
						+ "  biodata_result.sample_collection_method_identifier,"
						+ "  biodata_result.sample_collection_method_identifier_context,"
						+ "  biodata_result.sample_collection_method_name,"
						+ "  biodata_result.sample_collection_method_description_text,"
						+ "  biodata_result.sample_collection_equipment_name,"
						+ "  biodata_result.sample_collection_equipment_comment_text,"
						+ "  station_swap_stewards.station_id,"
						+ "  station_swap_stewards.site_id,"
						+ "  station_swap_stewards.organization,"
						+ "  station_swap_stewards.organization_name,"
						+ "  station_swap_stewards.site_type,"
						+ "  station_swap_stewards.huc,"
						+ "  station_swap_stewards.governmental_unit_code,"
						+ "  station_swap_stewards.geom,"
						+ "  station_swap_stewards.station_name,"
						+ "  project_data_swap_stewards.project_name"
						+ " from biodata_result"
						+ "      join station_swap_stewards"
						+ "        on biodata_result.monitoring_location_identifier = substring(station_swap_stewards.site_id, 5)"
						+ "      join project_data_swap_stewards"
						+ "        on biodata_result.project_identifier = project_data_swap_stewards.project_identifier"
						+ "   order by biodata_result.activity_identifier) a")
				.rowMapper(new BiodataResultActivityRowMapper())
				.build();
	}

	@Bean
	public ItemWriter<Activity> activityWriter() {
		JdbcBatchItemWriter<Activity> itemWriter = new JdbcBatchItemWriter<Activity>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("insert "
				+ " into activity_swap_stewards (data_source_id, data_source, station_id, site_id, event_date, activity," + 
				"                                sample_media, organization, site_type, huc, governmental_unit_code, geom," + 
				"                                organization_name, activity_id, activity_type_code, activity_start_time," + 
				"                                act_start_time_zone, project_id, project_name, monitoring_location_name," + 
				"                                sample_collect_method_id, sample_collect_method_ctx, sample_collect_method_name, sample_collect_equip_name)" + 
				"                        values (:dataSourceId, :dataSource, :stationId, :siteId, :eventDate, :activity," + 
				"                                :sampleMedia, :organization, :siteType, :huc, :governmentalUnitCode, :geom," + 
				"                                :organizationName, :activityId, :activityTypeCode, :activityStartTime," + 
				"                                :actStartTimeZone, :projectId, :projectName, :monitoringLocationName," + 
				"                                :sampleCollectMethodId, :sampleCollectMethodCtx, :sampleCollectMethodName, :sampleCollectEquipName)");
		ItemSqlParameterSourceProvider<Activity> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}

	@Bean
	public Step transformActivityStep() {
		return stepBuilderFactory
				.get("transformActivityStep")
				.<BiodataResult, Activity>chunk(10)
				.reader(activityReader())
				.processor(new ActivityProcessor())
				.writer(activityWriter())
				.build();
	}

	@Bean
	public Flow activityFlow() {
		return new FlowBuilder<SimpleFlow>("activityFlow")
				.start(setupActivitySwapTableFlow)
				.next(transformActivityStep())
				.next(buildActivityIndexesFlow)
				.build();
	}

}
