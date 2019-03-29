package gov.acwi.wqp.etl;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DbConfig {

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource-wqp")
	public DataSourceProperties wqpDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource-wqp")
	public DataSource wqpDataSource() {
		return wqpDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	@ConfigurationProperties("spring.datasource-biodata")
	public DataSourceProperties biodataDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource-biodata")
	public DataSource biodataDataSource() {
		return biodataDataSourceProperties().initializeDataSourceBuilder().build();
	}
}