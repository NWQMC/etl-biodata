package gov.acwi.wqp.etl.biodata.biodataResult;

public class BiodataBiodataResult {

    private Integer resultId;
    private Integer dwEffortId;
    private String publishedTaxonName;
    private Integer rawCount;
    private Integer weight;
    private Integer totalLength;
    private Integer standardLength;
    private Integer fieldSheetPage;
    private Integer fieldSheetLine;
    private String biodataTaxonName;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getDwEffortId() {
        return dwEffortId;
    }

    public void setDwEffortId(Integer dwEffortId) {
        this.dwEffortId = dwEffortId;
    }

    public String getPublishedTaxonName() {
        return publishedTaxonName;
    }

    public void setPublishedTaxonName(String publishedTaxonName) {
        this.publishedTaxonName = publishedTaxonName;
    }

    public Integer getRawCount() {
        return rawCount;
    }

    public void setRawCount(Integer rawCount) {
        this.rawCount = rawCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Integer totalLength) {
        this.totalLength = totalLength;
    }

    public Integer getStandardLength() {
        return standardLength;
    }

    public void setStandardLength(Integer standardLength) {
        this.standardLength = standardLength;
    }

    public Integer getFieldSheetPage() {
        return fieldSheetPage;
    }

    public void setFieldSheetPage(Integer fieldSheetPage) {
        this.fieldSheetPage = fieldSheetPage;
    }

    public Integer getFieldSheetLine() {
        return fieldSheetLine;
    }

    public void setFieldSheetLine(Integer fieldSheetLine) {
        this.fieldSheetLine = fieldSheetLine;
    }

    public String getBiodataTaxonName() {
        return biodataTaxonName;
    }

    public void setBiodataTaxonName(String biodataTaxonName) {
        this.biodataTaxonName = biodataTaxonName;
    }
}
