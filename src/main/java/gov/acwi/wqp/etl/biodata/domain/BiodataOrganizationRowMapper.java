package gov.acwi.wqp.etl.biodata.domain;

import gov.acwi.wqp.etl.extract.domain.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BiodataOrganizationRowMapper implements RowMapper<BiodataOrganization> {

	public static final String ORGANIZATION_COLUMN_NAME = "organization";
	public static final String ORGANIZATION_NAME_COLUMN_NAME = "organization_name";
	public static final String PROJECT_IDENTIFIER_COLUMN_NAME = "project_identifier";
	public static final String PROJECT_NAME_COLUMN_NAME = "project_name";
	public static final String PROJECT_DESCRIPTION_TEXT_COLUMN_NAME = "project_description_text";

	@Override
	public BiodataOrganization mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataOrganization organization = new BiodataOrganization();
		organization.setOrganizationIdentifier(rs.getString(ORGANIZATION_COLUMN_NAME));
		organization.setOrganizationName(rs.getString(ORGANIZATION_NAME_COLUMN_NAME));
		organization.setProjectIdentifier(rs.getString(PROJECT_IDENTIFIER_COLUMN_NAME));
		organization.setProjectName(rs.getString(PROJECT_NAME_COLUMN_NAME));
		organization.setProjectDescriptionText(rs.getString(PROJECT_DESCRIPTION_TEXT_COLUMN_NAME));
		return organization;
	}

}
