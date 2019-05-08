package gov.acwi.wqp.etl.biodata.projectData;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BiodataProjectDataRowMapper implements RowMapper<BiodataProjectData> {

    // set column name constants
    // private static final String CONSTANT = "column_name";

    @Override
    public BiodataProjectData mapRow(ResultSet rs, int rowNum) throws SQLException {
        BiodataProjectData biodataPD = new BiodataProjectData();

        // set fields

        return biodataPD;
    }
}
