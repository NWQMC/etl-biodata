package gov.acwi.wqp.etl.result;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.biodataResult.BiodataBiodataResult;
import gov.acwi.wqp.etl.biodata.biodataResult.BiodataBiodataResultRowMapper;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
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

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class TransformBiodataResult {

    @Autowired
    @Qualifier("biodataResultProcessor")
    private ItemProcessor<BiodataBiodataResult, BiodataBiodataResult> processor;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier(Application.DATASOURCE_BIODATA_QUALIFIER)
    private DataSource dataSourceBiodata;

    // Truncate and reset the biodata.result table
    @Autowired
    @Qualifier("truncateBiodataResult")
    private Tasklet truncateBiodataResult;

    @Value("classpath:sql/biodataResult/readBiodataResult.sql")
    private Resource readBiodataResultResource;

    @Value("classpath:sql/biodataResult/writeBiodataResult.sql")
    private Resource writeBiodataResultResource;

    @Bean
    public JdbcCursorItemReader<BiodataBiodataResult> biodataResultReader() throws IOException {
        return new JdbcCursorItemReaderBuilder<BiodataBiodataResult>()
                .dataSource(dataSourceBiodata)
                .name("biodataResultReader")
                .sql(new String(FileCopyUtils.copyToByteArray(readBiodataResultResource.getInputStream())))
                .rowMapper(new BiodataBiodataResultRowMapper())
                .build();
    }

    @Bean
    public ItemWriter<BiodataBiodataResult> biodataResultWriter() throws IOException {
        JdbcBatchItemWriter<BiodataBiodataResult> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSourceBiodata);
        itemWriter.setSql(new String(FileCopyUtils.copyToByteArray(writeBiodataResultResource.getInputStream())));
        ItemSqlParameterSourceProvider<BiodataBiodataResult> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
        itemWriter.setItemSqlParameterSourceProvider(paramProvider);
        return itemWriter;
    }

    @Bean
    public Step transformBiodataResultStep() throws IOException {
        return stepBuilderFactory
                .get("transformBiodataResultStep")
                .<BiodataBiodataResult, BiodataBiodataResult>chunk(1000)
                .reader(biodataResultReader())
                .processor(processor)
                .writer(biodataResultWriter())
                .build();
    }

    @Bean
    public Step truncateBiodataResultStep() {
        return stepBuilderFactory
                .get("truncateBiodataResultStep")
                .tasklet(truncateBiodataResult)
                .build();
    }

    @Bean
    public Flow setupBiodataResultTableFlow() throws IOException {
        return new FlowBuilder<SimpleFlow>("setupBiodataResultTableFlow")
                // This is to truncate and reset the biodata.result table
                .start(truncateBiodataResultStep())
                // This is to set up the biodata.result table
                .next(transformBiodataResultStep())
                .build();
    }
}
