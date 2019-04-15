package gov.acwi.wqp.etl.orgData;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataOrgData;
import org.springframework.batch.item.ItemProcessor;

public class OrgDataProcessor implements ItemProcessor<BiodataOrgData, OrgData>{
	@Override
	public OrgData process(BiodataOrgData biodataOD) throws Exception {
		OrgData orgData = new OrgData();
		
		orgData.setDataSourceId(Application.DATA_SOURCE_ID);
		orgData.setDataSource(Application.DATA_SOURCE);
		orgData.setOrganizationId(biodataOD.getOrganizationId());
		orgData.setOrganization(biodataOD.getOrganization());
		orgData.setOrganizationName(biodataOD.getOrganizationName());
		
		return orgData;
	}
}
