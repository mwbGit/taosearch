package com.taosearch.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Util {

	public static final int DEFAULT = 0;

	public static final int YM = 1;

	public static final int NO_SLASH = 2;

	public static final int YM_NO_SLASH = 3;

	public static final int DATE_TIME = 4;

	public static final int DATE_TIME_NO_SLASH = 5;

	public static final int DATE_HM = 6;

	public static final int TIME = 7;

	public static final int HM = 8;

	public static final int Y_M_D_H = 9;

	/**
	 * if input is null return ""
	 * 
	 * @param input
	 * @return
	 */
	public static String null2Blank(String input) {
		if (input == null) {
			return "";
		}
		return input;
	}

	/**
	 * if input is null return ""
	 * 
	 * @param input
	 * @return
	 */
	public static Integer null2Blank(Integer input) {
		if (input == null) {
			return 0;
		}
		return input;
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date, String pattern) {
		if (date == null)
			return null;
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return YYYY/MM/DD
	 */
	public static String dateToStr(Date date) {
		return dateToStr(date, "yyyy/MM/dd");
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	protected static Date strToDate(String date, String pattern) {
		if (date == null || date.equals(""))
			return null;
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e.toString());
		}
	}

	/**
	 * 
	 * @param date
	 *            YYYY/MM/DD
	 * @return
	 */
	public static Date strToDate(String date) {
		return strToDate(date, "yyyy/MM/dd");
	}

	public static String dateToStr(Date date, int type) {
		switch (type) {
		case DEFAULT:
			return dateToStr(date);
		case YM:
			return dateToStr(date, "yyyy/MM");
		case NO_SLASH:
			return dateToStr(date, "yyyyMMdd");
		case YM_NO_SLASH:
			return dateToStr(date, "yyyyMM");
		case DATE_TIME:
			return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
		case Y_M_D_H:
			return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
		case DATE_TIME_NO_SLASH:
			return dateToStr(date, "yyyyMMddHHmmss");
		case DATE_HM:
			return dateToStr(date, "yyyy/MM/dd HH:mm");
		case TIME:
			return dateToStr(date, "HH:mm:ss");
		case HM:
			return dateToStr(date, "HH:mm");
		default:
			throw new IllegalArgumentException("Type undefined : " + type);
		}
	}

	public static Date strToDate(String date, int type) {
		switch (type) {
		case DEFAULT:
			return strToDate(date);
		case YM:
			return strToDate(date, "yyyy/MM");
		case NO_SLASH:
			return strToDate(date, "yyyyMMdd");
		case YM_NO_SLASH:
			return strToDate(date, "yyyyMM");
		case DATE_TIME:
			return strToDate(date, "yyyy/MM/dd HH:mm:ss");
		case Y_M_D_H:
			return strToDate(date, "yyyy-MM-dd HH:mm:ss");
		case DATE_TIME_NO_SLASH:
			return strToDate(date, "yyyyMMddHHmmss");
		case DATE_HM:
			return strToDate(date, "yyyy/MM/dd HH:mm");
		case TIME:
			return strToDate(date, "HH:mm:ss");
		case HM:
			return strToDate(date, "HH:mm");
		default:
			throw new IllegalArgumentException("Type undefined : " + type);
		}
	}

	private static String images = "psd,pdd,bmp,rle,gif,eps,jpg,jpe,pcx,pdf,pdp,pct,pid,pxr,png,raw,sct,tga,vda,icb,vst,tif";

	public static boolean isImage(String filename) {
		boolean isImage = false;
		int lastdot = filename.lastIndexOf(".");
		String expName = filename.substring(lastdot + 1);
		String[] tImages = images.split(",");
		for (int i = 0, size = tImages.length; i < size; i++) {
			if (expName.equalsIgnoreCase(tImages[i])) {
				isImage = true;
				break;
			}
		}
		return isImage;
	}

	public static String toString(Number num) {
		if (num == null)
			return null;
		return toBigDecimal(num).toString();
	}

	public static BigDecimal toBigDecimal(Number num) {
		return (num instanceof BigDecimal ? (BigDecimal) num : new BigDecimal(num.toString()));
	}

	/**
	 * 将字符串时间进行格式化成yyyy-mm-dd hh:mm:ss
	 * 
	 * @param strDateTime
	 * @return
	 */
	public static String formatDateTime(String strDateTime) {
		String strRe = "";
		String year = "";
		String month = "";
		String day = "";
		String hour = "";
		String minute = "";
		String second = "";
		int iLength = strDateTime.length();
		if (iLength == 14) {
			year = strDateTime.substring(0, 4);
			month = strDateTime.substring(4, 6);
			day = strDateTime.substring(6, 8);
			hour = strDateTime.substring(8, 10);
			minute = strDateTime.substring(10, 12);
			second = strDateTime.substring(12, 14);
			strRe = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		}
		return strRe;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */

	public static long round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).longValue();
	}

	// 转unicode
	public static String convert(String s) {

		String unicode = "";
		char[] charAry = new char[s.length()];
		for (int i = 0; i < charAry.length; i++) {
			charAry[i] = (char) s.charAt(i);
			unicode += "\\u" + Integer.toString(charAry[i], 16);
		}
		return unicode;
	}

	/**
	 * 
	 * @Title: checkParameter
	 * @Description: 对POST请求参数进行非空验证和编码转换
	 * @param @param parameter
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author jiajia
	 * @date 2016年11月16日 下午8:18:07
	 * @throws
	 */
	public static String checkPOSTParameter(String parameter) {
		try {
			return URLDecoder.decode(parameter != null ? parameter : "", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @Title: checkGETParameter
	 * @Description: 对GET请求参数进行非空验证和编码转换
	 * @param @param parameter
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author jiajia
	 * @date 2016年11月16日 下午8:22:26
	 * @throws
	 */
	public static String checkGETParameter(String parameter) {
		if (parameter != null) {
			try {
				return new String(parameter.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 
	 * @Title: intToStringFormat
	 * @Description: 把数字序列化成几位字符串位数不足前边补0
	 * @param @param index 要处理 的数字
	 * @param @param str_format格式化格式例如五位 输入00000
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author jiajia
	 * @date 2016年11月16日 下午7:56:58
	 * @throws
	 */
	public static String intToStringFormat(int index, String str_format) {
		DecimalFormat df = new DecimalFormat(str_format);
		return df.format(index);
	}

	public static Connection getConnection(String url, Map<String, String> map) {
		try {
			URL u = new URL(url);
			String host = u.getHost();
			Connection connn = Jsoup.connect(url);
			if (null != map) {
				for (Entry<String, String> e : map.entrySet()) {
					connn.header(e.getKey(), e.getValue());
				}
			}
			connn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			connn.header("Accept-Encoding", "gzip, deflate, br");
			connn.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			connn.header("Connection", "keep-alive");
			connn.header("Host", host);
			connn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");
			return connn;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		URL u;
		try {
			u = new URL(URL);
			String strUrlParam = u.getQuery();
			String[] arrSplit = null;

			if (strUrlParam == null) {
				return mapRequest;
			}
			// 每个键值为一组 www.2cto.com
			arrSplit = strUrlParam.split("[&]");
			for (String strSplit : arrSplit) {
				String[] arrSplitEqual = null;
				arrSplitEqual = strSplit.split("[=]");

				// 解析出键值
				if (arrSplitEqual.length > 1) {
					// 正确解析
					mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

				} else {
					if (arrSplitEqual[0] != "") {
						// 只有参数没有值，不加入
						mapRequest.put(arrSplitEqual[0], "");
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return mapRequest;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		/*
		 * String s="总计： 2 真： 0 假： 2 待审批： 0"; String[] strs=s.split(" "); for (String string : strs) { System.out.println("=======>"+string); } System.out.println(checkPOSTParameter("乱码"));
		 */

		/*
		 * String str = "who am i"; String[] strs = str.split(" "); StringBuffer sb = new StringBuffer(); for (int i = strs.length - 1; i >= 0; i--) { if(i==0){ sb.append(strs[i]); }else {
		 * 
		 * sb.append(strs[i]).append(" "); }
		 * 
		 * } System.out.println(sb.toString());
		 */

		/*
		 * String regex="[0-9]+"; System.err.println("".matches(regex));
		 */

		// System.out.println(Integer.parseInt(Util.dateToStr(new Date(), "yyyy"))-1+"-01-01");
		String string = "dir=zljc&img=C2016111700003ae444f8610f945819da34f8c8b28c3b9.jpg";
		System.out.println("===path===>" + string.split("&")[0].split("=")[1]);
		System.out.println("===name===>" + string.split("&")[1].split("=")[1]);

	}
}
