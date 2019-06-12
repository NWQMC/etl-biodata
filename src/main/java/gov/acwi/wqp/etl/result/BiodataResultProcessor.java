package gov.acwi.wqp.etl.result;

import gov.acwi.wqp.etl.biodata.result.BiodataEffortTaxonomy;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BiodataResultProcessor implements ItemProcessor<BiodataEffortTaxonomy, BiodataEffortTaxonomy> {

    @Override
    public BiodataEffortTaxonomy process(BiodataEffortTaxonomy biodataEffortTaxonomy) {
        BiodataEffortTaxonomy biodataResult = new BiodataEffortTaxonomy();


        biodataResult.setDwEffortId(biodataEffortTaxonomy.getDwEffortId());
        biodataResult.setPublishedTaxonName(biodataEffortTaxonomy.getPublishedTaxonName());

        biodataResult.setGroupWeight(
                getGroupWeight(
                        biodataEffortTaxonomy.getRawCount()
                        ,biodataEffortTaxonomy.getWeight()
                )
        );

        biodataResult.setRawCount(biodataEffortTaxonomy.getRawCount());
        biodataResult.setWeight(biodataEffortTaxonomy.getWeight());

        biodataResult.setResBioIndividualId(
                getResBioIndividualId(
                        biodataEffortTaxonomy.getFieldSheetPage()
                        ,biodataEffortTaxonomy.getFieldSheetLine()
                )
        );

        biodataResult.setUnidentifiedSpeciesIdentifier(
                getUnidentifiedSpeciesIdentifier(
                        biodataEffortTaxonomy.getBiodataTaxonName()
                        ,biodataEffortTaxonomy.getPublishedTaxonName()
                )
        );

        biodataResult.setTotalLength(biodataEffortTaxonomy.getTotalLength());
        biodataResult.setStandardLength(biodataEffortTaxonomy.getStandardLength());
        biodataResult.setFieldSheetPage(biodataEffortTaxonomy.getFieldSheetPage());
        biodataResult.setFieldSheetLine(biodataEffortTaxonomy.getFieldSheetLine());
        biodataResult.setBiodataTaxonName(biodataEffortTaxonomy.getBiodataTaxonName());
        biodataResult.setCharacteristic(biodataEffortTaxonomy.getCharacteristic());
        biodataResult.setResultValue(biodataEffortTaxonomy.getResultValue());


        return biodataResult;
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
