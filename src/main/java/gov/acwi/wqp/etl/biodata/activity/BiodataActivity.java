package gov.acwi.wqp.etl.biodata.activity;

import java.time.LocalDateTime;

public class BiodataActivity {
	
	private Integer stationId;
	private String siteId;
	private LocalDateTime eventDate;
	private String sidno;
	private String methodCode;
	private String organization;
	private String siteType;
	private String huc;
	private String governmentalUnitCode;
	private String organizationName;
	private Integer activityId;
	private String sampleDataSource;
	private LocalDateTime sampleCollectionStartTime; // data type is timestamp without time zone in the pg database 
	private String activityStartTimeZone;
	private String projectId;
	private String activityComment;
	private Integer activityReachLength;
	private String activityReachLengthUnit;
	private Integer activityPassCount;
	private String sampleCollectMethodId;
	private String sampleCollectMethodCtx;
	private String sampleCollectMethodName;
	private String activitySampleCollectMethodDescription;
	private String sampleCollectEquipmentName;
	private String activitySampleCollectEquipmentComments;

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
	public String getSidno() {
		return sidno;
	}
	public void setSidno(String sidno) {
		this.sidno = sidno;
	}
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
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

	public String getActivityStartTimeZone() {
		return activityStartTimeZone;
	}

	public void setActivityStartTimeZone(String activityStartTimeZone) {
		this.activityStartTimeZone = activityStartTimeZone;
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

	public String getActivityReachLengthUnit() {
		return activityReachLengthUnit;
	}

	public void setActivityReachLengthUnit(String activityReachLengthUnit) {
		this.activityReachLengthUnit = activityReachLengthUnit;
	}

	public Integer getActivityPassCount() {
		return activityPassCount;
	}

	public void setActivityPassCount(Integer activityPassCount) {
		this.activityPassCount = activityPassCount;
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

	public String getSampleCollectEquipmentName() {
		return sampleCollectEquipmentName;
	}

	public void setSampleCollectEquipmentName(String sampleCollectEquipmentName) {
		this.sampleCollectEquipmentName = sampleCollectEquipmentName;
	}

	public String getActivitySampleCollectEquipmentComments() {
		return activitySampleCollectEquipmentComments;
	}

	public void setActivitySampleCollectEquipmentComments(String activitySampleCollectEquipmentComments) {
		this.activitySampleCollectEquipmentComments = activitySampleCollectEquipmentComments;
	}
}

