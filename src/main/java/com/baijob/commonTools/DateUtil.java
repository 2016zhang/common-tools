package com.baijob.commonTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author 刘世权 路小磊
 */
public class DateUtil {
	
	/** 毫秒 */
	public final static long MS = 1;
	/** 每秒钟的毫秒数 */
	public final static long SECOND_MS = MS * 1000;
	/** 每分钟的毫秒数 */
	public final static long MINUTE_MS = SECOND_MS * 60;
	/** 每小时的毫秒数 */
	public final static long HOUR_MS = MINUTE_MS * 60;
	/** 每天的毫秒数 */
	public final static long DAY_MS = HOUR_MS * 24;
	
	/**
	 * 根据特定格式格式化日期
	 * @param date 被格式化的日期
	 * @param format 格式
	 * @return
	 */
	public static String format(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 将特定格式的日期转换为Date对象
	 * @param dateString 特定格式的日期
	 * @param format 格式
	 * @return
	 */
	public static Date parse(String dateString, String format){
		try {
			return (new SimpleDateFormat(format)).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 当前时间，格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String now() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 当前日期，格式 yyyy-MM-dd
	 * @return
	 */
	public static String today() {
		return format(new Date(), "yyyy-MM-dd");
	}
	
	/**
	 * 格式 yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 */
	public static String formatDateTime(Date d) {
		return format(d, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 格式 yyyy-MM-dd
	 * @param d
	 * @return
	 */
	public static String formatDate(Date d) {
		return format(d, "yyyy-MM-dd");
	}
	
	/**
	 * 格式yyyy-MM-dd HH:mm:ss
	 * @param s
	 * @return
	 */
	public static Date parseDateTime(String s){
		return parse(s, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 格式yyyy-MM-dd
	 * @param s
	 * @return
	 */
	public static Date parseDate(String s){
		return parse(s, "yyyy-MM-dd");
	}
	
	/**
	 * 获取指定日期偏移指定时间后的时间
	 * @param date 基准日期
	 * @param field 偏移的粒度大小（小时、天、月等）使用Calender中的常数
	 * @param offsite 偏移量，正数为向后偏移，负数为向前偏移
	 * @return
	 */
	public static Date getOffsiteDate(Date date, int field, int offsite){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, offsite);
		return cal.getTime();
	}
	
	/**
	 * 判断两个日期相差的时长<br/>
	 * 返回 minuend - subtrahend 的差
	 * @param subtrahend 减数日期
	 * @param minuend 被减数日期
	 * @param diffField 相差的选项：相差的天、小时
	 * @return
	 */
	public static long dateDiff(Date subtrahend, Date minuend, long diffField){ 
	  long diff = minuend.getTime() - subtrahend.getTime();
	  return diff/diffField; 
	}
}
