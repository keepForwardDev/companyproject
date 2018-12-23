package com.doctortech.fhq.utils.excel.annotation;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

public class ImportRow {

	@XmlAttribute
	private int index = 0;

	@XmlAttribute
	private int start = 0;

	@XmlAttribute
	private int end = 0;

	@XmlAttribute
	private String list;
	
	@XmlAttribute
	private String className;

	private List<ImportColumn> columns = new LinkedList<ImportColumn>();

	public List<ImportColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ImportColumn> columns) {
		this.columns = columns;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	

}
