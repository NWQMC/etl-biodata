package gov.acwi.wqp.etl.monitoringLocation;

import gov.acwi.wqp.etl.biodata.domain.BiodataStation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * TODO: ALL GENERATED CODE, MAKE TESTS FOR THIS CLASS
 */
public class MonitoringLocationProcessorTest {
	
	public MonitoringLocationProcessorTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of process method, of class MonitoringLocationProcessor.
	 */
	@Test
	public void testProcess() throws Exception {
		System.out.println("process");
		BiodataStation biodataStation = null;
		MonitoringLocationProcessor instance = new MonitoringLocationProcessor();
		MonitoringLocation expResult = null;
		MonitoringLocation result = instance.process(biodataStation);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
