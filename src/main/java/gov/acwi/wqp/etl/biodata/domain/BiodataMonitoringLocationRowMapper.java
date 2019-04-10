package gov.acwi.wqp.etl.biodata.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgis.PGgeometry;

import org.springframework.jdbc.core.RowMapper;

public class BiodataMonitoringLocationRowMapper implements RowMapper<BiodataMonitoringLocation> {
	
	public static final String BIODATA_SITE_ID = "biodata_site_id";
	public static final String NWIS_SITE_ID = "site_id";
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
	public static final String ALT_DATUM_CD = "alt_datum_cd";
	public static final String ALTITUDE = "altitude";
	public static final String VDATUM_ID_CODE = "vdatum_id_code";
	public static final String DRAIN_AREA_VA = "drain_area_va";
	public static final String BIODATA_DRAIN_AREA_VA = "biodata_drain_area_va";
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
	public BiodataMonitoringLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataMonitoringLocation biodataML = new BiodataMonitoringLocation();
		biodataML.setBiodataSiteId(rs.getInt(BIODATA_SITE_ID));
		biodataML.setNwisSiteId(rs.getString(NWIS_SITE_ID));
		biodataML.setAgencyCd(rs.getString(AGENCY_CD));
		biodataML.setSiteNo(rs.getString(SITE_NO));
		biodataML.setOrganization(rs.getString(ORGANIZATION));
		biodataML.setSiteTypeLongName(rs.getString(SITE_TYPE_LONG_NAME));
		biodataML.setHucCd(rs.getString(HUC_CD));
		biodataML.setGovernmentalUnitCode(rs.getString(GOVERNMENTAL_UNIT_CODE));
		biodataML.setCountryCd(rs.getString(COUNTRY_CD));
		biodataML.setStateCd(rs.getString(STATE_CD));
		biodataML.setCountyCd(rs.getString(COUNTY_CD));
		biodataML.setGeoPoint( (PGgeometry) rs.getObject(GEO_POINT));
		biodataML.setStationNm(rs.getString(STATION_NM));
		biodataML.setOrganizationName(rs.getString(ORGANIZATION_NAME));
		biodataML.setStationTypeName(rs.getString(STATION_TYPE_NAME));
		biodataML.setDecLatitude(rs.getString(DEC_LATITUDE));
		biodataML.setDecLongitude(rs.getString(DEC_LONGITUDE));
		biodataML.setMapScale(rs.getString(MAP_SCALE));
		biodataML.setGeopositioningMethod(rs.getString(GEOPOSITIONING_METHOD));
		biodataML.setCoordDatumCd(rs.getString(COORD_DATUM_CD));
		biodataML.setAltDatumCd(rs.getString(ALT_DATUM_CD));
		biodataML.setAltitude(rs.getString(ALTITUDE));
		biodataML.setElevationValue(rs.getString(ELEVATION_VALUE));
		biodataML.setElevationUnit(rs.getString(ELEVATION_UNIT));
		biodataML.setElevationMethod(rs.getString(ELEVATION_METHOD));
		biodataML.setVdatumIdCode(rs.getString(VDATUM_ID_CODE));
		biodataML.setDrainAreaVa(rs.getString(DRAIN_AREA_VA));
		biodataML.setBiodataDrainAreaVa(rs.getString(BIODATA_DRAIN_AREA_VA));
		biodataML.setDrainAreaUnit(rs.getString(DRAIN_AREA_UNIT));
		biodataML.setContribDrainAreaValue(rs.getString(CONTRIB_DRAIN_AREA_VALUE));
		biodataML.setContribDrainAreaUnit(rs.getString(CONTRIB_DRAIN_AREA_UNIT));
		biodataML.setGeopositionAccyValue(rs.getString(GEOPOSITION_ACCY_VALUE));
		biodataML.setGeopositionAccyUnit(rs.getString(GEOPOSITION_ACCY_UNIT));
		biodataML.setVerticalAccuracyValue(rs.getString(VERTICAL_ACCURACY_VALUE));
		biodataML.setVerticalAccuracyUnit(rs.getString(VERTICAL_ACCURACY_UNIT));
		biodataML.setNatAqfrName(rs.getString(NAT_AQFR_NAME));
		biodataML.setAqfrName(rs.getString(AQFR_NAME));
		biodataML.setAqfrTypeName(rs.getString(AQFR_TYPE_NAME));
		biodataML.setConstructionDate(rs.getString(CONSTRUCTION_DATE));
		biodataML.setWellDepthValue(rs.getString(WELL_DEPTH_VALUE));
		biodataML.setWellDepthUnit(rs.getString(WELL_DEPTH_UNIT));
		biodataML.setHoleDepthValue(rs.getString(HOLE_DEPTH_VALUE));
		biodataML.setHoleDepthUnit(rs.getString(HOLE_DEPTH_UNIT));
		return biodataML;
	}

}
