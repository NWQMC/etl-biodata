package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;
import java.time.LocalDateTime;
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
		biodataActivity.setEventDate(LocalDateTime.MIN);
		biodataActivity.setActivity(TEST_AQFR_NAME);
		biodataActivity.setOrganization(TEST_ORGANIZATION);
		biodataActivity.setSiteType(TEST_SITE_TYPE);
		biodataActivity.setHuc(TEST_HUC);
		biodataActivity.setGovernmentalUnitCode(TEST_GOVERNMENTAL_UNIT_CODE);
		biodataActivity.setGeom(TEST_GEOM);
		biodataActivity.setOrganizationName(TEST_ORGANIZATION_NAME_1);
		biodataActivity.setActivityId(TEST_STATION_ID);
		biodataActivity.setSampleDataSource(TEST_DATA_SOURCE);
		biodataActivity.setSampleCollectionStartTime(LocalDateTime.MIN);
		biodataActivity.setProjectId(TEST_SITE_ID);
		biodataActivity.setActivityComment(TEST_AGENCY_CD);
		biodataActivity.setActivityReachLength(TEST_STATION_ID);
		biodataActivity.setEffortPass(TEST_HUC);
		biodataActivity.setSampleCollectMethodId(TEST_ELEVATION_METHOD);
		biodataActivity.setSampleCollectMethodCtx(TEST_ELEVATION_METHOD);
		biodataActivity.setSampleCollectMethodName(TEST_STATION_NAME);
		biodataActivity.setActivitySampleCollectMethodDescription(TEST_ELEVATION_METHOD);
		biodataActivity.setSampleTimeDatum(TEST_LATITUDE);
		biodataActivity.setEffortGear(TEST_HUC);
		biodataActivity.setSampleGearUsed(TEST_AGENCY_CD);
		biodataActivity.setDwSampleTypeId(TEST_STATION_ID);
		biodataActivity.setEffortSubreach(TEST_STATE_CD);
	}
	
	@Test
	public void testProcess() throws Exception {
		Activity actual = processor.process(biodataActivity);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
	}
}
