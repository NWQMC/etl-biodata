package gov.acwi.wqp.etl.biodata.activity;

import java.time.LocalDateTime;

public class BiodataActivity {
	
	private Integer stationId;
	private String siteId;
	private LocalDateTime eventDate;
	private String activity;
	private String sampleMedia; // 'Biological'
	private String organization;
	private String siteType;
	private String huc;
	private String governmentalUnitCode;
	private String organizationName;
	private Integer activityId;
	private String activityTypeCode; // 'Field Msr/Obs'
	private LocalDateTime activityStartTime;
	private String activityStartTimeZone;
	private String projectId;
	private String activityComment;
	private String assemblageSampledName; // 'Fish/Nekton'
	private Integer activityReachLength;
	private String activityReachLengthUnit;
	private Integer activityPassCount;
	private String sampleCollectMethodId;
	private String sampleCollectMethodCtx;
	private String sampleCollectMethodName;
	private String activitySampleCollectMethodDescription;
	private String sampleCollectEquipmentName;
	private String activitySampleCollectEquipmentComments;

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

	public String getSampleMedia() {
		return sampleMedia;
	}

	public void setSampleMedia(String sampleMedia) {
		this.sampleMedia = sampleMedia;
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

	public String getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public LocalDateTime getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(LocalDateTime activityStartTime) {
		this.activityStartTime = activityStartTime;
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

	public String getAssemblageSampledName() {
		return assemblageSampledName;
	}

	public void setAssemblageSampledName(String assemblageSampledName) {
		this.assemblageSampledName = assemblageSampledName;
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
