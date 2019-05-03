package gov.acwi.wqp.etl.biodata.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgis.PGgeometry;
import org.springframework.jdbc.core.RowMapper;

public class BiodataActivityRowMapper implements RowMapper<BiodataActivity> {
	
	private static final String STATION_ID = "station_id";
	private static final String SITE_ID = "site_id";
	private static final String ACTIVITY = "activity";
	private static final String ORGANIZATION = "organization";
	private static final String SITE_TYPE = "site_type";
	private static final String HUC = "huc";
	private static final String GOVERNMENTAL_UNIT_CODE = "governmental_unit_code";
	private static final String GEOM = "geom";
	private static final String ORGANIZATION_NAME = "organization_name";
	private static final String ACTIVITY_ID = "activity_id";
	private static final String PROJECT_ID = "project_id";
	private static final String ACTIVITY_COMMENT = "activity_comment";
	private static final String ACTIVITY_REACH_LENGTH = "act_reach_length";
	private static final String EFFORT_PASS = "pass";
	private static final String SAMPLE_COLLECT_METHOD_ID = "sample_collect_method_id";
	private static final String SAMPLE_COLLECT_METHOD_CTX = "sample_collect_method_ctx";
	private static final String SAMPLE_COLLECT_METHOD_NAME = "sample_collect_method_name";
	private static final String ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION = "act_sam_collect_meth_desc";
	private static final String SAMPLE_COLLECTION_START_TIME = "collection_start";
	private static final String SAMPLE_DATA_SOURCE = "data_source";
	private static final String SAMPLE_TIME_DATUM = "time_datum";
	private static final String EFFORT_GEAR = "gear";
	private static final String SAMPLE_GEAR_USED = "gear_used";
	private static final String DW_SAMPLE_TYPE_ID = "dw_sample_type_id";
	private static final String EFFORT_SUBREACH = "subreach";
	
	@Override
	public BiodataActivity mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataActivity biodataActivity = new BiodataActivity();
		
		biodataActivity.setStationId(rs.getInt(STATION_ID));
		biodataActivity.setSiteId(rs.getString(SITE_ID));
		biodataActivity.setActivity(rs.getString(ACTIVITY));
		biodataActivity.setOrganization(rs.getString(ORGANIZATION));
		biodataActivity.setSiteType(rs.getString(SITE_TYPE));
		biodataActivity.setHuc(rs.getString(HUC));
		biodataActivity.setGovernmentalUnitCode(rs.getString(GOVERNMENTAL_UNIT_CODE));
		biodataActivity.setGeom( (PGgeometry) rs.getObject(GEOM));
		biodataActivity.setOrganizationName(rs.getString(ORGANIZATION_NAME));
		biodataActivity.setActivityId(rs.getInt(ACTIVITY_ID));
		biodataActivity.setSampleDataSource(rs.getString(SAMPLE_DATA_SOURCE));
		biodataActivity.setSampleCollectionStartTime(rs.getTimestamp(SAMPLE_COLLECTION_START_TIME).toLocalDateTime());
		biodataActivity.setSampleTimeDatum(rs.getString(SAMPLE_TIME_DATUM));
		biodataActivity.setProjectId(rs.getString(PROJECT_ID));
		biodataActivity.setActivityComment(rs.getString(ACTIVITY_COMMENT));
		biodataActivity.setActivityReachLength(rs.getInt(ACTIVITY_REACH_LENGTH));
		biodataActivity.setEffortPass(rs.getString(EFFORT_PASS));
		biodataActivity.setSampleCollectMethodId(rs.getString(SAMPLE_COLLECT_METHOD_ID));
		biodataActivity.setSampleCollectMethodCtx(rs.getString(SAMPLE_COLLECT_METHOD_CTX));
		biodataActivity.setSampleCollectMethodName(rs.getString(SAMPLE_COLLECT_METHOD_NAME));
		biodataActivity.setActivitySampleCollectMethodDescription(rs.getString(ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION));
		biodataActivity.setEffortGear(rs.getString(EFFORT_GEAR));
		biodataActivity.setSampleGearUsed(rs.getString(SAMPLE_GEAR_USED));
		biodataActivity.setDwSampleTypeId(rs.getInt(DW_SAMPLE_TYPE_ID));
		biodataActivity.setEffortSubreach(rs.getString(EFFORT_SUBREACH));

		return biodataActivity;
	}
}
