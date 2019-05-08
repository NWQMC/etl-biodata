package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivityRowMapper;
import java.io.IOException;
import javax.sql.DataSource;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@Configuration
public class TransformActivity {
	
	@Autowired
	@Qualifier("activityProcessor")
	private ItemProcessor<BiodataActivity, Activity> processor;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSourceWqp;
	
	@Autowired
	@Qualifier(Application.DATASOURCE_BIODATA_QUALIFIER)
	private DataSource dataSourceBiodata;
	
	@Autowired
	@Qualifier(EtlConstantUtils.SETUP_ACTIVITY_SWAP_TABLE_FLOW)
	private Flow setupActivitySwapTableFlow;

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_ACTIVITY_INDEXES_FLOW)
	private Flow buildActivityIndexesFlow;
	
	@Value("classpath:sql/activity/readBiodataActivity.sql")
	private Resource readActivityResource;
	
	@Value("classpath:sql/activity/writeActivity.sql")
	private Resource writeActivityResource;

	@Bean
	public JdbcCursorItemReader<BiodataActivity> activityReader() throws IOException {
		return new JdbcCursorItemReaderBuilder<BiodataActivity>()
				.dataSource(dataSourceBiodata)
				.name("activityReader")
				.sql(new String(FileCopyUtils.copyToByteArray(readActivityResource.getInputStream())))
				.rowMapper(new BiodataActivityRowMapper())
				.build();
	}
	
	@Bean
	public ItemWriter<Activity> activityWriter() throws IOException {
		JdbcBatchItemWriter<Activity> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSourceWqp);
		itemWriter.setSql(new String(FileCopyUtils.copyToByteArray(writeActivityResource.getInputStream())));
		ItemSqlParameterSourceProvider<Activity> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}
	
	@Bean
	public Step transformActivityStep() throws IOException {
		return stepBuilderFactory
				.get("transformActivityStep")
				.<BiodataActivity, Activity>chunk(1000)
				.reader(activityReader())
				.processor(processor)
				.writer(activityWriter())
				.build();
	}
	
	@Bean
	public Flow activityFlow() throws IOException {
		return new FlowBuilder<SimpleFlow>("activityFlow")
				.start(setupActivitySwapTableFlow)
				.next(transformActivityStep())
				.next(buildActivityIndexesFlow)
				.build();
	}	
}
