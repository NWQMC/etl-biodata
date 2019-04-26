select 
	station.station_id, 
	station.site_id,
	sample.collection_start event_date,
	sample.sidno,
	effort.method_code,
	station.organization,
	station.site_type,
	station.huc,
	station.governmental_unit_code,
	station.organization_name,
	effort.dw_effort_id activity_id,
	sample.data_source
	sample.collection_start

	case
		when sample.data_source = 'BioTDB' 
			then null
			else sample.time_datum
	end act_start_time_zone,

	project.project_label project_id,
	effort.comments activity_comment,
	sample.reach_length_fished act_reach_length,

	case
		when sample.reach_length_fished is null 
			then null
			else 'm'
	end act_reach_length_unit,

	case
		when effort.pass = 'Pass 1 & 2 combined' 
			then '2'
			else '1'
	end act_pass_count,

	effort.method_code sample_collect_method_id,
	sample.sampling_method_reference || ' ' || effort.method_code sample_collect_method_ctx,
	sample.sampling_method_reference sample_collect_method_name,
	sample.sampling_method_ref_citation act_sam_collect_meth_desc,

	case lower(nvl(effort.gear, sample.gear_used))
		when 'backpack'	then 'Backpack Electroshock'
		when 'towed barge' then 'Electroshock (Other)'
		when 'boat' then 'Boat-Mounted Electroshock'
		when 'minnow seine' then 'Minnow Seine Net'
		when 'bag seine' then 'Seine Net'
		when 'beach seine' then 'Beach Seine Net'
		when 'snorkeling' then 'Visual Sighting'
		else null
	end sample_collect_equip_name,

	nvl(effort.gear, sample.gear_used) || 
		case 
			when sample.dw_sample_type_id = 16
				then case 
					when effort.subreach is not null
						then '+' || effort.subreach
						else null
					end
					when effort.pass is not null
						then '+' || effort.pass
						else null
					end act_sam_collect_equip_comments,

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