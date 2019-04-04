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
//		monitoringLocation.setStationId(biodataStation.getStationId());
//		monitoringLocation.setSiteId(String.join("-", biodataStation.getOrganization(), biodataStation.getMonitoringLocationIdentifier()));
		monitoringLocation.setOrganization(biodataStation.getOrganization());
//		monitoringLocation.setSiteType(biodataStation.getResolvedMonitoringLocationTypeName() == null ? DEFAULT_SITE_TYPE : biodataStation.getResolvedMonitoringLocationTypeName());
//		monitoringLocation.setHuc(biodataStation.getHucTwelveDigitCode() == null ? biodataStation.getHucEightDigitCode() : biodataStation.getHucTwelveDigitCode());
//		monitoringLocation.setGovernmentalUnitCode(String.join(":", biodataStation.getCountryCode(), biodataStation.getStateCode(), biodataStation.getCountyCode()));
//		monitoringLocation.setStationName(biodataStation.getMonitoringLocationName());
		monitoringLocation.setOrganizationName(biodataStation.getOrganizationName());
//		monitoringLocation.setDescriptionText(biodataStation.getMonitoringLocationDescriptionText());
//		monitoringLocation.setStationTypeName(biodataStation.getMonitoringLocationTypeName());
//		monitoringLocation.setLatitude(getBigDecimal(biodataStation.getLatitude()));
//		monitoringLocation.setLongitude(getBigDecimal(biodataStation.getLongitude()));
		monitoringLocation.setGeopositioningMethod(biodataStation.getGeopositioningMethod());
//		monitoringLocation.setHdatumIdCode(biodataStation.getHdatumIdCode());
//		monitoringLocation.setDrainAreaValue(getBigDecimal(biodataStation.getDrainAreaValue()));
		monitoringLocation.setDrainAreaUnit(biodataStation.getDrainAreaUnit());
		monitoringLocation.calculateGeom(
				monitoringLocation.getLatitude(),
				monitoringLocation.getLongitude(),
				MonitoringLocation.DEFAULT_SRID);
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
