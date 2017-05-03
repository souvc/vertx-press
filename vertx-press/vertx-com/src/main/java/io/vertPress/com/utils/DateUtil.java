package io.vertPress.com.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DateUtil {

	private final static String myFmt4 = new String("yyyy-MM-dd HH:mm:ss:SSS");
	private final static String myFmt = new String("yyyy-MM-dd HH:mm:ss");
	private final static String myFmt2 = new String("yyyy-MM-dd");
	private final static String myFmt3 = new String("EEE MMM dd HH:mm:ss zzz yyyy");
	private final static String myFmt5 = new String("yyyyMMddHHmmss");
	private final static String myFmt6 = new String("yyyyMM");
	private final static String myFmt7 = new String("yyyy");

	public static String Date7Str(Date date) {
		return new SimpleDateFormat(myFmt7).format(date);
	}

	public static String Date6Str(Date date) {
		return new SimpleDateFormat(myFmt6).format(date);
	}

	public static String Date5Str(Date date) {
		return new SimpleDateFormat(myFmt5).format(date);
	}

	public static String Date4Str(Date date) {
		return new SimpleDateFormat(myFmt4).format(date);
	}

	public static String Date2Str(Date date) {
		return new SimpleDateFormat(myFmt).format(date);
	}

	public static String Date2Str2(Date date) {
		return new SimpleDateFormat(myFmt2).format(date);
	}

	public static Date Str2D(String str) {
		if (null == str) {
			return null;
		}
		try {
			if (str.length() > 10) {
				return new SimpleDateFormat(myFmt).parse(str);
			} else {
				return new SimpleDateFormat(myFmt2).parse(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date Str2D2(String str) {
		try {
			return new SimpleDateFormat(myFmt3, java.util.Locale.ENGLISH).parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date createDate(int year, int month, int date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, date);
		return calendar.getTime();
	}

	/*****
	 * @author LIYE 字符串转时间
	 * @param date
	 * @return
	 */
	public static Date Str2Date(String date) {
		try {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			if (date.length() > 10) {
				ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			java.util.Date d = ft.parse(date);
			return new java.sql.Date(d.getTime());
		} catch (Exception ex) {
			return new Date(Calendar.getInstance().getTime().getTime());
		}
	}

	/**
	 * 获取当前日期的前一天
	 * 
	 * @author JJ
	 * 
	 * @return String format is:yyyy-MM-dd
	 */
	public static String getPrevDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.sql.Date(System.currentTimeMillis()));
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - 1);
		return new SimpleDateFormat(myFmt).format(calendar.getTime());
	}

	/**
	 * 获取当前日期的后一天
	 * 
	 * @author JJ
	 * 
	 * @return String format is:yyyy-MM-dd
	 */
	public static String getNextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.sql.Date(System.currentTimeMillis()));
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + 1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取当前日期
	 * 
	 * @return String format is:yyyy-MM-dd
	 */
	public static String getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.sql.Date(System.currentTimeMillis()));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取当前月
	 * 
	 * @author tzs 2011.06.21 pm
	 * @return month
	 */
	public static int getCurrentMonth() {
		Calendar ca = Calendar.getInstance();
		int month = ca.get(Calendar.MONTH) + 1;// 获取月
		return month;
	}

	/**
	 * 获取两位日
	 * 
	 * @return
	 */
	public static String getDay() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("dd");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	/**
	 * 获取两位月份
	 * 
	 * @return
	 */
	public static String getMonth() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("MM");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	/**
	 * 获取四位年份
	 * 
	 * @return
	 */
	public static String getYear() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	/**
	 * 获取两位年份
	 * 
	 * @return
	 */
	public static String getShortYear() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yy");
		String ctime = formatter.format(new Date());
		return ctime;
	}

	/**
	 * 获取当前周数
	 * 
	 * @author ShenLiang
	 * 
	 * @return int
	 */
	public static int getCurWeek() {
		int week = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK);
		int weekDay = 0;
		if (week == 1) {
			weekDay = 7;
		} else {
			weekDay = week - 1;
		}
		return weekDay;
	}

	/**
	 * 获取当前财务年度（3月31日~4月1日为财务一整年度）
	 * 
	 * @return
	 */
	public static String getFiscalYear() {
		SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy");
		String year = yearFormatter.format(new Date());

		SimpleDateFormat mmformatter = new SimpleDateFormat("MM");
		String month = mmformatter.format(new Date());
		Integer imonth = Integer.valueOf(month);

		String fiscalYear = year;
		if (imonth < 4) {
			Integer i = Integer.valueOf(year) - 1;
			fiscalYear = String.valueOf(i);
		}

		return fiscalYear;
	}

	/**
	 * 日期比较
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static boolean compare(String beginDate, String endDate) {
		Date b = Str2D(beginDate);
		Date e = Str2D(endDate);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (b.before(e))
			return true;
		else
			return false;
	}

	public static boolean compare(Date beginDate, String endDate) {
		Date e = Str2D(endDate);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (beginDate.before(e))
			return true;
		else
			return false;
	}

	/**
	 * 将八位的年月日格式化成10位带横杠的年月日如：20140404->2014-04-04
	 * 
	 * @param sapDate
	 * @return
	 * @throws Exception
	 */
	public static Date formatSAP8Date(String sapDate) throws Exception {
		boolean isInt = sapDate.matches("[0-9]*");
		if (isInt == false)
			throw new Exception("SAP日期格[" + sapDate + "]式校验错误,不符合8位数字类型日期格式。\n如:20140404");

		String year = sapDate.substring(0, 4);
		String month = sapDate.substring(4, 6);
		String day = sapDate.substring(6, 8);

		if (Integer.valueOf(month) > 12)
			throw new Exception("月份[" + month + "]式校验错误,月份不能大于12个月.");

		if (Integer.valueOf(day) > 31)
			throw new Exception("日期[" + day + "]式校验错误,日期不能大于31天.");

		return new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + day);
	}
}
