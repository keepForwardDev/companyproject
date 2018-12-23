package com.doctortech.fhq.utils.excel.annotation;

import javax.xml.bind.annotation.XmlAttribute;

public class ImportColumn {
	@XmlAttribute
	private String property;
	@XmlAttribute
	private Integer index=0;
	@XmlAttribute
	private String type;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
