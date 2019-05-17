package gov.acwi.wqp.etl.biodata.result;

import org.postgis.PGgeometry;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BiodataResult {

    private Integer stationId;
    private String siteId;
    private LocalDateTime eventDate;
    private String activity;
    private String characteristicName;
    private String sampleMedia;
    private String organization;
    private String siteType;
    private String huc;
    private String governmentalUnitCode;
    private String organizationName;
    private Integer activityId;
    private String activityTypeCode;
    private String activityMediaSubdivName;
    private String activityStartTime;
    private String actStartTimeZone;
    private String activityStopDate;
    private String activityStopTime;
    private String actStopTimeZone;
    private String activityRelativeDepthName;
    private String activityDepth;
    private String activityDepthUnit;
    private String activityDepthRefPoint;
    private String activityUpperDepth;
    private String activityUpperDepthUnit;
    private String activityLowerDepth;
    private String activityLowerDepthUnit;
    private String projectId;
    private String activityConductingOrg;
    private String activityComment;
    private PGgeometry geom;
    private BigDecimal activityLatitude;
    private BigDecimal activityLongitude;
    private Integer activitySourceMapScale;
    private String actHorizontalAccuracy;
    private String actHorizontalAccuracyUnit;
    private String actHorizontalCollectMethod;
    private String actHorizontalDatumName;
    private String assemblageSampledName;
    private String actCollectionDuration;
    private String actCollectionDurationUnit;
    private String actSamCompntName;
    private Integer actSamCompntPlaceInSeries;
    private String actReachLength;
    private String actReachLengthUnit;
    private String actReachWidth;
    private String actReachWidthUnit;
    private Integer actPassCount;
    private String netTypeName;
    private String actNetSurfaceArea;
    private String actNetSurfaceAreaUnit;
    private String actNetMeshSize;
    private String actNetMeshSizeUnit;
    private String actBoatSpeed;
    private String actBoatSpeedUnit;
    private String actCurrentSpeed;
    private String actCurrentSpeedUnit;
    private String toxicityTestTypeName;
    private String sampleCollectMethodId;
    private String sampleCollectMethodCtx;
    private String sampleCollectMethodName;
    private String actSamCollectMethQualType;
    private String actSamCollectMethDesc;
    private String sampleCollectEquipName;
    private String actSamCollectEquipComments;
    private String actSamPrepMethId;
    private String actSamPrepMethContext;
    private String actSamPrepMethName;
    private String actSamPrepMethQualType;
    private String actSamPrepMethDesc;
    private String sampleContainerType;
    private String sampleContainerColor;
    private String actSamChemicalPreservative;
    private String thermalPreservativeName;
    private String actSamTransportStorageDesc;
    private Integer resultId;
    private Integer resultMeasureValue;
    private String resBioIndividualId;
    private String sampleTissueTaxonomicName;
    private String unidentifiedSpeciesIdentifier;
    private Integer resGroupSummaryCtWt;
    private String resGroupSummaryCtWtUnit;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getSampleMedia() {
        return sampleMedia;
    }

    public void setSampleMedia(String sampleMedia) {
        this.sampleMedia = sampleMedia;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getHuc() {
        return huc;
    }

    public void setHuc(String huc) {
        this.huc = huc;
    }

    public String getGovernmentalUnitCode() {
        return governmentalUnitCode;
    }

    public void setGovernmentalUnitCode(String governmentalUnitCode) {
        this.governmentalUnitCode = governmentalUnitCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getActivityMediaSubdivName() {
        return activityMediaSubdivName;
    }

    public void setActivityMediaSubdivName(String activityMediaSubdivName) {
        this.activityMediaSubdivName = activityMediaSubdivName;
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActStartTimeZone() {
        return actStartTimeZone;
    }

    public void setActStartTimeZone(String actStartTimeZone) {
        this.actStartTimeZone = actStartTimeZone;
    }

    public String getActivityStopDate() {
        return activityStopDate;
    }

    public void setActivityStopDate(String activityStopDate) {
        this.activityStopDate = activityStopDate;
    }

    public String getActivityStopTime() {
        return activityStopTime;
    }

    public void setActivityStopTime(String activityStopTime) {
        this.activityStopTime = activityStopTime;
    }

    public String getActStopTimeZone() {
        return actStopTimeZone;
    }

    public void setActStopTimeZone(String actStopTimeZone) {
        this.actStopTimeZone = actStopTimeZone;
    }

    public String getActivityRelativeDepthName() {
        return activityRelativeDepthName;
    }

    public void setActivityRelativeDepthName(String activityRelativeDepthName) {
        this.activityRelativeDepthName = activityRelativeDepthName;
    }

    public String getActivityDepth() {
        return activityDepth;
    }

    public void setActivityDepth(String activityDepth) {
        this.activityDepth = activityDepth;
    }

    public String getActivityDepthUnit() {
        return activityDepthUnit;
    }

    public void setActivityDepthUnit(String activityDepthUnit) {
        this.activityDepthUnit = activityDepthUnit;
    }

    public String getActivityDepthRefPoint() {
        return activityDepthRefPoint;
    }

    public void setActivityDepthRefPoint(String activityDepthRefPoint) {
        this.activityDepthRefPoint = activityDepthRefPoint;
    }

    public String getActivityUpperDepth() {
        return activityUpperDepth;
    }

    public void setActivityUpperDepth(String activityUpperDepth) {
        this.activityUpperDepth = activityUpperDepth;
    }

    public String getActivityUpperDepthUnit() {
        return activityUpperDepthUnit;
    }

    public void setActivityUpperDepthUnit(String activityUpperDepthUnit) {
        this.activityUpperDepthUnit = activityUpperDepthUnit;
    }

    public String getActivityLowerDepth() {
        return activityLowerDepth;
    }

    public void setActivityLowerDepth(String activityLowerDepth) {
        this.activityLowerDepth = activityLowerDepth;
    }

    public String getActivityLowerDepthUnit() {
        return activityLowerDepthUnit;
    }

    public void setActivityLowerDepthUnit(String activityLowerDepthUnit) {
        this.activityLowerDepthUnit = activityLowerDepthUnit;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getActivityConductingOrg() {
        return activityConductingOrg;
    }

    public void setActivityConductingOrg(String activityConductingOrg) {
        this.activityConductingOrg = activityConductingOrg;
    }

    public String getActivityComment() {
        return activityComment;
    }

    public void setActivityComment(String activityComment) {
        this.activityComment = activityComment;
    }

    public PGgeometry getGeom() {
        return geom;
    }

    public void setGeom(PGgeometry geom) {
        this.geom = geom;
    }

    public BigDecimal getActivityLatitude() {
        return activityLatitude;
    }

    public void setActivityLatitude(BigDecimal activityLatitude) {
        this.activityLatitude = activityLatitude;
    }

    public BigDecimal getActivityLongitude() {
        return activityLongitude;
    }

    public void setActivityLongitude(BigDecimal activityLongitude) {
        this.activityLongitude = activityLongitude;
    }

    public Integer getActivitySourceMapScale() {
        return activitySourceMapScale;
    }

    public void setActivitySourceMapScale(Integer activitySourceMapScale) {
        this.activitySourceMapScale = activitySourceMapScale;
    }

    public String getActHorizontalAccuracy() {
        return actHorizontalAccuracy;
    }

    public void setActHorizontalAccuracy(String actHorizontalAccuracy) {
        this.actHorizontalAccuracy = actHorizontalAccuracy;
    }

    public String getActHorizontalAccuracyUnit() {
        return actHorizontalAccuracyUnit;
    }

    public void setActHorizontalAccuracyUnit(String actHorizontalAccuracyUnit) {
        this.actHorizontalAccuracyUnit = actHorizontalAccuracyUnit;
    }

    public String getActHorizontalCollectMethod() {
        return actHorizontalCollectMethod;
    }

    public void setActHorizontalCollectMethod(String actHorizontalCollectMethod) {
        this.actHorizontalCollectMethod = actHorizontalCollectMethod;
    }

    public String getActHorizontalDatumName() {
        return actHorizontalDatumName;
    }

    public void setActHorizontalDatumName(String actHorizontalDatumName) {
        this.actHorizontalDatumName = actHorizontalDatumName;
    }

    public String getAssemblageSampledName() {
        return assemblageSampledName;
    }

    public void setAssemblageSampledName(String assemblageSampledName) {
        this.assemblageSampledName = assemblageSampledName;
    }

    public String getActCollectionDuration() {
        return actCollectionDuration;
    }

    public void setActCollectionDuration(String actCollectionDuration) {
        this.actCollectionDuration = actCollectionDuration;
    }

    public String getActCollectionDurationUnit() {
        return actCollectionDurationUnit;
    }

    public void setActCollectionDurationUnit(String actCollectionDurationUnit) {
        this.actCollectionDurationUnit = actCollectionDurationUnit;
    }

    public String getActSamCompntName() {
        return actSamCompntName;
    }

    public void setActSamCompntName(String actSamCompntName) {
        this.actSamCompntName = actSamCompntName;
    }

    public Integer getActSamCompntPlaceInSeries() {
        return actSamCompntPlaceInSeries;
    }

    public void setActSamCompntPlaceInSeries(Integer actSamCompntPlaceInSeries) {
        this.actSamCompntPlaceInSeries = actSamCompntPlaceInSeries;
    }

    public String getActReachLength() {
        return actReachLength;
    }

    public void setActReachLength(String actReachLength) {
        this.actReachLength = actReachLength;
    }

    public String getActReachLengthUnit() {
        return actReachLengthUnit;
    }

    public void setActReachLengthUnit(String actReachLengthUnit) {
        this.actReachLengthUnit = actReachLengthUnit;
    }

    public String getActReachWidth() {
        return actReachWidth;
    }

    public void setActReachWidth(String actReachWidth) {
        this.actReachWidth = actReachWidth;
    }

    public String getActReachWidthUnit() {
        return actReachWidthUnit;
    }

    public void setActReachWidthUnit(String actReachWidthUnit) {
        this.actReachWidthUnit = actReachWidthUnit;
    }

    public Integer getActPassCount() {
        return actPassCount;
    }

    public void setActPassCount(Integer actPassCount) {
        this.actPassCount = actPassCount;
    }

    public String getNetTypeName() {
        return netTypeName;
    }

    public void setNetTypeName(String netTypeName) {
        this.netTypeName = netTypeName;
    }

    public String getActNetSurfaceArea() {
        return actNetSurfaceArea;
    }

    public void setActNetSurfaceArea(String actNetSurfaceArea) {
        this.actNetSurfaceArea = actNetSurfaceArea;
    }

    public String getActNetSurfaceAreaUnit() {
        return actNetSurfaceAreaUnit;
    }

    public void setActNetSurfaceAreaUnit(String actNetSurfaceAreaUnit) {
        this.actNetSurfaceAreaUnit = actNetSurfaceAreaUnit;
    }

    public String getActNetMeshSize() {
        return actNetMeshSize;
    }

    public void setActNetMeshSize(String actNetMeshSize) {
        this.actNetMeshSize = actNetMeshSize;
    }

    public String getActNetMeshSizeUnit() {
        return actNetMeshSizeUnit;
    }

    public void setActNetMeshSizeUnit(String actNetMeshSizeUnit) {
        this.actNetMeshSizeUnit = actNetMeshSizeUnit;
    }

    public String getActBoatSpeed() {
        return actBoatSpeed;
    }

    public void setActBoatSpeed(String actBoatSpeed) {
        this.actBoatSpeed = actBoatSpeed;
    }

    public String getActBoatSpeedUnit() {
        return actBoatSpeedUnit;
    }

    public void setActBoatSpeedUnit(String actBoatSpeedUnit) {
        this.actBoatSpeedUnit = actBoatSpeedUnit;
    }

    public String getActCurrentSpeed() {
        return actCurrentSpeed;
    }

    public void setActCurrentSpeed(String actCurrentSpeed) {
        this.actCurrentSpeed = actCurrentSpeed;
    }

    public String getActCurrentSpeedUnit() {
        return actCurrentSpeedUnit;
    }

    public void setActCurrentSpeedUnit(String actCurrentSpeedUnit) {
        this.actCurrentSpeedUnit = actCurrentSpeedUnit;
    }

    public String getToxicityTestTypeName() {
        return toxicityTestTypeName;
    }

    public void setToxicityTestTypeName(String toxicityTestTypeName) {
        this.toxicityTestTypeName = toxicityTestTypeName;
    }

    public String getSampleCollectMethodId() {
        return sampleCollectMethodId;
    }

    public void setSampleCollectMethodId(String sampleCollectMethodId) {
        this.sampleCollectMethodId = sampleCollectMethodId;
    }

    public String getSampleCollectMethodCtx() {
        return sampleCollectMethodCtx;
    }

    public void setSampleCollectMethodCtx(String sampleCollectMethodCtx) {
        this.sampleCollectMethodCtx = sampleCollectMethodCtx;
    }

    public String getSampleCollectMethodName() {
        return sampleCollectMethodName;
    }

    public void setSampleCollectMethodName(String sampleCollectMethodName) {
        this.sampleCollectMethodName = sampleCollectMethodName;
    }

    public String getActSamCollectMethQualType() {
        return actSamCollectMethQualType;
    }

    public void setActSamCollectMethQualType(String actSamCollectMethQualType) {
        this.actSamCollectMethQualType = actSamCollectMethQualType;
    }

    public String getActSamCollectMethDesc() {
        return actSamCollectMethDesc;
    }

    public void setActSamCollectMethDesc(String actSamCollectMethDesc) {
        this.actSamCollectMethDesc = actSamCollectMethDesc;
    }

    public String getSampleCollectEquipName() {
        return sampleCollectEquipName;
    }

    public void setSampleCollectEquipName(String sampleCollectEquipName) {
        this.sampleCollectEquipName = sampleCollectEquipName;
    }

    public String getActSamCollectEquipComments() {
        return actSamCollectEquipComments;
    }

    public void setActSamCollectEquipComments(String actSamCollectEquipComments) {
        this.actSamCollectEquipComments = actSamCollectEquipComments;
    }

    public String getActSamPrepMethId() {
        return actSamPrepMethId;
    }

    public void setActSamPrepMethId(String actSamPrepMethId) {
        this.actSamPrepMethId = actSamPrepMethId;
    }

    public String getActSamPrepMethContext() {
        return actSamPrepMethContext;
    }

    public void setActSamPrepMethContext(String actSamPrepMethContext) {
        this.actSamPrepMethContext = actSamPrepMethContext;
    }

    public String getActSamPrepMethName() {
        return actSamPrepMethName;
    }

    public void setActSamPrepMethName(String actSamPrepMethName) {
        this.actSamPrepMethName = actSamPrepMethName;
    }

    public String getActSamPrepMethQualType() {
        return actSamPrepMethQualType;
    }

    public void setActSamPrepMethQualType(String actSamPrepMethQualType) {
        this.actSamPrepMethQualType = actSamPrepMethQualType;
    }

    public String getActSamPrepMethDesc() {
        return actSamPrepMethDesc;
    }

    public void setActSamPrepMethDesc(String actSamPrepMethDesc) {
        this.actSamPrepMethDesc = actSamPrepMethDesc;
    }

    public String getSampleContainerType() {
        return sampleContainerType;
    }

    public void setSampleContainerType(String sampleContainerType) {
        this.sampleContainerType = sampleContainerType;
    }

    public String getSampleContainerColor() {
        return sampleContainerColor;
    }

    public void setSampleContainerColor(String sampleContainerColor) {
        this.sampleContainerColor = sampleContainerColor;
    }

    public String getActSamChemicalPreservative() {
        return actSamChemicalPreservative;
    }

    public void setActSamChemicalPreservative(String actSamChemicalPreservative) {
        this.actSamChemicalPreservative = actSamChemicalPreservative;
    }

    public String getThermalPreservativeName() {
        return thermalPreservativeName;
    }

    public void setThermalPreservativeName(String thermalPreservativeName) {
        this.thermalPreservativeName = thermalPreservativeName;
    }

    public String getActSamTransportStorageDesc() {
        return actSamTransportStorageDesc;
    }

    public void setActSamTransportStorageDesc(String actSamTransportStorageDesc) {
        this.actSamTransportStorageDesc = actSamTransportStorageDesc;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getResultMeasureValue() {
        return resultMeasureValue;
    }

    public void setResultMeasureValue(Integer resultMeasureValue) {
        this.resultMeasureValue = resultMeasureValue;
    }

    public String getResBioIndividualId() {
        return resBioIndividualId;
    }

    public void setResBioIndividualId(String resBioIndividualId) {
        this.resBioIndividualId = resBioIndividualId;
    }

    public String getSampleTissueTaxonomicName() {
        return sampleTissueTaxonomicName;
    }

    public void setSampleTissueTaxonomicName(String sampleTissueTaxonomicName) {
        this.sampleTissueTaxonomicName = sampleTissueTaxonomicName;
    }

    public String getUnidentifiedSpeciesIdentifier() {
        return unidentifiedSpeciesIdentifier;
    }

    public void setUnidentifiedSpeciesIdentifier(String unidentifiedSpeciesIdentifier) {
        this.unidentifiedSpeciesIdentifier = unidentifiedSpeciesIdentifier;
    }

    public Integer getResGroupSummaryCtWt() {
        return resGroupSummaryCtWt;
    }

    public void setResGroupSummaryCtWt(Integer resGroupSummaryCtWt) {
        this.resGroupSummaryCtWt = resGroupSummaryCtWt;
    }

    public String getResGroupSummaryCtWtUnit() {
        return resGroupSummaryCtWtUnit;
    }

    public void setResGroupSummaryCtWtUnit(String resGroupSummaryCtWtUnit) {
        this.resGroupSummaryCtWtUnit = resGroupSummaryCtWtUnit;
    }
}
