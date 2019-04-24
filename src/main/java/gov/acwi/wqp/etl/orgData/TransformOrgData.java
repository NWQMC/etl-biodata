package gov.acwi.wqp.etl.orgData;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.biodata.orgData.BiodataOrgData;
import gov.acwi.wqp.etl.biodata.orgData.BiodataOrgDataRowMapper;
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
public class TransformOrgData {
	
	@Autowired
	@Qualifier("orgDataProcessor")
	private ItemProcessor<BiodataOrgData, OrgData> processor;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("dataSourceWqp")
	private DataSource dataSourceWqp;
	
	@Autowired
	@Qualifier(EtlConstantUtils.SETUP_ORG_DATA_SWAP_TABLE_FLOW)
	private Flow setupOrgDataSwapTableFlow;
		
	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_ORG_DATA_INDEXES_FLOW)
	private Flow buildOrgDataIndexesFlow;
	
	@Value("classpath:sql/orgData/readOrgData.sql")
	private Resource sqlResourceReader;
	
	@Value("classpath:sql/orgData/writeOrgData.sql")
	private Resource sqlResourceWriter;
	
	@Bean
	public JdbcCursorItemReader<BiodataOrgData> orgDataReader() throws Exception {
		return new JdbcCursorItemReaderBuilder<BiodataOrgData>()
		.dataSource(dataSourceWqp)
		.name("monitoringLocationReader")
		.sql(new String(FileCopyUtils.copyToByteArray(sqlResourceReader.getInputStream())))
		.rowMapper(new BiodataOrgDataRowMapper())
		.build();
	}
	
	@Bean
	public ItemWriter<OrgData> orgDataWriter() throws IOException {
		JdbcBatchItemWriter<OrgData> itemWriter = new JdbcBatchItemWriter<OrgData>();
		itemWriter.setDataSource(dataSourceWqp);
		itemWriter.setSql(new String(FileCopyUtils.copyToByteArray(sqlResourceWriter.getInputStream())));
		ItemSqlParameterSourceProvider<OrgData> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}
	
	@Bean
	public Step transformOrgDataStep() throws Exception {
		return stepBuilderFactory
				.get("transformOrgDataStep")
				.<BiodataOrgData, OrgData>chunk(10)
				.reader(orgDataReader())
				.processor(processor)
				.writer(orgDataWriter())
				.build();
	}
	
	@Bean
	public Flow orgDataFlow() throws Exception {
		return new FlowBuilder<SimpleFlow>("orgDataFlow")
				.start(setupOrgDataSwapTableFlow)
				.next(transformOrgDataStep())
				.next(buildOrgDataIndexesFlow)
				.build();
	}
}
