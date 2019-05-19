package gov.acwi.wqp.etl.result;


import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.result.BiodataResult;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultProcessor implements ItemProcessor<BiodataResult, Result> {

    protected static final String BIOLOGICAL = "Biological";
    protected static final String WEIGHT = "Weight";
    protected static final String FISH_STANDARD_LENGTH = "Fish standard length";
    protected static final String LENGTH_TOTAL_FISH = "Length, Total (Fish)";
    protected static final String G_UNIT = "g";
    protected static final String MM_UNIT = "mm";
    protected static final String FINAL = "Final";
    protected static final String ACTUAL = "Actual";
    protected static final String POPULATION_CENSUS = "Population Census";

    private final ConfigurationService configurationService;

    @Autowired
    public ResultProcessor(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public Result process(BiodataResult br) {

        Result r = new Result();

        r.setDataSourceId(configurationService.getEtlDataSourceId());
        r.setDataSource(configurationService.getEtlDataSource());
        r.setCharacteristicType(BIOLOGICAL);
        r.setStationId(br.getStationId());
        r.setSiteId(br.getSiteId());
        r.setEventDate(br.getEventDate().toLocalDate());
        r.setActivity(br.getActivity());
        r.setCharacteristicName(br.getCharacteristicName());
        r.setSampleMedia(br.getSampleMedia());
        r.setOrganization(br.getOrganization());
        r.setSiteType(br.getSiteType());
        r.setHuc(br.getHuc());
        r.setGovernmentalUnitCode(br.getGovernmentalUnitCode());
        r.setOrganizationName(br.getOrganizationName());
        r.setActivityId(br.getActivityId());
        r.setActivityTypeCode(br.getActivityTypeCode());
        r.setActivityMediaSubdivName(br.getActivityMediaSubdivName());
        r.setActivityStartTime(br.getActivityStartTime());
        r.setActStartTimeZone(br.getActStartTimeZone());
        r.setActivityStopDate(br.getActivityStopDate());
        r.setActivityStopTime(br.getActivityStopTime());
        r.setActStopTimeZone(br.getActStopTimeZone());
        r.setActivityRelativeDepthName(br.getActivityRelativeDepthName());
        r.setActivityDepth(br.getActivityDepth());
        r.setActivityDepthUnit(br.getActivityDepthUnit());
        r.setActivityDepthRefPoint(br.getActivityDepthRefPoint());
        r.setActivityUpperDepth(br.getActivityUpperDepth());
        r.setActivityUpperDepthUnit(br.getActivityUpperDepthUnit());
        r.setActivityLowerDepth(br.getActivityLowerDepth());
        r.setActivityLowerDepthUnit(br.getActivityLowerDepthUnit());
        r.setProjectId(br.getProjectId());
        r.setActivityConductingOrg(br.getActivityConductingOrg());
        r.setActivityComment(br.getActivityComment());
        r.setGeom(br.getGeom());
        r.setActivitySourceMapScale(br.getActivitySourceMapScale());
        r.setActHorizontalAccuracy(br.getActHorizontalAccuracy());
        r.setActHorizontalAccuracyUnit(br.getActHorizontalAccuracyUnit());
        r.setActHorizontalCollectMethod(br.getActHorizontalCollectMethod());
        r.setActHorizontalDatumName(br.getActHorizontalDatumName());
        r.setAssemblageSampledName(br.getAssemblageSampledName());
        r.setActCollectionDuration(br.getActCollectionDuration());
        r.setActCollectionDurationUnit(br.getActCollectionDurationUnit());
        r.setActSamCompntName(br.getActSamCompntName());
        r.setActSamCompntPlaceInSeries(br.getActSamCompntPlaceInSeries());
        r.setActReachLength(br.getActReachLength());
        r.setActReachLengthUnit(br.getActReachLengthUnit());
        r.setActReachWidth(br.getActReachWidth());
        r.setActReachWidthUnit(br.getActReachWidthUnit());
        r.setActPassCount(br.getActPassCount());
        r.setNetTypeName(br.getNetTypeName());
        r.setActNetSurfaceArea(br.getActNetSurfaceArea());
        r.setActNetSurfaceAreaUnit(br.getActNetSurfaceAreaUnit());
        r.setActNetMeshSize(br.getActNetMeshSize());
        r.setActNetMeshSizeUnit(br.getActNetMeshSizeUnit());
        r.setActBoatSpeed(br.getActBoatSpeed());
        r.setActBoatSpeedUnit(br.getActBoatSpeedUnit());
        r.setActCurrentSpeed(br.getActCurrentSpeed());
        r.setActCurrentSpeedUnit(br.getActCurrentSpeedUnit());
        r.setToxicityTestTypeName(br.getToxicityTestTypeName());
        r.setSampleCollectMethodId(br.getSampleCollectMethodId());
        r.setSampleCollectMethodCtx(br.getSampleCollectMethodCtx());
        r.setSampleCollectMethodName(br.getSampleCollectMethodName());
        r.setActSamCollectMethQualType(br.getActSamCollectMethQualType());
        r.setActSamCollectMethDesc(br.getActSamCollectMethDesc());
        r.setSampleCollectEquipName(br.getSampleCollectEquipName());
        r.setActSamCollectEquipComments(br.getActSamCollectEquipComments());
        r.setActSamPrepMethId(br.getActSamPrepMethId());
        r.setActSamPrepMethContext(br.getActSamPrepMethContext());
        r.setActSamPrepMethName(br.getActSamPrepMethName());
        r.setActSamPrepMethQualType(br.getActSamPrepMethQualType());
        r.setActSamPrepMethDesc(br.getActSamPrepMethDesc());
        r.setSampleContainerType(br.getSampleContainerType());
        r.setSampleContainerColor(br.getSampleContainerColor());
        r.setActSamChemicalPreservative(br.getActSamChemicalPreservative());
        r.setThermalPreservativeName(br.getThermalPreservativeName());
        r.setActSamTransportStorageDesc(br.getActSamTransportStorageDesc());
        r.setResultId(br.getResultId());
        r.setResultMeasureValue(br.getResultMeasureValue().toString());

        r.setResultUnit(
                getResultUnit(
                      br.getCharacteristicName()
                )
        );

        r.setResultValueStatus(FINAL);
        r.setResultValueType(ACTUAL);
        r.setBiologicalIntent(POPULATION_CENSUS);
        r.setResBioIndividualId(br.getResBioIndividualId());
        r.setSampleTissueTaxonomicName(br.getSampleTissueTaxonomicName());
        r.setUnidentifiedSpeciesIdentifier(br.getUnidentifiedSpeciesIdentifier());
        r.setResGroupSummaryCtWt(br.getResGroupSummaryCtWt().toString());
        r.setResGroupSummaryCtWtUnit(br.getResGroupSummaryCtWtUnit());

        return r;
    }

    private String getResultUnit(String characteristicName) {
        switch (characteristicName) {
            case WEIGHT :
                return G_UNIT;
            case FISH_STANDARD_LENGTH :
            case LENGTH_TOTAL_FISH :
                return MM_UNIT;
            default:
                return null;
        }
    }
}
