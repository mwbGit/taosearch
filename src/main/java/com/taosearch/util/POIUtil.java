/**  
 * @Title: POIUtil.java
 * @Package com.srit.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author jiajia  
 * @date 2016年11月21日 下午6:58:33
 * @version V1.0  
 */
package com.taosearch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sun.misc.BASE64Decoder;

/**
 * @ClassName: POIUtil
 * @Description: POI工具类
 * @author jiajia
 * @date 2016年11月21日 下午6:58:33
 *
 */
public class POIUtil {
	public static XSSFCellStyle crateCellStyle(XSSFWorkbook wb) {
		XSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFont(createXssfFont(wb));
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setWrapText(true);
		return cellStyle;
	}

	public static XSSFFont createXssfFont(XSSFWorkbook wb) {
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("宋体");
		return font;
	}

	public static void createImage(String fileName, String data) {
		try {
			String[] url = data.split(",");
			String u = url[1];
			// Base64解码
			byte[] b = new BASE64Decoder().decodeBuffer(u);
			// 生成图片
			OutputStream out = new FileOutputStream(new File(fileName));
			out.write(b);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取单元格中的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(XSSFCell cell) {
		String cellValue = null;
		if (cell != null) {
			int type = cell.getCellType();
			DecimalFormat df = (DecimalFormat) NumberFormat.getPercentInstance();
			switch (type) {
			case XSSFCell.CELL_TYPE_STRING: // 字符串
				cellValue = cell.getStringCellValue();
				if (cellValue == null) {
					cellValue = " ";
				}
				break;
			case XSSFCell.CELL_TYPE_NUMERIC: // 数字
				double strCell = cell.getNumericCellValue();
				if (String.valueOf(strCell) == null) {
					cellValue = " ";
				}
				if (org.apache.poi.ss.usermodel.DateUtil.isValidExcelDate(strCell)) {
					return new SimpleDateFormat("yyyy-MM-dd").format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(strCell));
				}

				df.applyPattern("0");
				cellValue = df.format(strCell);
				if (Double.parseDouble(cellValue) != strCell) {
					df.applyPattern(Double.toString(strCell));
					cellValue = df.format(strCell);
				}

				break;
			case XSSFCell.CELL_TYPE_BLANK: // 空值
				cellValue = " ";
				break;
			default:
				System.out.print("\n---单元格格式不支持---");
				break;
			}
		}
		return cellValue == null ? "" : cellValue.trim();
	}

	/**
	 * 专利检查对象类型
	 * 
	 * @param s
	 * @return
	 */
	public static String getopctype(String s) {
		/**
		 * ----------------------------------------------专利--------------------
		 * ------------------------ 7.菜市场 8.规模以上商业零售经营单位 9.集贸市场 10.农副产品市场
		 * 11.食品生产企业
		 */
		switch (s) {
		case "菜市场":
			return "7";
		case "规模以上商业零售经营单位":
			return "8";
		case "集贸市场":
			return "9";
		case "农副产品市场":
			return "10";
		case "食品生产企业":
			return "11";
		default:
			return "444"; // 没有匹配上
		}

	}

	public static void main(String[] args) {
		String data = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABGUAAAPoCAYAAAB3Y0PWAAAgAElEQVR4nOzdz4sdV54n7PgzTK2UkXK5ytW0nBlXpl4Z3BpoKBUvqGswlIqiC2bAZhbemLFeZgzTTGmlojaioQyzmEEM6PVmFuWFYd7ZqLrT1TM1rwSvcC97kWgxzKqRoKC3+S58zs2TkefczBu6cU8o7vPAF1uZN1MnjyJvRHzi/GgaAAAAAAAAAAAAAAAAAAAAAAAAAAAAYPu6rrvfdd1RqEev+v2uX7/+Z4vF4vZisfhF+N73u647un79+p+taMPnsQ2Hh4cflz636nssFovbyc9xdHBwcO1Vfobke30+5Ht0XXd/sVj8YtXfcXh4+HHon0v/HV3XfbZYLG5f4nWfv+rPMLZN9DMAAAC8lg4ODq51XXcSqx+IXCQJdE4uqlXfu/c97pc+tyqM6Lru+av8LKkQ8MTvdbTu1/f69XkuIAp9t2zvRSFSC...wEnj/7nAAAAADf1qWRo+yK2f/pvVaUaV00LXMGAAAAgCu0Lpe+qBIRx0udMmfvGVcCAAAAeK/sdHnKMN/HYRiGtl3p/Lm+UKMoAwAAAPABaq27DPI9RsRpmqb7s/ubiFgiYs5Czv78GQAAAAD+IiIOl7phWlGm1ro7fyfvzxFx/LyTAgAAAPwgmSVzuhTom1uW1oh4Og8CrrVuI+L0eScFAAAA+GGywLKcB/3mvU12xSx9R0120hw+/7QAAAAAP0iOJC190G+v66g5lFKeI2K5tEYbAAAAgDfIUaW5lPLQgn5fGVlqXTOb1/4WAAAAAFfIEaVTK7R0Qb/rpaBfAAAAAD5Adr+8FvR7uhT0CwAAAMCNzldi99dzlOm3oF8AAAAA/iURsS+lPLfPEbF89ZkAAAAAfrzMlXkZazLCBAAAAPAJMvzXyBIAAADAZ5mm6T4iDl99DgAAAAAAAAAAAAAAAAAAAACA/xP/BbiqGxonrjdlAAAAAElFTkSuQmCC";
		String fileName = ConfigUtils.getProp("res_path") + "echart.png";
		createImage(fileName, data);
	}
}
