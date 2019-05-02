package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;
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
		biodataActivity.setEffortPass(TEST_EFFORT_PASS);
		biodataActivity.setSampleCollectMethodId(TEST_SAMPLE_COLLECT_METHOD_ID);
		biodataActivity.setSampleCollectMethodCtx(TEST_SAMPLE_COLLECT_METHOD_CTX);
		biodataActivity.setSampleCollectMethodName(TEST_SAMPLE_COLLECT_METHOD_NAME);
		biodataActivity.setActivitySampleCollectMethodDescription(TEST_ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION);
		biodataActivity.setSampleTimeDatum(TEST_SAMPLE_TIME_DATUM);
		biodataActivity.setEffortGear(TEST_EFFORT_GEAR);
		biodataActivity.setSampleGearUsed(TEST_SAMPLE_GEAR_USED);
		biodataActivity.setDwSampleTypeId(TEST_DW_SAMPLE_TYPE_ID_7);
		biodataActivity.setEffortSubreach(TEST_EFFORT_SUBREACH);
	}
	
	@Test
	public void testProcess() throws Exception {
		Activity actual = processor.process(biodataActivity);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertEquals(ActivityProcessor.DEFAULT_SAMPLE_MEDIA, actual.getSampleMedia());
		assertEquals(ActivityProcessor.DEFAULT_ACTIVITY_TYPE_CODE, actual.getActivityTypeCode());
		assertEquals(ActivityProcessor.DEFAULT_ASSEMBLAGE_SAMPLED_NAME, actual.getAssemblageSampledName());
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
		assertEquals(ActivityProcessor.DEFAULT_REACH_LENGTH_UNIT, actual.getActivityReachLengthUnit());
		assertEquals(ActivityProcessor.EFFORT_PASS_2, actual.getActivityPassCount());
		assertEquals(TEST_SAMPLE_COLLECT_METHOD_ID, actual.getSampleCollectMethodId());
		assertEquals(TEST_SAMPLE_COLLECT_METHOD_CTX, actual.getSampleCollectMethodCtx());
		assertEquals(TEST_SAMPLE_COLLECT_METHOD_NAME, actual.getSampleCollectMethodName());
		assertEquals(TEST_ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION, actual.getActivitySampleCollectMethodDescription());
		assertEquals(ActivityProcessor.ELECTROSHOCK_OTHER, actual.getSampleCollectEquipName());
		assertEquals(TEST_EFFORT_GEAR + "+" + TEST_EFFORT_PASS, actual.getActivitySampleCollectEquipmentComments());	
	}
	
	@Test 
	public void testProcessWithNullValues() throws Exception {
		biodataActivity.setActivityReachLength(null);
		biodataActivity.setEffortGear(null);
		
		Activity actual = processor.process(biodataActivity);
		
		assertNull(actual.getActivityReachLengthUnit());
		assertNull(actual.getSampleCollectEquipName());
	}
	
	@Test 
	public void testActivityStartTimeAndTimeZoneWithBIOTDB() throws Exception {
		biodataActivity.setSampleDataSource(TEST_SAMPLE_DATA_SOURCE_BIOTDB);
		
		Activity actual = processor.process(biodataActivity);

		assertNull(actual.getActivityStartTime());
		assertNull(actual.getActStartTimeZone());
	}
	
	@Test
	public void testActivityPassCount() throws Exception {
		biodataActivity.setEffortPass(TEST_EFFORT_PASS_2);

		Activity actual = processor.process(biodataActivity);
		
		assertEquals(ActivityProcessor.EFFORT_PASS_1, actual.getActivityPassCount());
	}
	
	@Test
	public void testSampleCollectEquipName() throws Exception {
		biodataActivity.setEffortGear(ActivityProcessor.BACKPACK);
		Activity actual = processor.process(biodataActivity);
		assertEquals(ActivityProcessor.BACKPACK_ELECTROSHOCK, actual.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(ActivityProcessor.BOAT);
		Activity actual2 = processor.process(biodataActivity);
		assertEquals(ActivityProcessor.BOAT_MOUNTED_ELECTROSHOCK, actual2.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(ActivityProcessor.MINNOW_SEINE);
		Activity actual3 = processor.process(biodataActivity);
		assertEquals(ActivityProcessor.MINNOW_SEINE_NET, actual3.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(ActivityProcessor.BAG_SEINE);
		Activity actual4 = processor.process(biodataActivity);
		assertEquals(ActivityProcessor.SEINE_NET, actual4.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(ActivityProcessor.BEACH_SEINE);
		Activity actual5 = processor.process(biodataActivity);
		assertEquals(ActivityProcessor.BEACH_SEINE_NET, actual5.getSampleCollectEquipName());
		
		biodataActivity.setEffortGear(ActivityProcessor.SNORKELING);
		Activity actual6 = processor.process(biodataActivity);
		assertEquals(ActivityProcessor.VISUAL_SIGHTING, actual6.getSampleCollectEquipName());
	}
	
	@Test
	public void testActivitySampleCollectEquipmentComments() throws Exception {
		biodataActivity.setEffortPass(null);
		Activity actual = processor.process(biodataActivity);
		assertEquals(TEST_EFFORT_GEAR, actual.getActivitySampleCollectEquipmentComments());
		
		biodataActivity.setDwSampleTypeId(ActivityProcessor.DEFAULT_DW_SAMPLE_TYPE_ID_16);
		Activity actual2 = processor.process(biodataActivity);
		assertEquals(TEST_EFFORT_GEAR + "+" + TEST_EFFORT_SUBREACH, actual2.getActivitySampleCollectEquipmentComments());
		
		biodataActivity.setEffortSubreach(null);
		Activity actual3 = processor.process(biodataActivity);
		assertEquals(TEST_EFFORT_GEAR, actual3.getActivitySampleCollectEquipmentComments());
	}
	
}
