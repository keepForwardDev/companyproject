package com.doctortech.fhq.utils.excel.annotation;

import javax.xml.bind.annotation.XmlAttribute;

public class Column {

	@XmlAttribute
	private String type = Type.name.name();
	@XmlAttribute
	private String listproperty;// type 为list 时，listproperty 表示属性
	@XmlAttribute
	private String value;
	@XmlAttribute 
	private Integer rowspan = 0;
	@XmlAttribute
	private Integer colspan = 0;
	@XmlAttribute
	private String align = Type.left.name();
	@XmlAttribute
	private Short fontsize = 10;// 字体大小
	@XmlAttribute
	private Integer width = 10;// 宽度
	@XmlAttribute
	private Double ratio=1d;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getRowspan() {
		return rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public Integer getColspan() {
		return colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Short getFontsize() {
		return fontsize;
	}

	public void setFontsize(Short fontsize) {
		this.fontsize = fontsize;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getListproperty() {
		return listproperty;
	}

	public void setListproperty(String listproperty) {
		this.listproperty = listproperty;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}


	public enum Type {
		list, name, property, left, right,
	}

}
