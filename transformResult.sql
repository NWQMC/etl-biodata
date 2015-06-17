show user;
select * from global_name;
set timing on;
set serveroutput on;
whenever sqlerror exit failure rollback;
whenever oserror exit failure rollback;
select 'transform result start time: ' || systimestamp from dual;

prompt populating biodata_activity
set define off;
truncate table biodata_activity;
insert /*+ append parallel(4) */
  into biodata_activity (dw_effort_id, station_id, site_id, event_date, analytical_method, activity, sample_media, organization, site_type, huc, governmental_unit_code,
                         organization_name, activity_type_code, activity_media_subdiv_name, activity_start_time, act_start_time_zone,
                         activity_stop_date, activity_stop_time, act_stop_time_zone, activity_relative_depth_name, activity_depth,
                         activity_depth_unit, activity_depth_ref_point, activity_upper_depth, activity_upper_depth_unit, activity_lower_depth,
                         activity_lower_depth_unit, project_id, activity_conducting_org, activity_comment, activity_latitude, activity_longitude,
                         activity_source_map_scale, act_horizontal_accuracy, act_horizontal_accuracy_unit, act_horizontal_collect_method,
                         act_horizontal_datum_name, assemblage_sampled_name, act_collection_duration, act_collection_duration_unit,
                         act_sam_compnt_name, act_sam_compnt_place_in_series, act_reach_length, act_reach_length_unit, act_reach_width,
                         act_reach_width_unit, act_pass_count, net_type_name, act_net_surface_area, act_net_surface_area_unit, act_net_mesh_size,
                         act_net_mesh_size_unit, act_boat_speed, act_boat_speed_unit, act_current_speed, act_current_speed_unit,
                         toxicity_test_type_name, sample_collect_method_id, sample_collect_method_ctx, sample_collect_method_name,
                         act_sam_collect_meth_qual_type, act_sam_collect_meth_desc, sample_collect_equip_name, act_sam_collect_equip_comments,
                         act_sam_prep_meth_id, act_sam_prep_meth_context, act_sam_prep_meth_name, act_sam_prep_meth_qual_type,
                         act_sam_prep_meth_desc, sample_container_type, sample_container_color, act_sam_chemical_preservative,
                         thermal_preservative_name, act_sam_transport_storage_desc)
select biodata_effort.dw_effort_id,
       station.station_id, 
       station.site_id,
       trunc(biodata_sample.collection_start) event_date,
       biodata_sample.sampling_method_ref_citation analytical_method,
       biodata_sample.sidno || '-' || biodata_effort.method_code activity,
       'Biological' sample_media,
       station.organization,
       station.site_type,
       station.huc,
       station.governmental_unit_code,
       station.organization_name,              
       'Field Msr/Obs' activity_type_code,
       null activity_media_subdiv_name,
       case
         when biodata_sample.data_source = 'BioTDB' then null
         else to_char(biodata_sample.collection_start, 'hh24:mi:ss')
       end  activity_start_time,
       case
         when biodata_sample.data_source = 'BioTDB' then null
         else biodata_sample.time_datum
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
       biodata_project.project_label project_id,
       null activity_conducting_org,
       biodata_effort.comments activity_comment,
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
       biodata_sample.reach_length_fished act_reach_length,
       case
         when biodata_sample.reach_length_fished is null then null
         else 'm'
       end act_reach_length_unit,
       null act_reach_width,
       null act_reach_width_unit,
       case
         when biodata_effort.pass = 'Pass 1 & 2 combined' then '2'
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
       null sample_collect_method_id,
       null sample_collect_method_ctx,
       null sample_collect_method_name,
       null act_sam_collect_meth_qual_type,
       null act_sam_collect_meth_desc,
       nvl(biodata_effort.gear, biodata_sample.gear_used) sample_collect_equip_name,
       null act_sam_collect_equip_comments,
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
  from biodata_effort
       join biodata_sample
         on biodata_effort.dw_sample_id = biodata_sample.dw_sample_id
       join station_swap_biodata station
         on biodata_sample.biodata_site_id = station.station_id
       join biodata_project
         on biodata_sample.dw_project_id = biodata_project.dw_project_id
 where biodata_sample.data_release_category = 'Public' and
       biodata_project.data_release_category = 'Public' and
       biodata_sample.dw_sample_type_id in (7, 15, 16, 24);
commit;
       
prompt dropping biodata result indexes
exec etl_helper.drop_indexes('result_swap_biodata');

prompt populating biodata result
truncate table result_swap_biodata;

insert into result_swap_biodata (data_source_id, data_source, station_id, site_id, event_date, analytical_method, activity,
                           characteristic_name, characteristic_type, sample_media, organization, site_type, huc, governmental_unit_code,
                           organization_name, activity_type_code, activity_media_subdiv_name, activity_start_time,
                           act_start_time_zone, activity_stop_date, activity_stop_time, act_stop_time_zone, activity_relative_depth_name, activity_depth,
                           activity_depth_unit, activity_depth_ref_point, activity_upper_depth, activity_upper_depth_unit,
                           activity_lower_depth, activity_lower_depth_unit, project_id, activity_conducting_org, activity_comment,
                           activity_latitude, activity_longitude, activity_source_map_scale, act_horizontal_accuracy, act_horizontal_accuracy_unit,
                           act_horizontal_collect_method, act_horizontal_datum_name, assemblage_sampled_name, act_collection_duration, act_collection_duration_unit,
                           act_sam_compnt_name, act_sam_compnt_place_in_series, act_reach_length, act_reach_length_unit, act_reach_width, act_reach_width_unit,
                           act_pass_count, net_type_name, act_net_surface_area, act_net_surface_area_unit, act_net_mesh_size, act_net_mesh_size_unit, act_boat_speed,
                           act_boat_speed_unit, act_current_speed, act_current_speed_unit, toxicity_test_type_name,
                           sample_collect_method_id, sample_collect_method_ctx, sample_collect_method_name,
                           act_sam_collect_meth_qual_type, act_sam_collect_meth_desc, sample_collect_equip_name, act_sam_collect_equip_comments, act_sam_prep_meth_id,
                           act_sam_prep_meth_context, act_sam_prep_meth_name, act_sam_prep_meth_qual_type, act_sam_prep_meth_desc, sample_container_type,
                           sample_container_color, act_sam_chemical_preservative, thermal_preservative_name, act_sam_transport_storage_desc,
                           result_id, res_data_logger_line, result_detection_condition_tx, method_specification_name, sample_fraction_type, result_measure_value,
                           result_unit, result_meas_qual_code, result_value_status, statistic_type, result_value_type, weight_basis_type, duration_basis,
                           temperature_basis_level, particle_size, precision, res_measure_bias, res_measure_conf_interval, res_measure_upper_conf_limit,
                           res_measure_lower_conf_limit, result_comment, result_depth_meas_value, result_depth_meas_unit_code, result_depth_alt_ref_pt_txt,
                           res_sampling_point_name, biological_intent, res_bio_individual_id, sample_tissue_taxonomic_name, unidentifiedspeciesidentifier,
                           sample_tissue_anatomy_name, res_group_summary_ct_wt, res_group_summary_ct_wt_unit, cell_form_name, cell_shape_name, habit_name, volt_name,
                           rtdet_pollution_tolerance, rtdet_pollution_tolernce_scale, rtdet_trophic_level, rtfgrp_functional_feeding_grp, taxon_citation_title,
                           taxon_citation_creator, taxon_citation_subject, taxon_citation_publisher, taxon_citation_date, taxon_citation_id,
                           analytical_procedure_id, analytical_procedure_source, analytical_method_name,
                           anlmth_qual_type, analytical_method_citation, lab_name, analysis_start_date, analysis_start_time, analysis_start_timezone, analysis_end_date,
                           analysis_end_time, analysis_end_timezone, rlcom_cd, lab_remark, detection_limit, detection_limit_unit, detection_limit_desc,
                           res_lab_accred_yn, res_lab_accred_authority, res_taxonomist_accred_yn, res_taxonomist_accred_authorty)
select 4 data_source_id,
       'BIODATA' data_source,
               biodata_activity.station_id, 
               biodata_activity.site_id,
               biodata_activity.event_date,
	           biodata_activity.analytical_method,
               biodata_activity.activity,
               result.characteristic characteristic_name,
               '???' characteristic_type,
               biodata_activity.sample_media,
               biodata_activity.organization,
               biodata_activity.site_type,
               biodata_activity.huc,
               biodata_activity.governmental_unit_code,
               biodata_activity.organization_name,              
               biodata_activity.activity_type_code,             
               biodata_activity.activity_media_subdiv_name,     
               biodata_activity.activity_start_time,            
               biodata_activity.act_start_time_zone,            
               biodata_activity.activity_stop_date,             
               biodata_activity.activity_stop_time,             
               biodata_activity.act_stop_time_zone,
               biodata_activity.activity_relative_depth_name,
               biodata_activity.activity_depth,                 
               biodata_activity.activity_depth_unit,            
               biodata_activity.activity_depth_ref_point,       
               biodata_activity.activity_upper_depth,           
               biodata_activity.activity_upper_depth_unit,      
               biodata_activity.activity_lower_depth,           
               biodata_activity.activity_lower_depth_unit,      
               biodata_activity.project_id,                     
               biodata_activity.activity_conducting_org,       
               biodata_activity.activity_comment,    
               biodata_activity.activity_latitude,
               biodata_activity.activity_longitude,
               biodata_activity.activity_source_map_scale,
               biodata_activity.act_horizontal_accuracy,
               biodata_activity.act_horizontal_accuracy_unit,
               biodata_activity.act_horizontal_collect_method,
               biodata_activity.act_horizontal_datum_name,
               biodata_activity.assemblage_sampled_name,
               biodata_activity.act_collection_duration,
               biodata_activity.act_collection_duration_unit,
               biodata_activity.act_sam_compnt_name,
               biodata_activity.act_sam_compnt_place_in_series,
               biodata_activity.act_reach_length,
               biodata_activity.act_reach_length_unit,
               biodata_activity.act_reach_width,
               biodata_activity.act_reach_width_unit,
               biodata_activity.act_pass_count,
               biodata_activity.net_type_name,
               biodata_activity.act_net_surface_area,
               biodata_activity.act_net_surface_area_unit,
               biodata_activity.act_net_mesh_size,
               biodata_activity.act_net_mesh_size_unit,
               biodata_activity.act_boat_speed,
               biodata_activity.act_boat_speed_unit,
               biodata_activity.act_current_speed,
               biodata_activity.act_current_speed_unit,
               biodata_activity.toxicity_test_type_name,
               biodata_activity.sample_collect_method_id,       
               biodata_activity.sample_collect_method_ctx,      
               biodata_activity.sample_collect_method_name, 
               biodata_activity.act_sam_collect_meth_qual_type,
               biodata_activity.act_sam_collect_meth_desc,
               biodata_activity.sample_collect_equip_name,
               biodata_activity.act_sam_collect_equip_comments,
               biodata_activity.act_sam_prep_meth_id,
               biodata_activity.act_sam_prep_meth_context,
               biodata_activity.act_sam_prep_meth_name,
               biodata_activity.act_sam_prep_meth_qual_type,
               biodata_activity.act_sam_prep_meth_desc,
               biodata_activity.sample_container_type,
               biodata_activity.sample_container_color,
               biodata_activity.act_sam_chemical_preservative,
               biodata_activity.thermal_preservative_name,
               biodata_activity.act_sam_transport_storage_desc,
               result.result_id,
               null res_data_logger_line,
               null result_detection_condition_tx,
               null method_specification_name,
               null sample_fraction_type,
               result.result_value result_measure_value,
               case result.characteristic
                 when 'Weight' then 'g'
                 when 'Length' then 'mm'
                 when 'Total (Fish)' then 'mm'
                 else null
               end result_unit,
               null result_meas_qual_code,
               'Final' result_value_status,
               null statistic_type,
               'Actual' result_value_type,
               null weight_basis_type,
               null duration_basis,
               null temperature_basis_level,
               null particle_size,
               null precision,
               null res_measure_bias,
               null res_measure_conf_interval,
               null res_measure_upper_conf_limit,
               null res_measure_lower_conf_limit,
               null result_comment,
               null result_depth_meas_value,
               null result_depth_meas_unit_code,
               null result_depth_alt_ref_pt_txt,
               null res_sampling_point_name,
               'Population Census' biological_intent,
               null res_bio_individual_id,
               result.published_taxon_name sample_tissue_taxonomic_name,
               null UnidentifiedSpeciesIdentifier,
               null sample_tissue_anatomy_name,
               result.group_weight res_group_summary_ct_wt,
               case
                 when result.group_weight is not null then 'g'
                 else null
               end res_group_summary_ct_wt_unit,
               null cell_form_name,
               null cell_shape_name,
               null habit_name,
               null volt_name,
               null rtdet_pollution_tolerance,
               null rtdet_pollution_tolernce_scale,
               null rtdet_trophic_level,
               null rtfgrp_functional_feeding_grp,
               null citatn_title,
               null citatn_creator,
               null citatn_subject,
               null citatn_publisher,
               null citatn_date,
               null citatn_id,
         	   null analytical_procedure_id,
         	   null analytical_procedure_source,
        	   null analytical_method_name,
               null anlmth_qual_type,
        	   null analytical_method_citation,
               null lab_name,
               null analysis_start_date,
               null res_lab_analysis_start_time,
               null analysis_start_timezone,
               null res_lab_analysis_end_date,
               null res_lab_analysis_end_time,
               null analysis_end_timezone,
               null rlcom_cd,
               null lab_remark,
               null detection_limit,
               null detection_limit_unit,
               null detection_limit_desc,
               null res_lab_accred_yn,
               null res_lab_accred_authority,
               null res_taxonomist_accred_yn,
               null res_taxonomist_accred_authorty
          from biodata_activity
               join (select a.*, rownum result_id
                       from (select biodata_taxonomic_result.dw_effort_id,
                                    biodata_taxon_wide.published_taxon_name,
                                    case
                                      when biodata_taxonomic_result.raw_count > 1 and
                                           biodata_taxonomic_result.weight > 0
                                        then weight
                                      else null
                                    end group_weight,
                                    biodata_taxonomic_result.raw_count,
                                    biodata_taxonomic_result.total_length,
                                    biodata_taxonomic_result.standard_length,
                                    biodata_taxonomic_result.weight
                               from biodata_taxonomic_result
                                    join biodata_taxon_wide
                                      on biodata_taxonomic_result.taxon_id = biodata_taxon_wide.bench_taxon_id
                              where biodata_taxonomic_result.biological_community = 'Fish' and
                                    biodata_taxonomic_result.data_release_category = 'Public')
                             unpivot (result_value for characteristic
                                         in (raw_count as 'Count',
                                             total_length as 'Length',
                                             standard_length as 'Total (Fish)',
                                             weight as 'Weight')
                            ) a
                    ) result
                 on biodata_activity.dw_effort_id = result.dw_effort_id
     order by biodata_activity.station_id;

commit;

prompt building biodata result indexes
exec etl_helper.create_result_indexes('biodata');

select 'transform result end time: ' || systimestamp from dual;
