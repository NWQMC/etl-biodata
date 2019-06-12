package gov.acwi.wqp.etl.result;

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

public class TransformResultIT extends BiodataBaseFlowIT {

    @Autowired
    @Qualifier("resultFlow")
    private Flow resultFlow;

    @Test
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/result/empty.xml")
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/activity/activity.xml")
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testResult/biodata/result/result.xml")
    @ExpectedDatabase(
            connection=CONNECTION_WQP,
            value="classpath:/testResult/wqp/result/result_swap_biodata.xml",
            assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void transformResultStepTest() {
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformResultStep", testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }


    @Test
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/result/empty.xml")
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/activity/activity.xml")
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testResult/biodata/result/result.xml")
    @ExpectedDatabase(
            value="classpath:/testResult/wqp/result/indexes/all.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
            query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'result_swap_biodata'")
    @ExpectedDatabase(
            connection=CONNECTION_INFORMATION_SCHEMA,
            value="classpath:/testResult/wqp/result/create.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
            query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'result_swap_biodata'")
    @ExpectedDatabase(
            connection=CONNECTION_WQP,
            value="classpath:/testResult/wqp/result/result_swap_biodata.xml",
            assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void resultFlowTest() {
        Job resultFlowTest = jobBuilderFactory
                .get("resultFlowTest")
                .start(resultFlow)
                .build()
                .build();
        jobLauncherTestUtils.setJob(resultFlowTest);
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
}
