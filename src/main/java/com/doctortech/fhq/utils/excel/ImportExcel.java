package com.doctortech.fhq.utils.excel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import com.doctortech.fhq.utils.ReflectHelper;
import com.doctortech.fhq.utils.excel.annotation.ImportColumn;
import com.doctortech.fhq.utils.excel.annotation.ImportRow;

/*
 * excel 导入数据
 */
@SuppressWarnings("all")
public class ImportExcel<T> {

	private Class clazz;

	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	private List data = new ArrayList();

	private StringBuffer errorMsg = new StringBuffer();

	public ImportExcel(Class _clazz) {
		this.clazz = _clazz;
	}

	public ImportExcel() {
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public List readExcelContent(InputStream is, int startRow) {
		// Map<Integer, String> content = new HashMap<Integer, String>();
		String str = StringUtils.EMPTY;
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		// if((rowNum+startRow)<2001){
		for (int i = startRow; i <= rowNum; i++) {
			row = sheet.getRow(i);
			try {
				Object obj = clazz.newInstance();
				Field[] fields = clazz.getDeclaredFields();
				for (int k = 0; k < colNum; k++) {
					str += getStringCellValue(row.getCell((short) k)).trim();
				}
				if (!StringUtils.isBlank(str)) {
					int j = 0;
					while (j <= colNum) {
						// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
						// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
						// str += getStringCellValue(row.getCell((short)
						// j)).trim() +
						// "-";
						str = getStringCellValue(row.getCell((short) j)).trim();

						// dateutil
						if (j < fields.length) {
							Field field = fields[j];
							String typeName = field.getGenericType().getTypeName();
							if (typeName.equals(Date.class.getName())) {
								Date date = null;
								if (!StringUtils.isBlank(str)) {
									double d = Double.valueOf(str);
									date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(d);
								}
								ReflectHelper.setValueByFieldName(obj, field.getName(), date);
							}else if(typeName.equals(Integer.class.getName())){
								if (!StringUtils.isBlank(str)) {
									ReflectHelper.setValueByFieldName(obj, field.getName(), Integer.parseInt(str));
								}else{
									ReflectHelper.setValueByFieldName(obj, field.getName(), 0);
								}
							}else if(typeName.equals(Double.class.getName())){
								if (!StringUtils.isBlank(str)) {
									ReflectHelper.setValueByFieldName(obj, field.getName(), Double.valueOf(str));
								}else{
									ReflectHelper.setValueByFieldName(obj, field.getName(), 0.0);
								}
							}else if(typeName.equals(Boolean.class.getName())){
								if (!StringUtils.isBlank(str)) {
									ReflectHelper.setValueByFieldName(obj, field.getName(), str);
								}else{
									ReflectHelper.setValueByFieldName(obj, field.getName(), null);
								}
							}else{
								ReflectHelper.setValueByFieldName(obj, field.getName(), str);
							}
						}
						j++;
					}
					data.add(obj);
				} else {
					break;
				}
				if (data.size() > 2000) {
					errorMsg.append("导入数据大于2000行拒绝导入");
				}
			} catch (Exception e) {
				errorMsg.append("第" + i + "行数据错误,请检查正确后再导入.");
				e.printStackTrace();
			}
		}
		// }else{

		// }
		return data;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = StringUtils.EMPTY;
		if (cell != null && cell.getCellType() >= 0)
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				cell.setCellType(Cell.CELL_TYPE_STRING);
				strCell = cell.getStringCellValue();
				// strCell = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = StringUtils.EMPTY;
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = StringUtils.EMPTY;
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public StringBuffer getErrorMsg() {
		return errorMsg;
	}

	public Map<String, Object> importExcelByXml(List<ImportRow> lists, InputStream is) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		String str = StringUtils.EMPTY;
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
        List<Object> list = new ArrayList<Object>();
		for (ImportRow importrow : lists) {
			List<ImportColumn> columns = importrow.getColumns();
			if (StringUtils.equals(importrow.getList(), Boolean.TRUE.toString())) {
				for (int i = importrow.getStart(); i < importrow.getEnd(); i++) {
					row = sheet.getRow(i - 1);
					Object obj = Class.forName(importrow.getClassName()).newInstance();
					
					for (ImportColumn column : columns) {
						str = getStringCellValue(row.getCell((short) ((int) column.getIndex() - 1))).trim();
						Field f = ReflectHelper.getFieldByFieldName(obj, column.getProperty());
						
						if (Date.class.getName().equals(f.getGenericType().getTypeName())) {
							Date date = null;
							if (!StringUtils.isBlank(str)) {
								double d = Double.valueOf(str);
								date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(d);
								ReflectHelper.setFieldValue(obj, f.getName(), date);
							}
						}else if(Integer.class.getName().equals(f.getGenericType().getTypeName())
								|| int.class.getName().equals(f.getGenericType().getTypeName())){
							if(!StringUtils.isBlank(str)){
								ReflectHelper.setFieldValue(obj, f.getName(), Integer.valueOf(str));
							}
						}else if(Double.class.getName().equals(f.getGenericType().getTypeName())
								|| double.class.getName().equals(f.getGenericType().getTypeName())){
							if(!StringUtils.isBlank(str)){
								ReflectHelper.setFieldValue(obj, f.getName(), Double.valueOf(str));
							}
						}else if(Float.class.getName().equals(f.getGenericType().getTypeName())
								|| float.class.getName().equals(f.getGenericType().getTypeName())){
							if(!StringUtils.isBlank(str)){
								ReflectHelper.setFieldValue(obj, f.getName(), Float.valueOf(str));
							}
						}else if(Float.class.getName().equals(f.getGenericType().getTypeName())
								|| float.class.getName().equals(f.getGenericType().getTypeName())){
							if(!StringUtils.isBlank(str)){
								ReflectHelper.setFieldValue(obj, f.getName(), Float.valueOf(str));
							}
						} else{
							ReflectHelper.setFieldValue(obj, f.getName(), str);
						}
					}
					
					list.add(obj);
				}
				map.put("list", list);

			} else {
				row = sheet.getRow(importrow.getIndex() - 1);
				for (ImportColumn column : columns) {
					str = getStringCellValue(row.getCell((short) ((int) column.getIndex() - 1))).trim();
					if (Date.class.getName().equals(column.getType())) {
						Date date = null;
						if (!StringUtils.isBlank(str)) {
							double d = Double.valueOf(str);
							date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(d);
						}
						if (date != null)
							map.put(column.getProperty(), date);
					} else {
						map.put(column.getProperty(), str);
					}
				}
			}
		}

		return map;
	}

}
