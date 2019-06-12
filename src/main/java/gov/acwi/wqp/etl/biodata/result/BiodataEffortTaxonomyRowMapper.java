package gov.acwi.wqp.etl.biodata.result;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BiodataEffortTaxonomyRowMapper implements RowMapper<BiodataEffortTaxonomy> {

    private static final String DW_EFFORT_ID = "dw_effort_id";
    private static final String PUBLISHED_TAXON_NAME = "published_taxon_name";
    private static final String RAW_COUNT = "raw_count";
    private static final String WEIGHT = "weight";
    private static final String TOTAL_LENGTH = "total_length";
    private static final String STANDARD_LENGTH = "standard_length";
    private static final String FIELD_SHEET_PAGE = "field_sheet_page";
    private static final String FIELD_SHEET_LINE = "field_sheet_line";
    private static final String BIODATA_TAXON_NAME = "biodata_taxon_name";
    private static final String CHARACTERISTIC = "characteristic";
    private static final String RESULT_VALUE = "result_value";

    @Override
    public BiodataEffortTaxonomy mapRow(ResultSet rs, int rowNum) throws SQLException {

        BiodataEffortTaxonomy biodataEffortTaxonomy = new BiodataEffortTaxonomy();

        biodataEffortTaxonomy.setDwEffortId(rs.getInt(DW_EFFORT_ID));
        biodataEffortTaxonomy.setPublishedTaxonName(rs.getString(PUBLISHED_TAXON_NAME));
        biodataEffortTaxonomy.setRawCount(rs.getInt(RAW_COUNT));
        biodataEffortTaxonomy.setWeight(rs.getInt(WEIGHT));
        biodataEffortTaxonomy.setTotalLength(rs.getInt(TOTAL_LENGTH));
        biodataEffortTaxonomy.setStandardLength(rs.getInt(STANDARD_LENGTH));
        biodataEffortTaxonomy.setFieldSheetPage(rs.getInt(FIELD_SHEET_PAGE));
        biodataEffortTaxonomy.setFieldSheetLine(rs.getInt(FIELD_SHEET_LINE));
        biodataEffortTaxonomy.setBiodataTaxonName(rs.getString(BIODATA_TAXON_NAME));
        biodataEffortTaxonomy.setCharacteristic(rs.getString(CHARACTERISTIC));
        biodataEffortTaxonomy.setResultValue(rs.getInt(RESULT_VALUE));

        return biodataEffortTaxonomy;
    }
}
