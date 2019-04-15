package gov.acwi.wqp.etl.biodata.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BiodataOrgDataRowMapper implements RowMapper<BiodataOrgData>{
	@Override
	public BiodataOrgData mapRow(ResultSet rs, int rowNum) throws SQLException {
		BiodataOrgData bioDataOD = new BiodataOrgData();
		return bioDataOD;
	}

}
