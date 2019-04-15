package gov.acwi.wqp.etl.orgData;

import gov.acwi.wqp.etl.biodata.domain.BiodataOrgData;
import org.springframework.batch.item.ItemProcessor;

public class OrgDataProcessor implements ItemProcessor<BiodataOrgData, OrgData>{
	@Override
	public OrgData process(BiodataOrgData biodataML) throws Exception {
		OrgData orgData = new OrgData();
		
		// orgdata.setSomeOrgData
		
		return orgData;
	}
}
