package gov.acwi.wqp.etl;

import org.springframework.context.annotation.Import;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.springframework.batch.core.JobParametersBuilder;

@Import({DBTestConfig.class, BiodataDBTestConfig.class})
@DbUnitConfiguration(databaseConnection={"wqp","pg","biodata"})
public abstract class BiodataBaseFlowIT extends BaseFlowIT{
	@Override
	public void baseSetup() {
		testJobParameters= new JobParametersBuilder()
				.addJobParameters(jobLauncherTestUtils.getUniqueJobParameters())
				.addString("wqpDataSourceId", "4", true)
				.addString("wqpDataSource", "biodata", true)
				.addString("schemaName", "wqp", false)
				.toJobParameters();
	}
}
