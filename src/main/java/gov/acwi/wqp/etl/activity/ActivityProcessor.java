package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityProcessor implements ItemProcessor<BiodataActivity, Activity>{
	
	private final ConfigurationService configurationService;
	
	public static final String DEFAULT_SAMPLE_MEDIA = "Biological";
	public static final String DEFAULT_ACTIVITY_TYPE_CODE = "Field Msr/Obs";
	public static final String DEFAULT_ASSEMBLAGE_SAMPLED_NAME = "Fish/Nekton";
	public static final String SAMPLE_DATA_SOURCE_BIOTB = "BioTDB";
	public static final String EFFORT_PASS_1_2_COMBINED = "Pass 1 & 2 combined";
	public static final Integer EFFORT_PASS_1 = 1;
	public static final Integer EFFORT_PASS_2 = 2;
	public static final String BACKPACK = "backpack";
	public static final String BACKPACK_ELECTROSHOCK = "Backpack Electroshock";
	public static final String TOWED_BARGE = "towed barge";
	public static final String ELECTROSHOCK_OTHER = "Electroshock (Other)";
	public static final String BOAT = "boat";
	public static final String BOAT_MOUNTED_ELECTROSHOCK = "Boat-Mounted Electroshock";
	public static final String MINNOW_SEINE = "minnow seine";
	public static final String MINNOW_SEINE_NET = "Minnow Seine Net";
	public static final String BAG_SEINE = "bag seine";
	public static final String SEINE_NET = "Seine Net";
	public static final String BEACH_SEINE = "beach seine";
	public static final String BEACH_SEINE_NET = "Beach Seine Net";
	public static final String SNORKELING = "snorkeling";
	public static final String VISUAL_SIGHTING = "Visual Sighting";
	public static final String DEFAULT_REACH_LENGTH_UNIT = "m";
	public static final Integer DEFAULT_DW_SAMPLE_TYPE_ID = 16;
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // originall hh24:mi:ss
	
	@Autowired
	public ActivityProcessor(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@Override
	public Activity process(BiodataActivity bdActivity) throws Exception {
		Activity activity = new Activity();
		
		String gear = bdActivity.getEffortGear() == null
				? bdActivity.getSampleGearUsed()
				: bdActivity.getEffortGear();
		
		activity.setDataSourceId(configurationService.getEtlDataSourceId());
		activity.setDataSource(configurationService.getEtlDataSource());
		
		activity.setSampleMedia(DEFAULT_SAMPLE_MEDIA);
		activity.setActivityTypeCode(DEFAULT_ACTIVITY_TYPE_CODE);
		activity.setAssemblageSampledName(DEFAULT_ASSEMBLAGE_SAMPLED_NAME);

		activity.setStationId(bdActivity.getStationId());
		activity.setSiteId(bdActivity.getSiteId());
		activity.setEventDate(bdActivity.getEventDate().toLocalDate());
		activity.setActivity(bdActivity.getActivity());
		activity.setOrganization(bdActivity.getOrganization());
		activity.setSiteType(bdActivity.getSiteType());
		activity.setHuc(bdActivity.getHuc());
		activity.setGovernmentalUnitCode(bdActivity.getGovernmentalUnitCode());
		activity.setGeom(bdActivity.getGeom());
		activity.setOrganizationName(bdActivity.getOrganizationName());
		activity.setActivityId(bdActivity.getActivityId());
		
		activity.setActivityStartTime(
				processActivityStartTime(
						bdActivity.getSampleDataSource(), 
						bdActivity.getSampleCollectionStartTime()));
		
		activity.setActStartTimeZone(
				processActivityStartTimeZone(
						bdActivity.getSampleDataSource(),
						bdActivity.getSampleTimeDatum()));
		
		activity.setProjectId(bdActivity.getProjectId());
		activity.setActivityComment(bdActivity.getActivityComment());
		activity.setActivityReachLength(bdActivity.getActivityReachLength());
		
		activity.setActivityReachLengthUnit(
				processActivityReachLengthUnit(
						bdActivity.getActivityReachLength()));
		
		activity.setActivityPassCount(
				processActivityPassCount(
						bdActivity.getEffortPass()));
		
		activity.setSampleCollectMethodId(bdActivity.getSampleCollectMethodId());
		activity.setSampleCollectMethodCtx(bdActivity.getSampleCollectMethodCtx());
		activity.setSampleCollectMethodName(bdActivity.getSampleCollectMethodName());
		activity.setActivitySampleCollectMethodDescription(
				bdActivity.getActivitySampleCollectMethodDescription());
		
		activity.setSampleCollectEquipName(
				processSampleCollectEquipName(gear));
		
		activity.setActivitySampleCollectEquipmentComments(
				processActivitySampleCollectEquipmentComments(
						gear,
						bdActivity.getDwSampleTypeId(),
						bdActivity.getEffortSubreach(),
						bdActivity.getEffortPass()));

		return activity;
	}

	public String processActivityStartTime(String sampleDataSource, LocalDateTime sampleCollectionStart) {
		String activityStartTime = SAMPLE_DATA_SOURCE_BIOTB.equals(sampleDataSource) 
				? null
				: dateFormatter.format(sampleCollectionStart);
		return activityStartTime;
	}
	
	public String processActivityStartTimeZone(String sampleDataSource, String sampleTimeDatum) {
		String activityStartTimeZone = SAMPLE_DATA_SOURCE_BIOTB.equals(sampleDataSource) 
				? null
				: sampleTimeDatum;
		return activityStartTimeZone;
	}
	
	public String processActivityReachLengthUnit(Integer activityReachLength) {
		String activityReachLengthUnit = null == activityReachLength
				? null
				: DEFAULT_REACH_LENGTH_UNIT;
		return activityReachLengthUnit;
	}
	
	public Integer processActivityPassCount(String effortPass) {
		Integer activityPassCount = EFFORT_PASS_1_2_COMBINED.equals(effortPass)
				? EFFORT_PASS_2
				: EFFORT_PASS_1;
		return activityPassCount;
	}
	
	public String processSampleCollectEquipName(String gear) {
		switch (gear.toLowerCase()) {
			case BACKPACK : return BACKPACK_ELECTROSHOCK;
			case TOWED_BARGE : return ELECTROSHOCK_OTHER;
			case BOAT : return BOAT_MOUNTED_ELECTROSHOCK;
			case MINNOW_SEINE : return MINNOW_SEINE_NET;
			case BAG_SEINE : return SEINE_NET;
			case BEACH_SEINE : return BEACH_SEINE_NET;
			case SNORKELING : return VISUAL_SIGHTING;
		default: return null;
		}
	}
	
	public String processActivitySampleCollectEquipmentComments(String comments, Integer dwSampleTypeId, String effortSubreach, String effortPass) {
		StringBuilder equipmentComments = new StringBuilder(comments);
		if (DEFAULT_DW_SAMPLE_TYPE_ID.equals(dwSampleTypeId)) {
			if (null != effortSubreach) {
				equipmentComments.append("+");
				equipmentComments.append(effortSubreach);
			}
		} else if (null != effortPass) {
			equipmentComments.append("+");
			equipmentComments.append(effortPass);
		}
		return equipmentComments.toString();
	}
}
