package com.sungeon.bos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SystemUtil {

	private static SimpleDateFormat sdf;

	/**
	 * 获取当前时间yyyy-MM-dd hh:mm:ss
	 * 
	 * @return
	 */
	public static String getTime() {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前时间yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String getTimeAll() {
		sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	/**
	 * 根据毫秒获取当前时间yyyy-MM-dd hh:mm:ss
	 * 
	 * @return
	 */
	public static String getTime(Long time) {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(time));
	}

	/**
	 * 获取当前时间yyyy-MM-dd hh:mm:ss
	 * 
	 * @return
	 */
	public static String getTime(String format) {
		sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * String类型时间转换成Date类型时间
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDateTime(String time) {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return date;
	}

	/**
	 * 获取当前日期yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDate() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前日期整型yyyyMMdd
	 * 
	 * @return
	 */
	public static Integer getDateNum() {
		sdf = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sdf.format(new Date()));
	}

	/**
	 * String类型时间转换成String类型日期
	 * 
	 * @param time
	 * @return
	 */
	public static String getDate(String time) {
		SimpleDateFormat sdf_base = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf_base.parse(time);
			return sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * Integer类型日期转换成String类型日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Integer date) {
		SimpleDateFormat sdf_base = new SimpleDateFormat("yyyyMMdd");
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date new_date = null;
		try {
			new_date = sdf_base.parse(date.toString());
			return sdf.format(new_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * String类型时间转换成Integer类型日期
	 * 
	 * @param time
	 * @return
	 */
	public static Integer getDateNum(String time) {
		SimpleDateFormat sdf_base = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf_base.parse(time);
			return Integer.parseInt(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * 获取当前时间的HHmiss
	 * 
	 * @return
	 */
	public static Integer getTimeNum() {
		sdf = new SimpleDateFormat("HHmmss");
		try {
			String time = sdf.format(new Date());
			return Integer.parseInt(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * @param date
	 *            需要处理的日期
	 * @param minites
	 *            异动的分钟：正数：之后N分钟；负数：之前N分钟
	 * @return
	 */
	public static String getBeforeTime(Date date, Integer minites) {
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.setTime(date);
		beforeTime.add(Calendar.MINUTE, minites);
		Date before = beforeTime.getTime();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(before);
	}

}