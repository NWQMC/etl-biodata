package gov.acwi.wqp.etl.monitoringLocation;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.monitoringLocation.BiodataMonitoringLocation;
import org.junit.Before;
import org.junit.Test;

import static gov.acwi.wqp.etl.monitoringLocation.MonitoringLocationProcessor.DEFAULT_DRAIN_AREA_UNIT;
import static gov.acwi.wqp.etl.monitoringLocation.MonitoringLocationProcessor.DEFAULT_ELEVATION_UNIT;
import static gov.acwi.wqp.etl.monitoringLocation.MonitoringLocationProcessor.DEFAULT_ELEVATION_VALUE;
import static org.junit.Assert.*;

public class MonitoringLocationProcessorTest extends BaseProcessorTest {

	private BiodataMonitoringLocation biodataMonitoringLocation;
	private MonitoringLocationProcessor processor;
	
	@Before
	public void setupTestClass() {
		biodataMonitoringLocation = new BiodataMonitoringLocation();
		processor = new MonitoringLocationProcessor(configurationService);
		
		biodataMonitoringLocation.setBiodataSiteId(TEST_STATION_ID);
		biodataMonitoringLocation.setNwisSiteId(TEST_SITE_ID);
		biodataMonitoringLocation.setAgencyCd(TEST_AGENCY_CD);
		biodataMonitoringLocation.setSiteNo(TEST_SITE_NO);
		biodataMonitoringLocation.setOrganization(TEST_ORGANIZATION);
		biodataMonitoringLocation.setSiteTypeLongName(TEST_SITE_TYPE);
		biodataMonitoringLocation.setHucCd(TEST_HUC);
		biodataMonitoringLocation.setGovernmentalUnitCode(TEST_GOVERNMENTAL_UNIT_CODE);
		biodataMonitoringLocation.setCountryCd(TEST_COUNTRY_CD);
		biodataMonitoringLocation.setStateCd(TEST_STATE_CD);
		biodataMonitoringLocation.setCountyCd(TEST_COUNTY_CD);
		biodataMonitoringLocation.setGeoPoint(TEST_GEOM);
		biodataMonitoringLocation.setStationNm(TEST_STATION_NAME);
		biodataMonitoringLocation.setStationTypeName(TEST_STATION_TYPE_NAME);
		biodataMonitoringLocation.setOrganizationName(TEST_ORGANIZATION_NAME_2);
		biodataMonitoringLocation.setDecLatitude(TEST_DECIMAL_LATITUDE);
		biodataMonitoringLocation.setDecLongitude(TEST_DECIMAL_LONGITUDE);
		biodataMonitoringLocation.setGeopositioningMethod(TEST_GEOPOSITIONING_METHOD);
		biodataMonitoringLocation.setCoordDatumCd(TEST_HDATUM_ID_CODE);
		biodataMonitoringLocation.setElevationValue(TEST_ELEVATION_VALUE);
		biodataMonitoringLocation.setElevationUnit(TEST_ELEVATION_UNIT);
		biodataMonitoringLocation.setElevationMethod(TEST_ELEVATION_METHOD);
		biodataMonitoringLocation.setAltDatumCd(TEST_VDATUM_ID_CODE);
		biodataMonitoringLocation.setAltitude(TEST_ELEVATION_VALUE);
		biodataMonitoringLocation.setVdatumIdCode(TEST_VDATUM_ID_CODE);
		biodataMonitoringLocation.setDrainAreaVa(TEST_DECIMAL_DRAIN_AREA_VALUE);
		biodataMonitoringLocation.setBiodataDrainAreaVa(TEST_DRAIN_AREA_VALUE);
		biodataMonitoringLocation.setDrainAreaUnit(DEFAULT_DRAIN_AREA_UNIT);
		biodataMonitoringLocation.setGeopositionAccyValue(TEST_GEOPOSITION_ACCY_VALUE);
		biodataMonitoringLocation.setGeopositionAccyUnit(TEST_GEOPOSITION_ACCY_UNIT);
		biodataMonitoringLocation.setVerticalAccuracyValue(TEST_VERTICAL_ACCURACY_VALUE);
		biodataMonitoringLocation.setVerticalAccuracyUnit(DEFAULT_ELEVATION_UNIT);
		biodataMonitoringLocation.setMapScale(TEST_MAP_SCALE);
		biodataMonitoringLocation.setContribDrainAreaValue(TEST_CONTRIB_DRAIN_AREA_VALUE);
		biodataMonitoringLocation.setContribDrainAreaUnit(TEST_CONTRIB_DRAIN_AREA_UNIT);
		biodataMonitoringLocation.setNatAqfrName(TEST_NAT_AQFR_NAME);
		biodataMonitoringLocation.setAqfrName(TEST_AQFR_NAME);
		biodataMonitoringLocation.setAqfrTypeName(TEST_AQFR_TYPE_NAME);
		biodataMonitoringLocation.setConstructionDate(TEST_CONSTRUCTION_DATE);
		biodataMonitoringLocation.setWellDepthValue(TEST_WELL_DEPTH_VALUE);
		biodataMonitoringLocation.setWellDepthUnit(TEST_WELL_DEPTH_UNIT);
		biodataMonitoringLocation.setHoleDepthValue(TEST_HOLE_DEPTH_VALUE);
		biodataMonitoringLocation.setHoleDepthUnit(TEST_HOLE_DEPTH_UNIT);
	}
	
	@Test 
	public void testProcess() throws Exception {
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		
		assertEquals(TEST_DATA_SOURCE_ID, actual.getDataSourceId());
		assertEquals(TEST_DATA_SOURCE, actual.getDataSource());
		assertEquals(TEST_STATION_ID, actual.getStationId());
		assertEquals(TEST_ORGANIZATION, actual.getOrganization());
		assertEquals(TEST_ORGANIZATION_NAME_2, actual.getOrganizationName());
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
		assertEquals(DEFAULT_ELEVATION_UNIT, actual.getVerticalAccuracyUnit());
		assertEquals(TEST_VERTICAL_ACCURACY_VALUE, actual.getVerticalAccuracyValue());
		assertEquals(TEST_DECIMAL_DRAIN_AREA_VALUE, actual.getDrainAreaValue());
		assertEquals(TEST_GEOM, actual.getGeom());
		assertEquals(TEST_ELEVATION_METHOD, actual.getElevationMethod());
		assertEquals(TEST_SITE_ID, actual.getSiteId());
		assertEquals(TEST_GOVERNMENTAL_UNIT_CODE, actual.getGovernmentalUnitCode());
		assertEquals(TEST_ELEVATION_UNIT, actual.getElevationUnit());
		assertEquals(TEST_ELEVATION_VALUE, actual.getElevationValue());
		assertEquals(TEST_VDATUM_ID_CODE, actual.getVdatumIdCode());
		assertEquals(DEFAULT_DRAIN_AREA_UNIT, actual.getDrainAreaUnit());
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
		
		biodataMonitoringLocation.setNwisSiteId(null);
		biodataMonitoringLocation.setGovernmentalUnitCode(null);
		biodataMonitoringLocation.setElevationUnit(null);
		biodataMonitoringLocation.setElevationValue(null);
		biodataMonitoringLocation.setVdatumIdCode(null);
		biodataMonitoringLocation.setDrainAreaUnit(null);
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		
		assertEquals(TEST_SITE_ID, actual.getSiteId());
		assertEquals(TEST_GOVERNMENTAL_UNIT_CODE, actual.getGovernmentalUnitCode());
		assertEquals(TEST_ELEVATION_UNIT, actual.getElevationUnit());
		assertEquals(TEST_ELEVATION_VALUE, actual.getElevationValue());
		assertEquals(TEST_VDATUM_ID_CODE, actual.getVdatumIdCode());
		assertEquals(DEFAULT_DRAIN_AREA_UNIT, actual.getDrainAreaUnit());
	}
	
	@Test
	public void testElevationUnitIfAltDatumCdIsNull() throws Exception {
		biodataMonitoringLocation.setElevationUnit(null);
		biodataMonitoringLocation.setAltDatumCd(null);
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		assertNull(actual.getElevationUnit());
	}
	
	@Test
	public void testElevationUnitIfAltitudeIsNull() throws Exception {
		biodataMonitoringLocation.setElevationUnit(null);
		biodataMonitoringLocation.setAltitude(null);
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		assertNull(actual.getElevationUnit());
	}
	
	@Test
	public void testElevationValueIfAltDatumCdIsNull() throws Exception {
		biodataMonitoringLocation.setElevationValue(null);
		biodataMonitoringLocation.setAltDatumCd(null);
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		assertNull(actual.getElevationValue());
	}
	
	@Test
	public void testElevationValueIfAltitudeIsDecimal() throws Exception {
		biodataMonitoringLocation.setElevationValue(null);
		biodataMonitoringLocation.setAltitude(".");
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		assertEquals(DEFAULT_ELEVATION_VALUE, actual.getElevationValue());
	}
	
	@Test
	public void testVdatumIdCodeIfAltitudeIsNull() throws Exception {
		biodataMonitoringLocation.setVdatumIdCode(null);
		biodataMonitoringLocation.setAltitude(null);
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		assertNull(actual.getVdatumIdCode());
	}
	
	@Test
	public void testDrainAreaUnitIfBiodataDrainAreaVaIsNull() throws Exception {
		biodataMonitoringLocation.setDrainAreaUnit(null);
		biodataMonitoringLocation.setBiodataDrainAreaVa(null);
		
		MonitoringLocation actual = processor.process(biodataMonitoringLocation);
		assertNull(actual.getDrainAreaUnit());
	}
}
