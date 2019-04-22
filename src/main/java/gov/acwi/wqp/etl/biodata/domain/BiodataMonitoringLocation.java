package gov.acwi.wqp.etl.biodata.domain;

import java.math.BigDecimal;
import org.postgis.PGgeometry;

public class BiodataMonitoringLocation {
	
	private Integer biodataSiteId;
	private String nwisSiteId;
	private String agencyCd;
	private String siteNo;
	private String organization;
	private String siteTypeLongName;
	private String hucCd;
	private String governmentalUnitCode;
	private String countryCd;
	private String stateCd;
	private String countyCd;
	private PGgeometry geoPoint;
	private String stationNm;
	private String stationTypeName;
	private String organizationName;
	private BigDecimal decLatitude;
	private BigDecimal decLongitude;
	private String mapScale;
	private String geopositioningMethod;
	private String coordDatumCd;
	private String elevationValue;
	private String elevationUnit;
	private String elevationMethod;
	private String altDatumCd;
	private String altitude;
	private String vdatumIdCode;
	private BigDecimal drainAreaVa;
	private String biodataDrainAreaVa;
	private String drainAreaUnit;
	private BigDecimal contribDrainAreaValue;
	private String contribDrainAreaUnit;
	private String geopositionAccyValue;
	private String geopositionAccyUnit;
	private String verticalAccuracyValue;
	private String verticalAccuracyUnit;
	private String natAqfrName;
	private String aqfrName;
	private String aqfrTypeName;
	private String constructionDate;
	private BigDecimal wellDepthValue;
	private String wellDepthUnit;
	private BigDecimal holeDepthValue;
	private String holeDepthUnit;

	public Integer getBiodataSiteId() {
		return biodataSiteId;
	}

	public void setBiodataSiteId(Integer biodataSiteId) {
		this.biodataSiteId = biodataSiteId;
	}

	public String getNwisSiteId() {
		return nwisSiteId;
	}

	public void setNwisSiteId(String nwisSiteId) {
		this.nwisSiteId = nwisSiteId;
	}

	public String getAgencyCd() {
		return agencyCd;
	}

	public void setAgencyCd(String agencyCd) {
		this.agencyCd = agencyCd;
	}

	public String getSiteNo() {
		return siteNo;
	}

	public void setSiteNo(String siteNo) {
		this.siteNo = siteNo;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSiteTypeLongName() {
		return siteTypeLongName;
	}

	public void setSiteTypeLongName(String siteTypeLongName) {
		this.siteTypeLongName = siteTypeLongName;
	}

	public String getHucCd() {
		return hucCd;
	}

	public void setHucCd(String hucCd) {
		this.hucCd = hucCd;
	}

	public String getGovernmentalUnitCode() {
		return governmentalUnitCode;
	}

	public void setGovernmentalUnitCode(String governmentalUnitCode) {
		this.governmentalUnitCode = governmentalUnitCode;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getCountyCd() {
		return countyCd;
	}

	public void setCountyCd(String countyCd) {
		this.countyCd = countyCd;
	}

	public PGgeometry getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(PGgeometry geoPoint) {
		this.geoPoint = geoPoint;
	}

	public String getStationNm() {
		return stationNm;
	}

	public void setStationNm(String stationNm) {
		this.stationNm = stationNm;
	}

	public String getStationTypeName() {
		return stationTypeName;
	}

	public void setStationTypeName(String stationTypeName) {
		this.stationTypeName = stationTypeName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public BigDecimal getDecLatitude() {
		return decLatitude;
	}

	public void setDecLatitude(BigDecimal decLatitude) {
		this.decLatitude = decLatitude;
	}

	public BigDecimal getDecLongitude() {
		return decLongitude;
	}

	public void setDecLongitude(BigDecimal decLongitude) {
		this.decLongitude = decLongitude;
	}

	public String getMapScale() {
		return mapScale;
	}

	public void setMapScale(String mapScale) {
		this.mapScale = mapScale;
	}

	public String getGeopositioningMethod() {
		return geopositioningMethod;
	}

	public void setGeopositioningMethod(String geopositioningMethod) {
		this.geopositioningMethod = geopositioningMethod;
	}

	public String getCoordDatumCd() {
		return coordDatumCd;
	}

	public void setCoordDatumCd(String coordDatumCd) {
		this.coordDatumCd = coordDatumCd;
	}

	public String getElevationValue() {
		return elevationValue;
	}

	public void setElevationValue(String elevationValue) {
		this.elevationValue = elevationValue;
	}

	public String getElevationUnit() {
		return elevationUnit;
	}

	public void setElevationUnit(String elevationUnit) {
		this.elevationUnit = elevationUnit;
	}

	public String getElevationMethod() {
		return elevationMethod;
	}

	public void setElevationMethod(String elevationMethod) {
		this.elevationMethod = elevationMethod;
	}

	public String getAltDatumCd() {
		return altDatumCd;
	}

	public void setAltDatumCd(String altDatumCd) {
		this.altDatumCd = altDatumCd;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getVdatumIdCode() {
		return vdatumIdCode;
	}

	public void setVdatumIdCode(String vdatumIdCode) {
		this.vdatumIdCode = vdatumIdCode;
	}

	public BigDecimal getDrainAreaVa() {
		return drainAreaVa;
	}

	public void setDrainAreaVa(BigDecimal drainAreaVa) {
		this.drainAreaVa = drainAreaVa;
	}

	public String getBiodataDrainAreaVa() {
		return biodataDrainAreaVa;
	}

	public void setBiodataDrainAreaVa(String biodataDrainAreaVa) {
		this.biodataDrainAreaVa = biodataDrainAreaVa;
	}

	public String getDrainAreaUnit() {
		return drainAreaUnit;
	}

	public void setDrainAreaUnit(String drainAreaUnit) {
		this.drainAreaUnit = drainAreaUnit;
	}

	public BigDecimal getContribDrainAreaValue() {
		return contribDrainAreaValue;
	}

	public void setContribDrainAreaValue(BigDecimal contribDrainAreaValue) {
		this.contribDrainAreaValue = contribDrainAreaValue;
	}

	public String getContribDrainAreaUnit() {
		return contribDrainAreaUnit;
	}

	public void setContribDrainAreaUnit(String contribDrainAreaUnit) {
		this.contribDrainAreaUnit = contribDrainAreaUnit;
	}

	public String getGeopositionAccyValue() {
		return geopositionAccyValue;
	}

	public void setGeopositionAccyValue(String geopositionAccyValue) {
		this.geopositionAccyValue = geopositionAccyValue;
	}

	public String getGeopositionAccyUnit() {
		return geopositionAccyUnit;
	}

	public void setGeopositionAccyUnit(String geopositionAccyUnit) {
		this.geopositionAccyUnit = geopositionAccyUnit;
	}

	public String getVerticalAccuracyValue() {
		return verticalAccuracyValue;
	}

	public void setVerticalAccuracyValue(String verticalAccuracyValue) {
		this.verticalAccuracyValue = verticalAccuracyValue;
	}

	public String getVerticalAccuracyUnit() {
		return verticalAccuracyUnit;
	}

	public void setVerticalAccuracyUnit(String verticalAccuracyUnit) {
		this.verticalAccuracyUnit = verticalAccuracyUnit;
	}

	public String getNatAqfrName() {
		return natAqfrName;
	}

	public void setNatAqfrName(String natAqfrName) {
		this.natAqfrName = natAqfrName;
	}

	public String getAqfrName() {
		return aqfrName;
	}

	public void setAqfrName(String aqfrName) {
		this.aqfrName = aqfrName;
	}

	public String getAqfrTypeName() {
		return aqfrTypeName;
	}

	public void setAqfrTypeName(String aqfrTypeName) {
		this.aqfrTypeName = aqfrTypeName;
	}

	public String getConstructionDate() {
		return constructionDate;
	}

	public void setConstructionDate(String constructionDate) {
		this.constructionDate = constructionDate;
	}

	public BigDecimal getWellDepthValue() {
		return wellDepthValue;
	}

	public void setWellDepthValue(BigDecimal wellDepthValue) {
		this.wellDepthValue = wellDepthValue;
	}

	public String getWellDepthUnit() {
		return wellDepthUnit;
	}

	public void setWellDepthUnit(String wellDepthUnit) {
		this.wellDepthUnit = wellDepthUnit;
	}

	public BigDecimal getHoleDepthValue() {
		return holeDepthValue;
	}

	public void setHoleDepthValue(BigDecimal holeDepthValue) {
		this.holeDepthValue = holeDepthValue;
	}

	public String getHoleDepthUnit() {
		return holeDepthUnit;
	}

	public void setHoleDepthUnit(String holeDepthUnit) {
		this.holeDepthUnit = holeDepthUnit;
	}

	
}
