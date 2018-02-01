package com.javens.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String FORMAT_DATETIME_LONG = "yyyyMMddHHmmss";
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATEMONTH = "yyyy-MM";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_MONTH_DAY = "MM-dd";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_TIME_HOUR_MIN = "HH:mm";
    public static final String FORMAT_TIME_HHMMSS = "HHmmss";
    public static final String FORMAT_DATETIME_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_DATETIME_4MIN = "yyyyMMddHHmm";
	public static final String FORMAT_DATETIME_CN="yyyy年MM月dd日 HH:mm:ss";
	public static final String FORMAT_HOUR = "HH" ;

	public final static String formatDate4HHMMSS(Date date) {
		if (date == null) {
			date = new Date();
		}
		return new SimpleDateFormat(FORMAT_TIME_HHMMSS).format(new Date());
	}

	public final static String formatDate4YYYYMMDD() {
		return format(FORMAT_DATETIME_YYYYMMDD);
	}

	/**
	 * 将当前时间转化为yyyy-MM-dd格式字符串
	 *
	 * @return
	 */
	public final static String formatDate() {
		return format(FORMAT_DATE);
	}

	public final static String formatMonthDay(Date date) {
		return format(date, FORMAT_MONTH_DAY);
	}

	/**
	 * 将当前时间转换为yyyy-MM-dd HH:mm:ss格式字符串
	 *
	 * @return
	 */
	public static String formatDateTime() {
		return format(FORMAT_DATETIME);
	}

	/**
	 * 将当前时间转换为yyyyMMddHHmmss格式字符串
	 *
	 * @return
	 */
	public final static String formatLongDateTime() {
		return format(FORMAT_DATETIME_LONG);
	}

	public final static String formatateTime4MIN() {
		return format(FORMAT_DATETIME_4MIN);
	}

	public final static String formatateTime4MIN(Date date) {
		return format(date, FORMAT_DATETIME_4MIN);
	}

	// public static void main(String[] args) {
	//
	// String c = formatateTime4MIN();
	//
	// //System.out.println(formatateTime4MIN(addDateMinu(parseLongDateTime4Min(c),
	// -1)));
	// }

	/**
	 * 将当前时间转换为HH:mm:ss格式字符串
	 *
	 * @return
	 */
	public final static String formatTime() {
		return format(FORMAT_TIME);
	}

	/**
	 * 获取指定格式的当前时间字符串
	 *
	 * @param strFormat
	 * @return
	 */
	public static String format(String strFormat) {
		return new SimpleDateFormat(strFormat).format(new Date());
	}

	public static String formatDate(String strFormat, Date date) {
		return new SimpleDateFormat(strFormat).format(date);
	}

	/**
	 * 将日期转化为yyyy-MM-dd格式字符串
	 *
	 * @param date
	 * @return
	 */
	public final static String formatDate(Date date) {
		return format(date, FORMAT_DATE);
	}


	public final static String formatYear(Date date) {
		return format(date, FORMAT_YEAR);
	}
	/**
	 * 将日期转化为yyyy-MM格式字符串
	 *
	 * @param date
	 * @return
	 */
	public final static String formatDateMonth(Date date) {
		return format(date, FORMAT_DATEMONTH);
	}

	public static Date parseDateMonth(String strDate) {
		return parse(strDate, FORMAT_DATEMONTH);
	}

	public static Date getMonthFirstDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));

		// 设置时间为0时
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getMonthLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		// 设置时间为0时
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date getTodayLastTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 设置时间为0时
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date beforeXMonth(Date date, Integer X) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, X);

		// 设置时间为0时
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 将日期转换为yyyy-MM-dd HH:mm:ss格式字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return format(date, FORMAT_DATETIME);
	}

	/**
	 * 将日期时间转换为yyyyMMddHHmmss格式字符串
	 *
	 * @param date
	 * @return
	 */
	public final static String formatLongDateTime(Date date) {
		return format(date, FORMAT_DATETIME_LONG);
	}

	/**
	 * 将日期转换为HH:mm:ss格式字符串
	 *
	 * @param date
	 * @return
	 */
	public final static String formatTime(Date date) {
		return format(date, FORMAT_TIME);
	}

	/**
	 * 将日期转换为指定格式字符串
	 *
	 * @param date
	 * @param strFormat
	 * @return
	 */
	public final static String format(Date date, String strFormat) {
		return new SimpleDateFormat(strFormat).format(date);
	}

	/**
	 * 将strInputFormat格式的日期字符串strDate转换为strOutputFormat格式字符串
	 *
	 * @param strDate
	 * @param strInputFormat
	 * @param strOutputFormat
	 * @return
	 */
	public static String format(String strDate, String strInputFormat, String strOutputFormat) {

		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		}

		try {
			SimpleDateFormat dbFormat = new SimpleDateFormat(strInputFormat);
			SimpleDateFormat displayFormat = new SimpleDateFormat(strOutputFormat);
			return displayFormat.format(dbFormat.parse(strDate));
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字符串转换为日期
	 *
	 * @param strDate
	 *            yyyy-MM-dd格式字符日期串
	 * @return
	 */
	public static Date parseDate(String strDate) {
		return parse(strDate, FORMAT_DATE);
	}

	public static Date parseDate(String strDate, String format) {
		return parse(strDate, format);
	}

	/**
	 * 将字符串转换为时间
	 *
	 * @param strDate
	 *            HH:mm:ss格式时间字符串
	 * @return
	 */
	public static Date parseTime(String strDate) {
		return parse(strDate, FORMAT_TIME);
	}

	public static Date parseTimeForHour(String str) {
		return parse(str, FORMAT_TIME_HOUR_MIN);
	}

	/**
	 * 将字符串转换为日期
	 *
	 * @param strDate
	 *            yyyy-MM-dd HH:mm:ss格式日期字符串
	 * @return
	 */
	public static Date parseDateTime(String strDate) {
		return parse(strDate, FORMAT_DATETIME);
	}

	/**
	 * 将字符串转换为日期时间
	 *
	 * @param strDate
	 *            yyyyMMddHHmmss格式日期字符串
	 * @return
	 */
	public static Date parseLongDateTime(String strDate) {
		return parse(strDate, FORMAT_DATETIME_LONG);
	}

	public static Date parseLongDateTime4Min(String strDate) {
		return parse(strDate, FORMAT_DATETIME_4MIN);
	}

	/**
	 * 将字符串转换为日期
	 *
	 * @param strDate
	 * @param strFormat
	 * @return
	 */
	public static Date parse(String strDate, String strFormat) {

		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		}

		try {
			return new SimpleDateFormat(strFormat).parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 创建日期
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @return
	 */
	public static Date buildDate(int year, int month, int date) {
		return buildDateTime(year, month, date, 0, 0, 0);
	}

	/**
	 * 创建时间
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hrs
	 *            时
	 * @param min
	 *            分
	 * @param sec
	 *            秒
	 * @return
	 */
	public static Date buildDateTime(int year, int month, int date, int hour, int min, int sec) {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, date);
		calendar.set(Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取时间date1与date2相差的秒数
	 *
	 * @param date1
	 *            起始时间
	 * @param date2
	 *            结束时间
	 * @return 返回相差的秒数
	 */
	public static int getOffsetSeconds(Date date1, Date date2) {
		int seconds = (int) ((date2.getTime() - date1.getTime()) / 1000);
		return seconds;
	}

	public static boolean compareDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date);
			Date dt2 = df.parse(format(FORMAT_DATETIME));
			if (dt1.getTime() > dt2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取时间date1与date2相差的分钟数
	 *
	 * @param date1
	 *            起始时间
	 * @param date2
	 *            结束时间
	 * @return 返回相差的分钟数
	 */
	public static int getOffsetMinutes(Date date1, Date date2) {
		return getOffsetSeconds(date1, date2) / 60;
	}

	public static Date addDateMinu(Date date, int minus) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minus);
		date = cal.getTime();
		return date;
	}

	public static Date addDateYear(Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);

		date = cal.getTime();
		return date;
	}

	public static Date getCleanDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();
		return date;
	}

	public static Date addDateDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		date = cal.getTime();
		return date;
	}

	public static String getWeekOfDate(String datestr) {
		Date date = parseLongDateTime(datestr);
		String[] weekOfDays = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	public static int getDaysBetweenToDate(Date end, Date start) {
		Calendar calst = Calendar.getInstance();
		Calendar caled = Calendar.getInstance();

		calst.setTime(start);
		caled.setTime(end);
		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
		return days;
	}

	public static int compare_date(Date dt1, Date dt2) {
		try {
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static Date nextDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	public static Date beforeDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static Date getLastFrd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK)) {
			cal.add(Calendar.DATE, -1);
		}
		if (Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)) {
			cal.add(Calendar.DATE, -2);
		}
		return cal.getTime();
	}
    public static Date getMinAfter(Date t, int after) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t);
        cal.add(Calendar.MINUTE, after);
        return cal.getTime();
    }

	public final static String getCurrentTime() {
		LocalDateTime today = LocalDateTime.now();
		return today.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"));
	}

	public final static String getWeeksInWeekYear(Date date) {
		Date now = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(now);

		return (formatYear(date) + calender.getWeeksInWeekYear());
	}

	/**
	 * 创建时间
	 *
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hour
	 *            24小时制度
	 * @param min
	 *            分
	 * @param sec
	 *            秒
	 * @return
	 */
	public static Date buildDateTimeBy24Hour(int year, int month, int date, int hour, int min, int sec) {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month -1);
		calendar.set(Calendar.DATE, date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
}
