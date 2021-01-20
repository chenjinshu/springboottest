package com.cjs.springboottest.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateTimeUtils {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期
     * */
    public static Date now() {
        return LocalDateTime.now().toDate();
    }

    /**
     * 字符串转日期(指定格式化模式)
     */
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 字符串转日期(使用标准格式化模式)
     */
    public static Date strToDate(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * 日期转字符串(使用指定格式化模式)
     */
    public static String dateToStr(Date date, String formatStr) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    /**
     * 日期转字符串(使用标准格式化模式)
     */
    public static String dateToStr(Date date) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    /**
     * 设置指定日期(包含时分秒)
     */
    public static Date getAssignDate(int year, int month, int day, int hour, int minute, int second) {
        return new LocalDateTime(year, month, day, hour, minute, second).toDate();
    }

    /**
     * 设置指定日期(不包含时分秒)
     */
    public static Date getAssignDate(int year, int month, int day) {
        return new LocalDate(year, month, day).toDate();
    }

    /**
     * 使用标准格式化模式获取当前日期字符串(不包含时分秒)
     */
    public static String getCurrDateStr() {
        return LocalDate.now().toString();
    }

    /**
     * 使用指定格式化模式获取当前日期字符串(不包含时分秒)
     */
    public static String getCurrDateStr(String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return LocalDate.now().toString(formatter);
    }

//    /**
//     * 取本年第一天
//     */
//    public static Date getThisMonthFirstDate() {
//        return LocalDate.now().dayOfMonth().withMinimumValue().toDate();
//    }
//
//    /**
//     * 取本年最后一天
//     */
//    public static Date getThisMonthLastDate() {
//        return LocalDate.now().dayOfMonth().withMaximumValue().toDate();
//    }

    /**
     * 取本季度第一天
     */
    public static Date getThisQuarterFirstDate() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth <= 9)
                c.set(Calendar.MONTH, 6);
            else if (currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 取本季度最后一天
     */
    public static Date getThisQuarterLastDate() {
        return LocalDateTime.fromDateFields(getThisQuarterFirstDate()).plusMonths(2).dayOfMonth().withMaximumValue().toDate();
    }

    /**
     * 取本月第一天
     */
    public static Date getThisMonthFirstDate() {
        return LocalDate.now().dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 取本月最后一天
     */
    public static Date getThisMonthLastDate() {
        return LocalDate.now().dayOfMonth().withMaximumValue().toDate();
    }

    /**
     * 取本月指定日期(如参数为20则取本月20号的日期)
     */
    public static Date getThisMonthAssignDate(int day) {
        return LocalDate.now().withDayOfMonth(day).toDate();
    }

    /**
     * 取上月第一天
     */
    public static Date getLastOneMonthFirstDate() {
        return LocalDateTime.fromDateFields(now()).plusMonths(-1).dayOfMonth().withMinimumValue().toDate();
    }

    /**
     * 取上月最后一天
     */
    public static Date getLastOneMonthLastDate() {
        return LocalDateTime.fromDateFields(now()).plusMonths(-1).dayOfMonth().withMaximumValue().toDate();
    }

    /**
     * 取本周第一天
     */
    public static Date getThisWeekFirstDate() {
        return LocalDate.now().dayOfWeek().withMinimumValue().toDate();
    }

    /**
     * 取本周最后一天
     */
    public static Date getThisWeekLastDate() {
        return LocalDate.now().dayOfWeek().withMaximumValue().toDate();
    }

    /**
     * 计算两个日期之间相差的年数(不足一年的天数会被丢弃, 如2018-04-02和2019-04-01之间相差的年数为0, 与时分秒无关)
     */
    public static int getYearIntervalCount(Date dateFirst, Date dateSecond) {
        return Years.yearsBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getYears();
    }

    /**
     * 计算两个日期之间相差的周数(仅与日期有关, 与时分秒无关)
     */
    public static int getWeekIntervalCount(Date dateFirst, Date dateSecond) {
        return Weeks.weeksBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getWeeks();
    }

    /**
     * 计算两个日期之间相差的月数(不足一月的天数会被丢弃, 如2018-04-02和2018-05-01之间相差的月数为0, 与时分秒无关)
     */
    public static int getMonthIntervalCount(Date dateFirst, Date dateSecond) {
        return Months.monthsBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getMonths();
    }

    /**
     * 计算两个日期之间相差的天数(仅与日期有关, 与时分秒无关)
     */
    public static int getDayIntervalCount(Date dateFirst, Date dateSecond) {
        return Days.daysBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getDays();
    }

    /**
     * 计算两个日期之间相差的小时数(仅与日期有关, 与时分秒无关)
     */
    public static int getHourIntervalCount(Date dateFirst, Date dateSecond) {
        return Hours.hoursBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getHours();
    }

    /**
     * 计算两个日期之间相差的分钟数(仅与日期有关, 与时分秒无关)
     */
    public static int getMinuteIntervalCount(Date dateFirst, Date dateSecond) {
        return Math.abs(Minutes.minutesBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getMinutes());
    }

    /**
     * 计算两个日期之间相差的秒数(仅与日期有关, 与时分秒无关)
     */
    public static int getSecondIntervalCount(Date dateFirst, Date dateSecond) {
        return Math.abs(Seconds.secondsBetween(LocalDate.fromDateFields(dateFirst), LocalDate.fromDateFields(dateSecond)).getSeconds());
    }

    /**
     * 取当前日期的前interval年的日期
     */
    public static Date getForYearIntervalBeforeNow(int interval) {
        return getForYearIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval年的日期
     */
    public static Date getForYearIntervalAfterNow(int interval) {
        return getForYearIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的前interval周的日期
     */
    public static Date getForWeekIntervalBeforeNow(int interval) {
        return getForWeekIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval周的日期
     */
    public static Date getForWeekIntervalAfterNow(int interval) {
        return getForWeekIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的前interval月的日期
     */
    public static Date getForMonthIntervalBeforeNow(int interval) {
        return getForMonthIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval月的日期
     */
    public static Date getForMonthIntervalAfterNow(int interval) {
        return getForMonthIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的前interval天的日期
     */
    public static Date getForDayIntervalBeforeNow(int interval) {
        return getForDayIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval天的日期
     */
    public static Date getForDayIntervalAfterNow(int interval) {
        return getForDayIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的前interval小时的日期
     */
    public static Date getForHourIntervalBeforeNow(int interval) {
        return getForHourIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval小时的日期
     */
    public static Date getForHourIntervalAfterNow(int interval) {
        return getForHourIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的前interval分钟的日期
     */
    public static Date getForMinuteIntervalBeforeNow(int interval) {
        return getForMinuteIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval分钟的日期
     */
    public static Date getForMinuteIntervalAfterNow(int interval) {
        return getForMinuteIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的前interval秒的日期
     */
    public static Date getForSecondIntervalBeforeNow(int interval) {
        return getForSecondIntervalBeforeDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取当前日期的后interval秒的日期
     */
    public static Date getForSecondIntervalAfterNow(int interval) {
        return getForSecondIntervalAfterDate(LocalDateTime.now().toDate(), interval);
    }

    /**
     * 取指定日期的前interval年的日期
     */
    public static Date getForYearIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusYears(-interval).toDate();
    }

    /**
     * 取指定日期的后interval年的日期
     */
    public static Date getForYearIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusYears(interval).toDate();
    }

    /**
     * 取指定日期的前interval周的日期
     */
    public static Date getForWeekIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusWeeks(-interval).toDate();
    }

    /**
     * 取指定日期的后interval周的日期
     */
    public static Date getForWeekIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusWeeks(interval).toDate();
    }

    /**
     * 取指定日期的前interval月的日期
     */
    public static Date getForMonthIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusMonths(-interval).toDate();
    }

    /**
     * 取指定日期的后interval月的日期
     */
    public static Date getForMonthIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusMonths(interval).toDate();
    }

    /**
     * 取指定日期的前interval天的日期
     */
    public static Date getForDayIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusDays(-interval).toDate();
    }

    /**
     * 取指定日期的后interval天的日期
     */
    public static Date getForDayIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusDays(interval).toDate();
    }

    /**
     * 取指定日期的前interval小时的日期
     */
    public static Date getForHourIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusHours(-interval).toDate();
    }

    /**
     * 取指定日期的后interval小时的日期
     */
    public static Date getForHourIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusHours(interval).toDate();
    }

    /**
     * 取指定日期的前interval分钟的日期
     */
    public static Date getForMinuteIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusMinutes(-interval).toDate();
    }

    /**
     * 取指定日期的后interval分钟的日期
     */
    public static Date getForMinuteIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusMinutes(interval).toDate();
    }

    /**
     * 取指定日期的前interval秒的日期
     */
    public static Date getForSecondIntervalBeforeDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusSeconds(-interval).toDate();
    }

    /**
     * 取指定日期的后interval秒的日期
     */
    public static Date getForSecondIntervalAfterDate(Date date, int interval) {
        return LocalDateTime.fromDateFields(date).plusSeconds(interval).toDate();
    }

    /**
     * 获取某个日期是星期几  （1-7分别代表周一到周末）
     */
    public static int getWeekOfDate(Date date) {
        int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取某个日期的号数
     */
    public static int getDayOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static boolean isNormDateTimeFornatStr(String dateStr) {
        String pattern = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        return Pattern.matches(pattern, dateStr);
    }
}
