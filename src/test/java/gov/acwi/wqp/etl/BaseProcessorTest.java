package gov.acwi.wqp.etl;

import gov.acwi.wqp.etl.monitoringLocation.MonitoringLocation;
import java.math.BigDecimal;
import org.postgis.PGgeometry;

public abstract class BaseProcessorTest {
	
	public static final String TEST_ORGANIZATION = "USGS-OH";
	public static final Integer TEST_ORGANIZATION_ID = 1;
	public static final String TEST_ORGANIZATION_NAME_1 = "USGS-OH";
	
	public static final String TEST_AGENCY_CD = "USGS";
	public static final String TEST_SITE_NO = "04178000";
	public static final String TEST_COUNTRY_CD = "US";
	public static final String TEST_STATE_CD = "39";
	public static final String TEST_COUNTY_CD = "039";
	public static final Integer TEST_STATION_ID = 61339982;
	public static final String TEST_SITE_ID ="USGS-04178000";
	public static final String TEST_SITE_TYPE ="Stream";
	public static final String TEST_HUC ="041000030505";
	public static final String TEST_GOVERNMENTAL_UNIT_CODE ="US:39:039";
	public static final String TEST_STATION_NAME ="St Joseph River near Newville IN";
	public static final String TEST_ORGANIZATION_NAME_2 ="USGS Ohio Water Science Center";
	public static final String TEST_STATION_TYPE_NAME ="Stream";
	public static final String TEST_LATITUDE = "41.3856061";
	public static final String TEST_LONGITUDE = "-84.8016259";
	public static final BigDecimal TEST_DECIMAL_LATITUDE = new BigDecimal(TEST_LATITUDE);
	public static final BigDecimal TEST_DECIMAL_LONGITUDE = new BigDecimal(TEST_LONGITUDE);
	public static final PGgeometry TEST_GEOM = 
			MonitoringLocation.calculateGeom(
					TEST_DECIMAL_LATITUDE,
					TEST_DECIMAL_LONGITUDE,
					4269);
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
}
