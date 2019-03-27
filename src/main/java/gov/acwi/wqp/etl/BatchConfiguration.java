package gov.acwi.wqp.etl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	@Qualifier("orgDataFlow")
	private Flow orgDataFlow;

	@Autowired
	@Qualifier("projectDataFlow")
	private Flow projectDataFlow;

	@Autowired
	@Qualifier("monitoringLocationFlow")
	private Flow monitoringLocationFlow;

	@Autowired
	@Qualifier("activityFlow")
	private Flow activityFlow;

	@Autowired
	@Qualifier("resultFlow")
	private Flow resultFlow;

//  TODO: are the following three flows needed?
//	@Autowired
//	@Qualifier("createSummariesFlow")
//	private Flow createSummariesFlow;
//
//	@Autowired
//	@Qualifier("createCodesFlow")
//	private Flow createCodesFlow;
//
//	@Autowired
//	@Qualifier("databaseFinalizeFlow")
//	private Flow databaseFinalizeFlow;

	@Bean
	public Job wqxEtl() {
		return jobBuilderFactory.get("WQP_BIODATA_ETL")
//				.incrementer(jobIncrementer)
				.start(orgDataFlow)
				.next(projectDataFlow)
//				.next(projectObjectFlow)
				.next(monitoringLocationFlow)
//				.next(biologicalHabitatMetricFlow)
//				.next(monitoringLocationObjectFlow)
				.next(activityFlow)
//				.next(activityObjectFlow)
//				.next(activityMetricFlow)
				.next(resultFlow)
//				.next(resultObjectFlow)
//				.next(projectMlWeightingFlow)
                
//              TODO: are the following three flows needed?
//				.next(createSummariesFlow)
//				.next(createCodesFlow)
//				.next(databaseFinalizeFlow)
                
				.build()
				.build();
	}

}
