package gov.acwi.wqp.etl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class EtlBiodataIT extends BiodataBaseFlowIT {

	@Test
	@DatabaseSetup(		connection="wqp",		value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(		connection="wqp",		value="classpath:/testData/nwis/station/nwisStation.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareBiodataSite.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup(		connection="biodata",	value="classpath:/testData/biodata/station/bioShareSampleType.xml")
	@ExpectedDatabase( value="classpath:/testResult/wqp/monitoringLocation/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
				query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'station_swap_biodata'")
	@ExpectedDatabase( connection="pg", value="classpath:/testResult/wqp/monitoringLocation/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
				query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'station_swap_biodata'")
	@ExpectedDatabase( value="classpath:/testResult/wqp/station/station_swap_biodata.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void endToEndTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
