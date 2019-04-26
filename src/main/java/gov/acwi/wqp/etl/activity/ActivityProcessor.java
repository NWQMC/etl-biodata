package gov.acwi.wqp.etl.activity;

import gov.acwi.wqp.etl.ConfigurationService;
import gov.acwi.wqp.etl.biodata.activity.BiodataActivity;
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
	public static final String EFFORT_PASS_1 = "1";
	public static final String EFFORT_PASS_2 = "2";
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // originall hh24:mi:ss
	
	@Autowired
	public ActivityProcessor(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@Override
	public Activity process(BiodataActivity bdActivity) throws Exception {
		Activity activity = new Activity();
		
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
		activity.setOrganizationName(bdActivity.getOrganizationName());
		activity.setActivityId(bdActivity.getActivityId());
		activity.setActivityStartTime(
				dateFormatter.format(bdActivity.getActivityStartTime()));
		activity.setActStartTimeZone(bdActivity.getActivityStartTimeZone());
		activity.setProjectId(bdActivity.getProjectId());
		activity.setActivityComment(bdActivity.getActivityComment());
		activity.setActivityReachLength(bdActivity.getActivityReachLength());
		activity.setActivityReachLengthUnit(bdActivity.getActivityReachLengthUnit());
		activity.setActivityPassCount(bdActivity.getActivityPassCount());
		activity.setSampleCollectMethodId(bdActivity.getSampleCollectMethodId());
		activity.setSampleCollectMethodCtx(bdActivity.getSampleCollectMethodCtx());
		activity.setSampleCollectMethodName(bdActivity.getSampleCollectMethodName());
		activity.setActivitySampleCollectMethodDescription(
				bdActivity.getActivitySampleCollectMethodDescription());
		activity.setSampleCollectEquipName(bdActivity.getSampleCollectEquipmentName());
		activity.setActivitySampleCollectEquipmentComments(
				bdActivity.getActivitySampleCollectEquipmentComments());
				
		return activity;
	}
	
}
