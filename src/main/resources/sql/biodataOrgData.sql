select distinct
	data_source_id,
	data_source,
	dense_rank() over (partition by data_source_id order by organization) organization_id,
	organization,
	organization organization_name
from
	station_swap_biodata
where
	organization is not null;
