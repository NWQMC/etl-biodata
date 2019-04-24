package gov.acwi.wqp.etl.orgData;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.orgData.BiodataOrgData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrgDataProcessor implements ItemProcessor<BiodataOrgData, OrgData>{
	
	private final ConfigurationService configurationService;
	
	@Autowired
	public OrgDataProcessor(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@Override
	public OrgData process(BiodataOrgData biodataOD) throws Exception {
		OrgData orgData = new OrgData();
		
		orgData.setDataSourceId(configurationService.getEtlDataSourceId());
		orgData.setDataSource(configurationService.getEtlDataSource());
		orgData.setOrganizationId(biodataOD.getOrganizationId());
		orgData.setOrganization(biodataOD.getOrganization());
		orgData.setOrganizationName(biodataOD.getOrganizationName());
		
		return orgData;
	}
}
