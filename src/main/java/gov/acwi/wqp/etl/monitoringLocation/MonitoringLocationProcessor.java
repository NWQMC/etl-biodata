package gov.acwi.wqp.etl.monitoringLocation;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;
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
		
		if (biodataML.getElevationUnit() == null) {
			monitoringLocation.setElevationUnit(biodataML.getAltDatumCd() != null && biodataML.getAltitude() != null
					? DEFAULT_ELEVATION_UNIT
					: null);
		} else {
			monitoringLocation.setElevationUnit(biodataML.getElevationUnit());
		}
		
		if (biodataML.getElevationValue() == null) {
			if (biodataML.getAltDatumCd() != null) {
				monitoringLocation.setElevationValue(biodataML.getAltitude().equals(".") 
						? DEFAULT_ELEVATION_VALUE 
						: biodataML.getAltitude().trim());
			} else {
				monitoringLocation.setElevationValue(null);
			}
		} else {
			monitoringLocation.setElevationValue(biodataML.getElevationValue());
		}
		
		if (biodataML.getVdatumIdCode() == null) {
			monitoringLocation.setVdatumIdCode(biodataML.getAltitude() != null 
					? biodataML.getAltDatumCd() 
					: null);
		} else {
			monitoringLocation.setVdatumIdCode(biodataML.getVdatumIdCode());
		}
		
		if (biodataML.getDrainAreaUnit() == null) {
			monitoringLocation.setDrainAreaUnit(biodataML.getBiodataDrainAreaVa() != null 
					? DEFAULT_DRAIN_AREA_UNIT 
					: null);
		} else {
			monitoringLocation.setDrainAreaUnit(biodataML.getDrainAreaUnit());
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
	
	public static PGgeometry calculateGeom(BigDecimal latitude, BigDecimal longitude, int srid) {
		Point point = null;
		try {
			point = new Point(longitude.doubleValue(), latitude.doubleValue());
			point.setSrid(srid);
			LOG.debug("Converted: from lat:{};long{} - to lat:{};long{}", latitude, longitude, point.getY(), point.getX());
		} catch (Throwable e) {
			LOG.info("Unable to determine point from coordinates:{}-{}", latitude, longitude);
		}
		return new PGgeometry(point);
	}
}
