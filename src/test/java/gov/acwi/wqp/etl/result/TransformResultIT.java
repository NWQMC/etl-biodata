package gov.acwi.wqp.etl.result;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.BiodataBaseFlowIT;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
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
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/result.xml")

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
}
