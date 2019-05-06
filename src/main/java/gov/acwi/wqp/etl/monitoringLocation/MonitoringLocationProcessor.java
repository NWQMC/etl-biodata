package gov.acwi.wqp.etl.monitoringLocation;


import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.monitoringLocation.BiodataMonitoringLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitoringLocationProcessor implements ItemProcessor<BiodataMonitoringLocation, MonitoringLocation>{
	
	private final ConfigurationService configurationService;
	
	@Autowired
	public MonitoringLocationProcessor(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	protected static final String DEFAULT_ELEVATION_UNIT = "feet";
	protected static final String DEFAULT_ELEVATION_VALUE = "0";
	protected static final String DEFAULT_DRAIN_AREA_UNIT = "sq mi";

	@Override
	public MonitoringLocation process(BiodataMonitoringLocation biodataML) {
		MonitoringLocation monitoringLocation = new MonitoringLocation();

		monitoringLocation.setDataSourceId(configurationService.getEtlDataSourceId());
		monitoringLocation.setDataSource(configurationService.getEtlDataSource());
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
				getSiteId(
						biodataML.getNwisSiteId(),
						biodataML.getAgencyCd(),
						biodataML.getSiteNo()));
		
		monitoringLocation.setGovernmentalUnitCode(
				getGovernmentUnitCode(
						biodataML.getGovernmentalUnitCode(), 
						biodataML.getCountryCd(), 
						biodataML.getStateCd(), 
						biodataML.getCountyCd()));

		monitoringLocation.setElevationUnit(
				getElevationUnit(
						biodataML.getElevationUnit(),
						biodataML.getAltDatumCd(),
						biodataML.getAltitude()));
		
		monitoringLocation.setElevationValue(
				getElevationValue(
						biodataML.getElevationValue(), 
						biodataML.getAltDatumCd(), 
						biodataML.getAltitude()));
		
		monitoringLocation.setVdatumIdCode(
				getVdatumIdCode(
						biodataML.getVdatumIdCode(),
						biodataML.getAltitude(),
						biodataML.getAltDatumCd()));
		
		monitoringLocation.setDrainAreaUnit(
				getDrainAreaUnit(
						biodataML.getDrainAreaUnit(),
						biodataML.getBiodataDrainAreaVa()));

		return monitoringLocation;
	}
	
	private String getSiteId(String nwisSiteId, String agencyCd, String siteNo) {
		return nwisSiteId == null
				? String.join("-", agencyCd, siteNo)
				: nwisSiteId;
	}
	
	private String getGovernmentUnitCode(String governmentUnitCode, String countryCode, String stateCode, String countyCode) {
		return governmentUnitCode == null
				? String.join(":", countryCode, stateCode, countyCode)
				: governmentUnitCode;
	}
	
	private String getElevationUnit(String elevationUnit, String altDatumCd, String altitude) {
		String elevationUnitML = elevationUnit;
		if (elevationUnitML == null && altDatumCd != null && altitude != null) {
			elevationUnitML = DEFAULT_ELEVATION_UNIT;
		}
		return elevationUnitML;
	}
	
	private String getElevationValue(String elevationValue, String altDatumCd, String altitude) {
		String elevationValueML = elevationValue;
		if (elevationValueML == null && altDatumCd != null) {
			elevationValueML = ".".equals(altitude) 
					? DEFAULT_ELEVATION_VALUE 
					: altitude.trim();
		}
		return elevationValueML;
	}
	
	private String getVdatumIdCode(String vDatumIdCode, String altitude, String altDatumCd) {
		String vDatumIdCodeML = vDatumIdCode;
		if (vDatumIdCodeML == null && altitude != null) {
			vDatumIdCodeML = altDatumCd;
		}
		return vDatumIdCodeML;
	}
	
	private String getDrainAreaUnit(String drainAreaUnit, String biodataDrainAreaVa) {
		String drainAreaUnitML = drainAreaUnit;
		if (drainAreaUnitML == null && biodataDrainAreaVa != null) {
			drainAreaUnitML = DEFAULT_DRAIN_AREA_UNIT;
		}
		return drainAreaUnitML;
	}
}
