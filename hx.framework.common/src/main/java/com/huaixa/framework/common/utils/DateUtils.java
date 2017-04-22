package com.huaixa.framework.common.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.format.datetime.joda.LocalTimeParser;

/**
 * 
 * Title: framework.common DateUtils.java <br>
 * Description: 时间工具类，基于joda-time库<br>
 * Date: 2016年9月12日 <br>
 * Copyright (c) 2016 Joinandjoin <br>
 * 
 * @author shilei
 */
public class DateUtils {

    /**
     * yyyy-MM-dd 格式
     */
    public static final String PATTERN_DATE_DEFAULT = "yyyy-MM-dd";

    /**
     * HH:mm:ss 格式
     */
    public static final String PATTERN_TIME_DEFAULT = "HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss:SS 格式
     */
    public static final String PATTERN_LONG_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss:SS";
    
    public static final String PATTERN_LONG_DATETIME_DEFAULT_NOLINK = "yyyyMMddHHmmssSSS";

    public static final String PATTERN_SHOT_DATETIME_DEFAULT_NOLINK = "yyyyMMddHHmmss";

    /**
     * yyyy-MM-dd HH:mm:ss 格式
     */
    public static final String PATTERN_SHOT_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 根据日期字符串和指定格式构造本地日期
     * 
     * @param dateChars
     *            String 日期字符串，可以为空，如果为空则默认当前日期
     * @param pattern
     *            String 日期格式字符串，如果dateChars参数为空，则该参数不起作用 如果该参数为空，则默认"yyyy-MM-dd"格式
     * @param zoneId
     *            DateTimeZone 指定时区
     * @return LocalDate 本地日期 可以为空，为空时默认时区
     * @author shilei
     */
    public static LocalDate getDate(String dateChars, String pattern, DateTimeZone zoneId) {
        LocalDate date = null;
        try {
            if (null == dateChars || dateChars.trim().isEmpty()) {
                // 未指定时区的话采用系统默认时区
                if (zoneId == null) {
                    date = new LocalDate(DateTimeZone.getDefault());
                } else {
                    date = new LocalDate(zoneId);
                }
            } else {
                DateTimeFormatter formmater = null;
                if (null == pattern || pattern.trim().isEmpty()) {
                    formmater = DateTimeFormat.forPattern(PATTERN_DATE_DEFAULT);
                } else {
                    formmater = DateTimeFormat.forPattern(pattern);
                }
                LocalDateParser localDateParser = new LocalDateParser(formmater);
                date = localDateParser.parse(dateChars, Locale.CHINA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * 根据日期字符串和指定格式构造本地时间
     * 
     * @param timeChars
     *            String 时间字符串，可以为空，如果为空则默认当前日期
     * @param pattern
     *            String 时间格式字符串，如果timeChars参数为空，则该参数不起作用 如果该参数为空，则默认"HH:mm:ss"格式
     * @param zoneId
     *            DateTimeZone 指定时区
     * @return LocalTime 本地时间
     * @author shilei
     */
    public static LocalTime getTime(String timeChars, String pattern, DateTimeZone zoneId) {
        LocalTime time = null;
        // timeChars 未空，则默认取当前日期
        try {
            if (null == timeChars || timeChars.trim().isEmpty()) {
                // 未指定时区的话采用系统默认时区
                if (zoneId == null) {
                    time = new LocalTime(DateTimeZone.getDefault());
                } else {
                    time = new LocalTime(zoneId);
                }
            } else {
                DateTimeFormatter formmater = null;
                if (null == pattern || pattern.trim().isEmpty()) {
                    formmater = DateTimeFormat.forPattern(PATTERN_TIME_DEFAULT);
                } else {
                    formmater = DateTimeFormat.forPattern(pattern);
                }

                LocalTimeParser localTimeParser = new LocalTimeParser(formmater);
                time = localTimeParser.parse(timeChars, Locale.CHINA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            time = null;
        }
        return time;
    }

    /**
     * 根据日期字符串和指定格式构造本地日期时间
     * 
     * @param dateTimeChars
     *            String 日期时间字符串，可以为空，如果为空则默认当前日期
     * @param pattern
     *            String 日期时间格式字符串，如果dateTimeChars参数为空，则该参数不起作用 如果该参数为空，则默认"yyyy-MM-dd HH:mm:ss"格式
     * @param zoneId
     *            DateTimeZone 指定时区
     * @return LocalDateTime 本地日期时间
     * @author shilei
     */
    public static LocalDateTime getDateTime(String dateTimeChars, String pattern,
            DateTimeZone zoneId) {
        LocalDateTime dateTime = null;
        // timeChars 未空，则默认取当前日期
        try {
            if (null == dateTimeChars || dateTimeChars.trim().isEmpty()) {
                // 未指定时区的话采用系统默认时区
                if (zoneId == null) {
                    dateTime = new LocalDateTime(DateTimeZone.getDefault());
                } else {
                    dateTime = new LocalDateTime(zoneId);
                }
            } else {
                DateTimeFormatter formmater = null;
                if (null == pattern || pattern.trim().isEmpty()) {
                    formmater = DateTimeFormat.forPattern(PATTERN_SHOT_DATETIME_DEFAULT);
                } else {
                    formmater = DateTimeFormat.forPattern(pattern);
                }
                LocalDateTimeParser localDateTimeParser = new LocalDateTimeParser(formmater);
                dateTime = localDateTimeParser.parse(dateTimeChars, Locale.CHINA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            dateTime = null;
        }
        return dateTime;
    }

    /**
     * 获取当前系统时间
     * 
     * @return Instant 当前系统时间
     * @author shilei
     */
    public static Instant getCurrInstant() {
        return new Instant();
    }

    /**
     * 获取当前系统时间
     * 
     * @return Date 当前系统时间Date对象
     * @author shilei
     */
    public static Date nowDate() {
        return getCurrInstant().toDate();
    }

    /**
     * 获取当前系统时间。
     * 
     * @return Timestamp 当前系统时间Timestamp对象 格式为yyyy-MM-dd HH:mm:ss
     * @author shilei
     */
    public static Timestamp nowTimestamp() {
        LocalDateTime dateTime = getCurrInstant().toDateTime().toLocalDateTime();
        return Timestamp.valueOf(dateTime.toString(PATTERN_SHOT_DATETIME_DEFAULT, Locale.CHINA));
    }

    /**
     * 以默认时区和指定日期时间字符串，日期时间格式构造Timestamp时间
     * 
     * @param dateTimeChars
     *            String 日期时间字符串
     * @param pattern
     *            String 日期时间格式
     * @return Timestamp Timestamp对象
     * @author shilei
     */
    public static Timestamp getTimestamp(String dateTimeChars, String pattern) {
        LocalDateTime dateTime = getDateTime(dateTimeChars, pattern, null);
        return Timestamp.valueOf(dateTime.toString());
    }

    /**
     * 以匹配yyyy-MM-dd HH:mm:ss格式的日期时间字符串构造Timestamp对象
     * 
     * @param dateTimeChars
     *            yyyy-MM-dd HH:mm:ss格式的日期时间字符串
     * @return Timestamp Timestamp对象
     * @author shilei
     */
    public static Timestamp getTimestamp(String dateTimeChars) {
        return getTimestamp(dateTimeChars, null);
    }

    /**
     * 以配置"yyyy-MM-dd HH:mm:ss" 格式的日期时间字符串构造Date对象
     * 
     * @param dateTimeChars
     *            String "yyyy-MM-dd HH:mm:ss"格式的日期时间字符串
     * @return Date Date对象
     * @author shilei
     */
    public static Date getDate(String dateTimeChars) {
        return new Date(getTimestamp(dateTimeChars, null).getTime());
    }

    /**
     * 以指定的日期时间字符串和其格式字符串构造Date对象
     * 
     * @param dateTimeChars
     *            String 日期时间字符串
     * @param pattern
     *            String 日期时间格式串
     * @return Date Date对象
     * @author shilei
     */
    public static Date getDate(String dateTimeChars, String pattern) {
        return new Date(getTimestamp(dateTimeChars, pattern).getTime());
    }

    /**
     * 以yyyy-MM-dd HH:mm:ss的字符形式返回当前时间
     * 
     * @return String yyyy-MM-dd HH:mm:ss格式的字符串
     * @author shilei
     */
    public static String formatNowYyyymmddhhmmss() {
        LocalDateTime dateTime = getCurrInstant().toDateTime().toLocalDateTime();
        return dateTime.toString(PATTERN_SHOT_DATETIME_DEFAULT, Locale.CHINA);
    }

    /**
     * 将Timestamp形式的时间对象格式化为yyyy-MM-dd HH:mm:ss格式的字符串
     * 
     * @param timestamp
     *            Timestamp 形式的时间对象
     * @return String yyyy-MM-dd HH:mm:ss格式的字符串
     * @author shilei
     */
    public static String formatYyyymmddhhmmss(Timestamp timestamp) {
        return formatPattern(timestamp, null);
    }

    /**
     * 将Date形式的时间对象格式化为yyyy-MM-dd HH:mm:ss格式的字符串
     * 
     * @param date
     *            Date 形式的时间对象
     * @return String yyyy-MM-dd HH:mm:ss格式的字符串
     * @author shilei
     */
    public static String formatYyyymmddhhmmss(Date date) {
        return formatPattern(date, null);
    }

    /**
     * 将Timestamp形式的时间对象格式化为指定格式的字符串
     * 
     * @param timestamp
     *            Timestamp 形式的时间对象
     * @param pattern
     *            String 最终格式的日期格式字符串
     * @return String 和pattern匹配的日期字符串
     * @author shilei
     */
    public static String formatPattern(Timestamp timestamp, String pattern) {
        DateTime dateTime = new DateTime(timestamp.getTime());
        if (null != pattern && !pattern.trim().isEmpty()) {
            return dateTime.toString(pattern, Locale.CHINA);
        } else {
            return dateTime.toString(PATTERN_SHOT_DATETIME_DEFAULT, Locale.CHINA);
        }
    }

    /**
     * 将Date形式的时间对象格式化为指定格式的字符串
     * 
     * @param date
     *            Date 形式的时间对象
     * @param pattern
     *            String 最终格式的日期格式字符串
     * @return String 和pattern匹配的日期字符串
     * @author shilei
     */
    public static String formatPattern(Date date, String pattern) {
        DateTime dateTime = new DateTime(date.getTime());
        if (null != pattern && !pattern.trim().isEmpty()) {
            return dateTime.toString(pattern, Locale.CHINA);
        } else {
            return dateTime.toString(PATTERN_SHOT_DATETIME_DEFAULT, Locale.CHINA);
        }
    }

    /**
     * 比较两个日期的大小
     * 
     * @param after
     *            Date 较大的日期Date形式
     * @param before
     *            Date 较小的日期Date形式
     * @return boolean 如果after大于before 则返回true，否则返回false
     * @author shilei
     */
    public static boolean compareDate(Date after, Date before) {
        LocalDateTime dateAfter = LocalDateTime.fromDateFields(after);
        LocalDateTime dateBefore = LocalDateTime.fromDateFields(before);
        return dateAfter.isAfter(dateBefore);
    }

    /**
     * 在指定的日期上加上或者减去指定的月数，然后返回一个新的日期的Date形式
     * 
     * @param date
     *            Date 指定的日期的Date形式
     * @param monthNum
     *            增加或者减去的月数，可以是负数
     * @return Date 增加后者减去指定月数后的日期的Date形式
     * @author shilei
     */
    public static Date increaseMonth(Date date, int monthNum) {
        LocalDateTime dateTime = LocalDateTime.fromDateFields(date);
        LocalDateTime increaseTime = dateTime.plusMonths(monthNum);
        return increaseTime.toDate();
    }

    /**
     * 在指定的日期上加上或者减去指定的天数，然后返回一个新的日期的Date形式
     * 
     * @param date
     *            Date 指定的日期的Date形式
     * @param intBetween
     *            增加或者减去的天数，可以是负数
     * @return Date 增加后者减去指定天数后的日期的Date形式
     * @author shilei
     */
    public static Date increaseDay(Date date, int intBetween) {
        LocalDateTime dateTime = LocalDateTime.fromDateFields(date);
        LocalDateTime increaseTime = dateTime.plusDays(intBetween);
        return increaseTime.toDate();
    }
}
