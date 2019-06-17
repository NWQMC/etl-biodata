package gov.acwi.wqp.etl.activity;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.acwi.wqp.etl.BiodataBaseFlowIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TransformActivityIT  extends BiodataBaseFlowIT {

	public static final String TABLE_NAME = "'activity_swap_biodata'";
	public static final String EXPECTED_DATABASE_QUERY_ANALYZE = BASE_EXPECTED_DATABASE_QUERY_ANALYZE + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_PRIMARY_KEY = BASE_EXPECTED_DATABASE_QUERY_PRIMARY_KEY
			+ EQUALS_QUERY + TABLE_NAME;
	public static final String EXPECTED_DATABASE_QUERY_FOREIGN_KEY = BASE_EXPECTED_DATABASE_QUERY_FOREIGN_KEY
			+ EQUALS_QUERY + TABLE_NAME;
	
	@Autowired
	@Qualifier("activityFlow")
	private Flow activityFlow;

    private Job setupFlowTestJob() {
        return jobBuilderFactory.get("activityFlowTest").start(activityFlow).build().build();
    }

	@Test
	@DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/activity/empty.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/activity/biodataEffort.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/station/station_swap_biodata.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/activity/biodataProject.xml")
	@ExpectedDatabase(
			connection=CONNECTION_WQP,
			value="classpath:/testResult/wqp/activity/activity.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformActivityStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformActivityStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
	
	@Test
	@DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/activity/empty.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/activity/biodataEffort.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testResult/wqp/station/station_swap_biodata.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/activity/biodataProject.xml")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + TABLE_NAME)
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/activity/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + TABLE_NAME)
	@ExpectedDatabase(
			connection=CONNECTION_WQP,
			value="classpath:/testResult/wqp/activity/activity.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(
			value="classpath:/testResult/biodata/analyze/activity.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_ANALYZE,
			query=EXPECTED_DATABASE_QUERY_ANALYZE)
	@ExpectedDatabase(
			value="classpath:/testResult/biodata/activity/primaryKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_PRIMARY_KEY,
			query=EXPECTED_DATABASE_QUERY_PRIMARY_KEY)
	@ExpectedDatabase(
			value="classpath:/testResult/biodata/activity/foreignKey.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_FOREIGN_KEY,
			query=EXPECTED_DATABASE_QUERY_FOREIGN_KEY)
	@ExpectedDatabase(
			value="classpath:/testResult/biodata/activity/indexes/pk.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX_PK + TABLE_NAME)
	public void activityFlowTest() {
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
