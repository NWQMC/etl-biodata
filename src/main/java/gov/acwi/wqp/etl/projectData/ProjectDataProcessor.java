package gov.acwi.wqp.etl.projectData;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataOrganization;
import gov.acwi.wqp.etl.projectData.ProjectData;

public class ProjectDataProcessor implements ItemProcessor<BiodataOrganization, ProjectData>{

	@Override
	public ProjectData process(BiodataOrganization biodataOrganization) throws Exception {
		ProjectData projectData = new ProjectData();
		projectData.setDataSourceId(Application.DATA_SOURCE_ID);
		projectData.setDataSource(Application.DATA_SOURCE);
		projectData.setOrganizationId(Application.ORGANIZATION_ID);
		if (null != biodataOrganization) {
			projectData.setOrganization(biodataOrganization.getOrganizationIdentifier());
			projectData.setOrganizationName(biodataOrganization.getOrganizationName());
			projectData.setProjectIdentifier(biodataOrganization.getProjectIdentifier());
			projectData.setProjectName(biodataOrganization.getProjectName());
			projectData.setDescription(biodataOrganization.getProjectDescriptionText());
		}

		return projectData;
	}

}
