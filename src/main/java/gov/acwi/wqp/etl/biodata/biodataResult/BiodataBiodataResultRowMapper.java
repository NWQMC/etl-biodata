package gov.acwi.wqp.etl.biodata.biodataResult;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BiodataBiodataResultRowMapper implements RowMapper<BiodataBiodataResult> {

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
    public BiodataBiodataResult mapRow(ResultSet rs, int rowNum) throws SQLException {

        BiodataBiodataResult biodataBiodataResult = new BiodataBiodataResult();

        biodataBiodataResult.setDwEffortId(rs.getInt(DW_EFFORT_ID));
        biodataBiodataResult.setPublishedTaxonName(rs.getString(PUBLISHED_TAXON_NAME));
        biodataBiodataResult.setRawCount(rs.getInt(RAW_COUNT));
        biodataBiodataResult.setWeight(rs.getInt(WEIGHT));
        biodataBiodataResult.setTotalLength(rs.getInt(TOTAL_LENGTH));
        biodataBiodataResult.setStandardLength(rs.getInt(STANDARD_LENGTH));
        biodataBiodataResult.setFieldSheetPage(rs.getInt(FIELD_SHEET_PAGE));
        biodataBiodataResult.setFieldSheetLine(rs.getInt(FIELD_SHEET_LINE));
        biodataBiodataResult.setBiodataTaxonName(rs.getString(BIODATA_TAXON_NAME));
        biodataBiodataResult.setCharacteristic(rs.getString(CHARACTERISTIC));
        biodataBiodataResult.setResultValue(rs.getInt(RESULT_VALUE));

        return biodataBiodataResult;
    }
}
