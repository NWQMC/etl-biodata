package gov.acwi.wqp.etl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static final Integer DATA_SOURCE_ID = 4;
	public static final String DATA_SOURCE = "BIODATA";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
