show user;
select * from global_name;
set timing on;
set serveroutput on;
whenever sqlerror exit failure rollback;
whenever oserror exit failure rollback;
select 'transform result start time: ' || systimestamp from dual;

prompt dropping biodata result indexes
exec etl_helper_result.drop_indexes('biodata');

prompt populating biodata result
truncate table result_swap_biodata;

insert into result_swap_biodata (data_source_id, data_source, station_id, site_id, event_date, analytical_method, activity,
                           characteristic_name, characteristic_type, sample_media, organization, site_type, huc, governmental_unit_code,
                           organization_name, activity_id, activity_type_code, activity_media_subdiv_name, activity_start_time,
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
select activity_swap_biodata.data_source_id,
               activity_swap_biodata.data_source,
               activity_swap_biodata.station_id, 
               activity_swap_biodata.site_id,
               activity_swap_biodata.event_date,
--               sample.sampling_method_ref_citation analytical_method,
               null analytical_method,
               activity_swap_biodata.activity,
               result.characteristic characteristic_name,
               'Biological' characteristic_type,
               activity_swap_biodata.sample_media,
               activity_swap_biodata.organization,
               activity_swap_biodata.site_type,
               activity_swap_biodata.huc,
               activity_swap_biodata.governmental_unit_code,
               activity_swap_biodata.organization_name,
               activity_swap_biodata.activity_id
               activity_swap_biodata.activity_type_code,
               activity_swap_biodata.activity_media_subdiv_name,
               activity_swap_biodata.activity_start_time,
               activity_swap_biodata.act_start_time_zone,
               activity_swap_biodata.activity_stop_date,
               activity_swap_biodata.activity_stop_time,
               activity_swap_biodata.act_stop_time_zone,
               activity_swap_biodata.activity_relative_depth_name,
               activity_swap_biodata.activity_depth,
               activity_swap_biodata.activity_depth_unit,
               activity_swap_biodata.activity_depth_ref_point,
               activity_swap_biodata.activity_upper_depth,
               activity_swap_biodata.activity_upper_depth_unit,
               activity_swap_biodata.activity_lower_depth,
               activity_swap_biodata.activity_lower_depth_unit,
               activity_swap_biodata.project_id,
               activity_swap_biodata.activity_conducting_org,
               activity_swap_biodata.activity_comment,
               activity_swap_biodata.activity_latitude,
               activity_swap_biodata.activity_longitude,
               activity_swap_biodata.activity_source_map_scale,
               activity_swap_biodata.act_horizontal_accuracy,
               activity_swap_biodata.act_horizontal_accuracy_unit,
               activity_swap_biodata.act_horizontal_collect_method,
               activity_swap_biodata.act_horizontal_datum_name,
               activity_swap_biodata.assemblage_sampled_name,
               activity_swap_biodata.act_collection_duration,
               activity_swap_biodata.act_collection_duration_unit,
               activity_swap_biodata.act_sam_compnt_name,
               activity_swap_biodata.act_sam_compnt_place_in_series,
               activity_swap_biodata.act_reach_length,
               activity_swap_biodata.act_reach_length_unit,
               activity_swap_biodata.act_reach_width,
               activity_swap_biodata.act_reach_width_unit,
               activity_swap_biodata.act_pass_count,
               activity_swap_biodata.net_type_name,
               activity_swap_biodata.act_net_surface_area,
               activity_swap_biodata.act_net_surface_area_unit,
               activity_swap_biodata.act_net_mesh_size,
               activity_swap_biodata.act_net_mesh_size_unit,
               activity_swap_biodata.act_boat_speed,
               activity_swap_biodata.act_boat_speed_unit,
               activity_swap_biodata.act_current_speed,
               activity_swap_biodata.act_current_speed_unit,
               activity_swap_biodata.toxicity_test_type_name,
               activity_swap_biodata.sample_collect_method_id,
               activity_swap_biodata.sample_collect_method_ctx,
               activity_swap_biodata.sample_collect_method_name,
               activity_swap_biodata.act_sam_collect_meth_qual_type,
               activity_swap_biodata.act_sam_collect_meth_desc,
               activity_swap_biodata.sample_collect_equip_name,
               activity_swap_biodata.act_sam_collect_equip_comments,
               activity_swap_biodata.act_sam_prep_meth_id,
               activity_swap_biodata.act_sam_prep_meth_context,
               activity_swap_biodata.act_sam_prep_meth_name,
               activity_swap_biodata.act_sam_prep_meth_qual_type,
               activity_swap_biodata.act_sam_prep_meth_desc,
               activity_swap_biodata.sample_container_type,
               activity_swap_biodata.sample_container_color,
               activity_swap_biodata.act_sam_chemical_preservative,
               activity_swap_biodata.thermal_preservative_name,
               activity_swap_biodata.act_sam_transport_storage_desc,
               result.result_id,
               null res_data_logger_line,
               null result_detection_condition_tx,
               null method_specification_name,
               null sample_fraction_type,
               result.result_value result_measure_value,
               case result.characteristic
                 when 'Weight' then 'g'
                 when 'Fish standard length' then 'mm'
                 when 'Length, Total (Fish)' then 'mm'
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
               result.res_bio_individual_id,
               result.published_taxon_name sample_tissue_taxonomic_name,
               result.UnidentifiedSpeciesIdentifier,
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
          from activity_swap_biodata
               join (select a.*, rownum result_id
                       from (select taxonomic_result.dw_effort_id,
                                    taxon_wide.published_taxon_name,
                                    case
                                      when taxonomic_result.raw_count > 1 and
                                           taxonomic_result.weight > 0
                                        then weight
                                      else null
                                    end group_weight,
                                    taxonomic_result.raw_count,
                                    taxonomic_result.total_length,
                                    taxonomic_result.standard_length,
                                    taxonomic_result.weight,
                                    taxonomic_result.field_sheet_page ||
                                       nvl2(taxonomic_result.field_sheet_line,
                                            '-' || taxonomic_result.field_sheet_line,
                                            null) res_bio_individual_id,
                                    case
                                      when taxon_wide.biodata_taxon_name = taxon_wide.published_taxon_name
                                        then null
                                      else taxon_wide.biodata_taxon_name
                                    end UnidentifiedSpeciesIdentifier
                               from biodata.taxonomic_result
                                    join biodata.taxon_wide
                                      on taxonomic_result.taxon_id = taxon_wide.bench_taxon_id
                              where taxonomic_result.biological_community = 'Fish' and
                                    taxonomic_result.data_release_category = 'Public')
                             unpivot (result_value for characteristic
                                         in (raw_count as 'Count',
                                             total_length as 'Length, Total (Fish)',
                                             standard_length as 'Fish standard length',
                                             weight as 'Weight')
                            ) a
                    ) result
                 on activity_swap_biodata.activity_id = result.dw_effort_id
     order by activity_swap_biodata.station_id;

commit;

prompt building biodata result indexes
exec etl_helper_result.create_indexes('biodata');

select 'transform result end time: ' || systimestamp from dual;