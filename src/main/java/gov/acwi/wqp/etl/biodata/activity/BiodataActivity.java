package gov.acwi.wqp.etl.biodata.activity;

import java.time.LocalDateTime;
import org.postgis.PGgeometry;

public class BiodataActivity {
	
	private Integer stationId;
	private String siteId;
	private LocalDateTime eventDate;
	private String activity;
	private String organization;
	private String siteType;
	private String huc;
	private String governmentalUnitCode;
	private PGgeometry geom;
	private String organizationName;
	private Integer activityId;
	private String sampleDataSource;
	private LocalDateTime sampleCollectionStartTime; 
	private String projectId;
	private String activityComment;
	private Integer activityReachLength;
	private String effortPass;
	private String sampleCollectMethodId;
	private String sampleCollectMethodCtx;
	private String sampleCollectMethodName;
	private String activitySampleCollectMethodDescription;
	private String sampleTimeDatum;
	private String effortGear;
	private String sampleGearUsed;
	private Integer dwSampleTypeId;
	private String effortSubreach;

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getHuc() {
		return huc;
	}

	public void setHuc(String huc) {
		this.huc = huc;
	}

	public String getGovernmentalUnitCode() {
		return governmentalUnitCode;
	}

	public void setGovernmentalUnitCode(String governmentalUnitCode) {
		this.governmentalUnitCode = governmentalUnitCode;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getSampleDataSource() {
		return sampleDataSource;
	}

	public void setSampleDataSource(String sampleDataSource) {
		this.sampleDataSource = sampleDataSource;
	}

	public LocalDateTime getSampleCollectionStartTime() {
		return sampleCollectionStartTime;
	}

	public void setSampleCollectionStartTime(LocalDateTime sampleCollectionStartTime) {
		this.sampleCollectionStartTime = sampleCollectionStartTime;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getActivityComment() {
		return activityComment;
	}

	public void setActivityComment(String activityComment) {
		this.activityComment = activityComment;
	}

	public Integer getActivityReachLength() {
		return activityReachLength;
	}

	public void setActivityReachLength(Integer activityReachLength) {
		this.activityReachLength = activityReachLength;
	}

	public String getEffortPass() {
		return effortPass;
	}

	public void setEffortPass(String effortPass) {
		this.effortPass = effortPass;
	}

	public String getSampleCollectMethodId() {
		return sampleCollectMethodId;
	}

	public void setSampleCollectMethodId(String sampleCollectMethodId) {
		this.sampleCollectMethodId = sampleCollectMethodId;
	}

	public String getSampleCollectMethodCtx() {
		return sampleCollectMethodCtx;
	}

	public void setSampleCollectMethodCtx(String sampleCollectMethodCtx) {
		this.sampleCollectMethodCtx = sampleCollectMethodCtx;
	}

	public String getSampleCollectMethodName() {
		return sampleCollectMethodName;
	}

	public void setSampleCollectMethodName(String sampleCollectMethodName) {
		this.sampleCollectMethodName = sampleCollectMethodName;
	}

	public String getActivitySampleCollectMethodDescription() {
		return activitySampleCollectMethodDescription;
	}

	public void setActivitySampleCollectMethodDescription(String activitySampleCollectMethodDescription) {
		this.activitySampleCollectMethodDescription = activitySampleCollectMethodDescription;
	}

	public String getSampleTimeDatum() {
		return sampleTimeDatum;
	}

	public void setSampleTimeDatum(String sampleTimeDatum) {
		this.sampleTimeDatum = sampleTimeDatum;
	}

	public String getEffortGear() {
		return effortGear;
	}

	public void setEffortGear(String effortGear) {
		this.effortGear = effortGear;
	}

	public String getSampleGearUsed() {
		return sampleGearUsed;
	}

	public void setSampleGearUsed(String sampleGearUsed) {
		this.sampleGearUsed = sampleGearUsed;
	}

	public Integer getDwSampleTypeId() {
		return dwSampleTypeId;
	}

	public void setDwSampleTypeId(Integer dwSampleTypeId) {
		this.dwSampleTypeId = dwSampleTypeId;
	}

	public String getEffortSubreach() {
		return effortSubreach;
	}

	public void setEffortSubreach(String effortSubreach) {
		this.effortSubreach = effortSubreach;
	}
}

