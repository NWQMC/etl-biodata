package gov.acwi.wqp.etl.monitoringLocation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.BaseFlowIT;
import gov.acwi.wqp.etl.monitoringLocation.index.BuildMonitoringLocationIndexesFlowIT;
import gov.acwi.wqp.etl.monitoringLocation.table.SetupMonitoringLocationSwapTableFlowIT;

public class TransformMonitoringLocationIT extends BaseFlowIT {

	@Autowired
	@Qualifier("monitoringLocationFlow")
	private Flow monitoringLocationFlow;

	@PostConstruct
	public void beforeClass() throws ScriptException, SQLException {
		EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
		ScriptUtils.executeSqlScript(dataSource.getConnection(), encodedResource);
	}

	@Before
	public void setup() {
		testJob = jobBuilderFactory.get("monitoringLocationFlowTest")
				.start(monitoringLocationFlow)
				.build()
				.build();
		jobLauncherTestUtils.setJob(testJob);
	}

	@Test
    /*
        1. make sure we have an empty station table to start
        2. look in the transformStation file to look at the current query being run, to see what tables are being read from
    
        The following 3 tables replace the middle three tables in the database setup annotations for this test
            3. bioshare biodata_site table contains some of the input data 
                (accessed through oracle sql developer using credentials from tomcat machine) 
                (only need the 3 sites that exist in the station_swap_biodata table in postgres rds db in chs, but you'll want 
                    to filter them down to 10 or 20 total records, maybe using date?)
            4. biodata_sample also from oracle 
            5. biodata_sample_type also from oracle (only 22 rows, can pull the whole table)
    
            transform the data
    
        6. end result from chs postgres station_biodata table
    */
	@DatabaseSetup(value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(value="classpath:/testData/biodata/bioShareBiodataSite.xml")
	@DatabaseSetup(value="classpath:/testData/biodata/bioShareSample.xml")
	@DatabaseSetup(value="classpath:/testData/biodata/bioShareSampleType.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/station/station.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
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
	@DatabaseSetup(value="classpath:/testResult/wqp/station/empty.xml")
	@DatabaseSetup(value="classpath:/testData/ars/arsSiteTypeToPrimary.xml")
	@DatabaseSetup(value="classpath:/testData/wqp/station/stationOld.xml")
	@DatabaseSetup(value="classpath:/testResult/ars/arsOrgProject.xml")
	@DatabaseSetup(value="classpath:/testResult/ars/arsStation.xml")
	@ExpectedDatabase(value="classpath:/testResult/wqp/monitoringLocation/indexes/all.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=BuildMonitoringLocationIndexesFlowIT.EXPECTED_DATABASE_TABLE,
				query=BuildMonitoringLocationIndexesFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(connection="pg", value="classpath:/testResult/wqp/monitoringLocation/create.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED,
				table=SetupMonitoringLocationSwapTableFlowIT.EXPECTED_DATABASE_TABLE,
				query=SetupMonitoringLocationSwapTableFlowIT.EXPECTED_DATABASE_QUERY)
	@ExpectedDatabase(value="classpath:/testResult/wqp/station/station.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
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
