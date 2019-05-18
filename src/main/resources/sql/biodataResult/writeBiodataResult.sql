insert into
    result (
        dw_effort_id
        ,published_taxon_name
        ,group_weight
        ,raw_count
        ,weight
        ,res_bio_individual_id
        ,unidentified_species_identifier
        ,total_length
        ,standard_length
        ,field_sheet_page
        ,field_sheet_line
        ,biodata_taxon_name
        ,characteristic
        ,result_value
    )
values (
    :dwEffortId
    ,:publishedTaxonName
    ,:groupWeight
    ,:rawCount
    ,:weight
    ,:resBioIndividualId
    ,:unidentifiedSpeciesIdentifier
    ,:totalLength
    ,:standardLength
    ,:fieldSheetPage
    ,:fieldSheetLine
    ,:biodataTaxonName
    ,:characteristic
    ,:resultValue
)