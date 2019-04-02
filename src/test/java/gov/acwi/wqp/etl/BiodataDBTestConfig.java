
package gov.acwi.wqp.etl;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BiodataDBTestConfig {

	@Autowired
	@Qualifier("biodataDataSource")
	private DataSource dataSource;
	
	@Autowired
	private DatabaseConfigBean dbUnitDatabaseConfig;
	@Bean
	public DatabaseDataSourceConnectionFactoryBean biodata() throws SQLException {   
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig);
		dbUnitDatabaseConnection.setDataSource(dataSource);
		dbUnitDatabaseConnection.setSchema("biodata");
		return dbUnitDatabaseConnection;
	}
	
}
