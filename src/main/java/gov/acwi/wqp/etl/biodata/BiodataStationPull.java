package gov.acwi.wqp.etl.biodata;

import gov.acwi.wqp.etl.extract.*;
import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import gov.acwi.wqp.etl.biodata.domain.BiodataStation;
import gov.acwi.wqp.etl.biodata.domain.BiodataStationProcessor;
import gov.acwi.wqp.etl.biodata.domain.WqxDrainageAreaMeasure;
import gov.acwi.wqp.etl.biodata.domain.WqxMonitoringLocation;
import gov.acwi.wqp.etl.biodata.domain.WqxMonitoringLocationGeospatial;
import gov.acwi.wqp.etl.biodata.domain.WqxMonitoringLocationIdentity;


@Configuration
public class BiodataStationPull {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Value("classpath:testData/xml/wqxStation.xml")
	private Resource resource;

	@Autowired
	@Qualifier("truncateBiodataStation")
	private Tasklet truncateBiodataStation;

	@Bean
	public StaxEventItemReader<WqxMonitoringLocation> biodataStationReader() {
		StaxEventItemReader<WqxMonitoringLocation> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(resource);
		staxEventItemReader.setFragmentRootElementName("MonitoringLocation");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(WqxMonitoringLocation.class, WqxMonitoringLocationIdentity.class,
				WqxDrainageAreaMeasure.class, WqxMonitoringLocationGeospatial.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public ItemWriter<BiodataStation> biodataStationWriter() {
		JdbcBatchItemWriter<BiodataStation> itemWriter = new JdbcBatchItemWriter<BiodataStation>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("insert "
				+ " into biodata_station (monitoring_location_identifier, monitoring_location_name, monitoring_location_type_name, monitoring_location_description_text, huc_eight_digit_code, huc_twelve_digit_code, drainage_area_measure_value, drainage_area_measure_unit_code, latitude_measure, longitude_measure, horizontal_collection_method_name, horizontal_coordinate_reference_system_datum_name, country_code, state_code, county_code)"
				+ " values (:monitoringLocationIdentifier, :monitoringLocationName, :monitoringLocationTypeName, :monitoringLocationDescriptionText, :hucEightDigitCode, :hucTwelveDigitCode, :drainageAreaMeasureValue, :drainageAreaMeasureUnitCode, :latitudeMeasure, :longitudeMeasure, :horizontalCollectionMethodName, :horizontalCoordinateReferenceSystemDatumName, :countryCode, :stateCode, :countyCode)");

		ItemSqlParameterSourceProvider<BiodataStation> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();

		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}


	@Bean
	public Step truncateBiodataStationStep() {
		return stepBuilderFactory
				.get("truncateBiodataStationStep")
				.tasklet(truncateBiodataStation)
				.build();
	}
	@Bean
	public Step biodataStationPullStep() {
		return stepBuilderFactory.get("biodataStationPullStep")
				.<WqxMonitoringLocation, BiodataStation>chunk(10)
				.reader(biodataStationReader())
				.processor(new BiodataStationProcessor())
				.writer(biodataStationWriter())
				.build();
	}

	@Bean
	public Flow biodataStationPullFlow() {
		return new FlowBuilder<SimpleFlow>("biodataStationPullFlow")
				.start(truncateBiodataStationStep())
				.next(biodataStationPullStep())
				.build();
	}

}
