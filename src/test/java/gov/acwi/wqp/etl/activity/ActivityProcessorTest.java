package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;

import static gov.acwi.wqp.etl.activity.ActivityProcessor.BACKPACK;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.BACKPACK_ELECTROSHOCK;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.BAG_SEINE;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.BEACH_SEINE;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.BEACH_SEINE_NET;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.BOAT;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.BOAT_MOUNTED_ELECTROSHOCK;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.DEFAULT_ACTIVITY_TYPE_CODE;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.DEFAULT_ASSEMBLAGE_SAMPLED_NAME;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.DEFAULT_DW_SAMPLE_TYPE_ID_16;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.DEFAULT_REACH_LENGTH_UNIT;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.DEFAULT_SAMPLE_MEDIA;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.EFFORT_PASS_1;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.EFFORT_PASS_1_2_COMBINED;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.EFFORT_PASS_2;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.ELECTROSHOCK_OTHER;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.MINNOW_SEINE;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.MINNOW_SEINE_NET;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.SAMPLE_DATA_SOURCE_BIOTB;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.SEINE_NET;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.SNORKELING;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.TOWED_BARGE;
import static gov.acwi.wqp.etl.activity.ActivityProcessor.VISUAL_SIGHTING;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ActivityProcessorTest extends BaseProcessorTest {
	
	private BiodataActivity biodataActivity;
	private ActivityProcessor processor;
	
	@Before
	public void setupTestClass() {
		
		biodataActivity = new BiodataActivity();
		processor = new ActivityProcessor(configurationService);
		
		biodataActivity.setStationId(TEST_STATION_ID);
		biodataActivity.setSiteId(TEST_SITE_ID);
		biodataActivity.setActivity(TEST_ACTIVITY);
		biodataActivity.setOrganization(TEST_ORGANIZATION);
		biodataActivity.setSiteType(TEST_SITE_TYPE);
		biodataActivity.setHuc(TEST_HUC);
		biodataActivity.setGovernmentalUnitCode(TEST_GOVERNMENTAL_UNIT_CODE);
		biodataActivity.setGeom(TEST_GEOM);
		biodataActivity.setOrganizationName(TEST_ORGANIZATION_NAME_1);
		biodataActivity.setActivityId(TEST_ACTIVITY_ID);
		biodataActivity.setSampleDataSource(TEST_SAMPLE_DATA_SOURCE);
		biodataActivity.setSampleCollectionStartTime(TEST_SAMPLE_COLLECTION_START_TIME);
		biodataActivity.setProjectId(TEST_PROJECT_ID);
		biodataActivity.setActivityComment(TEST_ACTIVITY_COMMENT);
		biodataActivity.setActivityReachLength(TEST_ACTIVITY_REACH_LENGTH);
		biodataActivity.setEffortPass(EFFORT_PASS_1_2_COMBINED);
		biodataActivity.setSampleCollectMethodId(TEST_SAMPLE_COLLECT_METHOD_ID);
		biodataActivity.setSampleCollectMethodCtx(TEST_SAMPLE_COLLECT_METHOD_CTX);
		biodataActivity.setSampleCollectMethodName(TEST_SAMPLE_COLLECT_METHOD_NAME);
		biodataActivity.setActivitySampleCollectMethodDescription(TEST_ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION);
		biodataActivity.setSampleTimeDatum(TEST_SAMPLE_TIME_DATUM);
		biodataActivity.setEffortGear(TOWED_BARGE);
		biodataActivity.setSampleGearUsed(TEST_SAMPLE_GEAR_USED);
		biodataActivity.setDwSampleTypeId(TEST_DW_SAMPLE_TYPE_ID_7);
		biodataActivity.setEffortSubreach(TEST_EFFORT_SUBREACH);
	}
	
	@Test
	public void testProcess() {
		Activity actual = processor.process(biodataActivity);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertEquals(DEFAULT_SAMPLE_MEDIA, actual.getSampleMedia());
		assertEquals(DEFAULT_ACTIVITY_TYPE_CODE, actual.getActivityTypeCode());
		assertEquals(DEFAULT_ASSEMBLAGE_SAMPLED_NAME, actual.getAssemblageSampledName());
		assertEquals(TEST_STATION_ID, actual.getStationId());
		assertEquals(TEST_SITE_ID, actual.getSiteId());
		assertEquals(TEST_EVENT_DATE_LOCAL_DATE, actual.getEventDate());
		assertEquals(TEST_ACTIVITY, actual.getActivity());
		assertEquals(TEST_ORGANIZATION, actual.getOrganization());
		assertEquals(TEST_SITE_TYPE, actual.getSiteType());
		assertEquals(TEST_HUC, actual.getHuc());
		assertEquals(TEST_GOVERNMENTAL_UNIT_CODE, actual.getGovernmentalUnitCode());
		assertEquals(TEST_GEOM, actual.getGeom());
		assertEquals(TEST_ORGANIZATION_NAME_1, actual.getOrganizationName());
		assertEquals(TEST_ACTIVITY_ID, actual.getActivityId());
		assertEquals(TEST_SAMPLE_COLLECTION_START_TIME_LOCAL_TIME, actual.getActivityStartTime());
		assertEquals(TEST_SAMPLE_TIME_DATUM, actual.getActStartTimeZone());
		assertEquals(TEST_PROJECT_ID, actual.getProjectId());
		assertEquals(TEST_ACTIVITY_COMMENT, actual.getActivityComment());
		assertEquals(TEST_ACTIVITY_REACH_LENGTH, actual.getActivityReachLength());
		assertEquals(DEFAULT_REACH_LENGTH_UNIT, actual.getActivityReachLengthUnit());
		assertEquals(EFFORT_PASS_2, actual.getActivityPassCount());
		assertEquals(TEST_SAMPLE_COLLECT_METHOD_ID, actual.getSampleCollectMethodId());
		assertEquals(TEST_SAMPLE_COLLECT_METHOD_CTX, actual.getSampleCollectMethodCtx());
		assertEquals(TEST_SAMPLE_COLLECT_METHOD_NAME, actual.getSampleCollectMethodName());
		assertEquals(TEST_ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION, actual.getActivitySampleCollectMethodDescription());
		assertEquals(ELECTROSHOCK_OTHER, actual.getSampleCollectEquipName());
		assertEquals(TOWED_BARGE + "+" + EFFORT_PASS_1_2_COMBINED, actual.getActivitySampleCollectEquipmentComments());
	}
	
	@Test 
	public void testProcessWithNullValues() {
		biodataActivity.setActivityReachLength(null);
		biodataActivity.setEffortGear(null);
		
		Activity actual = processor.process(biodataActivity);
		
		assertNull(actual.getActivityReachLengthUnit());
		assertNull(actual.getSampleCollectEquipName());
	}
	
	@Test 
	public void testActivityStartTimeAndTimeZoneWithBIOTDB() {
		biodataActivity.setSampleDataSource(SAMPLE_DATA_SOURCE_BIOTB);
		
		Activity actual = processor.process(biodataActivity);

		assertNull(actual.getActivityStartTime());
		assertNull(actual.getActStartTimeZone());
	}
	
	@Test
	public void testActivityPassCount() {
		biodataActivity.setEffortPass(TEST_EFFORT_PASS_2);

		Activity actual = processor.process(biodataActivity);
		
		assertEquals(EFFORT_PASS_1, actual.getActivityPassCount());
	}
	
	@Test
	public void testSampleCollectEquipName() {
		biodataActivity.setEffortGear(BACKPACK);
		Activity actual = processor.process(biodataActivity);
		assertEquals(BACKPACK_ELECTROSHOCK, actual.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(BOAT);
		Activity actual2 = processor.process(biodataActivity);
		assertEquals(BOAT_MOUNTED_ELECTROSHOCK, actual2.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(MINNOW_SEINE);
		Activity actual3 = processor.process(biodataActivity);
		assertEquals(MINNOW_SEINE_NET, actual3.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(BAG_SEINE);
		Activity actual4 = processor.process(biodataActivity);
		assertEquals(SEINE_NET, actual4.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(BEACH_SEINE);
		Activity actual5 = processor.process(biodataActivity);
		assertEquals(BEACH_SEINE_NET, actual5.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(SNORKELING);
		Activity actual6 = processor.process(biodataActivity);
		assertEquals(VISUAL_SIGHTING, actual6.getSampleCollectEquipName());
	}
	
	@Test
	public void testActivitySampleCollectEquipmentComments() {
		biodataActivity.setEffortPass(null);
		Activity actual = processor.process(biodataActivity);
		assertEquals(TOWED_BARGE, actual.getActivitySampleCollectEquipmentComments());
		
		biodataActivity.setDwSampleTypeId(DEFAULT_DW_SAMPLE_TYPE_ID_16);
		Activity actual2 = processor.process(biodataActivity);
		assertEquals(TOWED_BARGE + "+" + TEST_EFFORT_SUBREACH, actual2.getActivitySampleCollectEquipmentComments());
		
		biodataActivity.setEffortSubreach(null);
		Activity actual3 = processor.process(biodataActivity);
		assertEquals(TOWED_BARGE, actual3.getActivitySampleCollectEquipmentComments());
	}
	
}
