package gov.acwi.wqp.etl.result;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.biodata.result.BiodataResult;
import gov.acwi.wqp.etl.biodata.result.BiodataResultRowMapper;
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

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
public class TransformResult {

    @Autowired
    @Qualifier("resultProcessor")
    private ItemProcessor<BiodataResult, Result> processor;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSourceWqp;

    @Autowired
    @Qualifier(Application.DATASOURCE_BIODATA_QUALIFIER)
    private DataSource dataSourceBiodata;

    // This is to set up the biodata.result table
    @Autowired
    @Qualifier("setupBiodataResultTableFlow")
    private Flow setupBiodataResultTableFlow;

    @Autowired
    @Qualifier(EtlConstantUtils.SETUP_RESULT_SWAP_TABLE_FLOW)
    private Flow setupResultSwapTableFlow;

    @Autowired
    @Qualifier(EtlConstantUtils.BUILD_RESULT_INDEXES_FLOW)
    private Flow buildResultIndexesFlow;

    @Value("classpath:sql/result/readResult.sql")
    private Resource readResultResource;

    @Value("classpath:sql/result/writeResult.sql")
    private Resource writeResultResource;

    @Bean
    public JdbcCursorItemReader<BiodataResult> resultReader() throws IOException {
        return new JdbcCursorItemReaderBuilder<BiodataResult>()
                .dataSource(dataSourceBiodata)
                .name("resultReader")
                .sql(new String(FileCopyUtils.copyToByteArray(readResultResource.getInputStream())))
                .rowMapper(new BiodataResultRowMapper())
                .build();
    }

    @Bean
    public ItemWriter<Result> resultWriter() throws IOException {
        JdbcBatchItemWriter<Result> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSourceWqp);
        itemWriter.setSql(new String(FileCopyUtils.copyToByteArray(writeResultResource.getInputStream())));
        ItemSqlParameterSourceProvider<Result> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
        itemWriter.setItemSqlParameterSourceProvider(paramProvider);
        return itemWriter;
    }

    @Bean
    public Step transformResultStep() throws IOException {
        return stepBuilderFactory
                .get("transformResultStep")
                .<BiodataResult, Result>chunk(1000)
                .reader(resultReader())
                .processor(processor)
                .writer(resultWriter())
                .build();
    }

    @Bean
    public Flow resultFlow() throws IOException {
        return new FlowBuilder<SimpleFlow>("resultFlow")
                // This is to set up the biodata.result table
                .start(setupBiodataResultTableFlow)
                .next(setupResultSwapTableFlow)
                .next(transformResultStep())
                .next(buildResultIndexesFlow)
                .build();
    }

}
