package gov.acwi.wqp.etl.orgData;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.orgData.BiodataOrgData;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrgDataProcessorTest extends BaseProcessorTest {
	
	private BiodataOrgData biodataOD;
	private OrgDataProcessor odProcessor;
	
	@Before
	public void setUp() {
		biodataOD = new BiodataOrgData();
		odProcessor = new OrgDataProcessor(configurationService);
		
		biodataOD.setOrganization(TEST_ORGANIZATION);
		biodataOD.setOrganizationId(TEST_ORGANIZATION_ID);
		biodataOD.setOrganizationName(TEST_ORGANIZATION_NAME_1);
	}
	
	@Test 
	public void testProcess() throws Exception {
		OrgData actual = odProcessor.process(biodataOD);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertEquals(TEST_ORGANIZATION, actual.getOrganization());
		assertEquals(TEST_ORGANIZATION_ID, actual.getOrganizationId());
		assertEquals(TEST_ORGANIZATION_NAME_1, actual.getOrganizationName());
	}

	@Test
	public void testProcessWithNullValues() throws Exception {
		biodataOD.setOrganization(null);
		biodataOD.setOrganizationId(null);
		biodataOD.setOrganizationName(null);
		
		OrgData actual = odProcessor.process(biodataOD);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertNull(actual.getOrganization());
		assertNull(actual.getOrganizationId());
		assertNull(actual.getOrganizationName());
	}
}
