package gov.acwi.wqp.etl.projectData;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.projectData.BiodataProjectData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProjectDataProcessorTest extends BaseProcessorTest {

    private BiodataProjectData biodataProjectData;
    private ProjectDataProcessor processor;

    @Before
    public void setupTestClass() {
        biodataProjectData = new BiodataProjectData();
        processor = new ProjectDataProcessor(configurationService);

        biodataProjectData.setDwProjectId(TEST_DW_PROJECT_ID);
        biodataProjectData.setOrganization(TEST_ORGANIZATION);
        biodataProjectData.setOrganizationName(TEST_ORGANIZATION_NAME_1);
        biodataProjectData.setProjectId(TEST_PROJECT_ID);
        biodataProjectData.setProjectName(TEST_PROJECT_NAME);
        biodataProjectData.setProjectAbstract(TEST_PROJECT_ABSTRACT);
    }

    @Test
    public void testProcess() throws Exception {
        ProjectData actual = processor.process(biodataProjectData);

        assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
        assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
        assertEquals(TEST_DW_PROJECT_ID, actual.getProjectId());
        assertEquals(TEST_ORGANIZATION, actual.getOrganization());
        assertEquals(TEST_ORGANIZATION_NAME_1, actual.getOrganizationName());
        assertEquals(TEST_PROJECT_ID, actual.getProjectIdentifier());
        assertEquals(TEST_PROJECT_NAME, actual.getProjectName());
        assertEquals(TEST_PROJECT_ABSTRACT, actual.getDescription());
    }

    @Test
    public void testProcessWithNullValues() throws Exception {
        biodataProjectData.setDwProjectId(null);
        biodataProjectData.setOrganization(null);
        biodataProjectData.setOrganizationName(null);
        biodataProjectData.setProjectId(null);
        biodataProjectData.setProjectName(null);
        biodataProjectData.setProjectAbstract(null);

        ProjectData actual = processor.process(biodataProjectData);

        assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
        assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
        assertNull(actual.getProjectId());
        assertNull(actual.getOrganization());
        assertNull(actual.getOrganizationName());
        assertNull(actual.getProjectIdentifier());
        assertNull(actual.getProjectName());
        assertNull(actual.getDescription());
    }
}
