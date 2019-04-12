//package gov.acwi.wqp.etl.biodata.domain;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.batch.item.ItemProcessor;
//
//public class BiodataResultProcessor implements ItemProcessor<WqxActivity, BiodataResult>{
//
//	@Override
//	public BiodataResult process(WqxActivity wqxActivity) throws Exception {
//		BiodataResult biodataResult = new BiodataResult();
//		if (null != wqxActivity.getActivityDescription()) {
//			biodataResult.setActivityIdentifier(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityIdentifier()));
//			biodataResult.setActivityTypeCode(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityTypeCode()));
//			biodataResult.setActivityMediaName(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityMediaName()));
//			biodataResult.setActivityStartDate(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityStartDate()));
//			if (null != wqxActivity.getActivityDescription().getActivityStartTime()) {
//				biodataResult.setActivityStartTime(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityStartTime().getTime()));
//				biodataResult.setActivityStartTimeZoneCode(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityStartTime().getTimeZoneCode()));
//			}
//			biodataResult.setMeasureValue(StringUtils.trimToNull(wqxActivity.getActivityDescription().getMeasureValue()));
//			biodataResult.setMeasureUnitCode(StringUtils.trimToNull(wqxActivity.getActivityDescription().getMeasureUnitCode()));
//			biodataResult.setActivityDepthHeightMeasure(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityDepthHeightMeasure()));
//			biodataResult.setProjectIdentifier(StringUtils.trimToNull(wqxActivity.getActivityDescription().getProjectIdentifier()));
//			biodataResult.setMonitoringLocationIdentifier(StringUtils.trimToNull(wqxActivity.getActivityDescription().getMonitoringLocationIdentifier()));
//			biodataResult.setActivityCommentText(StringUtils.trimToNull(wqxActivity.getActivityDescription().getActivityCommentText()));
//		}
//		if (null != wqxActivity.getSampleDescription()) {
//			if (null != wqxActivity.getSampleDescription().getSampleCollectionMethod()) {
//				biodataResult.setSampleCollectionMethodIdentifier(StringUtils.trimToNull(wqxActivity.getSampleDescription().getSampleCollectionMethod().getMethodIdentifier()));
//				biodataResult.setSampleCollectionMethodIdentifierContext(StringUtils.trimToNull(wqxActivity.getSampleDescription().getSampleCollectionMethod().getMethodIdentifierContext()));
//				biodataResult.setSampleCollectionMethodName(StringUtils.trimToNull(wqxActivity.getSampleDescription().getSampleCollectionMethod().getMethodName()));
//				biodataResult.setSampleCollectionMethodDescriptionText(StringUtils.trimToNull(wqxActivity.getSampleDescription().getSampleCollectionMethod().getMethodDescriptionText()));
//			}
//			biodataResult.setSampleCollectionEquipmentName(StringUtils.trimToNull(wqxActivity.getSampleDescription().getSampleCollectionEquipmentName()));
//			biodataResult.setSampleCollectionEquipmentCommentText(StringUtils.trimToNull(wqxActivity.getSampleDescription().getSampleCollectionEquipmentCommentText()));
//		}
//		if (null != wqxActivity.getResult()) {
//			if (null != wqxActivity.getResult().getResultDescription()) {
//				biodataResult.setResultDetectionConditionText(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultDetectionConditionText()));
//				biodataResult.setCharacteristicName(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getCharacteristicName()));
//				biodataResult.setResultSampleFractionText(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultSampleFractionText()));
//				if (null != wqxActivity.getResult().getResultDescription().getResultMeasure()) {
//					biodataResult.setResultMeasureValue(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultMeasure().getResultMeasureValue()));
//					biodataResult.setResultMeasureUnitCode(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultMeasure().getMeasureUnitCode()));
//				}
//				biodataResult.setResultStatusIdentifier(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultStatusIdentifier()));
//				biodataResult.setResultValueTypeName(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultValueTypeName()));
//				if (null != wqxActivity.getResult().getResultDescription().getDataQuality()) {
//					biodataResult.setDataQualityPrecisionValue(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getDataQuality().getPrecisionValue()));
//				}
//				biodataResult.setResultCommentText(StringUtils.trimToNull(wqxActivity.getResult().getResultDescription().getResultCommentText()));
//			}
//			if (null != wqxActivity.getResult().getResultAnalyticalMethod()) {
//				biodataResult.setResultAnalyticalMethodIdentifier(StringUtils.trimToNull(wqxActivity.getResult().getResultAnalyticalMethod().getMethodIdentifier()));
//				biodataResult.setResultAnalyticalMethodIdentifierContext(StringUtils.trimToNull(wqxActivity.getResult().getResultAnalyticalMethod().getMethodIdentifierContext()));
//				biodataResult.setResultAnalyticalMethodName(StringUtils.trimToNull(wqxActivity.getResult().getResultAnalyticalMethod().getMethodName()));
//				biodataResult.setResultAnalyticalMethodDescriptionText(StringUtils.trimToNull(wqxActivity.getResult().getResultAnalyticalMethod().getMethodDescriptionText()));
//			}
//			if (null != wqxActivity.getResult().getResultLabInformation()
//					&& null != wqxActivity.getResult().getResultLabInformation().getResultDetectionQuantitationLimit()) {
//				biodataResult.setDetectionQuantitationLimitTypeName(StringUtils.trimToNull(wqxActivity.getResult().getResultLabInformation().getResultDetectionQuantitationLimit().getDetectionQuantitationLimitTypeName()));
//				if (null != wqxActivity.getResult().getResultLabInformation().getResultDetectionQuantitationLimit().getDetectionQuantitationLimitMeasure()) {
//					biodataResult.setDetectionQuantitationLimitMeasureValue(StringUtils.trimToNull(wqxActivity.getResult().getResultLabInformation().getResultDetectionQuantitationLimit().getDetectionQuantitationLimitMeasure().getMeasureValue()));
//					biodataResult.setDetectionQuantitationLimitMeasureUnitCode(StringUtils.trimToNull(wqxActivity.getResult().getResultLabInformation().getResultDetectionQuantitationLimit().getDetectionQuantitationLimitMeasure().getMeasureUnitCode()));
//				}
//			}
//		}
//		return biodataResult;
//	}
//}
