package com.doctortech.fhq.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import com.doctortech.fhq.utils.DateUtil;
import com.doctortech.fhq.utils.ReflectHelper;
import com.doctortech.fhq.utils.excel.annotation.Column;
import com.doctortech.fhq.utils.excel.annotation.Column.Type;
import com.doctortech.fhq.utils.excel.annotation.Row;
import com.doctortech.fhq.utils.excel.annotation.SelectChoose;

/*
 * excel 辅助类
 */
public class ExportExcel<T> {

	@SuppressWarnings("all")
	public HSSFWorkbook exportExcelByXml(List<Row> listrow, InputStream fis, Map<String, Object> map) throws Exception {
		// FileInputStream fis = fis = new FileInputStream(template);
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		// 生成一个表格
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);

		HSSFFont font1 = createFont(workbook, (short)10);

		HSSFCellStyle style = createCellStyle(workbook, font1, Type.left.name());

		HSSFRow row = null;
		// 遍历集合数据，产生数据行
		Iterator<Row> it = listrow.iterator();
		int index = 0;
		while (it.hasNext()) {
			Row xmlrow = it.next();
			row = sheet.createRow(index);
			// row.setHeight(xmlrow.getHeight());
			row.setHeight(xmlrow.getHeight());

			if (StringUtils.equals(xmlrow.getList(), Boolean.TRUE.toString())) {
				List<Object> list = (List<Object>)map.get("list");
				//写表头
				List<Column> cols = xmlrow.getColumns();
				for(int i=0;i<cols.size();i++){
					
					Column column = cols.get(i);
					HSSFCell cell = row.createCell(i);
					font1 =(column.getFontsize()==(short)10)?font1:createFont(workbook, column.getFontsize());
					style = (Type.left.name().equals(column.getAlign()))?style:createCellStyle(workbook, font1, column.getAlign());
					cell.setCellStyle(style);
					sheet.setColumnWidth(i, column.getWidth() * 255);
					String value = null;

					value = column.getValue();

					HSSFRichTextString richString = new HSSFRichTextString(value);
					cell.setCellValue(richString);
					
				}
				index++;
				//写内容
				for(Object obj: list){
					row = sheet.createRow(index);
					row.setHeight(xmlrow.getHeight());
					for(int i=0;i<cols.size();i++){
						Column column=cols.get(i);
						HSSFCell cell = row.createCell(i);
						font1 = (column.getFontsize()==(short)10)?font1:createFont(workbook, column.getFontsize());
						style = (Type.left.name().equals(column.getAlign()))?style:createCellStyle(workbook, font1, column.getAlign());
						cell.setCellStyle(style);
						sheet.setColumnWidth(i, column.getWidth() * 255);
						
						String property = column.getListproperty();
						String value = StringUtils.EMPTY;
						
						//System.out.println(property);
						Field field = ReflectHelper.getFieldByFieldName(obj, property);
						Object object=ReflectHelper.getValueByFieldName(obj, property);
						if(Date.class.getName().equals(field.getGenericType().getTypeName())){
							if(object!=null){
								value = String.valueOf(DateUtil.formatDate((Date)object));
							}
						}else if(Integer.class.getName().equals(field.getGenericType().getTypeName())
								|| int.class.getName().equals(field.getGenericType().getTypeName())
								|| Boolean.class.getName().equals(field.getGenericType().getTypeName())
								|| boolean.class.getName().equals(field.getGenericType().getTypeName())
								|| Short.class.getName().equals(field.getGenericType().getTypeName())
								|| short.class.getName().equals(field.getGenericType().getTypeName())
								|| Double.class.getName().equals(field.getGenericType().getTypeName())
								|| double.class.getName().equals(field.getGenericType().getTypeName())
								|| Float.class.getName().equals(field.getGenericType().getTypeName())
								|| float.class.getName().equals(field.getGenericType().getTypeName())){
							if(object!=null){
								if(column.getRatio()>1){
									if(object instanceof Double){
										object=((Double)(object))*column.getRatio();
									}else if(object instanceof Float){
										object=((Float)(object))*column.getRatio();
									}
								}
								value = String.valueOf(object);
							}
						}else if(String.class.getName().equals(field.getGenericType().getTypeName())){
							if(object!=null){
								value=(String)object;
							}
						}
						
						HSSFRichTextString richString = new HSSFRichTextString(value);
						cell.setCellValue(richString);
						
					}
					index++;
				}
				

			} else {
				int t_colspan =0;
				for (int i = 0; i < xmlrow.getColumns().size(); i++) {
					Column column = xmlrow.getColumns().get(i);
					HSSFCell cell = row.createCell(i+t_colspan);

					font1 = (column.getFontsize()==(short)10)?font1:createFont(workbook, column.getFontsize());
					style = (Type.left.name().equals(column.getAlign()))?style:createCellStyle(workbook, font1, column.getAlign());
					cell.setCellStyle(style);

					sheet.setColumnWidth(i+t_colspan, column.getWidth() * 255);

					// 合并单元格
					int rowspan = column.getRowspan();
					int colspan = column.getColspan();
					if (rowspan > 0 || colspan > 0) {
						//System.out.println("index:"+(short) index);
						//System.out.println("index + rowspan:"+(short) (index + rowspan));
						//System.out.println("i+t_colspan:"+(short) (i+t_colspan));
						int lastcols=0;
						if(t_colspan>0){
							lastcols=t_colspan+i + colspan - 1;
						}else{
							lastcols=i+colspan-1;
						}
						//System.out.println("lastcols:"+lastcols);
						CellRangeAddress cra = new CellRangeAddress( index,  (index + rowspan),  (i+t_colspan),lastcols);
						sheet.addMergedRegion(cra);
						// 使用RegionUtil类为合并后的单元格添加边框
						RegionUtil.setBorderBottom(1, cra, sheet, workbook); // 下边框
						RegionUtil.setBorderLeft(1, cra, sheet, workbook); // 左边框
						RegionUtil.setBorderRight(1, cra, sheet, workbook); // 有边框
						RegionUtil.setBorderTop(1, cra, sheet, workbook); // 上边框
					}

					String value = null;

					value = column.getValue();

					if (Column.Type.property.name().equals(column.getType())) {
						// Object objval =ReflectHelper.getValueByFieldName(obj,
						// column.getValue());
						Object objval = map.get(column.getValue());
						if (objval != null) {
							if(column.getRatio()>1){
								if(objval instanceof Double){
									objval=((Double)(objval))*column.getRatio();
								}else if(objval instanceof Float){
									objval=((Float)(objval))*column.getRatio();
								}
							}
							value = String.valueOf(objval);
						} else {
							value = StringUtils.EMPTY;
						}
					}

					HSSFRichTextString richString = new HSSFRichTextString(value);
					cell.setCellValue(richString);
					
					if(colspan>0){
						t_colspan=t_colspan+colspan-1;
					}
					
					
				}
				index++;
			}

		}
		//index++;
		try {
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workbook;
	}

	public HSSFWorkbook exportExcel(String title, Collection<T> dataset, File template) throws Exception {
		return this.exportExcel(title, null, dataset, template, "yyyy-MM-dd", 1);
	}

	public HSSFWorkbook exportExcel(String title, String[] headers, Collection<T> dataset, File template,
			String pattern, Integer startIndex) throws Exception {
		FileInputStream fis = fis = new FileInputStream(template);

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		// 生成一个表格
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 生成另一个字体
		HSSFFont font3 = workbook.createFont();
		font3.setColor(HSSFColor.BLUE.index);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		// comment.setAuthor("jied");

		// 产生表格标题行
		HSSFRow row = null;
		if (headers != null) {
			row = sheet.createRow(0);
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = startIndex;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				try {
					// 拿到注解
					Annotation[] annotations = field.getDeclaredAnnotations();
					Object value = ReflectHelper.getValueByFieldName(t, fieldName);

					// 判断值的类型后进行强制类型转换
					String textValue = null;

					String convert = StringUtils.EMPTY;

					if (annotations != null && annotations.length > 0) {
						for (Annotation a : annotations) {
							if (a instanceof SelectChoose) {
								convert = convertSelectChoose((SelectChoose) a, value);
							}
						}
					}

					if (value != null) {
						if (!StringUtils.isBlank(convert)) {
							textValue = convert;
						} else if (value instanceof Boolean) {
							boolean bValue = (Boolean) value;
							textValue = "男";
							if (!bValue) {
								textValue = "女";
							}
						} else if (value instanceof Date) {
							Date date = (Date) value;
							SimpleDateFormat sdf = new SimpleDateFormat(pattern);
							textValue = sdf.format(date);
						} else if (value instanceof byte[]) {
							// 有图片时，设置行高为60px;
							row.setHeightInPoints(60);
							// 设置图片所在列宽度为80px,注意这里单位的一个换算
							sheet.setColumnWidth(i, (short) (35.7 * 80));
							// sheet.autoSizeColumn(i);
							byte[] bsValue = (byte[]) value;
							HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
									index);
							anchor.setAnchorType(2);
							patriarch.createPicture(anchor,
									workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
						} else {
							// 其它数据类型都当作字符串简单处理
							textValue = value.toString();
						}
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
//							HSSFFont font3 = workbook.createFont();
//							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
					// fis.close();
				}
			}
		}
		try {
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return workbook;
		// try {
		// workbook.write(out);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	private String convertSelectChoose(SelectChoose sc, Object value) {
		Map<String, String> map = new HashMap<String, String>();
		String[] values = sc.value();
		for (String v : values) {
			String[] vv = StringUtils.split(v, ":");
			map.put(vv[0], vv[1]);
		}

		if (value != null) {
			return map.get(value.toString());
		} else {
			return StringUtils.EMPTY;
		}
	}

	private HSSFCellStyle createCellStyle(HSSFWorkbook workbook, HSSFFont font, String align) {
		HSSFCellStyle style = workbook.createCellStyle();
		if (Column.Type.left.name().equals(align)) {
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		} else if (Column.Type.right.name().equals(align)) {
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		} else {
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);

		// 设置边框
		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		if (font != null) {
			style.setFont(font);
		}

		return style;
	}

	private HSSFFont createFont(HSSFWorkbook workbook, short fontsize) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontsize);
		// font.setColor(HSSFColor.RED.index);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		return font;
	}

}
