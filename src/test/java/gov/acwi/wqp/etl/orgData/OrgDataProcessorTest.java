package gov.acwi.wqp.etl.orgData;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.orgData.BiodataOrgData;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrgDataProcessorTest extends BaseProcessorTest {
	
	private BiodataOrgData biodataOrgData;
	private OrgDataProcessor processor;
	
	@Before
	public void setupTestClass() {
		biodataOrgData = new BiodataOrgData();
		processor = new OrgDataProcessor(configurationService);
		
		biodataOrgData.setOrganization(TEST_ORGANIZATION);
		biodataOrgData.setOrganizationId(TEST_ORGANIZATION_ID);
		biodataOrgData.setOrganizationName(TEST_ORGANIZATION_NAME_1);
	}
	
	@Test 
	public void testProcess() throws Exception {
		OrgData actual = processor.process(biodataOrgData);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertEquals(TEST_ORGANIZATION, actual.getOrganization());
		assertEquals(TEST_ORGANIZATION_ID, actual.getOrganizationId());
		assertEquals(TEST_ORGANIZATION_NAME_1, actual.getOrganizationName());
	}

	@Test
	public void testProcessWithNullValues() throws Exception {
		biodataOrgData.setOrganization(null);
		biodataOrgData.setOrganizationId(null);
		biodataOrgData.setOrganizationName(null);
		
		OrgData actual = processor.process(biodataOrgData);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertNull(actual.getOrganization());
		assertNull(actual.getOrganizationId());
		assertNull(actual.getOrganizationName());
	}
}
