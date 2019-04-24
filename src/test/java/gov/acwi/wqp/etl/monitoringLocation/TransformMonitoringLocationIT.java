package gov.acwi.wqp.etl.monitoringLocation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BiodataBaseFlowIT;

public class TransformMonitoringLocationIT extends BiodataBaseFlowIT {
	
	@Autowired
	@Qualifier("monitoringLocationFlow")
	private Flow monitoringLocationFlow;
	
	@Test
	@DatabaseSetup(
			connection=CONNECTION_WQP,
			value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(
			connection=CONNECTION_WQP,
			value="classpath:/testData/nwis/station/nwisStation.xml")
	@DatabaseSetup(
			connection=CONNECTION_BIODATA,
			value="classpath:/testData/biodata/station/bioShareBiodataSite.xml")
	@DatabaseSetup(
			connection=CONNECTION_BIODATA,
			value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup(
			connection=CONNECTION_BIODATA,
			value="classpath:/testData/biodata/station/bioShareSampleType.xml")
	@ExpectedDatabase(
			connection=CONNECTION_WQP,
			value="classpath:/testResult/wqp/station/station_swap_biodata.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void transformMonitoringLocationStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformMonitoringLocationStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(
			connection=CONNECTION_WQP,
			value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(
			connection=CONNECTION_WQP,
			value="classpath:/testData/nwis/station/nwisStation.xml")
	@DatabaseSetup(
			connection=CONNECTION_BIODATA,
			value="classpath:/testData/biodata/station/bioShareBiodataSite.xml")
	@DatabaseSetup(
			connection=CONNECTION_BIODATA,
			value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup(
			connection=CONNECTION_BIODATA,
			value="classpath:/testData/biodata/station/bioShareSampleType.xml")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocation/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'station_swap_biodata'")
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/monitoringLocation/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'station_swap_biodata'")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/station/station_swap_biodata.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void monitoringLocationFlowTest() {
		Job monitoringLocationFlowTest = jobBuilderFactory
				.get("monitoringLocationFlowTest")
				.start(monitoringLocationFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(monitoringLocationFlowTest);
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}
}
