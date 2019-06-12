package gov.acwi.wqp.etl.result;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.result.BiodataEffortTaxonomy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class BiodataResultProcessorTest extends BaseProcessorTest {

    private BiodataEffortTaxonomy biodataEffortTaxonomy;
    private BiodataResultProcessor processor;

    @Before
    public void setupTestClass() {
        biodataEffortTaxonomy = new BiodataEffortTaxonomy();
        processor = new BiodataResultProcessor();

        biodataEffortTaxonomy.setDwEffortId(TEST_DW_EFFORT_ID);
        biodataEffortTaxonomy.setPublishedTaxonName(TEST_PUBLISHED_TAXON_NAME);
        biodataEffortTaxonomy.setRawCount(TEST_RAW_COUNT);
        biodataEffortTaxonomy.setWeight(TEST_WEIGHT);
        biodataEffortTaxonomy.setTotalLength(TEST_TOTAL_LENGTH);
        biodataEffortTaxonomy.setStandardLength(TEST_STANDARD_LENGTH);
        biodataEffortTaxonomy.setFieldSheetPage(TEST_FIELD_SHEET_PAGE);
        biodataEffortTaxonomy.setFieldSheetLine(TEST_FIELD_SHEET_LINE);
        biodataEffortTaxonomy.setBiodataTaxonName(TEST_BIODATA_TAXON_NAME);
        biodataEffortTaxonomy.setCharacteristic(TEST_CHARACTERISTIC);
        biodataEffortTaxonomy.setResultValue(TEST_RESULT_VALUE);
    }

    @Test
    public void testProcess() {
        BiodataEffortTaxonomy actual = processor.process(biodataEffortTaxonomy);

        assertEquals(TEST_DW_EFFORT_ID, actual.getDwEffortId());
        assertEquals(TEST_PUBLISHED_TAXON_NAME, actual.getPublishedTaxonName());
        assertEquals(TEST_WEIGHT, actual.getGroupWeight());
        assertEquals(TEST_RAW_COUNT, actual.getRawCount());
        assertEquals(TEST_WEIGHT, actual.getWeight());
        assertEquals(String.join("-", TEST_FIELD_SHEET_PAGE.toString(), TEST_FIELD_SHEET_LINE.toString()), actual.getResBioIndividualId());
        assertEquals(TEST_BIODATA_TAXON_NAME, actual.getUnidentifiedSpeciesIdentifier());
        assertEquals(TEST_TOTAL_LENGTH, actual.getTotalLength());
        assertEquals(TEST_STANDARD_LENGTH, actual.getStandardLength());
        assertEquals(TEST_FIELD_SHEET_PAGE, actual.getFieldSheetPage());
        assertEquals(TEST_FIELD_SHEET_LINE, actual.getFieldSheetLine());
        assertEquals(TEST_BIODATA_TAXON_NAME, actual.getBiodataTaxonName());
        assertEquals(TEST_CHARACTERISTIC, actual.getCharacteristic());
        assertEquals(TEST_RESULT_VALUE, actual.getResultValue());
    }

    @Test
    public void testProcessWithNullValues() {
        biodataEffortTaxonomy.setFieldSheetLine(null);

        BiodataEffortTaxonomy actual = processor.process(biodataEffortTaxonomy);

        assertNull(actual.getResBioIndividualId());
    }

    @Test
    public void testGroupWeightWithZeroRawCount() {
        biodataEffortTaxonomy.setRawCount(0);

        BiodataEffortTaxonomy actual = processor.process(biodataEffortTaxonomy);

        assertNull(actual.getGroupWeight());
    }

    @Test
    public void testGroupWeightWithZeroWeight() {
        biodataEffortTaxonomy.setWeight(0);

        BiodataEffortTaxonomy actual = processor.process(biodataEffortTaxonomy);

        assertNull(actual.getGroupWeight());
    }

    @Test
    public void testUnidentifiedSpeciesIdentifierWithSameBiodataTaxonNames() {
        biodataEffortTaxonomy.setPublishedTaxonName(TEST_BIODATA_TAXON_NAME);

        BiodataEffortTaxonomy actual = processor.process(biodataEffortTaxonomy);

        assertNull(actual.getUnidentifiedSpeciesIdentifier());
    }

    @Test
    public void testUnidentifiedSpeciesIdentifierWithSamePublishedTaxonNames() {
        biodataEffortTaxonomy.setBiodataTaxonName(TEST_PUBLISHED_TAXON_NAME);

        BiodataEffortTaxonomy actual = processor.process(biodataEffortTaxonomy);

        assertNull(actual.getUnidentifiedSpeciesIdentifier());
    }
}
