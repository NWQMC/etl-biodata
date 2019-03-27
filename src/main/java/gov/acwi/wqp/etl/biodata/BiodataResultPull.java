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

import gov.acwi.wqp.etl.biodata.domain.BiodataResult;
import gov.acwi.wqp.etl.biodata.domain.BiodataResultProcessor;
import gov.acwi.wqp.etl.biodata.domain.WqxActivity;
import gov.acwi.wqp.etl.biodata.domain.WqxActivityDescription;
import gov.acwi.wqp.etl.biodata.domain.WqxCollectionMethod;
import gov.acwi.wqp.etl.biodata.domain.WqxDataQuality;
import gov.acwi.wqp.etl.biodata.domain.WqxDetectionQuantitationLimitMeasure;
import gov.acwi.wqp.etl.biodata.domain.WqxResult;
import gov.acwi.wqp.etl.biodata.domain.WqxResultAnalyticalMethod;
import gov.acwi.wqp.etl.biodata.domain.WqxResultDescription;
import gov.acwi.wqp.etl.biodata.domain.WqxResultDetectionQuantitationLimit;
import gov.acwi.wqp.etl.biodata.domain.WqxResultLabInformation;
import gov.acwi.wqp.etl.biodata.domain.WqxResultMeasure;
import gov.acwi.wqp.etl.biodata.domain.WqxSampleDescription;
import gov.acwi.wqp.etl.biodata.domain.WqxTime;


@Configuration
public class BiodataResultPull {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Value("classpath:testData/xml/wqxResult.xml")
	private Resource resource;

	@Autowired
	@Qualifier("truncateBiodataResult")
	private Tasklet truncateBiodataResult;

	@Bean
	public StaxEventItemReader<WqxActivity> biodataResultReader() {
		StaxEventItemReader<WqxActivity> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(resource);
		staxEventItemReader.setFragmentRootElementName("Activity");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(WqxActivity.class, WqxActivityDescription.class,
				WqxTime.class, WqxSampleDescription.class, WqxCollectionMethod.class,
				WqxResult.class, WqxResultDescription.class, WqxResultMeasure.class,
				WqxDataQuality.class, WqxResultAnalyticalMethod.class, WqxDetectionQuantitationLimitMeasure.class,
				WqxResultLabInformation.class, WqxResultDetectionQuantitationLimit.class
				);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public ItemWriter<BiodataResult> biodataResultWriter() {
		JdbcBatchItemWriter<BiodataResult> itemWriter = new JdbcBatchItemWriter<BiodataResult>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("insert "
				+ " into biodata_result (activity_identifier, activity_type_code, activity_media_name, activity_start_date, activity_start_time, activity_start_time_zone_code,"
					+ " measure_value, measure_unit_code, activity_depth_height_measure, project_identifier, monitoring_location_identifier, activity_comment_text,"
					+ " sample_collection_method_identifier, sample_collection_method_identifier_context, sample_collection_method_name, sample_collection_method_description_text,"
					+ " sample_collection_equipment_name, sample_collection_equipment_comment_text, result_detection_condition_text, characteristic_name, result_sample_fraction_text,"
					+ " result_measure_value, result_measure_unit_code, result_status_identifier, result_value_type_name, data_quality_precision_value, result_comment_text,"
					+ " result_analytical_method_identifier, result_analytical_method_identifier_context, result_analytical_method_name, result_analytical_method_description_text,"
					+ " detection_quantitation_limit_type_name, detection_quantitation_limit_measure_value, detection_quantitation_limit_measure_unit_code)"
				+ " values (:activityIdentifier, :activityTypeCode, :activityMediaName, :activityStartDate, :activityStartTime, :activityStartTimeZoneCode,"
					+ " :measureValue, :measureUnitCode, :activityDepthHeightMeasure, :projectIdentifier, :monitoringLocationIdentifier, :activityCommentText,"
					+ " :sampleCollectionMethodIdentifier, :sampleCollectionMethodIdentifierContext, :sampleCollectionMethodName, :sampleCollectionMethodDescriptionText,"
					+ " :sampleCollectionEquipmentName, :sampleCollectionEquipmentCommentText, :resultDetectionConditionText, :characteristicName, :resultSampleFractionText,"
					+ " :resultMeasureValue, :resultMeasureUnitCode, :resultStatusIdentifier, :resultValueTypeName, :dataQualityPrecisionValue, :resultCommentText,"
					+ " :resultAnalyticalMethodIdentifier, :resultAnalyticalMethodIdentifierContext, :resultAnalyticalMethodName, :resultAnalyticalMethodDescriptionText,"
					+ " :detectionQuantitationLimitTypeName, :detectionQuantitationLimitMeasureValue, :detectionQuantitationLimitMeasureUnitCode)");

		ItemSqlParameterSourceProvider<BiodataResult> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();

		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}


	@Bean
	public Step truncateBiodataResultStep() {
		return stepBuilderFactory
				.get("truncateBiodataResultStep")
				.tasklet(truncateBiodataResult)
				.build();
	}
	@Bean
	public Step biodataResultPullStep() {
		return stepBuilderFactory.get("biodataResultPullStep")
				.<WqxActivity, BiodataResult>chunk(10)
				.reader(biodataResultReader())
				.processor(new BiodataResultProcessor())
				.writer(biodataResultWriter())
				.build();
	}

	@Bean
	public Flow biodataResultPullFlow() {
		return new FlowBuilder<SimpleFlow>("biodataResultPullFlow")
				.start(truncateBiodataResultStep())
				.next(biodataResultPullStep())
				.build();
	}

}
