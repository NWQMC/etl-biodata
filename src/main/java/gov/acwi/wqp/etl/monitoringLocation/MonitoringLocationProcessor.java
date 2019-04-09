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
		
		// Set Site Id
		monitoringLocation.setSiteId(
				biodataStation.getNwisSiteId() == null
					? String.join("-", biodataStation.getAgencyCd(), biodataStation.getSiteNo())
					: biodataStation.getNwisSiteId());
		
		// Set Governmental Unit Code
		monitoringLocation.setGovernmentalUnitCode(
				biodataStation.getGovernmentalUnitCode() == null 
					? String.join(":",biodataStation.getCountryCd(), biodataStation.getStateCd(), biodataStation.getCountyCd())
					: biodataStation.getGovernmentalUnitCode());
		
		// Set Elevation Unit
		if (biodataStation.getElevationUnit() == null) {
			monitoringLocation.setElevationUnit(
					biodataStation.getAltDatumCd() != null && biodataStation.getAltitude() != null
						? "feet"
						: null);
		} else {
			monitoringLocation.setElevationUnit(biodataStation.getElevationUnit());
		}
		
		// Set Elevation Value
		if (biodataStation.getElevationValue() == null) {
			if (biodataStation.getAltDatumCd() != null) {
				monitoringLocation.setElevationValue(
						biodataStation.getAltitude() == "."
							? "0"
							: biodataStation.getAltitude().trim()
				);
			} else {
				monitoringLocation.setElevationValue(null);
			}
		} else {
			monitoringLocation.setElevationValue(biodataStation.getElevationValue());
		}
		
		// Set VdatumIdCode
		String vDatumIdCode = biodataStation.getVdatumIdCode();
		if (vDatumIdCode == null) {
			vDatumIdCode = biodataStation.getAltitude() != null ? biodataStation.getAltDatumCd() : null;
		} 
		monitoringLocation.setVdatumIdCode(vDatumIdCode);
		
		// Set Drain Area Unit
		String drainAreaUnit = biodataStation.getDrainAreaUnit();
		if (drainAreaUnit == null) {
			drainAreaUnit = biodataStation.getBiodataDrainAreaVa() != null ? "sq mi" : null;
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
