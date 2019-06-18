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

    public static final String TABLE_NAME = "'result_swap_biodata'";
    public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
    public static final String EXPECTED_DATABASE_QUERY_PRIMARY_KEY = BASE_EXPECTED_DATABASE_QUERY_PRIMARY_KEY
            + EQUALS_QUERY + TABLE_NAME;
    public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
            + EQUALS_QUERY + TABLE_NAME;
    
    @Autowired
    @Qualifier("resultFlow")
    private Flow resultFlow;

    private Job setupFlowTestJob() {
        return jobBuilderFactory.get("resultFlowTest").start(resultFlow).build().build();
    }

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
    @DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/station/station_swap_biodata.xml")
    @ExpectedDatabase(
            value="classpath:/testResult/wqp/result/indexes/all.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
            query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + TABLE_NAME)
    @ExpectedDatabase(
            connection=CONNECTION_INFORMATION_SCHEMA,
            value="classpath:/testResult/wqp/result/create.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
            query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + TABLE_NAME)
    @ExpectedDatabase(
            connection=CONNECTION_WQP,
            value="classpath:/testResult/wqp/result/result_swap_biodata.xml",
            assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @ExpectedDatabase(
            value="classpath:/testResult/biodata/analyze/result.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
            query=EXPECTED_DATABASE_QUERY_ANALYZE)
    @ExpectedDatabase(
            value="classpath:/testResult/biodata/result/primaryKey.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY,
            query=EXPECTED_DATABASE_QUERY_PRIMARY_KEY)
    @ExpectedDatabase(
            value="classpath:/testResult/biodata/result/foreignKey.xml",
            assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
            table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
            query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
    public void resultFlowTest() {
        jobLauncherTestUtils.setJob(setupFlowTestJob());
        jdbcTemplate.execute("select add_monitoring_location_primary_key('biodata', 'wqp', 'station')");
        try {
            JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
            assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
}
