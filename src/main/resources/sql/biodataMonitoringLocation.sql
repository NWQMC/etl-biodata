select
	4 data_source_id,
	'BIODATA' data_source,
	station_nwis.site_id,
	biodata_site.biodata_site_id,
	biodata_site.agency_cd, 
	biodata_site.site_no,
	station_nwis.organization,
	coalesce(station_nwis.site_type, biodata_site.site_type_long_name) site_type_long_name,
	coalesce(station_nwis.huc, biodata_site.huc_cd) huc_cd,
	station_nwis.governmental_unit_code,
	biodata_site.country_cd,
	biodata_site.state_cd,
	biodata_site.county_cd,
	coalesce(station_nwis.geom, geo_point) geo_point,
	coalesce(station_nwis.station_name, trim(biodata_site.station_nm)) station_nm,
	station_nwis.organization_name,
	station_nwis.station_type_name,
	coalesce(station_nwis.latitude, round(biodata_site.dec_latitude , 7)) dec_latitude,
	coalesce(station_nwis.longitude, round(biodata_site.dec_longitude, 7)) dec_longitude,
	station_nwis.map_scale,
	station_nwis.geopositioning_method,
	coalesce(station_nwis.hdatum_id_code, biodata_site.coord_datum_cd, 'Unknown') coord_datum_cd,
	station_nwis.elevation_value,
	station_nwis.elevation_unit,
	biodata_site.altitude, 
	biodata_site.alt_datum_cd,
	station_nwis.elevation_method,
	station_nwis.vdatum_id_code,
	coalesce(station_nwis.drain_area_value, to_number(biodata_site.drain_area_va, '12345678')) drain_area_va,
	biodata_site.drain_area_va biodata_drain_area_va,
	station_nwis.drain_area_unit,
	station_nwis.contrib_drain_area_value,
	station_nwis.contrib_drain_area_unit,
	station_nwis.geoposition_accy_value,
	station_nwis.geoposition_accy_unit,
	station_nwis.vertical_accuracy_value,
	station_nwis.vertical_accuracy_unit,
	station_nwis.nat_aqfr_name,
	station_nwis.aqfr_name,
	station_nwis.aqfr_type_name,
	station_nwis.construction_date,
	station_nwis.well_depth_value,
	station_nwis.well_depth_unit,
	station_nwis.hole_depth_value,
	station_nwis.hole_depth_unit
from
	biodata.biodata_site
	left join
		station_nwis
	on
		biodata_site.agency_cd || '-' || biodata_site.site_no = station_nwis.site_id
where
	biodata_site.site_web_cd = 'Y' and
	biodata_site.data_release_category = 'Public' and
	exists (
		select
			null
		from
			biodata.sample
			join
				biodata.sample_type
			on 
				sample.dw_sample_type_id = sample_type.dw_sample_type_id
		where
			sample.biodata_site_id = biodata_site.biodata_site_id and
			sample_type.data_category_code = 'FSH' and
			biodata_site.data_release_category = 'Public' and
			sample.data_release_category = 'Public'
	);
