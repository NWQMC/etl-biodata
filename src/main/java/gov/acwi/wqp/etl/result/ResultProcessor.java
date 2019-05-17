package gov.acwi.wqp.etl.result;


import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.result.BiodataResult;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultProcessor implements ItemProcessor<BiodataResult, Result> {

    protected static final String BIOLOGICAL = "Biological";

    private final ConfigurationService configurationService;

    @Autowired
    public ResultProcessor(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public Result process(BiodataResult br) {

        Result result = new Result();

        result.setDataSourceId(configurationService.getEtlDataSourceId());
        result.setDataSource(configurationService.getEtlDataSource());

        result.setStationId(br.getStationId());
        result.setSiteId(br.getSiteId());
        result.setEventDate(br.getEventDate().toLocalDate());
        result.setActivity(br.getActivity());
        result.setCharacteristicName(br.getCharacteristicName());
        result.setSampleMedia(br.getSampleMedia());
        result.setOrganization(br.getOrganization());
        result.setSiteType(br.getSiteType());
        result.setHuc(br.getHuc());
        result.setGovernmentalUnitCode(br.getGovernmentalUnitCode());
        result.setOrganizationName(br.getOrganizationName());
        result.setActivityId(br.getActivityId());
        result.setActivityTypeCode(br.getActivityTypeCode());
        result.setActivityMediaSubdivName(br.getActivityMediaSubdivName());
        result.setActivityStartTime(br.getActivityStartTime());
        result.setActStartTimeZone(br.getActStartTimeZone());
        result.setActivityStopDate(br.getActivityStopDate());
        result.setActivityStopTime(br.getActivityStopTime());
        result.setActStopTimeZone(br.getActStopTimeZone());
        result.setActivityRelativeDepthName(br.getActivityRelativeDepthName());
        result.setActivityDepth(br.getActivityDepth());
        result.setActivityDepthUnit(br.getActivityDepthUnit());
        result.setActivityDepthRefPoint(br.getActivityDepthRefPoint());
        result.setActivityUpperDepth(br.getActivityUpperDepth());
        result.setActivityUpperDepthUnit(br.getActivityUpperDepthUnit());
        result.setActivityLowerDepth(br.getActivityLowerDepth());
        result.setActivityLowerDepthUnit(br.getActivityLowerDepthUnit());
        result.setProjectId(br.getProjectId());
        result.setActivityConductingOrg(br.getActivityConductingOrg());
        result.setActivityComment(br.getActivityComment());
        // TODO do we want lat/long or geom from Activity?
        result.setGeom(br.getGeom());

        result.setCharacteristicType(BIOLOGICAL);

        return result;
    }
}
