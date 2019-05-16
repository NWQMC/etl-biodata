select
    taxonomic_result.dw_effort_id
    ,taxon_wide.published_taxon_name
    ,case
        when taxonomic_result.raw_count > 1 and
            taxonomic_result.weight > 0
            then weight
        else null
        end group_weight
    ,taxonomic_result.raw_count
    ,taxonomic_result.total_length
    ,taxonomic_result.standard_length
    ,taxonomic_result.weight
    ,taxonomic_result.field_sheet_page ||
        case
            when taxonomic_result.field_sheet_line is not null
                then '-' || taxonomic_result.field_sheet_line
            else null
            end res_bio_individual_id
    ,case
        when taxon_wide.biodata_taxon_name = taxon_wide.published_taxon_name
            then null
        else taxon_wide.biodata_taxon_name
        end UnidentifiedSpeciesIdentifier
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
    and taxonomic_result.data_release_category = 'Public';
