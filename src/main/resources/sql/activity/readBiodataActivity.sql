select 
	station.station_id 
	,station.site_id
    ,sample.sidno
	,station.organization
	,station.site_type
	,station.huc
	,station.governmental_unit_code
	,station.geom
	,station.organization_name
	,effort.dw_effort_id activity_id
	,sample.data_source
	,sample.collection_start
	,sample.time_datum
	,project.project_label project_id
	,project.project_name
	,effort.comments activity_comment
	,sample.reach_length_fished act_reach_length
	,effort.pass
	,effort.method_code sample_collect_method_id
	,sample.sampling_method_reference sample_collect_method_name
	,sample.sampling_method_ref_citation act_sam_collect_meth_desc
	,effort.gear 
	,sample.gear_used
	,sample.dw_sample_type_id
	,effort.subreach
	
from biodata.effort
	join biodata.sample
		on effort.dw_sample_id = sample.dw_sample_id
	join station_swap_biodata station
		on sample.biodata_site_id = station.station_id
	join biodata.project
		on sample.dw_project_id = project.dw_project_id
where 
	sample.data_release_category = 'Public' and
	project.data_release_category = 'Public' and
	sample.dw_sample_type_id in (7, 15, 16, 24);