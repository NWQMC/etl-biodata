show user;
select * from global_name;
set timing on;
set serveroutput on;
whenever sqlerror exit failure rollback;
whenever oserror exit failure rollback;
select 'transform activity start time: ' || systimestamp from dual;

prompt populating activity_swap_biodata
set define off;
truncate table activity_swap_biodata;
insert /*+ append parallel(4) */
  into activity_swap_biodata (data_source_id, data_source, station_id, site_id, event_date, activity, sample_media,
                              organization, site_type, huc, governmental_unit_code, organization_name, activity_id,
                              activity_type_code, activity_media_subdiv_name, activity_start_time, act_start_time_zone,
                              activity_stop_date, activity_stop_time, act_stop_time_zone, activity_relative_depth_name,
                              activity_depth, activity_depth_unit, activity_depth_ref_point, activity_upper_depth,
                              activity_upper_depth_unit, activity_lower_depth, activity_lower_depth_unit, project_id,
                              activity_conducting_org, activity_comment, sample_aqfr_name, hydrologic_condition_name,
                              hydrologic_event_name, activity_latitude, activity_longitude, activity_source_map_scale,
                              act_horizontal_accuracy, act_horizontal_accuracy_unit, act_horizontal_collect_method,
                              act_horizontal_datum_name, assemblage_sampled_name, act_collection_duration,
                              act_collection_duration_unit, act_sam_compnt_name, act_sam_compnt_place_in_series,
                              act_reach_length, act_reach_length_unit, act_reach_width, act_reach_width_unit,
                              act_pass_count, net_type_name, act_net_surface_area, act_net_surface_area_unit,
                              act_net_mesh_size, act_net_mesh_size_unit, act_boat_speed, act_boat_speed_unit,
                              act_current_speed, act_current_speed_unit, toxicity_test_type_name,
                              sample_collect_method_id, sample_collect_method_ctx, sample_collect_method_name,
                              act_sam_collect_meth_qual_type, act_sam_collect_meth_desc, sample_collect_equip_name,
                              act_sam_collect_equip_comments, act_sam_prep_meth_id, act_sam_prep_meth_context,
                              act_sam_prep_meth_name, act_sam_prep_meth_qual_type, act_sam_prep_meth_desc,
                              sample_container_type, sample_container_color, act_sam_chemical_preservative,
                              thermal_preservative_name, act_sam_transport_storage_desc)
select 4 data_source_id,
       'BIODATA' data_source,
       station.station_id, 
       station.site_id,
       trunc(sample.collection_start) event_date,
       sample.sidno || '-' || effort.method_code activity,
       'Biological' sample_media,
       station.organization,
       station.site_type,
       station.huc,
       station.governmental_unit_code,
       station.organization_name,
       effort.dw_effort_id activity_id,
       'Field Msr/Obs' activity_type_code,
       null activity_media_subdiv_name,
       case
         when sample.data_source = 'BioTDB' then null
         else to_char(sample.collection_start, 'hh24:mi:ss')
       end  activity_start_time,
       case
         when sample.data_source = 'BioTDB' then null
         else sample.time_datum
       end act_start_time_zone,
       null activity_stop_date,
       null activity_stop_time,
       null act_stop_time_zone,
       null activity_relative_depth_name,
       null activity_depth,
       null activity_depth_unit,
       null activity_depth_ref_point,
       null activity_upper_depth,
       null activity_upper_depth_unit,
       null activity_lower_depth,
       null activity_lower_depth_unit,
       project.project_label project_id,
       null activity_conducting_org,
       effort.comments activity_comment,
       null sample_aqfr_name,
       null hydrologic_condition_name,
       null hydrologic_event_name,
       null activity_latitude,
       null activity_longitude,
       null activity_source_map_scale,
       null act_horizontal_accuracy,
       null act_horizontal_accuracy_unit,
       null act_horizontal_collect_method,
       null act_horizontal_datum_name,
       'Fish/Nekton' assemblage_sampled_name,
       null act_collection_duration,
       null act_collection_duration_unit,
       null act_sam_compnt_name,
       null act_sam_compnt_place_in_series,
       sample.reach_length_fished act_reach_length,
       case
         when sample.reach_length_fished is null then null
         else 'm'
       end act_reach_length_unit,
       null act_reach_width,
       null act_reach_width_unit,
       case
         when effort.pass = 'Pass 1 & 2 combined' then '2'
         else '1'
       end act_pass_count,
       null net_type_name,
       null act_net_surface_area,
       null act_net_surface_area_unit,
       null act_net_mesh_size,
       null act_net_mesh_size_unit,
       null act_boat_speed,
       null act_boat_speed_unit,
       null act_current_speed,
       null act_current_speed_unit,
       null toxicity_test_type_name,
       effort.method_code sample_collect_method_id,
       sample.sampling_method_reference || ' ' || effort.method_code sample_collect_method_ctx,
       sample.sampling_method_reference sample_collect_method_name,
       null act_sam_collect_meth_qual_type,
       sample.sampling_method_ref_citation act_sam_collect_meth_desc,
       case lower(nvl(effort.gear, sample.gear_used))
         when 'backpack' then 'Backpack Electroshock'
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
       null act_sam_prep_meth_id,
       null act_sam_prep_meth_context,
       null act_sam_prep_meth_name,
       null act_sam_prep_meth_qual_type,
       null act_sam_prep_meth_desc,
       null sample_container_type,
       null sample_container_color,
       null act_sam_chemical_preservative,
       null thermal_preservative_name,
       null act_sam_transport_storage_desc
  from biodata.effort
       join biodata.sample
         on effort.dw_sample_id = sample.dw_sample_id
       join station_swap_biodata station
         on sample.biodata_site_id = station.station_id
       join biodata.project
         on sample.dw_project_id = project.dw_project_id
 where sample.data_release_category = 'Public' and
       project.data_release_category = 'Public' and
       sample.dw_sample_type_id in (7, 15, 16, 24);
commit;

select 'transform activity end time: ' || systimestamp from dual;