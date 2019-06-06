select
    taxonomic_result.dw_effort_id
    ,taxon_wide.published_taxon_name
    ,taxonomic_result.raw_count
    ,taxonomic_result.total_length
    ,taxonomic_result.standard_length
    ,taxonomic_result.weight
    ,taxonomic_result.field_sheet_page
    ,taxonomic_result.field_sheet_line
    ,taxon_wide.biodata_taxon_name
    ,unnest(array['Count', 'Length, Total (Fish)', 'Fish standard length', 'Weight']) AS characteristic
    ,unnest(array[raw_count, total_length, standard_length, weight]) AS result_value
from
    biodata.taxonomic_result
    join
        biodata.taxon_wide
        on
            taxonomic_result.taxon_id = taxon_wide.bench_taxon_id
where
    taxonomic_result.biological_community = 'Fish'
    and taxonomic_result.data_release_category = 'Public'
order by dw_effort_id;
;
