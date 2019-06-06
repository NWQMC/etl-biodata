package gov.acwi.wqp.etl.biodata.result;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BiodataResultRowMapper implements RowMapper<BiodataResult> {

    private static final String STATION_ID = "station_id";
    private static final String SITE_ID = "site_id";
    private static final String EVENT_DATE = "event_date";
    private static final String ACTIVITY = "activity";
    private static final String CHARACTERISTIC_NAME = "characteristic_name";
    private static final String SAMPLE_MEDIA = "sample_media";
    private static final String ORGANIZATION = "organization";
    private static final String SITE_TYPE = "site_type";
    private static final String HUC = "huc";
    private static final String GOVERNMENTAL_UNIT_CODE = "governmental_unit_code";
    private static final String ORGANIZATION_NAME = "organization_name";
    private static final String ACTIVITY_ID = "activity_id";
    private static final String ACTIVITY_TYPE_CODE = "activity_type_code";
    private static final String ACTIVITY_MEDIA_SUBDIV_NAME = "activity_media_subdiv_name";
    private static final String ACTIVITY_START_TIME = "activity_start_time";
    private static final String ACT_START_TIME_ZONE = "act_start_time_zone";
    private static final String ACTIVITY_STOP_DATE = "activity_stop_date";
    private static final String ACTIVITY_STOP_TIME = "activity_stop_time";
    private static final String ACT_STOP_TIME_ZONE = "act_stop_time_zone";
    private static final String ACTIVITY_RELATIVE_DEPTH_NAME = "activity_relative_depth_name";
    private static final String ACTIVITY_DEPTH = "activity_depth";
    private static final String ACTIVITY_DEPTH_UNIT = "activity_depth_unit";
    private static final String ACTIVITY_DEPTH_REF_POINT = "activity_depth_ref_point";
    private static final String ACTIVITY_UPPER_DEPTH = "activity_upper_depth";
    private static final String ACTIVITY_UPPER_DEPTH_UNIT = "activity_upper_depth_unit";
    private static final String ACTIVITY_LOWER_DEPTH = "activity_lower_depth";
    private static final String ACTIVITY_LOWER_DEPTH_UNIT = "activity_lower_depth_unit";
    private static final String PROJECT_ID = "project_id";
    private static final String ACTIVITY_CONDUCTING_ORG = "activity_conducting_org";
    private static final String ACTIVITY_COMMENT = "activity_comment";
    private static final String ACTIVITY_LATITUDE = "activity_latitude";
    private static final String ACTIVITY_LONGITUDE = "activity_longitude";
    private static final String ACTIVITY_SOURCE_MAP_SCALE = "activity_source_map_scale";
    private static final String ACT_HORIZONTAL_ACCURACY = "act_horizontal_accuracy";
    private static final String ACT_HORIZONTAL_ACCURACY_UNIT = "act_horizontal_accuracy_unit";
    private static final String ACT_HORIZONTAL_COLLECT_METHOD = "act_horizontal_collect_method";
    private static final String ACT_HORIZONTAL_DATUM_NAME = "act_horizontal_datum_name";
    private static final String ASSEMBLAGE_SAMPLED_NAME = "assemblage_sampled_name";
    private static final String ACT_COLLECTION_DURATION = "act_collection_duration";
    private static final String ACT_COLLECTION_DURATION_UNIT = "act_collection_duration_unit";
    private static final String ACT_SAM_COMPNT_NAME = "act_sam_compnt_name";
    private static final String ACT_SAM_COMPNT_PLACE_IN_SERIES = "act_sam_compnt_place_in_series";
    private static final String ACT_REACH_LENGTH = "act_reach_length";
    private static final String ACT_REACH_LENGTH_UNIT = "act_reach_length_unit";
    private static final String ACT_REACH_WIDTH = "act_reach_width";
    private static final String ACT_REACH_WIDTH_UNIT = "act_reach_width_unit";
    private static final String ACT_PASS_COUNT = "act_pass_count";
    private static final String NET_TYPE_NAME = "net_type_name";
    private static final String ACT_NET_SURFACE_AREA = "act_net_surface_area";
    private static final String ACT_NET_SURFACE_AREA_UNIT = "act_net_surface_area_unit";
    private static final String ACT_NET_MESH_SIZE = "act_net_mesh_size";
    private static final String ACT_NET_MESH_SIZE_UNIT = "act_net_mesh_size_unit";
    private static final String ACT_BOAT_SPEED = "act_boat_speed";
    private static final String ACT_BOAT_SPEED_UNIT = "act_boat_speed_unit";
    private static final String ACT_CURRENT_SPEED = "act_current_speed";
    private static final String ACT_CURRENT_SPEED_UNIT = "act_current_speed_unit";
    private static final String TOXICITY_TEST_TYPE_NAME = "toxicity_test_type_name";
    private static final String SAMPLE_COLLECT_METHOD_ID = "sample_collect_method_id";
    private static final String SAMPLE_COLLECT_METHOD_CTX = "sample_collect_method_ctx";
    private static final String SAMPLE_COLLECT_METHOD_NAME = "sample_collect_method_name";
    private static final String ACT_SAM_COLLECT_METH_QUAL_TYPE = "act_sam_collect_meth_qual_type";
    private static final String ACT_SAM_COLLECT_METH_DESC = "act_sam_collect_meth_desc";
    private static final String SAMPLE_COLLECT_EQUIP_NAME = "sample_collect_equip_name";
    private static final String ACT_SAM_COLLECT_EQUIP_COMMENTS = "act_sam_collect_equip_comments";
    private static final String ACT_SAM_PREP_METH_ID = "act_sam_prep_meth_id";
    private static final String ACT_SAM_PREP_METH_CONTEXT = "act_sam_prep_meth_context";
    private static final String ACT_SAM_PREP_METH_NAME = "act_sam_prep_meth_name";
    private static final String ACT_SAM_PREP_METH_QUAL_TYPE = "act_sam_prep_meth_qual_type";
    private static final String ACT_SAM_PREP_METH_DESC = "act_sam_prep_meth_desc";
    private static final String SAMPLE_CONTAINER_TYPE = "sample_container_type";
    private static final String SAMPLE_CONTAINER_COLOR = "sample_container_color";
    private static final String ACT_SAM_CHEMICAL_PRESERVATIVE = "act_sam_chemical_preservative";
    private static final String THERMAL_PRESERVATIVE_NAME = "thermal_preservative_name";
    private static final String ACT_SAM_TRANSPORT_STORAGE_DESC = "act_sam_transport_storage_desc";
    private static final String RESULT_ID = "result_id";
    private static final String RESULT_MEASURE_VALUE = "result_measure_value";
    private static final String RES_BIO_INDIVIDUAL_ID = "res_bio_individual_id";
    private static final String SAMPLE_TISSUE_TAXONOMIC_NAME = "sample_tissue_taxonomic_name";
    private static final String UNIDENTIFIED_SPECIES_IDENTIFIER = "unidentified_species_identifier";
    private static final String RES_GROUP_SUMMARY_CT_WT = "res_group_summary_ct_wt";
    private static final String RES_GROUP_SUMMARY_CT_WT_UNIT = "res_group_summary_ct_wt_unit";

    @Override
    public BiodataResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        BiodataResult biodataResult = new BiodataResult();

        biodataResult.setStationId(rs.getInt(STATION_ID));
        biodataResult.setSiteId(rs.getString(SITE_ID));
        biodataResult.setEventDate(rs.getTimestamp(EVENT_DATE).toLocalDateTime());
        biodataResult.setActivity(rs.getString(ACTIVITY));
        biodataResult.setCharacteristicName(rs.getString(CHARACTERISTIC_NAME));
        biodataResult.setSampleMedia(rs.getString(SAMPLE_MEDIA));
        biodataResult.setOrganization(rs.getString(ORGANIZATION));
        biodataResult.setSiteType(rs.getString(SITE_TYPE));
        biodataResult.setHuc(rs.getString(HUC));
        biodataResult.setGovernmentalUnitCode(rs.getString(GOVERNMENTAL_UNIT_CODE));
        biodataResult.setOrganizationName(rs.getString(ORGANIZATION_NAME));
        biodataResult.setActivityId(rs.getInt(ACTIVITY_ID));
        biodataResult.setActivityTypeCode(rs.getString(ACTIVITY_TYPE_CODE));
        biodataResult.setActivityMediaSubdivName(rs.getString(ACTIVITY_MEDIA_SUBDIV_NAME));
        biodataResult.setActivityStartTime(rs.getString(ACTIVITY_START_TIME));
        biodataResult.setActStartTimeZone(rs.getString(ACT_START_TIME_ZONE));
        biodataResult.setActivityStopDate(rs.getString(ACTIVITY_STOP_DATE));
        biodataResult.setActivityStopTime(rs.getString(ACTIVITY_STOP_TIME));
        biodataResult.setActStopTimeZone(rs.getString(ACT_STOP_TIME_ZONE));
        biodataResult.setActivityRelativeDepthName(rs.getString(ACTIVITY_RELATIVE_DEPTH_NAME));
        biodataResult.setActivityDepth(rs.getString(ACTIVITY_DEPTH));
        biodataResult.setActivityDepthUnit(rs.getString(ACTIVITY_DEPTH_UNIT));
        biodataResult.setActivityDepthRefPoint(rs.getString(ACTIVITY_DEPTH_REF_POINT));
        biodataResult.setActivityUpperDepth(rs.getString(ACTIVITY_UPPER_DEPTH));
        biodataResult.setActivityUpperDepthUnit(rs.getString(ACTIVITY_UPPER_DEPTH_UNIT));
        biodataResult.setActivityLowerDepth(rs.getString(ACTIVITY_LOWER_DEPTH));
        biodataResult.setActivityLowerDepthUnit(rs.getString(ACTIVITY_LOWER_DEPTH_UNIT));
        biodataResult.setProjectId(rs.getString(PROJECT_ID));
        biodataResult.setActivityConductingOrg(rs.getString(ACTIVITY_CONDUCTING_ORG));
        biodataResult.setActivityComment(rs.getString(ACTIVITY_COMMENT));
        biodataResult.setActivitySourceMapScale(rs.getInt(ACTIVITY_SOURCE_MAP_SCALE));
        biodataResult.setActHorizontalAccuracy(rs.getString(ACT_HORIZONTAL_ACCURACY));
        biodataResult.setActHorizontalAccuracyUnit(rs.getString(ACT_HORIZONTAL_ACCURACY_UNIT));
        biodataResult.setActHorizontalCollectMethod(rs.getString(ACT_HORIZONTAL_COLLECT_METHOD));
        biodataResult.setActHorizontalDatumName(rs.getString(ACT_HORIZONTAL_DATUM_NAME));
        biodataResult.setAssemblageSampledName(rs.getString(ASSEMBLAGE_SAMPLED_NAME));
        biodataResult.setActCollectionDuration(rs.getString(ACT_COLLECTION_DURATION));
        biodataResult.setActCollectionDurationUnit(rs.getString(ACT_COLLECTION_DURATION_UNIT));
        biodataResult.setActSamCompntName(rs.getString(ACT_SAM_COMPNT_NAME));
        biodataResult.setActSamCompntPlaceInSeries(rs.getInt(ACT_SAM_COMPNT_PLACE_IN_SERIES));
        biodataResult.setActReachLength(rs.getString(ACT_REACH_LENGTH));
        biodataResult.setActReachLengthUnit(rs.getString(ACT_REACH_LENGTH_UNIT));
        biodataResult.setActReachWidth(rs.getString(ACT_REACH_WIDTH));
        biodataResult.setActReachWidthUnit(rs.getString(ACT_REACH_WIDTH_UNIT));
        biodataResult.setActPassCount(rs.getInt(ACT_PASS_COUNT));
        biodataResult.setNetTypeName(rs.getString(NET_TYPE_NAME));
        biodataResult.setActNetSurfaceArea(rs.getString(ACT_NET_SURFACE_AREA));
        biodataResult.setActNetSurfaceAreaUnit(rs.getString(ACT_NET_SURFACE_AREA_UNIT));
        biodataResult.setActNetMeshSize(rs.getString(ACT_NET_MESH_SIZE));
        biodataResult.setActNetMeshSizeUnit(rs.getString(ACT_NET_MESH_SIZE_UNIT));
        biodataResult.setActBoatSpeed(rs.getString(ACT_BOAT_SPEED));
        biodataResult.setActBoatSpeedUnit(rs.getString(ACT_BOAT_SPEED_UNIT));
        biodataResult.setActCurrentSpeed(rs.getString(ACT_CURRENT_SPEED));
        biodataResult.setActCurrentSpeedUnit(rs.getString(ACT_CURRENT_SPEED_UNIT));
        biodataResult.setToxicityTestTypeName(rs.getString(TOXICITY_TEST_TYPE_NAME));
        biodataResult.setSampleCollectMethodId(rs.getString(SAMPLE_COLLECT_METHOD_ID));
        biodataResult.setSampleCollectMethodCtx(rs.getString(SAMPLE_COLLECT_METHOD_CTX));
        biodataResult.setSampleCollectMethodName(rs.getString(SAMPLE_COLLECT_METHOD_NAME));
        biodataResult.setActSamCollectMethQualType(rs.getString(ACT_SAM_COLLECT_METH_QUAL_TYPE));
        biodataResult.setActSamCollectMethDesc(rs.getString(ACT_SAM_COLLECT_METH_DESC));
        biodataResult.setSampleCollectEquipName(rs.getString(SAMPLE_COLLECT_EQUIP_NAME));
        biodataResult.setActSamCollectEquipComments(rs.getString(ACT_SAM_COLLECT_EQUIP_COMMENTS));
        biodataResult.setActSamPrepMethId(rs.getString(ACT_SAM_PREP_METH_ID));
        biodataResult.setActSamPrepMethContext(rs.getString(ACT_SAM_PREP_METH_CONTEXT));
        biodataResult.setActSamPrepMethName(rs.getString(ACT_SAM_PREP_METH_NAME));
        biodataResult.setActSamPrepMethQualType(rs.getString(ACT_SAM_PREP_METH_QUAL_TYPE));
        biodataResult.setActSamPrepMethDesc(rs.getString(ACT_SAM_PREP_METH_DESC));
        biodataResult.setSampleContainerType(rs.getString(SAMPLE_CONTAINER_TYPE));
        biodataResult.setSampleContainerColor(rs.getString(SAMPLE_CONTAINER_COLOR));
        biodataResult.setActSamChemicalPreservative(rs.getString(ACT_SAM_CHEMICAL_PRESERVATIVE));
        biodataResult.setThermalPreservativeName(rs.getString(THERMAL_PRESERVATIVE_NAME));
        biodataResult.setActSamTransportStorageDesc(rs.getString(ACT_SAM_TRANSPORT_STORAGE_DESC));
        biodataResult.setResultId(rs.getInt(RESULT_ID));
        biodataResult.setResultMeasureValue(rs.getInt(RESULT_MEASURE_VALUE));
        biodataResult.setResBioIndividualId(rs.getString(RES_BIO_INDIVIDUAL_ID));
        biodataResult.setSampleTissueTaxonomicName(rs.getString(SAMPLE_TISSUE_TAXONOMIC_NAME));
        biodataResult.setUnidentifiedSpeciesIdentifier(rs.getString(UNIDENTIFIED_SPECIES_IDENTIFIER));
        biodataResult.setResGroupSummaryCtWt(rs.getInt(RES_GROUP_SUMMARY_CT_WT));

        return biodataResult;
    }
}
