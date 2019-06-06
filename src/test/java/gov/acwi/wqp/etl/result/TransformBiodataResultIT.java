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

public class TransformBiodataResultIT extends BiodataBaseFlowIT {

    @Autowired
    @Qualifier("setupBiodataResultTableFlow")
    private Flow biodataResultFlow;

    @Test
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/taxonomicResult.xml")
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/taxonWide.xml")
    @ExpectedDatabase(
            connection=CONNECTION_BIODATA,
            value="classpath:/testData/biodata/result/result.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void transformBiodataResultStepTest() {
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformBiodataResultStep", testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/taxonomicResult.xml")
    @DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/taxonWide.xml")
    @ExpectedDatabase(
            connection=CONNECTION_BIODATA,
            value="classpath:/testData/biodata/result/result.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void biodataResultFlowTest() {
        Job biodataResultFlowTest = jobBuilderFactory
                .get("biodataResultFlowTest")
                .start(biodataResultFlow)
                .build()
                .build();
        jobLauncherTestUtils.setJob(biodataResultFlowTest);
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
}
