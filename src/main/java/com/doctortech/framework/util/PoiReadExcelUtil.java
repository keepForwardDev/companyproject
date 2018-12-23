package com.doctortech.framework.util;

import java.util.ArrayList;
import com.doctortech.framework.util.poi.PoiExcel2k3Helper;
import com.doctortech.framework.util.poi.PoiExcel2k7Helper;
import com.doctortech.framework.util.poi.PoiExcelHelper;

/**
 * Excel统一POI处理类（针对2003以前和2007以后两种格式的兼容处理）
 * 
 * @author lyf
 * @date 2017-8-16 下午03:10:23
 * @note PoiHelper
 */
public class PoiReadExcelUtil {
	public static ArrayList<ArrayList<String>> readExcel(String filePath, int sheetIndex) {
		PoiExcelHelper helper = getPoiExcelHelper(filePath);
		//读取excel文件数据
		ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, sheetIndex);
		return dataList;
	}

	// 获取Excel处理类
	private static PoiExcelHelper getPoiExcelHelper(String filePath) {
		PoiExcelHelper helper;
		if (filePath.indexOf(".xlsx") != -1) {
			helper = new PoiExcel2k7Helper();
		} else {
			helper = new PoiExcel2k3Helper();
		}
		return helper;
	}

	//打印单元格数据
	private static void printBody(ArrayList<ArrayList<String>> dataList) {
		int index = 0;
		for (ArrayList<String> data : dataList) {
			index++;
			System.out.println();
			System.out.print(index);
			for (String v : data) {
				System.out.print("\t\t\t\t" + v);
			}
		}
	}
}
