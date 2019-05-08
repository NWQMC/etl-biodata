package gov.acwi.wqp.etl.projectData;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.projectData.BiodataProjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.batch.item.ItemProcessor;

@Component
public class ProjectDataProcessor implements ItemProcessor<BiodataProjectData, ProjectData> {

    private final ConfigurationService configurationService;

    @Autowired
    public ProjectDataProcessor(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public ProjectData process(BiodataProjectData biodataProjectData) throws Exception {
        ProjectData projectData = new ProjectData();

        // Process

        return projectData;
    }
}
