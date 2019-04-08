package gov.acwi.wqp.etl.biodata.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgis.PGgeometry;

import org.springframework.jdbc.core.RowMapper;

public class BiodataStationRowMapper implements RowMapper<BiodataStation> {
	
	public static final String BIODATA_SITE_ID = "biodata_site_id";
	public static final String AGENCY_CD = "agency_cd";
	public static final String SITE_NO = "site_no";
	public static final String ORGANIZATION = "organization";
	public static final String SITE_TYPE_LONG_NAME = "site_type_long_name";
	public static final String HUC_CD = "huc_cd";
	public static final String GOVERNMENTAL_UNIT_CODE = "governmental_unit_code";
	public static final String COUNTRY_CD = "country_cd";
	public static final String STATE_CD = "state_cd";
	public static final String COUNTY_CD = "county_cd";
	public static final String GEO_POINT = "geo_point";
	public static final String STATION_NM = "station_nm";
	public static final String ORGANIZATION_NAME = "organization_name";
	public static final String STATION_TYPE_NAME = "station_type_name";
	public static final String DEC_LATITUDE = "dec_latitude";
	public static final String DEC_LONGITUDE = "dec_longitude";
	public static final String MAP_SCALE = "map_scale";
	public static final String GEOPOSITIONING_METHOD = "geopositioning_method";
	public static final String COORD_DATUM_CD = "coord_datum_cd";
	public static final String ELEVATION_VALUE = "elevation_value";
	public static final String ELEVATION_UNIT = "elevation_unit";
	public static final String ELEVATION_METHOD = "elevation_method";
	public static final String VDATUM_ID_CODE = "vdatum_id_code";
	public static final String DRAIN_AREA_VA = "drain_area_va";
	public static final String DRAIN_AREA_UNIT = "drain_area_unit";
	public static final String CONTRIB_DRAIN_AREA_VALUE = "contrib_drain_area_value";
	public static final String CONTRIB_DRAIN_AREA_UNIT = "contrib_drain_area_unit";
	public static final String GEOPOSITION_ACCY_VALUE = "geoposition_accy_value";
	public static final String GEOPOSITION_ACCY_UNIT = "geoposition_accy_unit";
	public static final String VERTICAL_ACCURACY_VALUE = "vertical_accuracy_value";
	public static final String VERTICAL_ACCURACY_UNIT = "vertical_accuracy_unit";
	public static final String NAT_AQFR_NAME = "nat_aqfr_name";
	public static final String AQFR_NAME = "aqfr_name";
	public static final String AQFR_TYPE_NAME = "aqfr_type_name";
	public static final String CONSTRUCTION_DATE = "construction_date";
	public static final String WELL_DEPTH_VALUE = "well_depth_value";
	public static final String WELL_DEPTH_UNIT = "well_depth_unit";
	public static final String HOLE_DEPTH_VALUE = "hole_depth_value";
	public static final String HOLE_DEPTH_UNIT = "hole_depth_unit";

	@Override
	public BiodataStation mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataStation station = new BiodataStation();
		station.setBiodataSiteId(rs.getInt(BIODATA_SITE_ID));
		station.setAgencyCd(rs.getString(AGENCY_CD));
		station.setSiteNo(rs.getString(SITE_NO));
		station.setOrganization(rs.getString(ORGANIZATION));
		station.setSiteTypeLongName(rs.getString(SITE_TYPE_LONG_NAME));
		station.setHucCd(rs.getString(HUC_CD));
		station.setGovernmentalUnitCode(rs.getString(GOVERNMENTAL_UNIT_CODE));
		station.setCountryCd(rs.getString(COUNTRY_CD));
		station.setStateCd(rs.getString(STATE_CD));
		station.setCountyCd(rs.getString(COUNTY_CD));
		station.setGeoPoint( (PGgeometry) rs.getObject(GEO_POINT));
		station.setStationNm(rs.getString(STATION_NM));
		station.setOrganizationName(rs.getString(ORGANIZATION_NAME));
		station.setStationTypeName(rs.getString(STATION_TYPE_NAME));
		station.setDecLatitude(rs.getString(DEC_LATITUDE));
		station.setDecLongitude(rs.getString(DEC_LONGITUDE));
		station.setMapScale(rs.getString(MAP_SCALE));
		station.setGeopositioningMethod(rs.getString(GEOPOSITIONING_METHOD));
		station.setCoordDatumCd(rs.getString(COORD_DATUM_CD));
		station.setElevationValue(rs.getString(ELEVATION_VALUE));
		station.setElevationUnit(rs.getString(ELEVATION_UNIT));
		station.setElevationMethod(rs.getString(ELEVATION_METHOD));
		station.setVdatumIdCode(rs.getString(VDATUM_ID_CODE));
		station.setDrainAreaVa(rs.getString(DRAIN_AREA_VA));
		station.setDrainAreaUnit(rs.getString(DRAIN_AREA_UNIT));
		station.setContribDrainAreaValue(rs.getString(CONTRIB_DRAIN_AREA_VALUE));
		station.setContribDrainAreaUnit(rs.getString(CONTRIB_DRAIN_AREA_UNIT));
		station.setGeopositionAccyValue(rs.getString(GEOPOSITION_ACCY_VALUE));
		station.setGeopositionAccyUnit(rs.getString(GEOPOSITION_ACCY_UNIT));
		station.setVerticalAccuracyValue(rs.getString(VERTICAL_ACCURACY_VALUE));
		station.setVerticalAccuracyUnit(rs.getString(VERTICAL_ACCURACY_UNIT));
		station.setNatAqfrName(rs.getString(NAT_AQFR_NAME));
		station.setAqfrName(rs.getString(AQFR_NAME));
		station.setAqfrTypeName(rs.getString(AQFR_TYPE_NAME));
		station.setConstructionDate(rs.getString(CONSTRUCTION_DATE));
		station.setWellDepthValue(rs.getString(WELL_DEPTH_VALUE));
		station.setWellDepthUnit(rs.getString(WELL_DEPTH_UNIT));
		station.setHoleDepthValue(rs.getString(HOLE_DEPTH_VALUE));
		station.setHoleDepthUnit(rs.getString(HOLE_DEPTH_UNIT));
		return station;
	}

}
