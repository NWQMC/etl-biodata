show user;
select * from global_name;
set timing on;
set serveroutput on;
whenever sqlerror exit failure rollback;
whenever oserror exit failure rollback;
select 'transform station start time: ' || systimestamp from dual;

prompt dropping biodata station indexes
exec etl_helper.drop_indexes('station_swap_biodata');
        
prompt populating biodata station
truncate table station_swap_biodata;

insert /*+ append parallel(4) */
  into station_swap_biodata (data_source_id, data_source, station_id, site_id, organization, site_type, huc, governmental_unit_code,
                             geom, station_name, organization_name, description_text, station_type_name, latitude, longitude, map_scale,
                             geopositioning_method, hdatum_id_code, elevation_value, elevation_unit, elevation_method, vdatum_id_code,
                             drain_area_value, drain_area_unit, contrib_drain_area_value, contrib_drain_area_unit,
                             geoposition_accy_value, geoposition_accy_unit, vertical_accuracy_value, vertical_accuracy_unit,
                             nat_aqfr_name, aqfr_name, aqfr_type_name, construction_date, well_depth_value, well_depth_unit,
                             hole_depth_value, hole_depth_unit)
select /*+ parallel(4) */ 
       4 data_source_id,
       'BIODATA' data_source,
       biodata_site.biodata_site_id station_id,
       nvl(station.site_id, biodata_site.agency_cd || '-' || biodata_site.site_no) site_id,
       station.organization,
       nvl(station.site_type, biodata_site.site_type_long_name) site_type,
       nvl(station.huc, biodata_site.huc_cd) huc,
       nvl(station.governmental_unit_code, biodata_site.country_cd || ':' || biodata_site.state_cd || ':' || biodata_site.county_cd) governmental_unit_code,
       nvl(station.geom, sdo_cs.transform(geo_point, 4269)) geom,
       nvl(station.station_name, trim(biodata_site.station_nm)) station_name,
       station.organization_name,
       station.description_text,
       station.station_type_name,
       nvl(station.latitude, round(biodata_site.dec_latitude , 7)) latitude,
       nvl(station.longitude, round(biodata_site.dec_longitude, 7)) longitude,
       station.map_scale,
       station.geopositioning_method,
       nvl(station.hdatum_id_code, nvl(biodata_site.coord_datum_cd, 'Unknown')) hdatum_id_code,
       nvl(station.elevation_value,
           case when biodata_site.alt_datum_cd is not null then case when biodata_site.altitude = '.' then '0' else trim(biodata_site.altitude) end else null end
          ) elevation_value,
       nvl(station.elevation_unit,
           case when biodata_site.altitude is not null and biodata_site.alt_datum_cd is not null then 'feet' else null end
          ) elevation_unit,
       station.elevation_method,
       nvl(station.vdatum_id_code,
           case when biodata_site.altitude is not null then biodata_site.alt_datum_cd else null end
          ) vdatum_id_code,
       nvl(station.drain_area_value,
           to_number(biodata_site.drain_area_va)
          ) drain_area_value,
       nvl(station.drain_area_unit,
           nvl2(biodata_site.drain_area_va, 'sq mi', null)
          ) drain_area_unit,
       station.contrib_drain_area_value,
       station.contrib_drain_area_unit,
       station.geoposition_accy_value,
       station.geoposition_accy_unit,
       station.vertical_accuracy_value,
       station.vertical_accuracy_unit,
       station.nat_aqfr_name,
       station.aqfr_name,
       station.aqfr_type_name,
       station.construction_date,
       station.well_depth_value,
       station.well_depth_unit,
       station.hole_depth_value,
       station.hole_depth_unit
  from biodata.biodata_site
       left join (select station.*, sitefile.nwis_host, sitefile.db_no, sitefile.agency_cd, sitefile.site_no
                    from nwis_ws_star.sitefile,
                         station partition (station_nwis)
                   where sitefile.site_id = station.station_id) station
         on biodata_site.agency_cd = station.agency_cd and
            biodata_site.site_no = station.site_no and
            biodata_site.nwis_host = station.nwis_host and
            biodata_site.db_no = station.db_no
 where biodata_site.site_web_cd = 'Y' and
       biodata_site.data_release_category = 'Public' and
       exists (select null
                 from biodata.sample
                      join biodata.sample_type
                        on sample.dw_sample_type_id = sample_type.dw_sample_type_id
                where sample.biodata_site_id = site.biodata_site_id and
                      sample_type.data_category_code = 'FSH' and
                      site.data_release_category = 'Public' and
                      sample.data_release_category = 'Public') ;

commit;

prompt building biodata station indexes
exec etl_helper.create_station_indexes('biodata');

select 'transform station end time: ' || systimestamp from dual;
