package gov.acwi.wqp.etl;

import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.Before;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Import({
		DBTestConfig.class,
		BiodataDBTestConfig.class})
@DbUnitConfiguration(databaseConnection={
		BaseFlowIT.CONNECTION_WQP,
		BiodataBaseFlowIT.CONNECTION_BIODATA,
		BaseFlowIT.CONNECTION_NWIS,
		BaseFlowIT.CONNECTION_INFORMATION_SCHEMA})
public abstract class BiodataBaseFlowIT extends BaseFlowIT{
	
	public static final String CONNECTION_BIODATA = "biodata";
	
	@Autowired
	private ConfigurationService configurationService;
	
	@Before
	@Override
	public void baseSetup() {
		testJobParameters= new JobParametersBuilder()
				.addJobParameters(jobLauncherTestUtils.getUniqueJobParameters())
				.addString(EtlConstantUtils.JOB_PARM_DATA_SOURCE_ID, configurationService.getEtlDataSourceId().toString(), true)
				.addString(EtlConstantUtils.JOB_PARM_DATA_SOURCE, configurationService.getEtlDataSourceId().toString().toLowerCase(), true)
				.addString(EtlConstantUtils.JOB_PARM_WQP_SCHEMA, configurationService.getWqpSchemaName(), false)
				.addString(EtlConstantUtils.JOB_PARM_GEO_SCHEMA, configurationService.getGeoSchemaName(), false)
				.toJobParameters();
	}
}
