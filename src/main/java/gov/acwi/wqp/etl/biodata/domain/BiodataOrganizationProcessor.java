package gov.acwi.wqp.etl.biodata.domain;

import org.springframework.batch.item.ItemProcessor;

public class BiodataOrganizationProcessor implements ItemProcessor<BiodataOrganization, BiodataOrganization>{

	@Override
	public BiodataOrganization process(BiodataOrganization item) throws Exception {
		return new BiodataOrganization(item);
	}

}
