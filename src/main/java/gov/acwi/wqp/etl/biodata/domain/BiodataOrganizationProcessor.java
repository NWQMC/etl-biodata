package gov.acwi.wqp.etl.biodata.domain;

import org.springframework.batch.item.ItemProcessor;

public class BiodataOrganizationProcessor implements ItemProcessor<WqxOrganization, BiodataOrganization>{

	@Override
	public BiodataOrganization process(WqxOrganization item) throws Exception {
		return new BiodataOrganization(item);
	}

}
