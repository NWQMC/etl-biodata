package gov.acwi.wqp.etl.monitoringLocation;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataMonitoringLocation;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitoringLocationProcessor implements ItemProcessor<BiodataMonitoringLocation, MonitoringLocation>{
	
	private static final Logger LOG = LoggerFactory.getLogger(MonitoringLocation.class);

	public static final String DEFAULT_ELEVATION_UNIT = "feet";
	public static final String DEFAULT_ELEVATION_VALUE = "0";
	public static final String DEFAULT_DRAIN_AREA_UNIT = "sq mi";

	@Override
	public MonitoringLocation process(BiodataMonitoringLocation biodataML) throws Exception {
		MonitoringLocation monitoringLocation = new MonitoringLocation();

		monitoringLocation.setDataSourceId(Application.DATA_SOURCE_ID);
		monitoringLocation.setDataSource(Application.DATA_SOURCE);
		monitoringLocation.setStationId(biodataML.getBiodataSiteId());
		monitoringLocation.setOrganization(biodataML.getOrganization());
		monitoringLocation.setOrganizationName(biodataML.getOrganizationName());
		monitoringLocation.setStationTypeName(biodataML.getStationTypeName());
		monitoringLocation.setLatitude(biodataML.getDecLatitude());
		monitoringLocation.setLongitude(biodataML.getDecLongitude());
		monitoringLocation.setGeopositioningMethod(biodataML.getGeopositioningMethod());
		monitoringLocation.setGeopositionAccyUnit(biodataML.getGeopositionAccyUnit());
		monitoringLocation.setGeopositionAccyValue(biodataML.getGeopositionAccyValue());
		monitoringLocation.setHdatumIdCode(biodataML.getCoordDatumCd());
		monitoringLocation.setHuc(biodataML.getHucCd());
		monitoringLocation.setSiteType(biodataML.getSiteTypeLongName());
		monitoringLocation.setStationName(biodataML.getStationNm());
		monitoringLocation.setVerticalAccuracyUnit(biodataML.getVerticalAccuracyUnit());
		monitoringLocation.setVerticalAccuracyValue(biodataML.getVerticalAccuracyValue());
		monitoringLocation.setDrainAreaValue(biodataML.getDrainAreaVa());
		monitoringLocation.setGeom(biodataML.getGeoPoint());
		monitoringLocation.setElevationMethod(biodataML.getElevationMethod());
		monitoringLocation.setMapScale(biodataML.getMapScale());
		monitoringLocation.setContribDrainAreaValue(biodataML.getContribDrainAreaValue());
		monitoringLocation.setContribDrainAreaUnit(biodataML.getContribDrainAreaUnit());
		monitoringLocation.setNatAqfrName(biodataML.getNatAqfrName());
		monitoringLocation.setAqfrName(biodataML.getAqfrName());
		monitoringLocation.setAqfrTypeName(biodataML.getAqfrTypeName());
		monitoringLocation.setConstructionDate(biodataML.getConstructionDate());
		monitoringLocation.setWellDepthValue(biodataML.getWellDepthValue());
		monitoringLocation.setWellDepthUnit(biodataML.getWellDepthUnit());
		monitoringLocation.setHoleDepthValue(biodataML.getHoleDepthValue());
		monitoringLocation.setHoleDepthUnit(biodataML.getHoleDepthUnit());
		
		monitoringLocation.setSiteId(
				processSiteId(
						biodataML.getNwisSiteId(),
						biodataML.getAgencyCd(),
						biodataML.getSiteNo()));
		
		monitoringLocation.setGovernmentalUnitCode(
				processGovernmentUnitCode(
						biodataML.getGovernmentalUnitCode(), 
						biodataML.getCountryCd(), 
						biodataML.getStateCd(), 
						biodataML.getCountyCd()));

		monitoringLocation.setElevationUnit(
				processElevationUnit(
						biodataML.getElevationUnit(),
						biodataML.getAltDatumCd(),
						biodataML.getAltitude()));
		
		monitoringLocation.setElevationValue(
				processElevationValue(
						biodataML.getElevationValue(), 
						biodataML.getAltDatumCd(), 
						biodataML.getAltitude()));
		
		monitoringLocation.setVdatumIdCode(
				processVdatumIdCode(
						biodataML.getVdatumIdCode(),
						biodataML.getAltitude(),
						biodataML.getAltDatumCd()));
		
		monitoringLocation.setDrainAreaUnit(
				processDrainAreaUnit(
						biodataML.getDrainAreaUnit(),
						biodataML.getBiodataDrainAreaVa()));

		return monitoringLocation;
	}
	
	public String processSiteId(String nwisSiteId, String agencyCd, String siteNo) {
		String siteId = nwisSiteId == null
				? String.join("-", agencyCd, siteNo)
				: nwisSiteId;
		return siteId;
	}
	
	public String processGovernmentUnitCode(String governmentUnitCode, String countryCode, String stateCode, String countyCode) {
		String governmentUnitCodeML = governmentUnitCode == null 
				? String.join(":", countryCode, stateCode, countyCode)
				: governmentUnitCode;
		return governmentUnitCodeML;
	}
	
	public String processElevationUnit(String elevationUnit, String altDatumCd, String altitude) {
		String elevationUnitML = elevationUnit;
		if (elevationUnitML == null && altDatumCd != null && altitude != null) {
			elevationUnitML = DEFAULT_ELEVATION_UNIT;
		}
		return elevationUnitML;
	}
	
	public String processElevationValue(String elevationValue, String altDatumCd, String altitude) {
		String elevationValueML = elevationValue;
		if (elevationValueML == null && altDatumCd != null) {
			elevationValueML = ".".equals(altitude) 
					? DEFAULT_ELEVATION_VALUE 
					: altitude.trim();
		}
		return elevationValueML;
	}
	
	public String processVdatumIdCode(String vDatumIdCode, String altitude, String altDatumCd) {
		String vDatumIdCodeML = vDatumIdCode;
		if (vDatumIdCodeML == null && altitude != null) {
			vDatumIdCodeML = altDatumCd;
		}
		return vDatumIdCodeML;
	}
	
	public String processDrainAreaUnit(String drainAreaUnit, String biodataDrainAreaVa) {
		String drainAreaUnitML = drainAreaUnit;
		if (drainAreaUnitML == null && biodataDrainAreaVa != null) {
			drainAreaUnitML = DEFAULT_DRAIN_AREA_UNIT;
		}
		return drainAreaUnitML;
	}
}
