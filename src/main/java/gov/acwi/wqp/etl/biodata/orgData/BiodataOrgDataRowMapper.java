package gov.acwi.wqp.etl.biodata.orgData;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BiodataOrgDataRowMapper implements RowMapper<BiodataOrgData>{
	
	public static final String ORGANIZATION_ID = "organization_id";
	public static final String ORGANIZATION = "organization";
	public static final String ORGANIZATION_NAME = "organization_name";
	
	@Override
	public BiodataOrgData mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataOrgData bioDataOD = new BiodataOrgData();
		
		bioDataOD.setOrganizationId(rs.getInt(ORGANIZATION_ID));
		bioDataOD.setOrganization(rs.getString(ORGANIZATION));
		bioDataOD.setOrganizationName(rs.getString(ORGANIZATION_NAME));
		
		return bioDataOD;
	}

}
