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
	
	// resources from nwis and biodata
	@DatabaseSetup( connection=CONNECTION_WQP, value="classpath:/testData/nwis/station/nwisStation.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/station/bioShareBiodataSite.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/station/bioShareSample.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/station/bioShareSampleType.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/activity/biodataEffort.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/activity/biodataProject.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/taxonomicResult.xml")
	@DatabaseSetup( connection=CONNECTION_BIODATA, value="classpath:/testData/biodata/result/taxonWide.xml")

	// base tables
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/monitoringLocation/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'station_swap_biodata'")
	@ExpectedDatabase( 
			connection=CONNECTION_INFORMATION_SCHEMA, 
			value="classpath:/testResult/wqp/orgData/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'org_data_swap_biodata'")
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/activity/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'activity_swap_biodata'")
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/projectData/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'project_data_swap_biodata'")
	@ExpectedDatabase(
			connection=CONNECTION_INFORMATION_SCHEMA,
			value="classpath:/testResult/wqp/result/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_TABLE,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_TABLE + "'result_swap_biodata'")

	// Biodata base data
	@ExpectedDatabase(value="classpath:/testResult/wqp/station/station_swap_biodata.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/orgData/orgData.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/activity/activity.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/projectData/project_data_swap_biodata.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@ExpectedDatabase(value="classpath:/testResult/wqp/result/result_swap_biodata.xml", assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)

	// indexes
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/monitoringLocation/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'station_swap_biodata'")
	@ExpectedDatabase( 
			value="classpath:/testResult/wqp/orgData/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'org_data_swap_biodata'")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/activity/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'activity_swap_biodata'")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/projectData/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'project_data_swap_biodata'")
	@ExpectedDatabase(
			value="classpath:/testResult/wqp/result/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
			table=EXPECTED_DATABASE_TABLE_CHECK_INDEX,
			query=BASE_EXPECTED_DATABASE_QUERY_CHECK_INDEX + "'result_swap_biodata'")

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
