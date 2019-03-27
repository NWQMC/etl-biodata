package gov.acwi.wqp.etl.biodata;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.biodata.domain.BiodataOrganization;
import gov.acwi.wqp.etl.biodata.domain.BiodataOrganizationProcessor;
import gov.acwi.wqp.etl.biodata.domain.WqxOrganization;
import gov.acwi.wqp.etl.biodata.domain.WqxOrganizationDescription;
import gov.acwi.wqp.etl.biodata.domain.WqxProject;

@Component
public class BiodataOrganizationPull {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Value("classpath:testData/xml/wqxOrganization.xml")
	private Resource resource;

	@Autowired
	@Qualifier("truncateBiodataOrgProject")
	private Tasklet truncateBiodataOrgProject;

	@Bean
	public StaxEventItemReader<WqxOrganization> biodataOrganizationReader() {
		StaxEventItemReader<WqxOrganization> staxEventItemReader = new StaxEventItemReader<>();
		staxEventItemReader.setResource(resource);
		staxEventItemReader.setFragmentRootElementName("Organization");
		Jaxb2Marshaller unMarshaller = new Jaxb2Marshaller();
		unMarshaller.setClassesToBeBound(WqxOrganization.class, WqxOrganizationDescription.class, WqxProject.class);
		staxEventItemReader.setUnmarshaller(unMarshaller);
		return staxEventItemReader;
	}

	@Bean
	public ItemWriter<BiodataOrganization> biodataOrganizationWriter() {
		JdbcBatchItemWriter<BiodataOrganization> itemWriter = new JdbcBatchItemWriter<BiodataOrganization>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("insert "
				+ " into biodata_org_project (organization, organization_name, project_identifier, project_name, project_description_text)"
				+ " values (:organizationIdentifier, :organizationName, :projectIdentifier, :projectName, :projectDescriptionText)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BiodataOrganization>());
		itemWriter.afterPropertiesSet();
		return itemWriter;
	}

	@Bean
	public Step truncateBiodataOrgProjectStep() {
		return stepBuilderFactory
				.get("truncateBiodataOrgProjectStep")
				.tasklet(truncateBiodataOrgProject)
				.build();
	}

	@Bean
	public Step biodataOrganizationPullStep() {
		return stepBuilderFactory
				.get("biodataOrganizationPullStep")
				.<WqxOrganization, BiodataOrganization>chunk(10)
				.reader(biodataOrganizationReader())
				.processor(new BiodataOrganizationProcessor())
				.writer(biodataOrganizationWriter())
				.build();
	}

	@Bean
	public Flow biodataOrganizationPullFlow() {
		return new FlowBuilder<SimpleFlow>("biodataOrganizationPullFlow")
				.start(truncateBiodataOrgProjectStep())
				.next(biodataOrganizationPullStep())
				.build();
	}

}
