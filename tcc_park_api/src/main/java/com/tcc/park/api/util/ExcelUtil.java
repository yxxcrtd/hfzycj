package com.tcc.park.api.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	public static String getCellValue(Cell c) {
		String cellValue = null;
		if (c != null) {
			switch (c.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				cellValue = c.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = String.valueOf(new DecimalFormat("#.#").format(c
						.getNumericCellValue()));
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(c.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = "";
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = String.valueOf(c.getCellFormula());
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = "";
				break;
			default:
				cellValue = null;
			}

		}
		return cellValue;
	}
	
	
	public static Double getCellDoubleValue(Cell c) {
		Double cellValue = null;
		if (c != null) {
			switch (c.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = c.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				cellValue = null;
				break;
			default:
				cellValue = null;
			}

		}
		return cellValue;
	}
	
	
	public static boolean regCheck(String regEx, String srcString, boolean nullResult) {
		if (StringUtils.isEmpty(srcString))
			return nullResult;
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(srcString);
		boolean rs = mat.find();
		return rs;
	}
}
