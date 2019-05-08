package gov.acwi.wqp.etl.projectData;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.BiodataBaseFlowIT;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TransformProjectDataIT extends BiodataBaseFlowIT {

    @Autowired
    @Qualifier("projectDataFlow")
    private Flow projectDataFlow;

    @Test
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/activity/empty.xml")
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/project/biodataProject.xml")
    @ExpectedDatabase(
            connection=CONNECTION_WQP,
            value="classpath:/testRestult/wqp/projectData/project_data_swap_biodata.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void transformProjectDataStepTest() {
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformProjectDataStep", testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/activity/empty.xml")
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/project/biodataProject.xml")
    @ExpectedDatabase(
            value="classpath:/testResult/wqp/projectData/indexes/all.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
            query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'project_data_swap_biodata'")
    @ExpectedDatabase(
            connection=CONNECTION_INFORMATION_SCHEMA,
            value="classpath:/testResult/wqp/projectData/create.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
            query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'project_data_swap_biodata'")
    @ExpectedDatabase(
            value="classpath:/testRestult/wqp/projectData/project_data_swap_biodata.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void projectDataFlowTest() {
        Job projectDataFlowTest = jobBuilderFactory
                .get("projectDataFlowTest")
                .start(projectDataFlow)
                .build()
                .build();
        jobLauncherTestUtils.setJob(projectDataFlowTest);
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
}
