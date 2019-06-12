package gov.acwi.wqp.etl.result;


import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.result.BiodataResult;

import static gov.acwi.wqp.etl.result.ResultProcessor.G_UNIT;
import static gov.acwi.wqp.etl.result.ResultProcessor.WEIGHT;
import static gov.acwi.wqp.etl.result.ResultProcessor.FISH_STANDARD_LENGTH;
import static gov.acwi.wqp.etl.result.ResultProcessor.MM_UNIT;
import static gov.acwi.wqp.etl.result.ResultProcessor.LENGTH_TOTAL_FISH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import org.junit.Before;
import org.junit.Test;

public class ResultProcessorTest extends BaseProcessorTest {

    private BiodataResult br;
    private ResultProcessor p;

    @Before
    public void setupTestClass() {
        br = new BiodataResult();
        p = new ResultProcessor(configurationService);

        br.setStationId(TEST_STATION_ID);
        br.setSiteId(TEST_SITE_ID);
        br.setEventDate(TEST_SAMPLE_COLLECTION_START_TIME);
        br.setActivity(TEST_ACTIVITY);
        br.setCharacteristicName(TEST_CHARACTERISTIC);
        br.setSampleMedia(TEST_SAMPLE_MEDIA);
        br.setOrganization(TEST_ORGANIZATION);
        br.setSiteType(TEST_SITE_TYPE);
        br.setHuc(TEST_HUC);
        br.setGovernmentalUnitCode(TEST_GOVERNMENTAL_UNIT_CODE);
        br.setOrganizationName(TEST_ORGANIZATION_NAME_1);
        br.setActivityId(TEST_ACTIVITY_ID);
        br.setActivityTypeCode(TEST_ACTIVITY_TYPE_CODE);
        br.setActivityMediaSubdivName(TEST_ACTIVITY_MEDIA_SUBDIV_NAME);
        br.setActivityStartTime(TEST_SAMPLE_COLLECTION_START_TIME_LOCAL_TIME);
        br.setActStartTimeZone(TEST_ACT_START_TIME_ZONE);
        br.setActivityStopDate(TEST_ACTIVITY_STOP_DATE);
        br.setActivityStopTime(TEST_ACTIVITY_STOP_TIME);
        br.setActStopTimeZone(TEST_ACT_START_TIME_ZONE);
        br.setActivityRelativeDepthName(TEST_ACTIVITY_RELATIVE_DEPTH_NAME);
        br.setActivityDepth(TEST_ACTIVITY_DEPTH);
        br.setActivityDepthUnit(TEST_ACTIVITY_DEPTH_UNIT);
        br.setActivityDepthRefPoint(TEST_ACTIVITY_DEPTH_REF_POINT);
        br.setActivityUpperDepth(TEST_ACTIVITY_UPPER_DEPTH);
        br.setActivityUpperDepthUnit(TEST_ACTIVITY_UPPER_DEPTH_UNIT);
        br.setActivityLowerDepth(TEST_ACTIVITY_LOWER_DEPTH);
        br.setActivityLowerDepthUnit(TEST_ACTIVITY_LOWER_DEPTH_UNIT);
        br.setProjectId(TEST_PROJECT_ID);
        br.setActivityConductingOrg(TEST_ACTIVITY_CONDUCTING_ORG);
        br.setActivityComment(TEST_ACTIVITY_COMMENT);
        br.setGeom(TEST_GEOM);
        br.setActivitySourceMapScale(TEST_ACTIVITY_SOURCE_MAP_SCALE);
        br.setActHorizontalAccuracy(TEST_ACT_HORIZONTAL_ACCURACY);
        br.setActHorizontalAccuracyUnit(TEST_ACT_HORIZONTAL_ACCURACY_UNIT);
        br.setActHorizontalCollectMethod(TEST_ACT_HORIZONTAL_COLLECT_METHOD);
        br.setActHorizontalDatumName(TEST_HORIZONTAL_DATUM_NAME);
        br.setAssemblageSampledName(TEST_ASSEMBLAGE_SAMPLED_NAME);
        br.setActCollectionDuration(TEST_ACT_COLLECTION_DURATION);
        br.setActCollectionDurationUnit(TEST_ACT_COLLECTION_DURATION_UNIT);
        br.setActSamCompntName(TEST_ACT_SAM_COMPNT_NAME);
        br.setActSamCompntPlaceInSeries(TEST_ACT_SAM_COMPNT_PLACE_IN_SERIES);
        br.setActReachLength(TEST_ACTIVITY_REACH_LENGTH_STRING);
        br.setActReachLengthUnit(TEST_ACTIVITY_REACH_LENGTH_UNIT);
        br.setActReachWidth(TEST_ACTIVITY_REACH_WIDTH);
        br.setActReachWidthUnit(TEST_ACTIVITY_REACH_WIDTH_UNIT);
        br.setActPassCount(TEST_ACT_PASS_COUNT);
        br.setNetTypeName(TEST_NET_TYPE_NAME);
        br.setActNetSurfaceArea(TEST_ACT_NET_SURFACE_AREA);
        br.setActNetSurfaceAreaUnit(TEST_ACT_NET_SURFACE_AREA_UNIT);
        br.setActNetMeshSize(TEST_ACT_NET_MESH_SIZE);
        br.setActNetMeshSizeUnit(TEST_ACT_NET_MESH_SIZE_UNIT);
        br.setActBoatSpeed(TEST_ACT_BOAT_SPEED);
        br.setActBoatSpeedUnit(TEST_ACT_BOAT_SPEED_UNIT);
        br.setActCurrentSpeed(TEST_ACT_CURRENT_SPEED);
        br.setActCurrentSpeedUnit(TEST_ACT_CURRENT_SPEED_UNIT);
        br.setToxicityTestTypeName(TEST_TOXICITY_TEST_TYPE_NAME);
        br.setSampleCollectMethodId(TEST_SAMPLE_COLLECT_METHOD_ID);
        br.setSampleCollectMethodCtx(TEST_SAMPLE_COLLECT_METHOD_CTX);
        br.setSampleCollectMethodName(TEST_SAMPLE_COLLECT_METHOD_NAME);
        br.setActSamCollectMethQualType(TEST_ACT_SAM_COLLECT_METH_QUAL_TYPE);
        br.setActSamCollectMethDesc(TEST_ACT_SAM_COLLECT_METH_DESC);
        br.setSampleCollectEquipName(TEST_SAMPLE_COLLECT_EQUIP_NAME);
        br.setActSamCollectEquipComments(TEST_SAMPLE_COLLECT_EQUIP_COMMENTS);
        br.setActSamPrepMethId(TEST_ACT_SAM_PREP_METH_ID);
        br.setActSamPrepMethContext(TEST_ACT_SAM_PREP_METH_CONTEXT);
        br.setActSamPrepMethName(TEST_ACT_SAM_PREP_METH_NAME);
        br.setActSamPrepMethQualType(TEST_ACT_SAM_PREP_METH_QUAL_TYPE);
        br.setActSamPrepMethDesc(TEST_ACT_SAM_PREP_METH_DESC);
        br.setSampleContainerType(TEST_SAMPLE_CONTAINER_TYPE);
        br.setSampleContainerColor(TEST_SAMPLE_CONTAINER_COLOR);
        br.setActSamChemicalPreservative(TEST_ACT_SAM_CHEMICAL_PRESERVATIVE);
        br.setThermalPreservativeName(TEST_THERMAL_PRESERVATIVE_NAME);
        br.setActSamTransportStorageDesc(TEST_ACT_SAM_TRANSPORT_STORAGE_DESC);
        br.setResultId(TEST_RESULT_ID);
        br.setResultMeasureValue(TEST_RESULT_MEASURE_VALUE);
        br.setResBioIndividualId(TEST_RES_BIO_INDIVIDUAL_ID);
        br.setSampleTissueTaxonomicName(TEST_SAMPLE_TISSUE_TAXONOMIC_NAME);
        br.setUnidentifiedSpeciesIdentifier(TEST_UNIDENTIFIED_SPECIES_IDENTIFIER);
        br.setResGroupSummaryCtWt(TEST_RES_GROUP_SUMMARY_CT_WT);
    }

    @Test
    public void testProcess() {
        Result actual = p.process(br);

        assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
        assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
        assertEquals(TEST_STATION_ID, actual.getStationId());
        assertEquals(TEST_SITE_ID, actual.getSiteId());
        assertEquals(TEST_EVENT_DATE_LOCAL_DATE, actual.getEventDate());
        assertEquals(TEST_ACTIVITY, actual.getActivity());
        assertEquals(TEST_CHARACTERISTIC, actual.getCharacteristicName());
        assertEquals(TEST_SAMPLE_MEDIA, actual.getSampleMedia());
        assertEquals(TEST_ORGANIZATION, actual.getOrganization());
        assertEquals(TEST_SITE_TYPE, actual.getSiteType());
        assertEquals(TEST_HUC, actual.getHuc());
        assertEquals(TEST_GOVERNMENTAL_UNIT_CODE, actual.getGovernmentalUnitCode());
        assertEquals(TEST_ORGANIZATION_NAME_1, actual.getOrganizationName());
        assertEquals(TEST_ACTIVITY_ID, actual.getActivityId());
        assertEquals(TEST_ACTIVITY_TYPE_CODE, actual.getActivityTypeCode());
        assertEquals(TEST_ACTIVITY_MEDIA_SUBDIV_NAME, actual.getActivityMediaSubdivName());
        assertEquals(TEST_SAMPLE_COLLECTION_START_TIME_LOCAL_TIME, actual.getActivityStartTime());
        assertEquals(TEST_ACT_START_TIME_ZONE, actual.getActStartTimeZone());
        assertEquals(TEST_ACTIVITY_STOP_DATE, actual.getActivityStopDate());
        assertEquals(TEST_ACTIVITY_STOP_TIME, actual.getActivityStopTime());
        assertEquals(TEST_ACT_START_TIME_ZONE, actual.getActStopTimeZone());
        assertEquals(TEST_ACTIVITY_RELATIVE_DEPTH_NAME, actual.getActivityRelativeDepthName());
        assertEquals(TEST_ACTIVITY_DEPTH, actual.getActivityDepth());
        assertEquals(TEST_ACTIVITY_DEPTH_UNIT, actual.getActivityDepthUnit());
        assertEquals(TEST_ACTIVITY_DEPTH_REF_POINT, actual.getActivityDepthRefPoint());
        assertEquals(TEST_ACTIVITY_UPPER_DEPTH, actual.getActivityUpperDepth());
        assertEquals(TEST_ACTIVITY_UPPER_DEPTH_UNIT, actual.getActivityUpperDepthUnit());
        assertEquals(TEST_ACTIVITY_LOWER_DEPTH, actual.getActivityLowerDepth());
        assertEquals(TEST_ACTIVITY_LOWER_DEPTH_UNIT, actual.getActivityLowerDepthUnit());
        assertEquals(TEST_PROJECT_ID, actual.getProjectId());
        assertEquals(TEST_ACTIVITY_CONDUCTING_ORG, actual.getActivityConductingOrg());
        assertEquals(TEST_ACTIVITY_COMMENT, actual.getActivityComment());
        assertEquals(TEST_GEOM, actual.getGeom());
        assertEquals(TEST_ACTIVITY_SOURCE_MAP_SCALE, actual.getActivitySourceMapScale());
        assertEquals(TEST_ACT_HORIZONTAL_ACCURACY, actual.getActHorizontalAccuracy());
        assertEquals(TEST_ACT_HORIZONTAL_ACCURACY_UNIT, actual.getActHorizontalAccuracyUnit());
        assertEquals(TEST_ACT_HORIZONTAL_COLLECT_METHOD, actual.getActHorizontalCollectMethod());
        assertEquals(TEST_HORIZONTAL_DATUM_NAME, actual.getActHorizontalDatumName());
        assertEquals(TEST_ASSEMBLAGE_SAMPLED_NAME, actual.getAssemblageSampledName());
        assertEquals(TEST_ACT_COLLECTION_DURATION, actual.getActCollectionDuration());
        assertEquals(TEST_ACT_COLLECTION_DURATION_UNIT, actual.getActCollectionDurationUnit());
        assertEquals(TEST_ACT_SAM_COMPNT_NAME, actual.getActSamCompntName());
        assertEquals(TEST_ACT_SAM_COMPNT_PLACE_IN_SERIES, actual.getActSamCompntPlaceInSeries());
        assertEquals(TEST_ACTIVITY_REACH_LENGTH_STRING, actual.getActReachLength());
        assertEquals(TEST_ACTIVITY_REACH_LENGTH_UNIT, actual.getActReachLengthUnit());
        assertEquals(TEST_ACTIVITY_REACH_WIDTH, actual.getActReachWidth());
        assertEquals(TEST_ACTIVITY_REACH_WIDTH_UNIT, actual.getActReachWidthUnit());
        assertEquals(TEST_ACT_PASS_COUNT, actual.getActPassCount());
        assertEquals(TEST_NET_TYPE_NAME, actual.getNetTypeName());
        assertEquals(TEST_ACT_NET_SURFACE_AREA, actual.getActNetSurfaceArea());
        assertEquals(TEST_ACT_NET_SURFACE_AREA_UNIT, actual.getActNetSurfaceAreaUnit());
        assertEquals(TEST_ACT_NET_MESH_SIZE, actual.getActNetMeshSize());
        assertEquals(TEST_ACT_NET_MESH_SIZE_UNIT, actual.getActNetMeshSizeUnit());
        assertEquals(TEST_ACT_BOAT_SPEED, actual.getActBoatSpeed());
        assertEquals(TEST_ACT_BOAT_SPEED_UNIT, actual.getActBoatSpeedUnit());
        assertEquals(TEST_ACT_CURRENT_SPEED, actual.getActCurrentSpeed());
        assertEquals(TEST_ACT_CURRENT_SPEED_UNIT, actual.getActCurrentSpeedUnit());
        assertEquals(TEST_TOXICITY_TEST_TYPE_NAME, actual.getToxicityTestTypeName());
        assertEquals(TEST_SAMPLE_COLLECT_METHOD_ID, actual.getSampleCollectMethodId());
        assertEquals(TEST_SAMPLE_COLLECT_METHOD_CTX, actual.getSampleCollectMethodCtx());
        assertEquals(TEST_SAMPLE_COLLECT_METHOD_NAME, actual.getSampleCollectMethodName());
        assertEquals(TEST_ACT_SAM_COLLECT_METH_QUAL_TYPE, actual.getActSamCollectMethQualType());
        assertEquals(TEST_ACT_SAM_COLLECT_METH_DESC, actual.getActSamCollectMethDesc());
        assertEquals(TEST_SAMPLE_COLLECT_EQUIP_NAME, actual.getSampleCollectEquipName());
        assertEquals(TEST_SAMPLE_COLLECT_EQUIP_COMMENTS, actual.getActSamCollectEquipComments());
        assertEquals(TEST_ACT_SAM_PREP_METH_ID, actual.getActSamPrepMethId());
        assertEquals(TEST_ACT_SAM_PREP_METH_CONTEXT, actual.getActSamPrepMethContext());
        assertEquals(TEST_ACT_SAM_PREP_METH_NAME, actual.getActSamPrepMethName());
        assertEquals(TEST_ACT_SAM_PREP_METH_QUAL_TYPE, actual.getActSamPrepMethQualType());
        assertEquals(TEST_ACT_SAM_PREP_METH_DESC, actual.getActSamPrepMethDesc());
        assertEquals(TEST_SAMPLE_CONTAINER_TYPE, actual.getSampleContainerType());
        assertEquals(TEST_SAMPLE_CONTAINER_COLOR, actual.getSampleContainerColor());
        assertEquals(TEST_ACT_SAM_CHEMICAL_PRESERVATIVE, actual.getActSamChemicalPreservative());
        assertEquals(TEST_THERMAL_PRESERVATIVE_NAME, actual.getThermalPreservativeName());
        assertEquals(TEST_ACT_SAM_TRANSPORT_STORAGE_DESC, actual.getActSamTransportStorageDesc());
        assertEquals(TEST_RESULT_ID, actual.getResultId());
        assertEquals(TEST_RESULT_MEASURE_VALUE_STRING, actual.getResultMeasureValue());
        assertEquals(TEST_RES_BIO_INDIVIDUAL_ID, actual.getResBioIndividualId());
        assertEquals(TEST_SAMPLE_TISSUE_TAXONOMIC_NAME, actual.getSampleTissueTaxonomicName());
        assertEquals(TEST_UNIDENTIFIED_SPECIES_IDENTIFIER, actual.getUnidentifiedSpeciesIdentifier());
        assertEquals(TEST_RES_GROUP_SUMMARY_CT_WT_STRING, actual.getResGroupSummaryCtWt());
        assertEquals(TEST_RES_GROUP_SUMMARY_CT_WT_UNIT, actual.getResGroupSummaryCtWtUnit());
    }

    @Test
    public void testNullValues() {
        br.setResGroupSummaryCtWt(null);
        br.setResultMeasureValue(null);
        Result actual = p.process(br);
        assertNull(actual.getResGroupSummaryCtWtUnit());
        assertNull(actual.getResultMeasureValue());
    }

    @Test
    public void testResultUnit() {
        br.setCharacteristicName(WEIGHT);
        Result actual = p.process(br);
        assertEquals(G_UNIT, actual.getResultUnit());

        br.setCharacteristicName(FISH_STANDARD_LENGTH);
        Result actual2 = p.process(br);
        assertEquals(MM_UNIT, actual2.getResultUnit());

        br.setCharacteristicName(LENGTH_TOTAL_FISH);
        Result actual3 = p.process(br);
        assertEquals(MM_UNIT, actual3.getResultUnit());
    }
}
