package gov.acwi.wqp.etl.biodata.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.jdbc.core.RowMapper;

public class BiodataActivityRowMapper implements RowMapper<BiodataActivity> {
	
	public static final String STATION_ID = "station_id";
	public static final String SITE_ID = "site_id";
	public static final String EVENT_DATE = "event_date";
	public static final String ACTIVITY = "activity";
	public static final String SAMPLE_MEDIA = "sample_media";
	public static final String ORGANIZATION = "organization";
	public static final String SITE_TYPE = "site_type";
	public static final String HUC = "huc";
	public static final String GOVERNMENTAL_UNIT_CODE = "governmental_unit_code";
	public static final String ORGANIZATION_NAME = "organization_name";
	public static final String ACTIVITY_ID = "activity_id";
	public static final String ACTIVITY_TYPE_CODE = "activity_type_code";
	public static final String ACTIVITY_START_TIME = "activity_start_time";
	public static final String ACTIVITY_START_TIME_ZONE = "act_start_time_zone";
	public static final String PROJECT_ID = "project_id";
	public static final String ACTIVITY_COMMENT = "activity_comment";
	public static final String ASSEMBLAGE_SAMPLED_NAME= "assemblage_sampled_name";
	public static final String ACTIVITY_REACH_LENGTH = "act_reach_length";
	public static final String ACTIVITY_REACH_LENGTH_UNIT = "act_reach_length_unit";
	public static final String ACTIVITY_PASS_COUNT = "act_pass_count";
	public static final String SAMPLE_COLLECT_METHOD_ID = "sample_collect_method_id";
	public static final String SAMPLE_COLLECT_METHOD_CTX = "sample_collect_method_ctx";
	public static final String SAMPLE_COLLECT_METHOD_NAME = "sample_collect_method_name";
	public static final String ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION = "act_sam_collect_meth_desc";
	public static final String SAMPLE_COLLECT_EQUIPMENT_NAME= "sample_collect_equip_name";
	public static final String ACTIVITY_SAMPLE_COLLECT_EQUIPMENT_COMMENTS = "act_sam_collect_equip_comments";
	
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public BiodataActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataActivity biodataActivity = new BiodataActivity();
		
		biodataActivity.setStationId(rs.getInt(STATION_ID));
		biodataActivity.setSiteId(rs.getString(SITE_ID));
		biodataActivity.setEventDate(
				LocalDateTime.parse(
						rs.getString(EVENT_DATE),
						dateTimeFormatter));
		biodataActivity.setActivity(rs.getString(ACTIVITY));
		biodataActivity.setSampleMedia(rs.getString(SAMPLE_MEDIA));
		biodataActivity.setOrganization(rs.getString(ORGANIZATION));
		biodataActivity.setSiteType(rs.getString(SITE_TYPE));
		biodataActivity.setHuc(rs.getString(HUC));
		biodataActivity.setGovernmentalUnitCode(rs.getString(GOVERNMENTAL_UNIT_CODE));
		biodataActivity.setOrganizationName(rs.getString(ORGANIZATION_NAME));
		biodataActivity.setActivityId(rs.getInt(ACTIVITY_ID));
		biodataActivity.setActivityTypeCode(rs.getString(ACTIVITY_TYPE_CODE));
		biodataActivity.setActivityStartTime(
				LocalDateTime.parse(
						rs.getString(ACTIVITY_START_TIME), 
						dateTimeFormatter));
		biodataActivity.setActivityStartTimeZone(rs.getString(ACTIVITY_START_TIME_ZONE));
		biodataActivity.setProjectId(rs.getString(PROJECT_ID));
		biodataActivity.setActivityComment(rs.getString(ACTIVITY_COMMENT));
		biodataActivity.setAssemblageSampledName(rs.getString(ASSEMBLAGE_SAMPLED_NAME));
		biodataActivity.setActivityReachLength(rs.getInt(ACTIVITY_REACH_LENGTH));
		biodataActivity.setActivityReachLengthUnit(rs.getString(ACTIVITY_REACH_LENGTH_UNIT));
		biodataActivity.setActivityPassCount(rs.getInt(ACTIVITY_PASS_COUNT));
		biodataActivity.setSampleCollectMethodId(rs.getString(SAMPLE_COLLECT_METHOD_ID));
		biodataActivity.setSampleCollectMethodCtx(rs.getString(SAMPLE_COLLECT_METHOD_CTX));
		biodataActivity.setSampleCollectMethodName(rs.getString(SAMPLE_COLLECT_METHOD_NAME));
		biodataActivity.setActivitySampleCollectMethodDescription(
				rs.getString(ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION));
		biodataActivity.setSampleCollectEquipmentName(rs.getString(SAMPLE_COLLECT_EQUIPMENT_NAME));
		biodataActivity.setActivitySampleCollectEquipmentComments(
				rs.getString(ACTIVITY_SAMPLE_COLLECT_EQUIPMENT_COMMENTS));

		return biodataActivity;
	}
}
