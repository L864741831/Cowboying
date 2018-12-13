package com.ibeef.cowboying.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

	// yyyy-MM-dd hh:mm:ss 12小时制
	// yyyy-MM-dd HH:mm:ss 24小时制

	public static final String TYPE_01 = "yyyy-MM-dd HH:mm:ss";
	public static final String TYPE_08 = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String TYPE_09 = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String TYPE_02 = "yyyy-MM-dd";

	public static final String TYPE_03 = "HH:mm:ss";
	public static final String TYPE_06 = "ss";

	public static final String TYPE_04 = "yyyy年MM月dd日";
	public static final String TYPE_07 = "yyyy年MM月dd日HH时mm分ss秒";

	public static final String TYPE_05 = "yyyy年MM月";

	private static final String[] WEEK = {"天", "一", "二", "三", "四", "五", "六"};
	public static final String XING_QI = "星期";
	public static final String ZHOU = "周";

	public static String formatDate(long time, String format) {
		Calendar cal = Calendar.getInstance();
		if (time > 0) {
			cal.setTimeInMillis(time);
		}
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static String formatDate(String longStr, String format) {
		try {
			return formatDate(Long.parseLong(longStr), format);
		} catch (Exception e) {
		}
		return "";
	}

	public static String main(String str) throws ParseException {
		String s = "Tue Jul 12 00:00:00 GMT+08:00 2016";
		SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
		Date date = sf1.parse(str);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		return sf2.format(date);
	}

	public static String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String getTime(String type, Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		return format.format(date);
	}

	/**
	 *
	 * @param strTime strTime要转换的string类型的时间，
	 * @param formatType formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒，strTime的时间格式必须要与formatType的时间格式相同
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String strTime, String formatType)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}

	/**
	 * date要转换的date类型的时间
	 * @param date
	 * @return
	 */
	public static long dateToLong(Date date) {
		return date.getTime();
	}



	/**
	 *
	 * @param strTime strTime要转换的String类型的时间
	 * @param formatType formatType时间格式 strTime的时间格式和formatType的时间格式必须相同
	 * @return
	 * @throws ParseException
	 */
	public static long stringToLong(String strTime, String formatType)
			throws ParseException {
		Date date = stringToDate(strTime, formatType);
		// String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			long currentTime = dateToLong(date);
			// date类型转成long类型
			return currentTime;
		}
	}

	/**
	 * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
	 *
	 * @param time
	 * @return
	 */
	public static String data(String time) {
		SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		Date date;
		String times = null;
		try {
			date = sdr.parse(time);
			long l = date.getTime();
			String stf = String.valueOf(l);
			times = stf.substring(0, 10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return times;
	}

	public static long formatStr(String timeStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (timeStr == null) {
			timeStr = "2016-09-08T15:26:31+08:00";
		}
		try {
			return sdf.parse(timeStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static String getWeek(int num, String format) {
		final Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		int weekNum = c.get(Calendar.DAY_OF_WEEK) + num;
		if (weekNum > 7) {
			weekNum = weekNum - 7;
		}
		return format + WEEK[weekNum - 1];
	}

	public static String getZhouWeek() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd");
		return format.format(new Date(System.currentTimeMillis())) + " "
				+ getWeek(0, ZHOU);
	}

	public static long getLongTime(String time, String format) {
		try {
			time = time.substring(0, time.indexOf('.'));
			Date date = new SimpleDateFormat(format).parse(time);
			return date.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	private static String getTime(long time) {
		return new SimpleDateFormat("HH:mm").format(new Date(time));
	}

	public static String getTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}

	public static String getTime1() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 转换日期
	 *
	 * @param timesamp
	 * @return
	 */
	public static String getDay(long timesamp) {
		/*if(timesamp == 0L){
			return "未";
		}*/
		String result = "未";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date today = new Date(System.currentTimeMillis());
		Date otherDay = new Date(timesamp);
		int temp = Integer.parseInt(sdf.format(today))
				- Integer.parseInt(sdf.format(otherDay));

		switch (temp) {
			case 0:
				result = "今天" + getTime(timesamp);
				break;
			case 1:
				result = "昨天" + getTime(timesamp);
				break;
			case 2:
				result = temp + "天前";
				break;

			default:
				result = temp + "天前";
				break;
		}
		return result;
	}

	/**
	 * 根据生日计算年龄
	 * @param birthDay
	 * @return
	 */
	public static int getAge(Date birthDay) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}

	public static Date showDate(String szBeginTime) {
		SimpleDateFormat fmt = new SimpleDateFormat(TYPE_01);
		Date date = null;
		try {
			date = fmt.parse(szBeginTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private final static long MINUTE = 60 * 1000;
	// 1分钟

	private final static long HOUR = 60 * MINUTE;
	// 1小时

	private final static long DAY = 24 * HOUR;
	// 1天

	private final static long MONTH = 31 * DAY;
	// 月

	private final static long YEAR = 12 * MONTH;
	// 年

	/**
	 * 返回文字描述的日期
	 *
	 * @param date
	 * @return
	 */
	public static String getTimeFormatText(Date date) {
		if (date == null) {
			return null;
		}
		long diff = System.currentTimeMillis() - date.getTime();
		long r = 0;
		if (diff > YEAR) {
			r = (diff / YEAR);
			return r + "年前";
		}
		if (diff > MONTH) {
			r = (diff / MONTH);
			return r + "个月前";
		}
		if (diff > DAY) {
			r = (diff / DAY);
			return r + "天前";
		}
		if (diff > HOUR) {
			r = (diff / HOUR);
			return r + "个小时前";
		}
		if (diff > MINUTE) {
			r = (diff / MINUTE);
			return r + "分钟前";
		}
		return "刚刚";
	}

	public static long getReduce(String fromDate, String toDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff=0;
		try {
			//前的时间
			Date fd = df.parse(fromDate);
			//后的时间
			Date td = df.parse(toDate);
			//两时间差,精确到毫秒
			 diff = td.getTime() - fd.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}
	public static long getReducenew(String fromDate, String toDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff=0;
		try {
			//前的时间
			Date fd = df.parse(fromDate);
			//后的时间
			Date td = df.parse(toDate);
			//两时间差,精确到毫秒
			diff = td.getTime() - fd.getTime();
			long day = diff / 86400000;                         //以天数为单位取整
			long hour = diff % 86400000 / 3600000;               //以小时为单位取整
			long min = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整
			long seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整
//			天时分秒
			System.out.println("两时间差---> " + day + "天" + hour + "小时" + min + "分" + seconds + "秒");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diff;
	}


	/**
	 * 两个时间之间相差距离多少天
	 * @param str1 时间参数 1：
	 * @param str2 时间参数 2：
	 * @return 相差天数
	 */
	public static long getDistanceDays(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date one;
		Date two;
		long days=0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff ;
			if(time1<time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}
}
