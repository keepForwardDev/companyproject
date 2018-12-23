package com.doctortech.fhq.utils.excel.annotation;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

public class Row {

	@XmlAttribute
	private Short height = 380;
	
	@XmlAttribute
	private String list;

	private List<Column> columns = new LinkedList<Column>();

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Short getHeight() {
		return height;
	}

	public void setHeight(Short height) {
		this.height = height;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}
	
	

}
