package com.cesaas.android.order.utils;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author FGB
 * Description
 * Created 2017/4/10 20:19
 * Version 1.0
 */
public class AbDateUtil {
    /** 时间日期格式化到年月日时分秒. */
    public static String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    /** 时间日期格式化到年月日. */
    public static String dateFormatYMD = "yyyy-MM-dd";

    /** 时间日期格式化到年月. */
    public static String dateFormatYM = "yyyy-MM";

    /** 时间日期格式化到年月日时分. */
    public static String dateFormatYMDHM = "yyyy-MM-dd HH:mm";

    /** 时间日期格式化到月日. */
    public static String dateFormatMD = "MM/dd";

    /** 时分秒. */
    public static String dateFormatHMS = "HH:mm:ss";

    /** 时分. */
    public static String dateFormatHM = "HH:mm";

    public static String month(String CreateDate) {
        // 获取月份
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(CreateDate);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH) + 1;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return CreateDate;
    }

    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate
     *            String形式的日期时间
     * @param format
     *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 返回当前日期时间字符串<br>
     * 默认格式:MMddHHmmss
     *
     * @return String 返回当前字符串型日期时间
     */
    public static BigDecimal getCurrentTimeAsNumber() {
        String returnStr = String.valueOf(System.currentTimeMillis());
        SimpleDateFormat f = new SimpleDateFormat("MMddHHmm");
        Date date = new Date();
        returnStr = f.format(date);
        return new BigDecimal(returnStr);
    }

    /**
     * 返回当前日期时间字符串<br>
     * 默认格式:yyyymmddhhmmss
     *
     * @return String 返回当前字符串型日期时间
     */
    public static BigDecimal getSystemCurrentTime() {
        String returnStr = String.valueOf(System.currentTimeMillis());
        SimpleDateFormat f = new SimpleDateFormat("yy-MM-dd-HH");
        Date date = new Date();
        returnStr = f.format(date);
        return new BigDecimal(returnStr);
    }

    /**
     * 描述：获取偏移之后的Date.
     *
     * @param date
     *            日期时间
     * @param calendarField
     *            Calendar属性，对应offset的值，
     *            如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset
     *            偏移(值大于0,表示+,值小于0,表示－)
     * @return Date 偏移之后的日期时间
     */
    public Date getDateByOffset(Date date, int calendarField, int offset) {
        Calendar c = new GregorianCalendar();
        try {
            c.setTime(date);
            c.add(calendarField, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 描述：获取指定日期时间的字符串(可偏移).
     *
     * @param strDate
     *            String形式的日期时间
     * @param format
     *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField
     *            Calendar属性，对应offset的值，
     *            如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset
     *            偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format,
                                           int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：Date类型转化为String类型(可偏移).
     *
     * @param date
     *            the date
     * @param format
     *            the format
     * @param calendarField
     *            the calendar field
     * @param offset
     *            the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format,
                                           int calendarField, int offset) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date
     *            the date
     * @param format
     *            the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate
     *            String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format
     *            输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
                    dateFormatYMDHMS);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param format
     *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }

    /**
     * 描述：获取表示当前日期时间的字符串.
     *
     * @param format
     *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curDateTime;

    }

    /**
     * 获取昨天日期
     * @param format 格式化字符串，如："yyyy-MM-dd
     */
    public static String YesTerDay(String format){
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String yesterday = new SimpleDateFormat( format).format(cal.getTime());
        return yesterday;
    }

    /**
     * 描述：获取表示当前日期时间的字符串(可偏移).
     *
     * @param format
     *            格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField
     *            Calendar属性，对应offset的值，
     *            如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset
     *            偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getCurrentDateByOffset(String format,
                                                int calendarField, int offset) {
        String mDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;

    }

    /**
     * 描述：计算两个日期所差的天数.
     *
     * @param date1
     *            第一个时间的毫秒表示
     * @param date2
     *            第二个时间的毫秒表示
     * @return int 所差的天数
     */
    public static int getOffectDay(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        // 先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1
     *            第一个时间的毫秒表示
     * @param date2
     *            第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1 - h2 + day * 24;
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1
     *            第一个时间的毫秒表示
     * @param date2
     *            第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1 - m2 + h * 60;
        return m;
    }

    /**
     * 描述：获取本周一.
     *
     * @param format
     *            the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 描述：获取本周日.
     *
     * @param format
     *            the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.SUNDAY);
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format
     *            the format
     * @param calendarField
     *            the calendar field
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format
     *            the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;

    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format
     *            the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：获取表示当前日期24点时间毫秒数.
     *
     * @return the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 获取当前日期
     * 日期格式 yyyy-MM-dd
     * new Date()为获取当前系统时间
     * @return
     */
    public static String getCurrentTate(){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        return df.format(new Date());
    }

    /**
     * 描述：判断是否是闰年()
     * <p>
     * (year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year
     *            年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 描述：根据时间返回几天前或几分钟的描述.
     *
     * @param strDate
     *            the str date
     * @return the string
     */
    public static String formatDateStr2Desc(String strDate, String outFormat) {

        DateFormat df = new SimpleDateFormat(dateFormatYMDHMS);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(df.parse(strDate));
            c1.setTime(new Date());
            int d = getOffectDay(c1.getTimeInMillis(), c2.getTimeInMillis());
            if (d == 0) {
                int h = getOffectHour(c1.getTimeInMillis(),
                        c2.getTimeInMillis());
                if (h > 0) {
                    return h + "小时前";
                } else if (h < 0) {
                    return Math.abs(h) + "小时后";
                } else if (h == 0) {
                    int m = getOffectMinutes(c1.getTimeInMillis(),
                            c2.getTimeInMillis());
                    if (m > 0) {
                        return m + "分钟前";
                    } else if (m < 0) {
                        return Math.abs(m) + "分钟后";
                    } else {
                        return "刚刚";
                    }
                }
            } else if (d > 0) {
                if (d == 1) {
                    return "昨天";
                } else if (d == 2) {
                    return "前天";
                }
            } else if (d < 0) {
                if (d == -1) {
                    return "明天";
                } else if (d == -2) {
                    return "后天";
                }
                return Math.abs(d) + "天后";
            }

            String out = getStringByFormat(strDate, outFormat);
            if (!AbStrUtil.isEmpty(out)) {
                return out;
            }
        } catch (Exception e) {
        }

        return strDate;
    }

    /**
     * 取指定日期为星期几
     *
     * @param strDate
     *            指定日期
     * @param inFormat
     *            指定日期格式
     * @return String 星期几
     */
    public static String getWeekNumber(String strDate, String inFormat) {
        String week = "星期日";
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        return week;
    }

    /**
     * 获取系统当前时间格式化
     *
     * @return String
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }

    public static Date toDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = simpleDateFormat.parse(dateString, position);
        return dateValue;
    }


    /* 时间戳转换成字符窜 */
    public static String getDateToString(String time){

        SimpleDateFormat sf =new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date(time);

        return sf.format(date);
    }

    /*将字符串转为时间戳*/
    public static Long getStringToDate(String time) {
        SimpleDateFormat sf =new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date(time);
        try {
            date=sf.parse(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取年月日【2016-07-11】
     * @param date
     * @return
     */
    public  static String toDateYMD(String date){

        Date strDate;
        StringBuilder sb=new StringBuilder();
        try {
            strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            SimpleDateFormat sdf2= new SimpleDateFormat("dd");
            String str1 = sdf0.format(strDate);
            String str2 = sdf1.format(strDate);
            String str3 = sdf2.format(strDate);
            sb.append(str1).append("-").append(str2).append("-").append(str3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public  static String toDateYMD(){

        Date strDate;
        StringBuilder sb=new StringBuilder();;
        try {
            strDate = new SimpleDateFormat("yyMMddHHmmss").parse(String.valueOf(System.currentTimeMillis()));
            SimpleDateFormat sdf0 = new SimpleDateFormat("yy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            SimpleDateFormat sdf2= new SimpleDateFormat("dd");
            String str1 = sdf0.format(strDate);
            String str2 = sdf1.format(strDate);
            String str3 = sdf2.format(strDate);
            sb.append(str1).append("-").append(str2).append("-").append(str3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public  static String getHMS(String date){

        Date strDate;
        StringBuilder sb=new StringBuilder();
        try {
            strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
            SimpleDateFormat sdf0 = new SimpleDateFormat("HH");
            SimpleDateFormat sdf1 = new SimpleDateFormat("mm");
            SimpleDateFormat sdf2= new SimpleDateFormat("ss");
            String str1 = sdf0.format(strDate);
            String str2 = sdf1.format(strDate);
            String str3 = sdf2.format(strDate);
            sb.append(str1).append(":").append(str2).append(":").append(str3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


}