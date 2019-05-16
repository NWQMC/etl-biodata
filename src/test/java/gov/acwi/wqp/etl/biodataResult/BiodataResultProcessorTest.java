package gov.acwi.wqp.etl.biodataResult;

import gov.acwi.wqp.etl.BaseProcessorTest;
import gov.acwi.wqp.etl.biodata.biodataResult.BiodataBiodataResult;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BiodataResultProcessorTest extends BaseProcessorTest {

    private BiodataBiodataResult inputBdBiodataResult;
    private BiodataResultProcessor processor;

    @Before
    public void setupTestClass() {
        inputBdBiodataResult = new BiodataBiodataResult();
        processor = new BiodataResultProcessor();

        inputBdBiodataResult.setResultId(TEST_RESULT_ID);
        inputBdBiodataResult.setDwEffortId(TEST_DW_EFFORT_ID);
        inputBdBiodataResult.setPublishedTaxonName(TEST_PUBLISHED_TAXON_NAME);
        inputBdBiodataResult.setRawCount(TEST_RAW_COUNT);
        inputBdBiodataResult.setWeight(TEST_WEIGHT);
        inputBdBiodataResult.setTotalLength(TEST_TOTAL_LENGTH);
        inputBdBiodataResult.setStandardLength(TEST_STANDARD_LENGTH);
        inputBdBiodataResult.setFieldSheetPage(TEST_FIELD_SHEET_PAGE);
        inputBdBiodataResult.setFieldSheetLine(TEST_FIELD_SHEET_LINE);
        inputBdBiodataResult.setBiodataTaxonName(TEST_BIODATA_TAXON_NAME);
        inputBdBiodataResult.setCharacteristic(TEST_CHARACTERISTIC);
        inputBdBiodataResult.setResultValue(TEST_RESULT_VALUE);
    }

    @Test
    public void testProcess() {
        BiodataBiodataResult actual = processor.process(inputBdBiodataResult);

        assertEquals(TEST_RESULT_ID, actual.getResultId());
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
        inputBdBiodataResult.setFieldSheetLine(null);

        BiodataBiodataResult actual = processor.process(inputBdBiodataResult);

        assertNull(actual.getResBioIndividualId());
    }

    @Test
    public void testGroupWeightWithZeroRawCount() {
        inputBdBiodataResult.setRawCount(0);

        BiodataBiodataResult actual = processor.process(inputBdBiodataResult);

        assertNull(actual.getGroupWeight());
    }

    @Test
    public void testGroupWeightWithZeroWeight() {
        inputBdBiodataResult.setWeight(0);

        BiodataBiodataResult actual = processor.process(inputBdBiodataResult);

        assertNull(actual.getGroupWeight());
    }

    @Test
    public void testUnidentifiedSpeciesIdentifierWithSameBiodataTaxonNames() {
        inputBdBiodataResult.setPublishedTaxonName(TEST_BIODATA_TAXON_NAME);

        BiodataBiodataResult actual = processor.process(inputBdBiodataResult);

        assertNull(actual.getUnidentifiedSpeciesIdentifier());
    }

    @Test
    public void testUnidentifiedSpeciesIdentifierWithSamePublishedTaxonNames() {
        inputBdBiodataResult.setBiodataTaxonName(TEST_PUBLISHED_TAXON_NAME);

        BiodataBiodataResult actual = processor.process(inputBdBiodataResult);

        assertNull(actual.getUnidentifiedSpeciesIdentifier());
    }
}
