package gov.acwi.wqp.etl.biodata.domain;

import gov.acwi.wqp.etl.extract.domain.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgis.PGgeometry;
import org.springframework.jdbc.core.RowMapper;

public class BiodataResultResultRowMapper implements RowMapper<BiodataResult> {

	public static final String RESULT_ID_COLUMN_NAME = "result_id";
	public static final String RESULT_DETECTION_CONDITION_TEXT_COLUMN_NAME = "result_detection_condition_text";
	public static final String CHARACTERISTIC_NAME_COLUMN_NAME = "characteristic_name";
	public static final String CHARACTERISTIC_TYPE_COLUMN_NAME = "characteristic_type";
	public static final String RESULT_SAMPLE_FRACTION_TEXT_COLUMN_NAME = "result_sample_fraction_text";
	public static final String RESULT_MEASURE_COLUMN_NAME = "result_measure_value";
	public static final String RESULT_MEASURE_UNIT_CODE_COLUMN_NAME = "result_measure_unit_code";
	public static final String RESULT_STATUS_IDENTIFIER_COLUMN_NAME = "result_status_identifier";
	public static final String RESULT_VALUE_TYPE_NAME_COLUMN_NAME = "result_value_type_name";
	public static final String DATA_QUALITY_PRECISION_VALUE_COLUMN_NAME = "data_quality_precision_value";
	public static final String RESULT_COMMENT_TEXT_COLUMN_NAME = "result_comment_text";
	public static final String RESULT_ANALYTICAL_METHOD_IDENTIFIER_COLUMN_NAME = "result_analytical_method_identifier";
	public static final String RESULT_ANALYTICAL_METHOD_IDENTIFIER_CONTEXT_COLUMN_NAME = "result_analytical_method_identifier_context";
	public static final String RESULT_ANALYTICAL_METHOD_NAME_COLUMN_NAME = "result_analytical_method_name";
	public static final String RESULT_ANALYTICAL_METHOD_DESCRIPTION_TEXT_COLUMN_NAME = "result_analytical_method_description_text";
	public static final String DETECTION_QUANTITATIVE_LIMIT_TYPE_NAME_COLUMN_NAME = "detection_quantitation_limit_type_name";
	public static final String DETECTION_QUANTITATIVE_LIMIT_MEASURE_VALUE_COLUMN_NAME = "detection_quantitation_limit_measure_value";
	public static final String DETECTION_QUANTITATIVE_LIMIT_MEASURE_UNIT_CODE_COLUMN_NAME = "detection_quantitation_limit_measure_unit_code";

	@Override
	public BiodataResult mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataResult arsResult = new BiodataResult();
		arsResult.setOrganization(rs.getString(BiodataOrganizationRowMapper.ORGANIZATION_COLUMN_NAME));
		arsResult.setOrganizationName(rs.getString(BiodataOrganizationRowMapper.ORGANIZATION_NAME_COLUMN_NAME));
		arsResult.setProjectIdentifier(rs.getString(BiodataOrganizationRowMapper.PROJECT_IDENTIFIER_COLUMN_NAME));
		arsResult.setProjectName(rs.getString(BiodataOrganizationRowMapper.PROJECT_NAME_COLUMN_NAME));
		arsResult.setStationId(rs.getInt(BiodataStationRowMapper.STATION_ID_COLUMN_NAME));
		arsResult.setSiteId(rs.getString(BiodataResultActivityRowMapper.SITE_ID_COLUMN_NAME));
		arsResult.setMonitoringLocationName(rs.getString(BiodataStationRowMapper.MONITORING_LOCATION_NAME_COLUMN_NAME));
		arsResult.setResolvedMonitoringLocationTypeName(rs.getString(BiodataResultActivityRowMapper.SITE_TYPE_COLUMN_NAME));
		arsResult.setHucTwelveDigitCode(rs.getString(BiodataResultActivityRowMapper.HUC_COLUMN_NAME));
		arsResult.setGovernmentalUnitCode(rs.getString(BiodataResultActivityRowMapper.GOVERNMENTAL_UNIT_CODE_COLUMN_NAME));
		arsResult.setGeom((PGgeometry) rs.getObject(BiodataResultActivityRowMapper.GEOM_COLUMN_NAME));
		arsResult.setActivityId(rs.getInt(BiodataResultActivityRowMapper.ACTIVITY_ID_COLUMN_NAME));
		arsResult.setActivityIdentifier(rs.getString(BiodataResultActivityRowMapper.ACTIVITY_IDENTIFIER_COLUMN_NAME));
		arsResult.setActivityTypeCode(rs.getString(BiodataResultActivityRowMapper.ACTIVITY_TYPE_CODE_COLUMN_NAME));
		arsResult.setActivityMediaName(rs.getString(BiodataResultActivityRowMapper.ACTIVITY_MEDIA_NAME_COLUMN_NAME));
		arsResult.setActivityStartDate(rs.getString(BiodataResultActivityRowMapper.ACTIVITY_START_DATE_COLUMN_NAME));
		arsResult.setActivityStartTime(rs.getString(BiodataResultActivityRowMapper.ACTIVITY_START_TIME_COLUMN_NAME));
		arsResult.setActivityStartTimeZoneCode(rs.getString(BiodataResultActivityRowMapper.ACTIVITY_START_TIME_ZONE_CODE_COLUMN_NAME));
		arsResult.setSampleCollectionMethodIdentifier(rs.getString(BiodataResultActivityRowMapper.SAMPLE_COLLECTION_METHOD_IDENTIFIER_COLUMN_NAME));
		arsResult.setSampleCollectionMethodIdentifierContext(rs.getString(BiodataResultActivityRowMapper.SAMPLE_COLLECTION_METHOD_IDENTIFIER_CONTEXT_COLUMN_NAME));
		arsResult.setSampleCollectionMethodName(rs.getString(BiodataResultActivityRowMapper.SAMPLE_COLLECTION_METHOD_NAME_COLUMN_NAME));
		arsResult.setSampleCollectionEquipmentName(rs.getString(BiodataResultActivityRowMapper.SAMPLE_COLLECTION_EQUIPMENT_NAME_COLUMN_NAME));

		arsResult.setResultId(rs.getInt(RESULT_ID_COLUMN_NAME));
		arsResult.setResultDetectionConditionText(rs.getString(RESULT_DETECTION_CONDITION_TEXT_COLUMN_NAME));
		arsResult.setCharacteristicName(rs.getString(CHARACTERISTIC_NAME_COLUMN_NAME));
		arsResult.setCharacteristicType(rs.getString(CHARACTERISTIC_TYPE_COLUMN_NAME));
		arsResult.setResultSampleFractionText(rs.getString(RESULT_SAMPLE_FRACTION_TEXT_COLUMN_NAME));
		arsResult.setResultMeasureValue(rs.getString(RESULT_MEASURE_COLUMN_NAME));
		arsResult.setResultMeasureUnitCode(rs.getString(RESULT_MEASURE_UNIT_CODE_COLUMN_NAME));
		arsResult.setResultStatusIdentifier(rs.getString(RESULT_STATUS_IDENTIFIER_COLUMN_NAME));
		arsResult.setResultValueTypeName(rs.getString(RESULT_VALUE_TYPE_NAME_COLUMN_NAME));
		arsResult.setDataQualityPrecisionValue(rs.getString(DATA_QUALITY_PRECISION_VALUE_COLUMN_NAME));
		arsResult.setResultCommentText(rs.getString(RESULT_COMMENT_TEXT_COLUMN_NAME));
		arsResult.setResultAnalyticalMethodIdentifier(rs.getString(RESULT_ANALYTICAL_METHOD_IDENTIFIER_COLUMN_NAME));
		arsResult.setResultAnalyticalMethodIdentifierContext(rs.getString(RESULT_ANALYTICAL_METHOD_IDENTIFIER_CONTEXT_COLUMN_NAME));
		arsResult.setResultAnalyticalMethodName(rs.getString(RESULT_ANALYTICAL_METHOD_NAME_COLUMN_NAME));
		arsResult.setResultAnalyticalMethodDescriptionText(rs.getString(RESULT_ANALYTICAL_METHOD_DESCRIPTION_TEXT_COLUMN_NAME));
		arsResult.setDetectionQuantitationLimitTypeName(rs.getString(DETECTION_QUANTITATIVE_LIMIT_TYPE_NAME_COLUMN_NAME));
		arsResult.setDetectionQuantitationLimitMeasureValue(rs.getString(DETECTION_QUANTITATIVE_LIMIT_MEASURE_VALUE_COLUMN_NAME));
		arsResult.setDetectionQuantitationLimitMeasureUnitCode(rs.getString(DETECTION_QUANTITATIVE_LIMIT_MEASURE_UNIT_CODE_COLUMN_NAME));
		return arsResult;
	}

}
