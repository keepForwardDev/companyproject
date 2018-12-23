package com.doctortech.fhq.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class ExcelUtil {

    public static <T> T headOrBodyStr(int row ,T head ,T body){
        if(row==0) return head ;
        return body ;
    }
    
    /**
     * 日期
     * @param book
     * @param cell
     */
    public static HSSFCellStyle createDateStyle(HSSFWorkbook book ){
        
        HSSFCellStyle cellStyle = book.createCellStyle();
        
        HSSFDataFormat format= book.createDataFormat();

        cellStyle.setDataFormat(format.getFormat("yyyy年m月d日"));
        
        return cellStyle ;
        
    }
    
    /**
     * 保留两位小数格式
     * @param book
     * @param cell
     */
    public static HSSFCellStyle createNumStyle(HSSFWorkbook book){
        
        HSSFCellStyle cellStyle = book.createCellStyle();

        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        
        return cellStyle ;
        
    }
    
    /**
     * 百分比格式
     * @param book
     * @param cell
     */
    public static HSSFCellStyle createPercentStyle(HSSFWorkbook book){
        
        HSSFCellStyle cellStyle = book.createCellStyle();

        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
        
        return cellStyle ;
        
    }
    
    
    public static String getCellStr(XSSFCell cell){
        String s="";
		try {
			if(cell==null) return "" ;
			String str="" ;
			switch (cell.getCellTypeEnum()) {
			    case NUMERIC:
			        str= cell.getRawValue();
			        break ;
			    case STRING:
			        str=cell.getRichStringCellValue().toString();
			        break ;
			    case FORMULA:
			        str=cell.getCellFormula();
			        break ;
			    case BLANK:
			        str= "";
			        break ;
			    case BOOLEAN:
			        str=cell.getBooleanCellValue() ? "是" : "否";
			        break ;
			    case ERROR:
			        str= "";
			        break ;
			    default:
			        str= "Unknown Cell Type: " + cell.getCellTypeEnum();
			}
			s = StringUtils.replace(str, " ", "");
			if(StringUtils.isNotBlank(s)) {
				s=s.trim();
				s=CharacterUtil.filterOffUtf8Mb4(s);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return s;
    }
    
    public static Date getCellDate(XSSFCell cell){
        if(cell!=null){
            return cell.getDateCellValue();
        }
        
        return null ;
    }
    
    public static String getCellRaw(XSSFCell cell){
        if(cell!=null){
            return cell.getRawValue();
        }
        
        return "" ;
    }
  
    public static String getResult(Integer i ,String zeroRe , String oneRe ,String nullRe){
        if(i==null) return nullRe ;
        if(i==0) return zeroRe ;
        if(i==1) return oneRe ;
        return "" ;
    }
}
