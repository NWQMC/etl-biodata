package gov.acwi.wqp.etl.biodata.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DataQuality")
public class WqxDataQuality {
	private String precisionValue;

	public String getPrecisionValue() {
		return precisionValue;
	}
	@XmlElement(name = "PrecisionValue")
	public void setPrecisionValue(String precisionValue) {
		this.precisionValue = precisionValue;
	}
}
