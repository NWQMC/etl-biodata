package gov.acwi.wqp.etl.projectData;

import gov.acwi.wqp.etl.BiodataBaseFlowIT;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TransformProjectDataIT extends BiodataBaseFlowIT {

    @Autowired
    @Qualifier("projectDataFlow")
    private Flow projectDataFlow;
}
