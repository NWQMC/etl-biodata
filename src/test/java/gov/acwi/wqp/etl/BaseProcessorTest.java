package gov.acwi.wqp.etl;

import gov.acwi.wqp.etl.monitoringLocation.MonitoringLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Before;
import org.postgis.PGgeometry;

public abstract class BaseProcessorTest {

	protected static final Integer TEST_DATA_SOURCE_ID = 4;
	protected static final String TEST_DATA_SOURCE = "BIODATA";
	
	protected static final String TEST_ORGANIZATION = "USGS-OH";
	protected static final Integer TEST_ORGANIZATION_ID = 1;
	protected static final String TEST_ORGANIZATION_NAME_1 = "USGS-OH";
	
	protected static final String TEST_AGENCY_CD = "USGS";
	protected static final String TEST_SITE_NO = "04178000";
	protected static final String TEST_COUNTRY_CD = "US";
	protected static final String TEST_STATE_CD = "39";
	protected static final String TEST_COUNTY_CD = "039";
	protected static final Integer TEST_STATION_ID = 61339982;
	protected static final String TEST_SITE_ID ="USGS-04178000";
	protected static final String TEST_SITE_TYPE ="Stream";
	protected static final String TEST_HUC ="041000030505";
	protected static final String TEST_GOVERNMENTAL_UNIT_CODE ="US:39:039";
	protected static final String TEST_STATION_NAME ="St Joseph River near Newville IN";
	protected static final String TEST_ORGANIZATION_NAME_2 ="USGS Ohio Water Science Center";
	protected static final String TEST_STATION_TYPE_NAME ="Stream";
	private static final String TEST_LATITUDE = "41.3856061";
	private static final String TEST_LONGITUDE = "-84.8016259";
	protected static final BigDecimal TEST_DECIMAL_LATITUDE = new BigDecimal(TEST_LATITUDE);
	protected static final BigDecimal TEST_DECIMAL_LONGITUDE = new BigDecimal(TEST_LONGITUDE);
	protected static final PGgeometry TEST_GEOM =
			MonitoringLocation.calculateGeom(
					TEST_DECIMAL_LATITUDE,
					TEST_DECIMAL_LONGITUDE,
					4269);
	protected static final String TEST_GEOPOSITIONING_METHOD ="Interpolated from MAP.";
	protected static final String TEST_HDATUM_ID_CODE ="NAD83";
	protected static final String TEST_ELEVATION_VALUE ="795.4";
	protected static final String TEST_ELEVATION_UNIT ="feet";
	protected static final String TEST_ELEVATION_METHOD ="Unknown.";
	protected static final String TEST_VDATUM_ID_CODE ="NGVD29";
	protected static final String TEST_DRAIN_AREA_VALUE = "610";
	protected static final BigDecimal TEST_DECIMAL_DRAIN_AREA_VALUE = new BigDecimal("610");
	protected static final String TEST_GEOPOSITION_ACCY_VALUE ="1";
	protected static final String TEST_GEOPOSITION_ACCY_UNIT ="seconds";
	protected static final String TEST_VERTICAL_ACCURACY_VALUE =".01";
	protected static final String TEST_MAP_SCALE="testMapScale";
	protected static final BigDecimal TEST_CONTRIB_DRAIN_AREA_VALUE = new BigDecimal("2");
	protected static final String TEST_CONTRIB_DRAIN_AREA_UNIT = "testContribDrainAreaUnit";
	protected static final String TEST_NAT_AQFR_NAME = "testNatAqfrName";
	protected static final String TEST_AQFR_NAME = "testAqfrName";
	protected static final String TEST_AQFR_TYPE_NAME = "testAqfrTypeName";
	protected static final String TEST_CONSTRUCTION_DATE = "testConstructionDate";
	protected static final BigDecimal TEST_WELL_DEPTH_VALUE = new BigDecimal("3");
	protected static final String TEST_WELL_DEPTH_UNIT = "testWellDepthUnit";
	protected static final BigDecimal TEST_HOLE_DEPTH_VALUE = new BigDecimal("4");
	protected static final String TEST_HOLE_DEPTH_UNIT = "testHoleDepthUnit";
	
	protected static final LocalDateTime TEST_SAMPLE_COLLECTION_START_TIME = LocalDateTime.parse("1996-07-22T13:00:00");
	protected static final LocalDate TEST_EVENT_DATE_LOCAL_DATE = TEST_SAMPLE_COLLECTION_START_TIME.toLocalDate();
	protected static final String TEST_SAMPLE_COLLECTION_START_TIME_LOCAL_TIME = TEST_SAMPLE_COLLECTION_START_TIME
			.toLocalTime()
			.format(Application.TIME_FORMATTER);
	
	protected static final String TEST_ACTIVITY = "BDB-000031046-Towed-P1&2";
	protected static final String TEST_SIDNO = "BDB-000031046";
	protected static final Integer TEST_ACTIVITY_ID = 268023;
	protected static final String TEST_SAMPLE_DATA_SOURCE = "test sample data source";
	protected static final String TEST_PROJECT_ID = "LERI BioTDB";
	protected static final String TEST_PROJECT_NAME = "test project name";
	protected static final String TEST_ACTIVITY_COMMENT = "Test comment, what a great activity!";
	protected static final Integer TEST_ACTIVITY_REACH_LENGTH = 300;
	protected static final String TEST_ACTIVITY_REACH_LENGTH_STRING = "300";
	protected static final String TEST_EFFORT_PASS_2 = "test effort pass 2";
	protected static final String TEST_SAMPLE_COLLECT_METHOD_ID = "Towed-P1&2";
	protected static final String TEST_SAMPLE_COLLECT_METHOD_CTX = "NAWQA Fish  Towed-P1&2";
	protected static final String TEST_SAMPLE_COLLECT_METHOD_NAME = "NAWQA Fish ";
	protected static final String TEST_ACTIVITY_SAMPLE_COLLECT_METHOD_DESCRIPTION = "https://www.nemi.gov/methods/method_summary/12237/";
	protected static final String TEST_SAMPLE_TIME_DATUM = "CDT - Central Daylight Time";
	protected static final String TEST_SAMPLE_GEAR_USED = "Towed barge - sample";
	protected static final Integer TEST_DW_SAMPLE_TYPE_ID_7 = 7;
	protected static final String TEST_EFFORT_SUBREACH = "A-B";

	protected static final Integer TEST_DW_PROJECT_ID = 28;
	protected static final String TEST_PROJECT_ABSTRACT = "This is a test project abstract.  It is mapped to the projectData description.";

	protected static final Integer TEST_DW_EFFORT_ID = 268873;
	protected static final String TEST_PUBLISHED_TAXON_NAME = "Catostomus commersonii";
	protected static final Integer TEST_RAW_COUNT = 2;
	protected static final Integer TEST_WEIGHT = 50;
	protected static final Integer TEST_TOTAL_LENGTH = 250;
	protected static final Integer TEST_STANDARD_LENGTH	= 175;
	protected static final Integer TEST_FIELD_SHEET_PAGE = 3;
	protected static final Integer TEST_FIELD_SHEET_LINE = 4;
	protected static final String TEST_BIODATA_TAXON_NAME = "Campostoma anomalum";
	protected static final String TEST_CHARACTERISTIC = "Length, Total (Fish)";
	protected static final Integer TEST_RESULT_VALUE	= 250;



	protected ConfigurationService configurationService;
	
	@Before
	public void setup() {
		configurationService = new ConfigurationService();
		configurationService.setEtlDataSourceId(TEST_DATA_SOURCE_ID);
		configurationService.setEtlDataSource(TEST_DATA_SOURCE);
	}
}
