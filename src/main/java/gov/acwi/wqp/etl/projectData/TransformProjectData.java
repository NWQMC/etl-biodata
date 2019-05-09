package gov.acwi.wqp.etl.projectData;

import gov.acwi.wqp.etl.EtlConstantUtils;
import gov.acwi.wqp.etl.biodata.projectData.BiodataProjectData;
import gov.acwi.wqp.etl.biodata.projectData.BiodataProjectDataRowMapper;
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
public class TransformProjectData {

    @Autowired
    @Qualifier("projectDataProcessor")
    private ItemProcessor<BiodataProjectData, ProjectData> processor;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("dataSourceWqp")
    private DataSource dataSourceWqp;

    @Autowired
    @Qualifier("dataSourceBiodata")
    private DataSource dataSourceBiodata;

    @Autowired
    @Qualifier(EtlConstantUtils.SETUP_PROJECT_DATA_SWAP_TABLE_FLOW)
    private Flow setupProjectDataSwapTableFlow;

    @Autowired
    @Qualifier(EtlConstantUtils.BUILD_PROJECT_DATA_INDEXES_FLOW)
    private Flow buildProjectDataIndexesFlow;

    @Value("classpath:sql/projectData/readBiodataProjectData.sql")
    private Resource sqlResourceReader;

    @Value("classpath:sql/projectData/writeProjectData.sql")
    private Resource sqlResourceWriter;

    @Bean
    public JdbcCursorItemReader<BiodataProjectData> projectDataReader() throws IOException {
        return new JdbcCursorItemReaderBuilder<BiodataProjectData>()
                .dataSource(dataSourceBiodata)
                .name("projectDataReader")
                .sql(new String(FileCopyUtils.copyToByteArray(sqlResourceReader.getInputStream())))
                .rowMapper(new BiodataProjectDataRowMapper())
                .build();
    }

    @Bean
    public ItemWriter<ProjectData> projectDataWriter() throws IOException {
        JdbcBatchItemWriter<ProjectData> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSourceWqp);
        itemWriter.setSql(new String(FileCopyUtils.copyToByteArray(sqlResourceWriter.getInputStream())));
        ItemSqlParameterSourceProvider<ProjectData> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
        itemWriter.setItemSqlParameterSourceProvider(paramProvider);
        return itemWriter;
    }

    @Bean
    public Step transformProjectDataStep() throws Exception {
        return stepBuilderFactory
                .get("transformProjectDataStep")
                .<BiodataProjectData, ProjectData>chunk(10)
                .reader(projectDataReader())
                .processor(processor)
                .writer(projectDataWriter())
                .build();
    }

    @Bean
    public Flow projectDataFlow() throws Exception {
        return new FlowBuilder<SimpleFlow>("projectDataFlow")
                .start(setupProjectDataSwapTableFlow)
                .next(transformProjectDataStep())
                .next(buildProjectDataIndexesFlow)
                .build();
    }
}
