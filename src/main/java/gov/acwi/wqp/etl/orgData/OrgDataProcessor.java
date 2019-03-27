package gov.acwi.wqp.etl.orgData;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataOrganization;
import gov.acwi.wqp.etl.orgData.OrgData;

public class OrgDataProcessor implements ItemProcessor<BiodataOrganization, OrgData>{

	@Override
	public OrgData process(BiodataOrganization biodataOrganization) throws Exception {
		OrgData orgData = new OrgData();
		orgData.setDataSourceId(Application.DATA_SOURCE_ID);
		orgData.setDataSource(Application.DATA_SOURCE);
		orgData.setOrganizationId(Application.ORGANIZATION_ID);
		if (null != biodataOrganization) {
			orgData.setOrganization(biodataOrganization.getOrganizationIdentifier());
			orgData.setOrganizationName(biodataOrganization.getOrganizationName());
		}
		return orgData;
	}

}
