package cn.nj.storm.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 日期时间类
 *
 * <p>
 * 对Calendar的封装,以便于使用
 * </p>
 *
 * @author qsyang
 * @version 1.0
 */
public class DateTime implements Serializable
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 3569189899575120677L;
    
    /**
     * yyyy-MM-dd HH:mm:ss 格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * yyyyMMddHHmmss格式 为缓存的score格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT_PATTERN_SCORE = "yyyyMMddHHmmss";
    
    /**
     * yyyy-MM-dd HH:mm 格式
     */
    public static final String DEFAULT_DATE_TIME_HHmm_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";
    
    /**
     * yyyy-MM-dd HH 格式
     */
    public static final String DEFAULT_DATE_TIME_HH_FORMAT_PATTERN = "yyyy-MM-dd HH";
    
    /**
     * yyyy-MM-dd 格式
     */
    public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    
    /**
     * yyyyMMdd 格式
     */
    public static final String YMD_FORMAT = "yyyyMMdd";
    
    /**
     * yyyy-MM-dd 格式类型装换
     */
    public static SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN);
    
    /**
     * yyyy-MM-dd HH:mm:ss格式类型装换
     */
    public static SimpleDateFormat formater2 = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT_PATTERN);
    
    /**
     * yyyyMMdd 格式类型转换
     */
    public static SimpleDateFormat ymdFormat = new SimpleDateFormat(YMD_FORMAT);
    
    /**
     * HH:mm:ss 格式
     */
    public static final String DEFAULT_TIME_FORMAT_PATTERN = "HH:mm:ss";
    
    /**
     * HH:mm 格式
     */
    public static final String DEFAULT_TIME_HHmm_FORMAT_PATTERN = "HH:mm";
    
    /**
     * 年
     * <p>
     * 可以通过DateTime.now().get(DateTime.YEAR_FIELD)来获取当前时间的年
     * </p>
     */
    public static final int YEAR_FIELD = java.util.Calendar.YEAR;
    
    /**
     * 月
     * <p>
     * 可以通过DateTime.now().get(DateTime.MONTH_FIELD)来获取当前时间的月
     * </p>
     */
    public static final int MONTH_FIELD = java.util.Calendar.MONTH;
    
    /**
     * 日
     * <p>
     * 可以通过DateTime.now().get(DateTime.DAY_FIELD)来获取当前时间的日
     * </p>
     */
    public static final int DAY_FIELD = java.util.Calendar.DATE;
    
    /**
     * 小时
     * <p>
     * 可以通过DateTime.now().get(DateTime.HOUR_FIELD)来获取当前时间的小时
     * </p>
     */
    public static final int HOUR_FIELD = java.util.Calendar.HOUR_OF_DAY;
    
    /**
     * 分钟
     * <p>
     * 可以通过DateTime.now().get(DateTime.MINUTE_FIELD)来获取当前时间的分钟
     * </p>
     */
    public static final int MINUTE_FIELD = java.util.Calendar.MINUTE;
    
    /**
     * 秒
     * <p>
     * 可以通过DateTime.now().get(DateTime.SECOND_FIELD)来获取当前时间的秒
     * </p>
     */
    public static final int SECOND_FIELD = java.util.Calendar.SECOND;
    
    /**
     * 毫秒
     * <p>
     * 可以通过DateTime.now().get(DateTime.MILLISECOND_FIELD)来获取当前时间的毫秒
     * </p>
     */
    public static final int MILLISECOND_FIELD = java.util.Calendar.MILLISECOND;
    
    /**
     * 天数转换毫秒时间戳
     */
    public static final int DAY_CONVERT_MILLISECOND = 24 * 60 * 60 * 1000;
    
    /**
     * 时间戳最长位数:毫秒
     */
    public static final int TIME_STAMP_MAX_LENGTH = 13;
    
    /**
     * 时间戳最长位数:秒
     */
    public static final int TIME_STAMP_MAX_LENGTH_SECOND = 10;
    
    private java.util.Calendar c; // 日历类
    
    /**
     * 获取一个DateTime,此DateTime尚未初始化,表示的时间是1970-1-1 00:00:00.000
     * <p>
     * 要获取当前系统时间,请用DateTime.now();
     * </p>
     */
    public DateTime()
    {
        c = Calendar.getInstance();
        c.clear();
    }
    
    /**
     * 设置时间
     * <p>
     * 可以传入一个时间对象，将会被转换为DateTime类型
     * </p>
     *
     * @param date
     *            时间对象
     */
    public DateTime(java.util.Date date)
    {
        c = Calendar.getInstance();
        c.setTime(date);
    }
    
    /**
     * 设置时间
     * <p>
     * 可以传入一个日历对象，将会被转换为DateTime类型
     * </p>
     *
     * @param calendar
     *            日历对象
     */
    public DateTime(java.util.Calendar calendar)
    {
        this.c = calendar;
    }
    
    /**
     * 获取当前系统时间
     *
     * @return DateTime 当前系统时间
     */
    public static DateTime now()
    {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar);
    }
    
    /**
     * 用毫秒来设置时间, 时间的基数是1970-1-1 00:00:00.000;
     * <p>
     * 比如,new DateTime(1000) 则表示1970-1-1 00:00:01.000;<br>
     * 用负数表示基础时间以前的时间
     * </p>
     *
     * @param milliseconds
     *            毫秒
     */
    public DateTime(long milliseconds)
    {
        c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
    }
    
    /**
     * 转换为Date类型
     *
     * @return Date时间
     */
    public Date toDate()
    {
        return c.getTime();
    }
    
    /**
     * 转换成 日历对象
     *
     * @return Calendar对象
     */
    public java.util.Calendar toCalendar()
    {
        return c;
    }
    
    /**
     * 转换成java.sql.Date(yyyy-MM-dd)日期
     *
     * @return java.sql.Date日期
     */
    public java.sql.Date toSqlDate()
    {
        return new java.sql.Date(c.getTimeInMillis());
    }
    
    /**
     * 转换为java.sql.Time(hh:mm:ss)时间
     *
     * @return java.sql.Time时间
     */
    public java.sql.Time toSqlTime()
    {
        return new java.sql.Time(c.getTimeInMillis());
    }
    
    /**
     * 转换为java.sql.Timestamp(时间戳)
     *
     * @return java.sql.Timestamp时间戳
     */
    public java.sql.Timestamp toSqlTimestamp()
    {
        return new java.sql.Timestamp(c.getTimeInMillis());
    }
    
    /**
     * 解析时间
     * <p>
     * 根据DateTime中的DEFAULT_TIME_FORMAT_PATTERN规则转换为hh:mm:ss或hh:mm格式
     * </p>
     *
     * @param time
     *            字符串格式时间
     * @return DateTime 日期时间对象
     */
    public static DateTime parseTime(String time)
        throws java.text.ParseException
    {
        try
        {
            return DateTime.parseDateTime(time, DateTime.DEFAULT_TIME_FORMAT_PATTERN);
        }
        catch (ParseException e)
        {
            return DateTime.parseDateTime(time, DateTime.DEFAULT_TIME_HHmm_FORMAT_PATTERN);
        }
    }
    
    /**
     * 解析日期
     * <p>
     * 根据DateTime中的DEFAULT_DATE_FORMAT_PATTERN规则转换为yyyy-MM-dd格式
     * </p>
     *
     * @param date
     *            字符串格式日期
     * @return DateTime 日期时间类
     */
    public static DateTime parseDate(String date)
        throws java.text.ParseException
    {
        return DateTime.parseDateTime(date, DateTime.DEFAULT_DATE_FORMAT_PATTERN);
    }
    
    /**
     * 解析日期时间
     * <p>
     * 根据DateTime中的DEFAULT_DATE_TIME_FORMAT_PATTERN规则转换为yyyy-MM-dd HH:mm:ss格式
     * </p>
     *
     * @param datetime
     *            字符串格式日期时间
     * @return DateTime 日期时间对象
     */
    public static DateTime parseDateTime(String datetime)
        throws java.text.ParseException
    {
        DateTime result = null;
        // 尝试按yyyy-MM-dd HH:mm:ss分析
        try
        {
            result = DateTime.parseDateTime(datetime, DateTime.DEFAULT_DATE_TIME_FORMAT_PATTERN);
        }
        catch (ParseException e)
        {
            // 解析错误
            result = null;
        }
        
        // 尝试按yyyy-MM-dd HH:mm分析
        if (null == result)
        {
            try
            {
                result = DateTime.parseDateTime(datetime, DateTime.DEFAULT_DATE_TIME_HHmm_FORMAT_PATTERN);
            }
            catch (ParseException e)
            {
                // 解析错误
                result = null;
            }
        }
        
        // 尝试按yyyy-MM-dd HH分析
        if (null == result)
        {
            try
            {
                result = DateTime.parseDateTime(datetime, DateTime.DEFAULT_DATE_TIME_HH_FORMAT_PATTERN);
            }
            catch (ParseException e)
            {
                // 解析错误
                result = null;
            }
        }
        
        // 尝试按yyyy-MM-dd分析
        if (null == result)
        {
            try
            {
                result = DateTime.parseDate(datetime);
            }
            catch (ParseException e)
            {
                // 解析错误
                result = null;
            }
        }
        
        // 尝试按时间分析
        if (null == result)
        {
            result = DateTime.parseTime(datetime);
        }
        return result;
    }
    
    /**
     * 用指定的pattern分析字符串
     * <p>
     * pattern的用法参见java.text.SimpleDateFormat
     * </p>
     *
     * @param datetime
     *            字符串格式日期时间
     * @param pattern
     *            日期解析规则
     * @return DateTime 日期时间对象
     * @see java.text.SimpleDateFormat
     */
    public static DateTime parseDateTime(String datetime, String pattern)
        throws java.text.ParseException
    {
        SimpleDateFormat fmt = (SimpleDateFormat)DateFormat.getDateInstance();
        fmt.applyPattern(pattern);
        return new DateTime(fmt.parse(datetime));
    }
    
    /**
     * 转换为 DEFAULT_DATE_FORMAT_PATTERN (yyyy-MM-dd) 格式字符串
     *
     * @return yyyy-MM-dd格式字符串
     */
    public String toDateString()
    {
        return toDateTimeString(DateTime.DEFAULT_DATE_FORMAT_PATTERN);
    }
    
    /**
     * 转换为 DEFAULT_TIME_FORMAT_PATTERN (HH:mm:ss) 格式字符串
     *
     * @return HH:mm:ss 格式字符串
     */
    public String toTimeString()
    {
        return toDateTimeString(DateTime.DEFAULT_TIME_FORMAT_PATTERN);
    }
    
    /**
     * 转换为 DEFAULT_DATE_TIME_FORMAT_PATTERN (yyyy-MM-dd HH:mm:ss) 格式字符串
     *
     * @return yyyy-MM-dd HH:mm:ss 格式字符串
     */
    public String toDateTimeString()
    {
        return toDateTimeString(DateTime.DEFAULT_DATE_TIME_FORMAT_PATTERN);
    }
    
    /**
     * 使用日期转换pattern
     * <p>
     * pattern的用法参见java.text.SimpleDateFormat
     * </p>
     *
     * @param pattern
     *            日期解析规则
     * @return 按规则转换后的日期时间字符串
     */
    public String toDateTimeString(String pattern)
    {
        SimpleDateFormat fmt = (SimpleDateFormat)DateFormat.getDateInstance();
        fmt.applyPattern(pattern);
        return fmt.format(c.getTime());
    }
    
    /**
     * 获取DateTime所表示时间的某个度量的值
     *
     * @param field
     *            int 取值为:<br>
     *            DateTime.YEAR_FIELD -- 返回年份<br>
     *            DateTime.MONTH_FIELD -- 返回月份,一月份返回1,二月份返回2,依次类推<br>
     *            DateTime.DAY_FIELD -- 返回当前的天(本月中的)<br>
     *            DateTime.HOUR_FIELD -- 返回小时数(本天中的),24小时制<br>
     *            DateTime.MINUTE_FIELD -- 返回分钟数(本小时中的)<br>
     *            DateTime.SECOND_FIELD -- 返回秒数(本分钟中的)<br>
     *            DateTime.MILLISECOND_FIELD -- 返回毫秒数(本秒中的)
     * @return int field对应的值
     */
    public int get(int field)
    {
        // 月份需要+1(月份从0开始)
        if (DateTime.MONTH_FIELD == field)
        {
            return c.get(field) + 1;
        }
        else
        {
            return c.get(field);
        }
    }
    
    /**
     * 返回自 1970-1-1 0:0:0 至此时间的毫秒数
     *
     * @return long 毫秒数
     */
    public long getTimeInMillis()
    {
        return c.getTimeInMillis();
    }
    
    /**
     * 设置field字段的值
     *
     * @param field
     *            int 取值为:<br>
     *            DateTime.YEAR_FIELD -- 年份<br>
     *            DateTime.MONTH_FIELD -- 月份,一月份从1开始<br>
     *            DateTime.DAY_FIELD -- 当前的天(本月中的)<br>
     *            DateTime.HOUR_FIELD -- 小时数(本天中的),24小时制<br>
     *            DateTime.MINUTE_FIELD -- 分钟数(本小时中的)<br>
     *            DateTime.SECOND_FIELD -- 秒数(本分钟中的)<br>
     *            DateTime.MILLISECOND_FIELD -- 毫秒数(本秒中的)
     * @param value
     */
    public void set(int field, int value)
    {
        // 月份需要-1(月份从0开始)
        if (DateTime.MONTH_FIELD == field)
        {
            c.set(field, value - 1);
        }
        else
        {
            c.set(field, value);
        }
    }
    
    /**
     * 设置DateTime日期的年/月/日
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     */
    public void set(int year, int month, int day)
    {
        set(DateTime.YEAR_FIELD, year);
        set(DateTime.MONTH_FIELD, month);
        set(DateTime.DAY_FIELD, day);
    }
    
    /**
     * 设置DateTime日期的年/月/日/小时
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @param hour
     *            小时
     */
    public void set(int year, int month, int day, int hour)
    {
        set(year, month, day);
        set(DateTime.HOUR_FIELD, hour);
    }
    
    /**
     * 设置DateTime日期的年/月/日/小时/分钟
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @param hour
     *            小时
     * @param minute
     *            分钟
     */
    public void set(int year, int month, int day, int hour, int minute)
    {
        set(year, month, day, hour);
        set(DateTime.MINUTE_FIELD, minute);
    }
    
    /**
     * 设置DateTime日期的年/月/日/小时/分钟/秒
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @param hour
     *            小时
     * @param minute
     *            分钟
     * @param second
     *            秒
     */
    public void set(int year, int month, int day, int hour, int minute, int second)
    {
        set(year, month, day, hour, minute);
        set(DateTime.SECOND_FIELD, second);
    }
    
    /**
     * 设置DateTime日期的年/月/日/小时/分钟/秒/毫秒
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @param hour
     *            小时
     * @param minute
     *            分钟
     * @param second
     *            秒
     * @param milliSecond
     *            毫秒
     */
    public void set(int year, int month, int day, int hour, int minute, int second, int milliSecond)
    {
        set(year, month, day, hour, minute, second);
        set(DateTime.MILLISECOND_FIELD, milliSecond);
    }
    
    /**
     * 对field值进行相加
     * <p>
     * add() 的功能非常强大，add 可以对 DateTime 的字段进行计算。<br>
     * 如果需要减去值，那么使用负数值就可以了，如 add(field, -value)。<br>
     * 或者调用DateTime.reduce(int,int)进行日期相减
     * </p>
     *
     * @param field
     *            int 取值为:<br>
     *            DateTime.YEAR_FIELD -- 年份<br>
     *            DateTime.MONTH_FIELD -- 月份,一月份从1开始<br>
     *            DateTime.DAY_FIELD -- 当前的天(本月中的)<br>
     *            DateTime.HOUR_FIELD -- 小时数(本天中的),24小时制<br>
     *            DateTime.MINUTE_FIELD -- 分钟数(本小时中的)<br>
     *            DateTime.SECOND_FIELD -- 秒数(本分钟中的)<br>
     *            DateTime.MILLISECOND_FIELD -- 毫秒数(本秒中的)
     * @param amount
     *            数量(如果数量小于0则为相减)
     */
    public void add(int field, int amount)
    {
        c.add(field, amount);
    }
    
    /**
     * 对field值进行相减
     * <p>
     * 对add() 的功能进行封装，add 可以对 Calendar 的字段进行计算。<br>
     * 如果需要减去值，那么使用负数值就可以了，如 add(field, -value)。<br>
     * 详细用法参见Calendar.add(int,int)
     * </p>
     *
     * @param field
     *            int 取值为:<br>
     *            DateTime.YEAR_FIELD -- 年份<br>
     *            DateTime.MONTH_FIELD -- 月份,一月份从1开始<br>
     *            DateTime.DAY_FIELD -- 当前的天(本月中的)<br>
     *            DateTime.HOUR_FIELD -- 小时数(本天中的),24小时制<br>
     *            DateTime.MINUTE_FIELD -- 分钟数(本小时中的)<br>
     *            DateTime.SECOND_FIELD -- 秒数(本分钟中的)<br>
     *            DateTime.MILLISECOND_FIELD -- 毫秒数(本秒中的)
     * @param amount
     *            数量(如果数量小于0则为相加)
     */
    public void reduce(int field, int amount)
    {
        c.add(field, -amount);
    }
    
    /**
     * 判断此 DateTime 表示的时间是否在指定 Object 表示的时间之后，返回判断结果。
     * <p>
     * 此方法等效于：compareTo(when) > 0<br>
     * 当且仅当 when 是一个 DateTime 实例时才返回 true。否则该方法返回 false。
     *
     * @param when
     *            要比较的 Object
     * @return 如果此 DateTime 的时间在 when 表示的时间之后，则返回 true；否则返回 false。
     */
    public boolean after(Object when)
    {
        if (when instanceof DateTime)
        {
            return c.after(((DateTime)when).c);
        }
        return c.after(when);
    }
    
    /**
     * 判断此 DateTime 表示的时间是否在指定 Object 表示的时间之前，返回判断结果。
     * <p>
     * 此方法等效于：compareTo(when) < 0<br>
     * 当且仅当 when 是一个 DateTime 实例时才返回 true。否则该方法返回 false。
     * </p>
     *
     * @param when
     *            要比较的 Object
     * @return 如果此 Calendar 的时间在 when 表示的时间之前，则返回 true；否则返回 false。
     */
    public boolean before(Object when)
    {
        if (when instanceof DateTime)
        {
            return c.before(((DateTime)when).c);
        }
        return c.before(when);
    }
    
    /**
     * 创建并返回此对象的一个副本
     *
     * @return 日期时间对象
     */
    @Override
    public Object clone()
    {
        return new DateTime((Calendar)c.clone());
    }
    
    /**
     * 返回该此日历的哈希码
     *
     * @return 此对象的哈希码值。
     * @see Object
     */
    @Override
    public int hashCode()
    {
        return c.hashCode();
    }
    
    /**
     * 将此 DateTime 与指定 Object 比较。
     *
     * @param obj
     *            - 要与之比较的对象。
     * @return 如果此对象等于 obj，则返回 true；否则返回 false。
     * @see Object
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof DateTime)
        {
            return c.equals(((DateTime)obj).toCalendar());
        }
        if (obj instanceof Calendar)
        {
            return c.equals(obj);
        }
        if (obj instanceof java.util.Date)
        {
            return c.getTime().equals(obj);
        }
        return false;
    }
    
    /**
     * @param format
     * @param format
     * @return
     */
    public String getOracleTime(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (c.get(Calendar.DAY_OF_WEEK) == 1)
        {
            c.add(Calendar.WEEK_OF_YEAR, -2);
        }
        else
        {
            c.add(Calendar.WEEK_OF_YEAR, -1);
        }
        return sdf.format(c.getTime());
    }
    
    /**
     * 正常周查询
     * 
     * @param format
     * @param format
     * @return
     */
    public String getJavaTime(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (c.get(Calendar.DAY_OF_WEEK) == 1)
        {
            c.add(Calendar.WEEK_OF_YEAR, 0);
        }
        else
        {
            c.add(Calendar.WEEK_OF_YEAR, 1);
        }
        return sdf.format(c.getTime());
    }
    
    public static Long getCurrentYear()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());
        return Long.parseLong(year);
    }
    
    public static Long getCurrentMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String month = sdf.format(new Date());
        return Long.parseLong(month);
    }
    
    public static Long getCurrentDay()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String day = sdf.format(new Date());
        return Long.parseLong(day);
    }
    
    public static Long getCurrentWeek()
    {
        Date date = new Date();
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy");
        String ymd = myFmt.format(date);
        String week = ymd + new DateTime(date).getOracleTime("ww");
        return Long.parseLong(week);
    }
    
    /**
     * 
     * 一年中第几周 <功能详细描述>
     * 
     * @param num
     *            当前周之前的第几周
     * @return 周数
     * @see [类、类#方法、类#成员]
     */
    public static Integer getYearWeekNum(Integer num)
    {
        num = num == null ? 0 : num;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(3, num * -1);
        return c.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 
     * 一年周第几周 <功能详细描述>
     * 
     * @param num
     *            当前周之前的第几周
     * @return 年+周
     * @see [类、类#方法、类#成员]
     */
    public static String getWeekNum(Integer num)
    {
        num = num == null ? 0 : num + 1;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(3, num * -1);
        Date d = c.getTime();
        c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.YEAR) + "" + c.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 
     * 一年周第几周 <功能详细描述>
     * 
     * @param num
     *            当前周之前的第几周
     * @return 年+月+日
     * @see [类、类#方法、类#成员]
     */
    public static String getWeekYMDNum(Integer num)
    {
        num = num == null ? 0 : num;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(3, num * -1);
        Date d = c.getTime();
        c = Calendar.getInstance();
        c.setTime(d);
        int mt = (c.get(Calendar.MONTH) + 1);
        int dt = c.get(Calendar.DAY_OF_MONTH);
        return c.get(Calendar.YEAR) + "" + (mt <= 9 ? ("0" + mt) : (mt + "")) + "" + (dt <= 9 ? ("0" + dt) : (dt + ""));
    }
    
    /**
     * 
     * 获取本周第一天和最后一天 <功能详细描述>
     * 
     * @param falg
     *            true:第一天 ；false:最后一天
     * @return date
     * @see [类、类#方法、类#成员]
     */
    public static Long getWeekYMDNum(boolean falg)
    {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(new Date());
        if (falg)
        {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 获取本周一的日期
        }
        else
        {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 获取本周日的日期
        }
        return c.getTime().getTime();
    }
    
    /**
     * 
     * 获取本周第一天和最后一天 <功能详细描述>
     * 
     * @param falg
     *            true:第一天 ；false:最后一天
     * @return date
     * @see [类、类#方法、类#成员]
     */
    public static Date getWeekYMDNum(boolean falg, Integer num)
    {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        c.add(3, num * -1);
        if (falg)
        {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 获取本周一的日期
        }
        else
        {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 获取本周日的日期
        }
        return c.getTime();
    }
    
    /**
     * 
     * 获取指定周的 首末天数 <功能详细描述>
     * 
     * @param falg
     *            true 第一天 false 最后一天
     * @param num
     *            本周为0 上周为1 依次类推
     * @param d
     *            指定时间
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getWeekYMDNum(boolean falg, Integer num, Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(d);
        c.add(3, num * -1);
        if (falg)
        {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 获取本周一的日期
        }
        else
        {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);// 获取本周日的日期
        }
        return c.getTime();
    }
    
    /**
     * 
     * 根据当前时间计算每周的天数 <功能详细描述>
     * 
     * @param date
     * @param num
     *            本周为0 上周为1 依次类推
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getWeekDay(Date date, Integer num)
    {
        num = num == null ? 0 : num;
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.add(Calendar.DAY_OF_MONTH, num);
        int mt = (c.get(Calendar.MONTH) + 1);
        int dt = c.get(Calendar.DAY_OF_MONTH);
        return c.get(Calendar.YEAR) + "" + (mt <= 9 ? ("0" + mt) : (mt + "")) + "" + (dt <= 9 ? ("0" + dt) : (dt + ""));
    }
    
    /**
     * 
     * 获取 指定周的第几天 <功能详细描述>
     * 
     * @param date
     *            指定周
     * @param num
     *            指定周第几天
     * @return Long
     * @see [类、类#方法、类#成员]
     */
    public static Long getWeekDayTime(Date date, Integer num)
    {
        num = num == null ? 0 : num;
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        c.add(Calendar.DAY_OF_MONTH, num);
        return c.getTime().getTime();
    }
    
    /**
     * 
     * 获取本周第一天和最后一天 <功能详细描述>
     * 
     * @param str
     *            时间
     * @param falg
     *            true 周第一天 false：周最后一天
     * @return Date
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public static Date StrToDate(String str, boolean falg)
        throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(str);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (!falg)
        {
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
        }
        else
        {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
        }
        return date;
    }
    
    /**
     * 
     * 获取周的所有天数 <功能详细描述>
     * 
     * @param startTime
     *            获取的周
     * @param num
     *            本周为0 上周为1 依次类推
     * @return 20160809
     * @see [类、类#方法、类#成员]
     */
    public static List<String> getDateYMDNum(Date startTime, int num)
    {
        List<String> set = new ArrayList<String>();
        for (int i = 1; i < 8; i++)
        {
            set.add(getWeekDay(startTime, i));
        }
        return set;
    }
    
    /**
     * 
     * <一句话功能简述>时间戳转换为日期类型 <功能详细描述>
     * 
     * @param times
     * @param formartStr
     *            转换日期格式
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date Long2Date(Long times, String formartStr)
    {
        Date date = null;
        try
        {
            if (!CommonUtil.isNull(times))
            {
                if (times.toString().length() == TIME_STAMP_MAX_LENGTH)
                {
                    date = new Date(times);
                }
                else
                {
                    SimpleDateFormat format = new SimpleDateFormat(formartStr);
                    Long time = new Long(times * 1000);
                    String dateStr = format.format(time);
                    date = format.parse(dateStr);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 
     * 校验时间戳并转换为Date类型 <功能详细描述>
     * 
     * @param times
     *            毫秒
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date Time2Date(Long times)
    {
        Date date = null;
        int timeSize = times.toString().length();
        if (timeSize == TIME_STAMP_MAX_LENGTH)
        {
            date = new Date(times);
        }
        else if (timeSize == TIME_STAMP_MAX_LENGTH_SECOND)
        {
            date = new Date(times * 1000);
        }
        return date;
    }
    
    /**
     * 时间戳转换为String类型 <功能详细描述>
     * 
     * @param times
     * @param formartStr
     *            转换日期格式
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String Time2String(Long times, String formartStr)
    {
        String dateStr = null;
        if (times.toString().length() <= TIME_STAMP_MAX_LENGTH)
        {
            SimpleDateFormat format = new SimpleDateFormat(formartStr);
            dateStr = format.format(new Date(times * 1000L));
        }
        return dateStr;
    }
    
    /**
     * 
     * Description: 将时间格式转换为String类型<br>
     * 
     * @author 黄浩 <br>
     * @taskId <br>
     * @param date：日期
     *            <br>
     * @param format：格式
     *            <br>
     * @return <br>
     */
    public static String date2String(Date date, String format)
    {
        String result = null;
        if (date != null)
        {
            DateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        }
        return result;
    }
    
    /**
     * 
     * 时间加减 <功能详细描述>
     * 
     * @param time
     *            对该时间加减，为null取当前时间
     * @param num
     *            需要的天数
     * @return 处理后时间
     * @see [类、类#方法、类#成员]
     */
    public static Date computeTime(Date time, int num)
    {
        time = time == null ? (new Date()) : time;
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + num);
        cal.add(Calendar.SECOND, -1);
        return cal.getTime();
    }
    
    /**
     * 
     * 字符串转Date <功能详细描述>
     * 
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date toDate(String date)
    {
        try
        {
            if (StringUtils.isBlank(date))
            {
                return null;
            }
            DateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT_PATTERN);
            return sdf.parse(date);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * 
     * <yyyy-MM-dd格式字符串转时间>
     * 
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date toDateYMD(String date)
    {
        try
        {
            if (StringUtils.isBlank(date))
            {
                return null;
            }
            DateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN);
            return sdf.parse(date);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * <YMD格式字符串转时间戳>
     * @param date
     * @return
     */
    public static Long dateYMD2Stamp(String date)
    {
        try
        {
            if (StringUtils.isBlank(date))
            {
                return null;
            }
            DateFormat sdf = new SimpleDateFormat(YMD_FORMAT);
            return sdf.parse(date).getTime();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * @Title: getbeGinDayTime
     * @Description: TODO
     * @return
     * @throws ParseException
     * @return: long
     */
    static public long getbeGinDayTime()
        throws ParseException
    {
        
        return formater2.parse(formater.format(new Date()) + " 00:00:00").getTime();
    }
    
    /**
     * @Title: getEndDayTime
     * @Description: TODO
     * @return
     * @throws ParseException
     * @return: long
     */
    static public long getEndDayTime()
        throws ParseException
    {
        
        return formater2.parse(formater.format(new Date()) + " 23:59:59").getTime();
    }
    
    /**
     * @Title: getbeGinDayTime
     * @Description: TODO
     * @return
     * @throws ParseException
     * @return: Date
     */
    static public Date getToDayDate()
        throws ParseException
    {
        
        return formater2.parse(formater.format(new Date()) + " 12:00:00");
    }
    
    // /**
    // * @Title: getbeGinDayTime
    // * @Description: TODO
    // * @return
    // * @throws ParseException
    // * @return: Date
    // */
    // static public String getbeGinDayDate() throws ParseException{
    //
    // return formater.format(new Date())+ " 12:00:00";
    // }
    
    /**
     * @Title: getbeGinDayTime
     * @Description: TODO
     * @return
     * @throws ParseException
     * @return: Date
     */
    static public Date getbafterDayDate()
        throws ParseException
    {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        return formater2.parse(formater.format(date) + " 12:00:00");
    }
    
    /**
     * @Title: getbeGinDayTime
     * @Description: TODO
     * @return
     * @throws ParseException
     * @return: Date
     */
    static public Date getbeforeDayDate()
        throws ParseException
    {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);// 把日期推前一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        return formater2.parse(formater.format(date) + " 12:00:00");
    }
    
    // /**
    // * @Title: getbeGinDayTime
    // * @Description: TODO
    // * @return
    // * @throws ParseException
    // * @return: Date
    // */
    // static public String getbeEndDayDate() throws ParseException
    // {
    // Date date=new Date();//取时间
    // Calendar calendar = new GregorianCalendar();
    // calendar.setTime(date);
    // calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
    // date=calendar.getTime(); //这个时间就是日期往后推一天的结果
    // return formater.format(date)+ " 12:00:00";
    // }
    
    /**
     * 取得当月天数
     */
    public static int getCurrentMonthMaxDay()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    
    /**
     * 得到指定月的天数
     */
    public static int getMonthMaxDay(Date time)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time); // 放入你的日期
        int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDate;
    }
    
    /**
     * 根据周数换算成当前周的第一天和最后一天在一个月当中的第几天
     * 
     * @param week
     * @return
     */
    public static String[] convertDaysOfMonth(int year, int week)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] days = new String[7];
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        days[0] = format.format(calendar.getTime());
        int wk = Integer.valueOf(new DateTime(calendar.getTime()).getOracleTime("ww"));
        if (week == 1 && wk >= 52)
        {
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            days[0] = format.format(calendar.getTime());
        }
        Calendar calendar2 = new GregorianCalendar();
        String today = format.format(calendar2.getTime());
        if (today.equals(days[0]))
        {
            return days;
        }
        
        for (int i = 1; i < 7; i++)
        {
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            days[i] = format.format(calendar.getTime());
            if (today.equals(days[i]))
            {
                return days;
            }
        }
        return days;
    }
    
    /**
     *
     * 获取某月的最后一天 <功能详细描述>
     * 
     * @param year
     * @param month
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getLastDayOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    /**
     * 获取某年某月一共有多少天
     * 
     * @return
     */
    public static int getDaysOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }
    
    /**
     *
     * 获取当前月的后某月最后一天 <功能详细描述>
     * 
     * @param month
     *            多少个月
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getLastDayOfMonth(int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
    
    /**
     *
     * 获取当前月的后某月最后一天 <功能详细描述>
     * 
     * @param month
     *            多少个月
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getLastDayOfMonth(Date date, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
    
    /**
     * 根据 开始日期和结束日期换成成当前的每一天
     * 
     * @return
     */
    public static List<Date> convertDaysOfDate(String beginDate, String endDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        List<Date> list = new ArrayList<Date>();
        Date beginD = new Date(Long.valueOf(beginDate));
        Date endD = new Date(Long.valueOf(endDate));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(beginD);
        list.add(calendar.getTime());
        int bt = Integer.valueOf(format.format(calendar.getTime()));
        int et = Integer.valueOf(format.format(endD));
        while (bt < et)
        {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            bt = Integer.valueOf(format.format(calendar.getTime()));
            list.add(calendar.getTime());
        }
        
        return list;
    }
    
    /**
     * 根据周数换算成当前周的第一天和最后一天
     * 
     * @param week
     * @return
     */
    public static Date[] convertDateOfWeek(int year, int week)
    {
        Date[] days = new Date[2];
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        days[0] = calendar.getTime();
        int wk = Integer.valueOf(new DateTime(days[0]).getOracleTime("ww"));
        if (week == 1 && wk >= 52)
        {
            calendar.add(Calendar.DAY_OF_WEEK, 7);
            days[0] = calendar.getTime();
        }
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        days[1] = calendar.getTime();
        return days;
    }
    
    /**
     * 获取某年某月已经过去了多少天
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getDayedOfMonth(long year, long month)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = new GregorianCalendar();
        String ym = format.format(new Date());
        String ym2 = year + "-" + (month < 10 ? "0" + month : month);
        int days = 0;
        if (ym.equals(ym2))
        {
            calendar.setTime(new Date());
            days = calendar.get(Calendar.DAY_OF_MONTH);
        }
        else
        {
            try
            {
                calendar.setTime(format.parse(ym2));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            days = calendar.getActualMaximum(Calendar.DATE);
        }
        return days;
    }
    
    /**
     * 获取某年以过去的月数
     * 
     * @param year
     * @return
     */
    public static int getMonthedOfYear(int year)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        int year2 = calendar.get(Calendar.YEAR);
        if (year == year2)
        {
            return calendar.get(Calendar.MONTH) + 1;
        }
        else
        {
            return 12;
        }
    }
    
    /**
     * 根据周数换算成所在的月份
     * 
     * @param week
     * @return
     */
    public static int convertWeekToMonth(int week)
    {
        int[] days = new int[2];
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }
    
    /**
     * 
     * 获取某月开始、结束时间 <功能详细描述>
     * 
     * @param month
     *            0本月
     * @param falg
     *            true:月第一条，false:月最后一天
     * @return Date
     * @see [类、类#方法、类#成员]
     */
    public static Date getMonthDay(int month, boolean falg)
    {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0 + month);
        if (falg)
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }
        else
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
        }
        return cal.getTime();
    }
    
    /**
     * 
     * 获取某月开始、结束时间 <功能详细描述>
     * 
     * @param month
     *            0本月
     * @param falg
     *            true:月第一条，false:月最后一天
     * @return Date
     * @see [类、类#方法、类#成员]
     */
    public static Date getMonthDay(Date date, int month, boolean falg)
    {
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
        // HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0 + month);
        if (falg)
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }
        else
        {
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
        }
        return cal.getTime();
    }
    
    /**
     * 获取某年最后一天日期
     * @param year
     *            年份
     * @return Date
     */
    public static Date getCurrYearLast(Date date)
    {
        int year = 0;
        Calendar c = Calendar.getInstance();
        if (null != date)
        {
            Date d = new Date();
            c.setTime(d);
            year = c.get(Calendar.YEAR);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        
        return currYearLast;
    }
    
    public static void main(String[] args)
        throws Exception
    {
        
        System.out.println(getCurrYearLast(new Date()));
        //        System.out.println(getMonthDay(0, false));
        
        //
        // // Date[] ds= DateTime.convertDateOfWeek(47);
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // Date beginDate1 = format.parse("2017-01-09");
        // Date endDate1 = format.parse("2017-01-15");
        // // String startTime = beginDate1.getTime()+"";
        // // String endTime = endDate1.getTime()+"";
        //
        // String startTime = "1483891200" + "000";
        // String endTime = "1484495999" + "000";
        // List<Date> list = DateTime.convertDaysOfDate(startTime, endTime);
        //
        // System.out.println(list);
    }
    
    public static String getMonth(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(date);
    }
    
    public static String getYear(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }
    
    /**
     * Clob类型转为String类型
     * 
     * @author zhengweishun <br>
     * @param clob
     * @return
     */
    public static String Clob2String(Clob clob)
    {
        String reString = "";
        Reader is = null;
        try
        {
            is = clob.getCharacterStream();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // 得到流
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try
        {
            s = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (s != null)
        {
            sb.append(s);
            try
            {
                s = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        reString = sb.toString();
        return reString;
    }
    
    /**
     * 将Clob转成String ,静态方法
     * 
     * @param clob
     *            字段
     * @return 内容字串，如果出现错误，返回 null
     */
    public static String clobToString(Clob clob)
    {
        if (clob == null)
            return null;
        StringBuffer sb = new StringBuffer();
        Reader clobStream = null;
        try
        {
            clobStream = clob.getCharacterStream();
            char[] b = new char[60000];// 每次获取60K
            int i = 0;
            while ((i = clobStream.read(b)) != -1)
            {
                sb.append(b, 0, i);
            }
        }
        catch (Exception ex)
        {
            sb = null;
        }
        finally
        {
            try
            {
                if (clobStream != null)
                {
                    clobStream.close();
                }
            }
            catch (Exception e)
            {
            }
        }
        if (sb == null)
            return null;
        else
            return sb.toString();
    }
    
    /**
     * 将String转成Clob ,静态方法
     * 
     * @param str
     *            字段
     * @return clob对象，如果出现错误，返回 null
     */
    public static Clob stringToClob(String str)
    {
        if (null == str)
            return null;
        else
        {
            try
            {
                java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
                return c;
            }
            catch (Exception e)
            {
                return null;
            }
        }
    }
    
    public static Map<String, String> getTimeBetweenDay(Date startDate, Date endDate)
    {
        Map<String, String> map = new LinkedHashMap<>();
        if (null != startDate && null != endDate && startDate.compareTo(endDate) <= 0)
        {
            Calendar start = Calendar.getInstance();
            start.setTime(startDate);
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);
            Long endTime = end.getTimeInMillis();
            Long oneDay = 1000 * 60 * 60 * 24l;
            Long time = start.getTimeInMillis();
            while (time <= endTime)
            {
                Date d = new Date(time);
                DateFormat df = new SimpleDateFormat("yyyyMMdd");
                map.put(df.format(d), "0");
                time += oneDay;
            }
        }
        return map;
    }
    
    /**
     * 取某天零点
     * 
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getStartTimeOfDay(Date date)
    {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
        return day.getTime();
    }
    
    /**
     * 取某天末点
     * 
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getEndTimeOfDay(Date date)
    {
        Calendar day = Calendar.getInstance();
        day.setTime(date);
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 59);
        day.set(Calendar.SECOND, 59);
        day.set(Calendar.MILLISECOND, 999);
        return day.getTime();
    }
    
    /**
     * 
     * <获取出生日期的年龄>
     * <功能详细描述>
     * @param birthday
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int getAgeByBirth(Date birthday)
    {
        int age = 0;
        try
        {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间
            
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);
            
            if (birth.after(now))
            {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            }
            else
            {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR))
                {
                    age += 1;
                }
            }
            return age;
        }
        catch (Exception e)
        {//兼容性更强,异常后返回数据
            return 0;
        }
    }
}
