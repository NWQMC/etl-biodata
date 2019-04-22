package gov.acwi.wqp.etl.monitoringLocation;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataMonitoringLocation;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.postgis.PGgeometry;

public class MonitoringLocationProcessorTest {

	public static final String TEST_AGENCY_CD = "USGS";
	public static final String TEST_SITE_NO = "04178000";
	public static final String TEST_COUNTRY_CD = "US";
	public static final String TEST_STATE_CD = "39";
	public static final String TEST_COUNTY_CD = "039";
	public static final Integer TEST_STATION_ID = 61339982;
	public static final String TEST_SITE_ID ="USGS-04178000";
	public static final String TEST_ORGANIZATION ="USGS-OH";
	public static final String TEST_SITE_TYPE ="Stream";
	public static final String TEST_HUC ="041000030505";
	public static final String TEST_GOVERNMENTAL_UNIT_CODE ="US:39:039";
	public static final String TEST_STATION_NAME ="St Joseph River near Newville IN";
	public static final String TEST_ORGANIZATION_NAME ="USGS Ohio Water Science Center";
	public static final String TEST_STATION_TYPE_NAME ="Stream";
	public static final String TEST_LATITUDE = "41.3856061";
	public static final String TEST_LONGITUDE = "-84.8016259";
	public static final BigDecimal TEST_DECIMAL_LATITUDE = new BigDecimal(TEST_LATITUDE);
	public static final BigDecimal TEST_DECIMAL_LONGITUDE = new BigDecimal(TEST_LONGITUDE);
	public static final PGgeometry TEST_GEOM = MonitoringLocation.calculateGeom(TEST_DECIMAL_LATITUDE,TEST_DECIMAL_LONGITUDE,4269);
	public static final String TEST_GEOPOSITIONING_METHOD ="Interpolated from MAP.";
	public static final String TEST_HDATUM_ID_CODE ="NAD83";
	public static final String TEST_ELEVATION_VALUE ="795.4";
	public static final String TEST_ELEVATION_UNIT ="feet";
	public static final String TEST_ELEVATION_METHOD ="Unknown.";
	public static final String TEST_VDATUM_ID_CODE ="NGVD29";
	public static final String TEST_DRAIN_AREA_VALUE = "610";
	public static final BigDecimal TEST_DECIMAL_DRAIN_AREA_VALUE = new BigDecimal("610");
	public static final String TEST_DRAIN_AREA_UNIT ="sq mi";
	public static final String TEST_GEOPOSITION_ACCY_VALUE ="1";
	public static final String TEST_GEOPOSITION_ACCY_UNIT ="seconds";
	public static final String TEST_VERTICAL_ACCURACY_VALUE =".01";
	public static final String TEST_VERTICAL_ACCURACY_UNIT ="feet";
	public static final String TEST_MAP_SCALE="testMapScale";
	public static final BigDecimal TEST_CONTRIB_DRAIN_AREA_VALUE = new BigDecimal("2");
	public static final String TEST_CONTRIB_DRAIN_AREA_UNIT = "testContribDrainAreaUnit";
	public static final String TEST_NAT_AQFR_NAME = "testNatAqfrName";
	public static final String TEST_AQFR_NAME = "testAqfrName";
	public static final String TEST_AQFR_TYPE_NAME = "testAqfrTypeName";
	public static final String TEST_CONSTRUCTION_DATE = "testConstructionDate";
	public static final BigDecimal TEST_WELL_DEPTH_VALUE = new BigDecimal("3");
	public static final String TEST_WELL_DEPTH_UNIT = "testWellDepthUnit";
	public static final BigDecimal TEST_HOLE_DEPTH_VALUE = new BigDecimal("4");
	public static final String TEST_HOLE_DEPTH_UNIT = "testHoleDepthUnit";
	
	private BiodataMonitoringLocation biodataML;
	private MonitoringLocationProcessor mlProcessor;
	
	@Before
	public void setUp() {
		biodataML = new BiodataMonitoringLocation();
		mlProcessor = new MonitoringLocationProcessor();
		
		biodataML.setBiodataSiteId(TEST_STATION_ID);
		biodataML.setNwisSiteId(TEST_SITE_ID);
		biodataML.setAgencyCd(TEST_AGENCY_CD);
		biodataML.setSiteNo(TEST_SITE_NO);
		biodataML.setOrganization(TEST_ORGANIZATION);
		biodataML.setSiteTypeLongName(TEST_SITE_TYPE);
		biodataML.setHucCd(TEST_HUC);
		biodataML.setGovernmentalUnitCode(TEST_GOVERNMENTAL_UNIT_CODE);
		biodataML.setCountryCd(TEST_COUNTRY_CD);
		biodataML.setStateCd(TEST_STATE_CD);
		biodataML.setCountyCd(TEST_COUNTY_CD);
		biodataML.setGeoPoint(TEST_GEOM);
		biodataML.setStationNm(TEST_STATION_NAME);
		biodataML.setStationTypeName(TEST_STATION_TYPE_NAME);
		biodataML.setOrganizationName(TEST_ORGANIZATION_NAME);
		biodataML.setDecLatitude(TEST_DECIMAL_LATITUDE);
		biodataML.setDecLongitude(TEST_DECIMAL_LONGITUDE);
		biodataML.setGeopositioningMethod(TEST_GEOPOSITIONING_METHOD);
		biodataML.setCoordDatumCd(TEST_HDATUM_ID_CODE);
		biodataML.setElevationValue(TEST_ELEVATION_VALUE);
		biodataML.setElevationUnit(TEST_ELEVATION_UNIT);
		biodataML.setElevationMethod(TEST_ELEVATION_METHOD);
		biodataML.setAltDatumCd(TEST_VDATUM_ID_CODE);
		biodataML.setAltitude(TEST_ELEVATION_VALUE);
		biodataML.setVdatumIdCode(TEST_VDATUM_ID_CODE);
		biodataML.setDrainAreaVa(TEST_DECIMAL_DRAIN_AREA_VALUE);
		biodataML.setBiodataDrainAreaVa(TEST_DRAIN_AREA_VALUE);
		biodataML.setDrainAreaUnit(TEST_DRAIN_AREA_UNIT);
		biodataML.setGeopositionAccyValue(TEST_GEOPOSITION_ACCY_VALUE);
		biodataML.setGeopositionAccyUnit(TEST_GEOPOSITION_ACCY_UNIT);
		biodataML.setVerticalAccuracyValue(TEST_VERTICAL_ACCURACY_VALUE);
		biodataML.setVerticalAccuracyUnit(TEST_VERTICAL_ACCURACY_UNIT);
		biodataML.setMapScale(TEST_MAP_SCALE);
		biodataML.setContribDrainAreaValue(TEST_CONTRIB_DRAIN_AREA_VALUE);
		biodataML.setContribDrainAreaUnit(TEST_CONTRIB_DRAIN_AREA_UNIT);
		biodataML.setNatAqfrName(TEST_NAT_AQFR_NAME);
		biodataML.setAqfrName(TEST_AQFR_NAME);
		biodataML.setAqfrTypeName(TEST_AQFR_TYPE_NAME);
		biodataML.setConstructionDate(TEST_CONSTRUCTION_DATE);
		biodataML.setWellDepthValue(TEST_WELL_DEPTH_VALUE);
		biodataML.setWellDepthUnit(TEST_WELL_DEPTH_UNIT);
		biodataML.setHoleDepthValue(TEST_HOLE_DEPTH_VALUE);
		biodataML.setHoleDepthUnit(TEST_HOLE_DEPTH_UNIT);
	}
	
	@Test 
	public void testProcess() throws Exception {
		MonitoringLocation actual = mlProcessor.process(biodataML);
		
		assertEquals(Application.DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(Application.DATA_SOURCE, actual.getDataSource());
		assertEquals(TEST_STATION_ID, actual.getStationId());
		assertEquals(TEST_ORGANIZATION, actual.getOrganization());
		assertEquals(TEST_ORGANIZATION_NAME, actual.getOrganizationName());
		assertEquals(TEST_STATION_TYPE_NAME, actual.getStationTypeName());
		assertEquals(TEST_DECIMAL_LATITUDE, actual.getLatitude());
		assertEquals(TEST_DECIMAL_LONGITUDE, actual.getLongitude());
		assertEquals(TEST_GEOPOSITIONING_METHOD, actual.getGeopositioningMethod());
		assertEquals(TEST_GEOPOSITION_ACCY_UNIT, actual.getGeopositionAccyUnit());
		assertEquals(TEST_GEOPOSITION_ACCY_VALUE, actual.getGeopositionAccyValue());
		assertEquals(TEST_HDATUM_ID_CODE, actual.getHdatumIdCode());
		assertEquals(TEST_HUC, actual.getHuc());
		assertEquals(TEST_SITE_TYPE, actual.getSiteType());
		assertEquals(TEST_STATION_NAME, actual.getStationName());
		assertEquals(TEST_VERTICAL_ACCURACY_UNIT, actual.getVerticalAccuracyUnit());
		assertEquals(TEST_VERTICAL_ACCURACY_VALUE, actual.getVerticalAccuracyValue());
		assertEquals(TEST_DECIMAL_DRAIN_AREA_VALUE, actual.getDrainAreaValue());
		assertEquals(TEST_GEOM, actual.getGeom());
		assertEquals(TEST_ELEVATION_METHOD, actual.getElevationMethod());
		assertEquals(TEST_SITE_ID, actual.getSiteId());
		assertEquals(TEST_GOVERNMENTAL_UNIT_CODE, actual.getGovernmentalUnitCode());
		assertEquals(TEST_ELEVATION_UNIT, actual.getElevationUnit());
		assertEquals(TEST_ELEVATION_VALUE, actual.getElevationValue());
		assertEquals(TEST_VDATUM_ID_CODE, actual.getVdatumIdCode());
		assertEquals(TEST_DRAIN_AREA_UNIT, actual.getDrainAreaUnit());
		assertEquals(TEST_MAP_SCALE, actual.getMapScale());
		assertEquals(TEST_CONTRIB_DRAIN_AREA_VALUE, actual.getContribDrainAreaValue());
		assertEquals(TEST_CONTRIB_DRAIN_AREA_UNIT, actual.getContribDrainAreaUnit());
		assertEquals(TEST_NAT_AQFR_NAME, actual.getNatAqfrName());
		assertEquals(TEST_AQFR_NAME, actual.getAqfrName());
		assertEquals(TEST_AQFR_TYPE_NAME, actual.getAqfrTypeName());
		assertEquals(TEST_CONSTRUCTION_DATE, actual.getConstructionDate());
		assertEquals(TEST_WELL_DEPTH_VALUE, actual.getWellDepthValue());
		assertEquals(TEST_WELL_DEPTH_UNIT, actual.getWellDepthUnit());
		assertEquals(TEST_HOLE_DEPTH_VALUE, actual.getHoleDepthValue());
		assertEquals(TEST_HOLE_DEPTH_UNIT, actual.getHoleDepthUnit());
	}

	@Test
	public void testProcessWithNullValues() throws Exception {
		
		biodataML.setNwisSiteId(null);
		biodataML.setGovernmentalUnitCode(null);
		biodataML.setElevationUnit(null);
		biodataML.setElevationValue(null);
		biodataML.setVdatumIdCode(null);
		biodataML.setDrainAreaUnit(null);
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		
		assertEquals(TEST_SITE_ID, actual.getSiteId());
		assertEquals(TEST_GOVERNMENTAL_UNIT_CODE, actual.getGovernmentalUnitCode());
		assertEquals(TEST_ELEVATION_UNIT, actual.getElevationUnit());
		assertEquals(TEST_ELEVATION_VALUE, actual.getElevationValue());
		assertEquals(TEST_VDATUM_ID_CODE, actual.getVdatumIdCode());
		assertEquals(TEST_DRAIN_AREA_UNIT, actual.getDrainAreaUnit());
	}
	
	@Test
	public void testElevationUnitIfAltDatumCdIsNull() throws Exception {
		biodataML.setElevationUnit(null);
		biodataML.setAltDatumCd(null);
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		assertNull(actual.getElevationUnit());
	}
	
	@Test
	public void testElevationUnitIfAltitudeIsNull() throws Exception {
		biodataML.setElevationUnit(null);
		biodataML.setAltitude(null);
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		assertNull(actual.getElevationUnit());
	}
	
	@Test
	public void testElevationValueIfAltDatumCdIsNull() throws Exception {
		biodataML.setElevationValue(null);
		biodataML.setAltDatumCd(null);
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		assertNull(actual.getElevationValue());
	}
	
	@Test
	public void testElevationValueIfAltitudeIsDecimal() throws Exception {
		biodataML.setElevationValue(null);
		biodataML.setAltitude(".");
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		assertEquals(MonitoringLocationProcessor.DEFAULT_ELEVATION_VALUE, actual.getElevationValue());
	}
	
	@Test
	public void testVdatumIdCodeIfAltitudeIsNull() throws Exception {
		biodataML.setVdatumIdCode(null);
		biodataML.setAltitude(null);
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		assertNull(actual.getVdatumIdCode());
	}
	
	@Test
	public void testDrainAreaUnitIfBiodataDrainAreaVaIsNull() throws Exception {
		biodataML.setDrainAreaUnit(null);
		biodataML.setBiodataDrainAreaVa(null);
		
		MonitoringLocation actual = mlProcessor.process(biodataML);
		assertNull(actual.getDrainAreaUnit());
	}
}
