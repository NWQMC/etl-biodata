package gov.acwi.wqp.etl.biodata.projectData;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BiodataProjectDataRowMapper implements RowMapper<BiodataProjectData> {

    private static final String DW_PROJECT_ID = "dw_project_id";
    private static final String ORGANIZATION = "organization";
    private static final String ORGANIZATION_NAME = "organization_name";
    private static final String PROJECT_ID = "project_id";
    private static final String PROJECT_NAME = "project_name";
    private static final String PROJECT_ABSTRACT = "project_abstract";


    @Override
    public BiodataProjectData mapRow(ResultSet rs, int rowNum) throws SQLException {
        BiodataProjectData biodataPD = new BiodataProjectData();

        biodataPD.setDwProjectId(rs.getInt(DW_PROJECT_ID));
        biodataPD.setOrganization(rs.getString(ORGANIZATION));
        biodataPD.setOrganizationName(rs.getString(ORGANIZATION_NAME));
        biodataPD.setProjectId(rs.getString(PROJECT_ID));
        biodataPD.setProjectName(rs.getString(PROJECT_NAME));
        biodataPD.setProjectAbstract(rs.getString(PROJECT_ABSTRACT));

        return biodataPD;
    }
}
