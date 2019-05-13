package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;
import java.time.LocalDateTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityProcessor implements ItemProcessor<BiodataActivity, Activity>{

	protected static final String DEFAULT_SAMPLE_MEDIA = "Biological";
	protected static final String DEFAULT_ACTIVITY_TYPE_CODE = "Field Msr/Obs";
	protected static final String DEFAULT_ASSEMBLAGE_SAMPLED_NAME = "Fish/Nekton";
	protected static final String SAMPLE_DATA_SOURCE_BIOTB = "BioTDB";
	protected static final String EFFORT_PASS_1_2_COMBINED = "Pass 1 & 2 combined";
	protected static final Integer EFFORT_PASS_1 = 1;
	protected static final Integer EFFORT_PASS_2 = 2;
	protected static final String BACKPACK = "backpack";
	protected static final String BACKPACK_ELECTROSHOCK = "Backpack Electroshock";
	protected static final String TOWED_BARGE = "towed barge";
	protected static final String ELECTROSHOCK_OTHER = "Electroshock (Other)";
	protected static final String BOAT = "boat";
	protected static final String BOAT_MOUNTED_ELECTROSHOCK = "Boat-Mounted Electroshock";
	protected static final String MINNOW_SEINE = "minnow seine";
	protected static final String MINNOW_SEINE_NET = "Minnow Seine Net";
	protected static final String BAG_SEINE = "bag seine";
	protected static final String SEINE_NET = "Seine Net";
	protected static final String BEACH_SEINE = "beach seine";
	protected static final String BEACH_SEINE_NET = "Beach Seine Net";
	protected static final String SNORKELING = "snorkeling";
	protected static final String VISUAL_SIGHTING = "Visual Sighting";
	protected static final String DEFAULT_REACH_LENGTH_UNIT = "m";
	protected static final Integer DEFAULT_DW_SAMPLE_TYPE_ID_16 = 16;

	private final ConfigurationService configurationService;

	@Autowired
	public ActivityProcessor(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Override
	public Activity process(BiodataActivity bdActivity) {
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
		activity.setEventDate(bdActivity.getSampleCollectionStartTime().toLocalDate());
		activity.setActivity(
				String.join("-",
				bdActivity.getSidno(),
				bdActivity.getSampleCollectMethodId()));
		activity.setOrganization(bdActivity.getOrganization());
		activity.setSiteType(bdActivity.getSiteType());
		activity.setHuc(bdActivity.getHuc());
		activity.setGovernmentalUnitCode(bdActivity.getGovernmentalUnitCode());
		activity.setGeom(bdActivity.getGeom());
		activity.setOrganizationName(bdActivity.getOrganizationName());
		activity.setActivityId(bdActivity.getActivityId());

		activity.setActivityStartTime(
				getActivityStartTime(
						bdActivity.getSampleDataSource(),
						bdActivity.getSampleCollectionStartTime()));

		activity.setActStartTimeZone(
				getActivityStartTimeZone(
						bdActivity.getSampleDataSource(),
						bdActivity.getSampleTimeDatum()));

		activity.setProjectId(bdActivity.getProjectId());
		activity.setProjectName(bdActivity.getProjectName());
		activity.setActivityComment(bdActivity.getActivityComment());
		activity.setActReachLength(String.valueOf(bdActivity.getActivityReachLength()));

		activity.setActReachLengthUnit(
				getActivityReachLengthUnit(
						bdActivity.getActivityReachLength()));

		activity.setActPassCount(
				getActivityPassCount(
						bdActivity.getEffortPass()));

		activity.setSampleCollectMethodId(bdActivity.getSampleCollectMethodId());
		activity.setSampleCollectMethodCtx(
				String.join(" ",
				bdActivity.getSampleCollectMethodName(),
				bdActivity.getSampleCollectMethodId()
				)
		);
		activity.setSampleCollectMethodName(bdActivity.getSampleCollectMethodName());
		activity.setActSamCollectMethDesc(
				bdActivity.getActivitySampleCollectMethodDescription());

		activity.setSampleCollectEquipName(
				getSampleCollectEquipName(gear));

		activity.setActSamCollectEquipComments(
				getActivitySampleCollectEquipmentComments(
						gear,
						bdActivity.getDwSampleTypeId(),
						bdActivity.getEffortSubreach(),
						bdActivity.getEffortPass()));

		return activity;
	}

	private String getActivityStartTime(String sampleDataSource, LocalDateTime sampleCollectionStart) {
		return SAMPLE_DATA_SOURCE_BIOTB.equals(sampleDataSource)
				? null
				: sampleCollectionStart.toLocalTime().format(Application.TIME_FORMATTER);
	}

	private String getActivityStartTimeZone(String sampleDataSource, String sampleTimeDatum) {
		return SAMPLE_DATA_SOURCE_BIOTB.equals(sampleDataSource)
				? null
				: sampleTimeDatum;
	}

	private String getActivityReachLengthUnit(Integer activityReachLength) {
		return null == activityReachLength
				? null
				: DEFAULT_REACH_LENGTH_UNIT;
	}

	private Integer getActivityPassCount(String effortPass) {
		return EFFORT_PASS_1_2_COMBINED.equals(effortPass)
				? EFFORT_PASS_2
				: EFFORT_PASS_1;
	}

	private String getSampleCollectEquipName(String gear) {
		switch (gear.toLowerCase()) {
			case BACKPACK :
				return BACKPACK_ELECTROSHOCK;
			case TOWED_BARGE :
				return ELECTROSHOCK_OTHER;
			case BOAT :
				return BOAT_MOUNTED_ELECTROSHOCK;
			case MINNOW_SEINE :
				return MINNOW_SEINE_NET;
			case BAG_SEINE :
				return SEINE_NET;
			case BEACH_SEINE :
				return BEACH_SEINE_NET;
			case SNORKELING :
				return VISUAL_SIGHTING;
			default:
				return null;
		}
	}

	private String getActivitySampleCollectEquipmentComments(String gear, Integer dwSampleTypeId, String effortSubreach, String effortPass) {
		StringBuilder equipmentComments = new StringBuilder(gear);
		if (DEFAULT_DW_SAMPLE_TYPE_ID_16.equals(dwSampleTypeId)) {
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
