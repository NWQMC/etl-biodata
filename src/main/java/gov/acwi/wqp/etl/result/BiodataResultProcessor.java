package gov.acwi.wqp.etl.result;

import gov.acwi.wqp.etl.biodata.biodataResult.BiodataBiodataResult;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BiodataResultProcessor implements ItemProcessor<BiodataBiodataResult, BiodataBiodataResult> {

    @Override
    public BiodataBiodataResult process(BiodataBiodataResult inputBdBiodataResult) {
        BiodataBiodataResult outputBdBiodataResult = new BiodataBiodataResult();


        outputBdBiodataResult.setResultId(inputBdBiodataResult.getResultId());
        outputBdBiodataResult.setDwEffortId(inputBdBiodataResult.getDwEffortId());
        outputBdBiodataResult.setPublishedTaxonName(inputBdBiodataResult.getPublishedTaxonName());

        outputBdBiodataResult.setGroupWeight(
                getGroupWeight(
                        inputBdBiodataResult.getRawCount()
                        ,inputBdBiodataResult.getWeight()
                )
        );

        outputBdBiodataResult.setRawCount(inputBdBiodataResult.getRawCount());
        outputBdBiodataResult.setWeight(inputBdBiodataResult.getWeight());

        outputBdBiodataResult.setResBioIndividualId(
                getResBioIndividualId(
                        inputBdBiodataResult.getFieldSheetPage()
                        ,inputBdBiodataResult.getFieldSheetLine()
                )
        );

        outputBdBiodataResult.setUnidentifiedSpeciesIdentifier(
                getUnidentifiedSpeciesIdentifier(
                        inputBdBiodataResult.getBiodataTaxonName()
                        ,inputBdBiodataResult.getPublishedTaxonName()
                )
        );

        outputBdBiodataResult.setTotalLength(inputBdBiodataResult.getTotalLength());
        outputBdBiodataResult.setStandardLength(inputBdBiodataResult.getStandardLength());
        outputBdBiodataResult.setFieldSheetPage(inputBdBiodataResult.getFieldSheetPage());
        outputBdBiodataResult.setFieldSheetLine(inputBdBiodataResult.getFieldSheetLine());
        outputBdBiodataResult.setBiodataTaxonName(inputBdBiodataResult.getBiodataTaxonName());
        outputBdBiodataResult.setCharacteristic(inputBdBiodataResult.getCharacteristic());
        outputBdBiodataResult.setResultValue(inputBdBiodataResult.getResultValue());


        return outputBdBiodataResult;
    }

    private Integer getGroupWeight(Integer rawCount, Integer weight) {
        return rawCount > 1 && weight > 0
                ? weight
                : null;
    }

    private String getResBioIndividualId(Integer fieldSheetPage, Integer fieldSheetLine) {
        return fieldSheetLine != null
                ? String.join("-", fieldSheetPage.toString(), fieldSheetLine.toString())
                : null;
    }

    private String getUnidentifiedSpeciesIdentifier(String biodataTaxonName, String publishedTaxonName) {
        return biodataTaxonName.equals(publishedTaxonName)
                ? null
                : biodataTaxonName;
    }
}
