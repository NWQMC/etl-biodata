//package gov.acwi.wqp.etl.biodata;
//
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.job.flow.support.SimpleFlow;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class BiodataExtract {
//
//	@Autowired
//	@Qualifier("biodataOrganizationPullFlow")
//	private Flow biodataOrganizationPullFlow;
//
//	@Autowired
//	@Qualifier("biodataStationPullFlow")
//	private Flow biodataStationPullFlow;
//
//	@Autowired
//	@Qualifier("biodataResultPullFlow")
//	private Flow biodataResultPullFlow;
//
//	@Bean
//	public Flow biodataExtractFlow() {
//		return new FlowBuilder<SimpleFlow>("biodataExtractFlow")
//				.start(biodataOrganizationPullFlow)
//				.next(biodataStationPullFlow)
//				.next(biodataResultPullFlow)
//				.build();
//	}
//
//}
