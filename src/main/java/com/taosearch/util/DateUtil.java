package com.taosearch.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 获取 当前年、半年、季度、月、日、小时 开始结束时间
	 */
	private  static SimpleDateFormat beginformat=new SimpleDateFormat("yyyy-MM-dd 00:00:00");;
	private  static SimpleDateFormat lastformat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

	/**
	 * 获得本周的第一天，周一
	 * 
	 * @return
	 */
	public static String getCurrentWeekDayStartTime() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
			c.add(Calendar.DATE, -weekday);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beginformat.format(c.getTime());
	}

	/**
	 * 获得本周的最后一天，周日
	 * 
	 * @return
	 */
	public static String  getCurrentWeekDayEndTime() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK);
			c.add(Calendar.DATE, 8 - weekday);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastformat.format(c.getTime());
	}

	/**
	 * 获得本天的开始时间，即2012-01-01 00:00:00
	 * 
	 * @return
	 */
	public static String getCurrentDayStartTime() {
		String now="";
		Date date=new Date();
		try {
			now = beginformat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获得本天的结束时间，即2012-01-01 23:59:59
	 * 
	 * @return
	 */
	public static String getCurrentDayEndTime() {
		String now="";
		Date date=new Date();
		try {
			now = lastformat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	

	/**
	 * 获得本月的开始时间，即2012-01-01 00:00:00
	 * 
	 * @return
	 */
	public static String getCurrentMonthStartTime() {
		Calendar c = Calendar.getInstance();
		String now = "";
		try {
			c.set(Calendar.DATE, 1);
			now = beginformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前月的结束时间，即2012-01-31 23:59:59
	 * 
	 * @return
	 */
	public static String getCurrentMonthEndTime() {
		Calendar c = Calendar.getInstance();
		String now = "";
		try {
			c.set(Calendar.DATE, 1);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DATE, -1);
			now = lastformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前年的开始时间，即2012-01-01 00:00:00
	 * 
	 * @return
	 */
	public static String getCurrentYearStartTime() {
		Calendar c = Calendar.getInstance();
		String now = "";
		try {
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DATE, 1);
			now = beginformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前年的结束时间，即2012-12-31 23:59:59
	 * 
	 * @return
	 */
	public static String getCurrentYearEndTime() {
		Calendar c = Calendar.getInstance();
		String now = "";
		try {
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
			now =lastformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 * 
	 * @return
	 */
	public static String getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String now = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = beginformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 * 
	 * @return
	 */
	public static String getCurrentQuarterEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String now = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = lastformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获取前/后半年的开始时间
	 * 
	 * @return
	 */
	public static String getHalfYearStartTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String now = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 7 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 6);
			}
			c.set(Calendar.DATE, 1);
			now =beginformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;

	}

	/**
	 * 获取前/后半年的结束时间
	 * 
	 * @return
	 */
	public static String getHalfYearEndTime() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		String now = "";
		try {
			if (currentMonth >= 1 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = lastformat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

}
