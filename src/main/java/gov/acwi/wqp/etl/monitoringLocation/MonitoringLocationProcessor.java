package gov.acwi.wqp.etl.monitoringLocation;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataMonitoringLocation;

public class MonitoringLocationProcessor implements ItemProcessor<BiodataMonitoringLocation, MonitoringLocation>{

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
		monitoringLocation.setLatitude(getBigDecimal(biodataML.getDecLatitude()));
		monitoringLocation.setLongitude(getBigDecimal(biodataML.getDecLongitude()));
		monitoringLocation.setGeopositioningMethod(biodataML.getGeopositioningMethod());
		monitoringLocation.setGeopositionAccyUnit(biodataML.getGeopositionAccyUnit());
		monitoringLocation.setGeopositionAccyValue(biodataML.getGeopositionAccyValue());
		monitoringLocation.setHdatumIdCode(biodataML.getCoordDatumCd());
		monitoringLocation.setHuc(biodataML.getHucCd());
		monitoringLocation.setSiteType(biodataML.getSiteTypeLongName());
		monitoringLocation.setStationName(biodataML.getStationNm());
		monitoringLocation.setVerticalAccuracyUnit(biodataML.getVerticalAccuracyUnit());
		monitoringLocation.setVerticalAccuracyValue(biodataML.getVerticalAccuracyValue());
		monitoringLocation.setDrainAreaValue(getBigDecimal(biodataML.getDrainAreaVa()));
		monitoringLocation.setGeom(biodataML.getGeoPoint());
		monitoringLocation.setElevationMethod(biodataML.getElevationMethod());
		
		monitoringLocation.setSiteId(biodataML.getNwisSiteId() == null
				? String.join("-", biodataML.getAgencyCd(), biodataML.getSiteNo())
				: biodataML.getNwisSiteId());
		
		monitoringLocation.setGovernmentalUnitCode(biodataML.getGovernmentalUnitCode() == null 
				? String.join(":",biodataML.getCountryCd(), biodataML.getStateCd(), biodataML.getCountyCd())
				: biodataML.getGovernmentalUnitCode());
		
		String elevationUnit = biodataML.getElevationUnit();
		if (elevationUnit == null) {
			monitoringLocation.setElevationUnit(biodataML.getAltDatumCd() != null && biodataML.getAltitude() != null
					? DEFAULT_ELEVATION_UNIT
					: null);
		}
		monitoringLocation.setElevationUnit(elevationUnit);
		
		String elevationValue = biodataML.getElevationValue();
		if (elevationValue == null) {
			if (biodataML.getAltDatumCd() != null) {
				elevationValue = biodataML.getAltitude().equals(".") 
						? DEFAULT_ELEVATION_VALUE 
						: biodataML.getAltitude().trim();
			}
		}
		monitoringLocation.setElevationValue(elevationValue);
		
		String vDatumIdCode = biodataML.getVdatumIdCode();
		if (vDatumIdCode == null) {
			vDatumIdCode = biodataML.getAltitude() != null 
					? biodataML.getAltDatumCd() 
					: null;
		} 
		monitoringLocation.setVdatumIdCode(vDatumIdCode);
		
		String drainAreaUnit = biodataML.getDrainAreaUnit();
		if (drainAreaUnit == null) {
			drainAreaUnit = biodataML.getBiodataDrainAreaVa() != null 
					? DEFAULT_DRAIN_AREA_UNIT 
					: null;
		}
		monitoringLocation.setDrainAreaUnit(drainAreaUnit);

		return monitoringLocation;
	}

	private BigDecimal getBigDecimal(String string) {
		if (NumberUtils.isCreatable(string)) {
			return NumberUtils.createBigDecimal(string);
		} else {
			return null;
		}
	}

}
