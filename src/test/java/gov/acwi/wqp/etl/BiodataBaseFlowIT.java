package gov.acwi.wqp.etl;

import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Before;
import org.springframework.batch.core.JobParametersBuilder;

@Import({DBTestConfig.class, BiodataDBTestConfig.class})
@DbUnitConfiguration(databaseConnection={"wqp","pg","biodata"})
public abstract class BiodataBaseFlowIT extends BaseFlowIT{
	
	@Before
	@Override
	public void baseSetup() {
		testJobParameters= new JobParametersBuilder()
				.addJobParameters(jobLauncherTestUtils.getUniqueJobParameters())
//				.addString("wqpDataSourceId", "4", true)
//				.addString("wqpDataSource", "biodata", true)
//				.addString("schemaName", "wqp", false)
				.addString(EtlConstantUtils.JOB_PARM_DATA_SOURCE_ID, Application.DATA_SOURCE_ID.toString(), true)
				.addString(EtlConstantUtils.JOB_PARM_DATA_SOURCE, Application.DATA_SOURCE.toLowerCase(), true)
				.addString(EtlConstantUtils.JOB_PARM_SCHEMA, EtlConstantUtils.WQP_SCHEMA_NAME, false)
//				.addString(EtlConstantUtils.JOB_PARM_GEO_SCHEMA, EtlConstantUtils.NWIS_SCHEMA_NAME, false)
				.toJobParameters();
	}
}
