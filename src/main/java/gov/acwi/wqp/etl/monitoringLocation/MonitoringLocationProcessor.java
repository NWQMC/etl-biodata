package gov.acwi.wqp.etl.monitoringLocation;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.biodata.domain.BiodataStation;

public class MonitoringLocationProcessor implements ItemProcessor<BiodataStation, MonitoringLocation>{

	public static final String DEFAULT_SITE_TYPE = "Not Assigned";

	@Override
	public MonitoringLocation process(BiodataStation biodataStation) throws Exception {
		MonitoringLocation monitoringLocation = new MonitoringLocation();

		monitoringLocation.setDataSourceId(Application.DATA_SOURCE_ID);
		monitoringLocation.setDataSource(Application.DATA_SOURCE);
		monitoringLocation.setStationId(biodataStation.getBiodataSiteId());
		monitoringLocation.setOrganization(biodataStation.getOrganization());
		monitoringLocation.setOrganizationName(biodataStation.getOrganizationName());
		monitoringLocation.setStationTypeName(biodataStation.getStationTypeName());
		monitoringLocation.setLatitude(getBigDecimal(biodataStation.getDecLatitude()));
		monitoringLocation.setLongitude(getBigDecimal(biodataStation.getDecLongitude()));
		monitoringLocation.setGeopositioningMethod(biodataStation.getGeopositioningMethod());
		monitoringLocation.setGeopositionAccyUnit(biodataStation.getGeopositionAccyUnit());
		monitoringLocation.setGeopositionAccyValue(biodataStation.getGeopositionAccyValue());
		monitoringLocation.setHdatumIdCode(biodataStation.getCoordDatumCd());
		monitoringLocation.setHuc(biodataStation.getHucCd());
		monitoringLocation.setSiteType(biodataStation.getSiteTypeLongName());
		monitoringLocation.setStationName(biodataStation.getStationNm());
		monitoringLocation.setVerticalAccuracyUnit(biodataStation.getVerticalAccuracyUnit());
		monitoringLocation.setVerticalAccuracyValue(biodataStation.getVerticalAccuracyValue());
		monitoringLocation.setDrainAreaValue(getBigDecimal(biodataStation.getDrainAreaVa()));
		monitoringLocation.setGeom(biodataStation.getGeoPoint());
		monitoringLocation.setElevationMethod(biodataStation.getElevationMethod());
		monitoringLocation.setElevationUnit(biodataStation.getElevationUnit());
		monitoringLocation.setElevationValue(biodataStation.getElevationValue());
		monitoringLocation.setVdatumIdCode(biodataStation.getVdatumIdCode());
		monitoringLocation.setDrainAreaUnit(biodataStation.getDrainAreaUnit());
		
		monitoringLocation.setSiteId(biodataStation.getNwisSiteId() == null
				? String.join("-", biodataStation.getAgencyCd(), biodataStation.getSiteNo())
				: biodataStation.getNwisSiteId());
		
		monitoringLocation.setGovernmentalUnitCode(biodataStation.getGovernmentalUnitCode() == null 
				? String.join(":",biodataStation.getCountryCd(), biodataStation.getStateCd(), biodataStation.getCountyCd())
				: biodataStation.getGovernmentalUnitCode());
		
		if (monitoringLocation.getElevationUnit() == null) {
			monitoringLocation.setElevationUnit(biodataStation.getAltDatumCd() != null && biodataStation.getAltitude() != null
					? "feet"
					: null);
		}
		
		if (monitoringLocation.getElevationValue() == null) {
			if (biodataStation.getAltDatumCd() != null) {
				monitoringLocation.setElevationValue(biodataStation.getAltitude().equals(".") 
						? "0" 
						: biodataStation.getAltitude().trim());
			}
		}
		
		if (monitoringLocation.getVdatumIdCode() == null) {
			monitoringLocation.setVdatumIdCode(biodataStation.getAltitude() != null 
					? biodataStation.getAltDatumCd() 
					: null);
		} 
		
		if (monitoringLocation.getDrainAreaUnit() == null) {
			monitoringLocation.setDrainAreaUnit(biodataStation.getBiodataDrainAreaVa() != null 
					? "sq mi" 
					: null);
		}

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
