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
	
	public static final String STATION_SWAP_BIODATA = "'station_swap_biodata'";
	public static final String EXPECTED_DATABASE_QUERY_INDEX = BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + STATION_SWAP_BIODATA;
	public static final String EXPECTED_DATABASE_QUERY_TABLE = BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + STATION_SWAP_BIODATA;

	@Test
	@DatabaseSetup(		connection="wqp",		value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(		connection="wqp",		value="classpath:/testData/nwis/station/nwisStation.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareBiodataSite.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareSampleType.xml")
	@ExpectedDatabase(	connection="wqp",		value="classpath:/testResult/wqp/station/station_swap_biodata.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
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
	@DatabaseSetup(		connection="wqp",		value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(		connection="wqp",		value="classpath:/testData/nwis/station/nwisStation.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareBiodataSite.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareSampleType.xml")
	@ExpectedDatabase( value="classpath:/testResult/wqp/monitoringLocation/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
				query=EXPECTED_DATABASE_QUERY_INDEX)
	@ExpectedDatabase( connection="pg", value="classpath:/testResult/wqp/monitoringLocation/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
				query=EXPECTED_DATABASE_QUERY_TABLE)
	@ExpectedDatabase( value="classpath:/testResult/wqp/station/station_swap_biodata.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void monitoringLocationFlowTest() {
		Job monitoringLocationFlowTest = jobBuilderFactory.get("monitoringLocationFlowTest")
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
