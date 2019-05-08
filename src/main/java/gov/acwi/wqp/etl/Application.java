package gov.acwi.wqp.etl;

import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static final String DATASOURCE_BIODATA_QUALIFIER = "dataSourceBiodata";
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
