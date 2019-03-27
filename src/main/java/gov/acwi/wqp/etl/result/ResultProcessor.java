package gov.acwi.wqp.etl.result;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataResult;
import gov.acwi.wqp.etl.result.Result;

public class ResultProcessor implements ItemProcessor<BiodataResult, Result>{
	private static final Logger LOG = LoggerFactory.getLogger(ResultProcessor.class);

	public static final int DEFAULT_SRID = 4269;

	@Override
	public Result process(BiodataResult biodataResult) throws Exception {
		Result result = new Result();
		result.setDataSourceId(Application.DATA_SOURCE_ID);
		result.setDataSource(Application.DATA_SOURCE);
		result.setStationId(biodataResult.getStationId());
		result.setSiteId(biodataResult.getSiteId());
		result.setEventDate(LocalDate.parse(biodataResult.getActivityStartDate()));
		result.setActivity(biodataResult.getActivityIdentifier());
		result.setSampleMedia(biodataResult.getActivityMediaName());
		result.setOrganization(biodataResult.getOrganization());
		result.setSiteType(biodataResult.getResolvedMonitoringLocationTypeName());
		result.setHuc(biodataResult.getHucTwelveDigitCode());
		result.setGovernmentalUnitCode(biodataResult.getGovernmentalUnitCode());
		result.setGeom(biodataResult.getGeom()); 
		result.setOrganizationName(biodataResult.getOrganizationName());
		result.setActivityId(biodataResult.getActivityId());
		result.setActivityTypeCode(biodataResult.getActivityTypeCode());
		result.setActivityStartTime(biodataResult.getActivityStartTime());
		result.setActStartTimeZone(biodataResult.getActivityStartTimeZoneCode());
		result.setProjectId(biodataResult.getProjectIdentifier());
		result.setProjectName(biodataResult.getProjectName());
		result.setMonitoringLocationName(biodataResult.getMonitoringLocationName());
		result.setSampleCollectMethodId(biodataResult.getSampleCollectionMethodIdentifier());
		result.setSampleCollectMethodCtx(biodataResult.getSampleCollectionMethodIdentifierContext());
		result.setSampleCollectMethodName(biodataResult.getSampleCollectionMethodName());
		result.setSampleCollectEquipName(biodataResult.getSampleCollectionEquipmentName());

		result.setResultId(biodataResult.getResultId());
		result.setResultDetectionConditionTx(biodataResult.getResultDetectionConditionText());
		result.setCharacteristicName(biodataResult.getCharacteristicName());
		result.setCharacteristicType(biodataResult.getCharacteristicType());
		result.setSampleFractionType(biodataResult.getResultSampleFractionText());
		result.setResultMeasureValue(biodataResult.getResultMeasureValue());
		result.setResultUnit(biodataResult.getResultMeasureUnitCode());
		result.setResultValueStatus(biodataResult.getResultStatusIdentifier());
		result.setResultValueType(biodataResult.getResultValueTypeName());
		result.setPrecision(biodataResult.getDataQualityPrecisionValue());
		result.setResultComment(biodataResult.getResultCommentText());
		result.setAnalyticalProcedureId(biodataResult.getResultAnalyticalMethodIdentifier());
		result.setAnalyticalProcedureSource(biodataResult.getResultAnalyticalMethodIdentifierContext());
		result.setAnalyticalMethodName(biodataResult.getResultAnalyticalMethodName());
		result.setAnalyticalMethodCitation(biodataResult.getResultAnalyticalMethodDescriptionText());
		result.setDetectionLimit(biodataResult.getDetectionQuantitationLimitMeasureValue());
		result.setDetectionLimitUnit(biodataResult.getDetectionQuantitationLimitMeasureUnitCode());
		result.setDetectionLimitDesc(biodataResult.getDetectionQuantitationLimitTypeName());
		LOG.info(result.toString());
		return result;
	}

}
