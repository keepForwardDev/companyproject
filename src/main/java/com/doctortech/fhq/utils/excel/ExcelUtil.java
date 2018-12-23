package com.doctortech.fhq.utils.excel;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

public class ExcelUtil {
	private static final String EXTENSION_XLS = "xls";
	private static final String EXTENSION_XLSX = "xlsx";

	public static <T> T headOrBodyStr(int row, T head, T body) {
		if (row == 0)
			return head;
		return body;
	}

	/**
	 * 日期
	 * 
	 * @param book
	 * @param cell
	 */
	public static HSSFCellStyle createDateStyle(HSSFWorkbook book) {

		HSSFCellStyle cellStyle = book.createCellStyle();

		HSSFDataFormat format = book.createDataFormat();

		cellStyle.setDataFormat(format.getFormat("yyyy年m月d日"));

		return cellStyle;

	}

	/**
	 * 保留两位小数格式
	 * 
	 * @param book
	 * @param cell
	 */
	public static HSSFCellStyle createNumStyle(HSSFWorkbook book) {

		HSSFCellStyle cellStyle = book.createCellStyle();

		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

		return cellStyle;

	}

	/**
	 * 百分比格式
	 * 
	 * @param book
	 * @param cell
	 */
	public static HSSFCellStyle createPercentStyle(HSSFWorkbook book) {

		HSSFCellStyle cellStyle = book.createCellStyle();

		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));

		return cellStyle;

	}

	public static String getCellStr(XSSFCell cell) {
		if (cell == null)
			return "";
		String str = "";
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			str = cell.getRawValue();
			break;
		case STRING:
			str = cell.getRichStringCellValue().toString();
			break;
		case FORMULA:
			str = cell.getCellFormula();
			break;
		case BLANK:
			str = "";
			break;
		case BOOLEAN:
			str = cell.getBooleanCellValue() ? "是" : "否";
			break;
		case ERROR:
			str = "";
			break;
		default:
			str = "Unknown Cell Type: " + cell.getCellTypeEnum();
		}
		return org.apache.commons.lang3.StringUtils.replace(str, " ", "");
	}

	public static Date getCellDate(XSSFCell cell) {
		if (cell != null) {
			return cell.getDateCellValue();
		}

		return null;
	}

	public static String getCellRaw(XSSFCell cell) {
		if (cell != null) {
			return cell.getRawValue();
		}

		return "";
	}

	public static String getResult(Integer i, String zeroRe, String oneRe, String nullRe) {
		if (i == null)
			return nullRe;
		if (i == 0)
			return zeroRe;
		if (i == 1)
			return oneRe;
		return "";
	}

	/**
	 * 生成2007工作簿
	 * 
	 * @author jiaxm
	 * @date 2018年6月6日
	 * @param sheetName
	 * @param wb
	 * @param obj
	 *            可以传Map or bean
	 * @param headConf
	 *            表头与数据map对应 例如 name ： 名称，生成的工作簿表头为 名称 data.get(name)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static XSSFWorkbook creatXSSFExcel(String sheetName, XSSFWorkbook wb, List<?> obj,
			Map<String, String> headConf) throws Exception {
		if (obj == null || obj.isEmpty()) {
			return wb;
		}
		XSSFSheet sheet = wb.createSheet(sheetName);
		// 生成一个样式
		XSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		// 生成并设置另一个样式
		XSSFCellStyle style2 = wb.createCellStyle();
		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 如果直接传的map无需转换
		if (obj.get(0) instanceof Map) {
			// 生成文号sheet页
			exportXSSFExcel(sheet, (List<Map<String, Object>>) obj, headConf, style, style2);
		} else {
			List<Map<String, Object>> result = new ArrayList<>();
			for (Object o : obj) {
				result.add(objectToMap(o));
			}
			// 生成文号sheet页
			exportXSSFExcel(sheet, result, headConf, style, style2);
		}
		return wb;
	}

	/**
	 * bean 转 map
	 * 
	 * @author jiaxm
	 * @date 2018年6月6日
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			map.put(key, value);
		}

		return map;
	}

	/**
	 * 赋值excel数据
	 * 
	 * @author jiaxm
	 * @date 2018-06-06
	 * @param sheet
	 * @param result
	 *            数据集合，如果是bean 需要通过 {@link #objectToMap(Object)} 进行转换
	 * @param headConf
	 *            表头与数据map对应 例如 name ： 名称
	 * @param headStyle
	 *            表头样式
	 * @param dataStyle
	 *            数据样式
	 * @throws Exception
	 */
	public static void exportXSSFExcel(XSSFSheet sheet, List<Map<String, Object>> result, Map<String, String> headConf,
			XSSFCellStyle headStyle, XSSFCellStyle dataStyle) throws Exception {
		// 第一行生成表头
		XSSFRow head = sheet.createRow(0);
		Map<String, Object> data = null;
		String value = "";
		int temp = 0;// 表头单元格索引
		for (String key : headConf.keySet()) {
			createCell(head, temp, headConf.get(key), headStyle);
			temp++;
		}
		for (int i = 0; i < result.size(); i++) {
			int cellIndex = 0;// 数据单元格索引
			data = result.get(i);
			head = sheet.createRow(i + 1);
			for (String key : headConf.keySet()) {
				value = data.get(key) == null ? "" : data.get(key).toString();
				createCell(head, cellIndex, value, dataStyle);
				cellIndex++;
			}
		}
	}

	/**
	 * 创建单元格
	 * 
	 * @author jiaxm
	 * @date 2018-06-06
	 * @param row
	 * @param index
	 * @param value
	 * @param normalStyle
	 */
	private static void createCell(XSSFRow row, int index, String value, XSSFCellStyle normalStyle) {
		// 创建单元格
		XSSFCell cell = row.createCell(index);
		// 给第一个单元格赋值
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
		// 设置Style
		cell.setCellStyle(normalStyle);
	}

	public static Workbook getWorkbook(String filePath) throws Exception {
		Workbook workbook = null;
		InputStream is = new FileInputStream(filePath);
		if (filePath.endsWith(EXTENSION_XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (filePath.endsWith(EXTENSION_XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return workbook;
	}

	public static void preReadCheck(String filePath) throws FileNotFoundException, FileFormatException {
		// 常规检查
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FileNotFoundException("传入的文件不存在：" + filePath);
		}

		if (!(filePath.endsWith(EXTENSION_XLS) || filePath.endsWith(EXTENSION_XLSX))) {
			throw new FileFormatException("传入的文件不是excel");
		}
	}

	/**
	 * 读取excel
	 * 
	 * @author jiaxm
	 * @date 2018年6月8日
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Map<String, List<List<String>>> readExcel(String filePath) throws Exception {
		Map<String, List<List<String>>> map = new HashMap<>();
		List<List<String>> result = null;
		// 检查
		preReadCheck(filePath);
		// 获取workbook对象
		Workbook workbook = null;

		try {
			workbook = getWorkbook(filePath);
			// 读文件 一个sheet一个sheet地读取
			for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workbook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				System.out.println("=======================" + sheet.getSheetName() + "=========================");
				result = new ArrayList<>();
				int firstRowIndex = sheet.getFirstRowNum();
				int lastRowIndex = sheet.getPhysicalNumberOfRows();
				// 获取表头行
				Row head = sheet.getRow(0);
				int firstColumnIndex = head.getFirstCellNum(); // 首列
				int lastColumnIndex = head.getLastCellNum();// 最后一列
				// 读取数据行
				for (int rowIndex = firstRowIndex + 1; rowIndex < lastRowIndex; rowIndex++) {
					Row currentRow = sheet.getRow(rowIndex);// 当前行
					List<String> array = new ArrayList<>();
					for (int columnIndex = firstColumnIndex; columnIndex <lastColumnIndex; columnIndex++) {
						Cell currentCell = currentRow.getCell(columnIndex);// 当前单元格
						String currentCellValue = getCellValue(currentCell, true);// 当前单元格的值
						array.add(currentCellValue);
					}
					result.add(array);
				}
				map.put(sheet.getSheetName(), result);
			}
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	private static String getCellValue(Cell cell, boolean treatAsStr) {
		if (cell == null) {
			return "";
		}
		// 全部设置为文本读取
		if (treatAsStr) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}
}
