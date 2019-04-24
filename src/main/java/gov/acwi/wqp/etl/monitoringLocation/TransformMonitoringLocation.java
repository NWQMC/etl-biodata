package gov.acwi.wqp.etl.monitoringLocation;

import gov.acwi.wqp.etl.EtlConstantUtils;
import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.acwi.wqp.etl.biodata.monitoringLocation.BiodataMonitoringLocation;
import gov.acwi.wqp.etl.biodata.monitoringLocation.BiodataMonitoringLocationRowMapper;
import java.io.IOException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@Configuration
public class TransformMonitoringLocation {
	
	@Autowired
	@Qualifier("monitoringLocationProcessor")
	private ItemProcessor<BiodataMonitoringLocation, MonitoringLocation> processor;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("dataSourceWqp")
	private DataSource dataSourceWqp;

	@Autowired
	@Qualifier("dataSourceBiodata")
	private DataSource dataSourceBiodata;

	@Autowired
	@Qualifier(EtlConstantUtils.SETUP_MONITORING_LOCATION_SWAP_TABLE_FLOW)
	private Flow setupMonitoringLocationSwapTableFlow;

	@Autowired
	@Qualifier(EtlConstantUtils.BUILD_MONITORING_LOCATION_INDEXES_FLOW)
	private Flow buildMonitoringLocationIndexesFlow;
	
	@Value("classpath:sql/monitoringLocation/readBiodataMonitoringLocation.sql")
	private Resource sqlResourceReader;
	
	@Value("classpath:sql/monitoringLocation/writeMonitoringLocation.sql")
	private Resource sqlResourceWriter;

	@Bean
	public JdbcCursorItemReader<BiodataMonitoringLocation> monitoringLocationReader() throws IOException {
		return new JdbcCursorItemReaderBuilder<BiodataMonitoringLocation>()
		.dataSource(dataSourceBiodata)
		.name("monitoringLocationReader")
		.sql(new String(FileCopyUtils.copyToByteArray(sqlResourceReader.getInputStream())))
		.rowMapper(new BiodataMonitoringLocationRowMapper())
		.build();
	}

	@Bean
	public ItemWriter<MonitoringLocation> monitoringLocationWriter() throws IOException {
		JdbcBatchItemWriter<MonitoringLocation> itemWriter = new JdbcBatchItemWriter<MonitoringLocation>();
		itemWriter.setDataSource(dataSourceWqp);
		itemWriter.setSql(new String(FileCopyUtils.copyToByteArray(sqlResourceWriter.getInputStream())));
		ItemSqlParameterSourceProvider<MonitoringLocation> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
		itemWriter.setItemSqlParameterSourceProvider(paramProvider);
		return itemWriter;
	}

	@Bean
	public Step transformMonitoringLocationStep() throws Exception {
		return stepBuilderFactory
				.get("transformMonitoringLocationStep")
				.<BiodataMonitoringLocation, MonitoringLocation>chunk(10)
				.reader(monitoringLocationReader())
				.processor(processor)
				.writer(monitoringLocationWriter())
				.build();
	}

	@Bean
	public Flow monitoringLocationFlow() throws Exception {
		return new FlowBuilder<SimpleFlow>("monitoringLocationFlow")
				.start(setupMonitoringLocationSwapTableFlow)
				.next(transformMonitoringLocationStep())
				.next(buildMonitoringLocationIndexesFlow)
				.build();
	}
}
