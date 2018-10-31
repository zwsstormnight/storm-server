package cn.nj.storm.common.utils;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.nj.storm.common.bean.BaseResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public final class CommonUtil
{
    
    public static SerializerFeature[] features = {SerializerFeature.WriteMapNullValue};
    
    /**
     * random
     */
    private static final Random RANDOM = new Random();
    
    /**
     * Description: 判断数组是否为NULL或为空<br>
     * 
     * @author yang.zhipeng <br>
     * @taskId <br>
     * @param t
     *            <br>
     * @param <T>
     *            <br>
     * @return <br>
     */
    public static <T> boolean isEmpty(T[] t)
    {
        return t == null || t.length == 0;
    }
    
    /**
     * 判断数组不为NULL也不为空
     * 
     * @param t
     *            <br>
     * @param <T>
     *            <br>
     * @return <br>
     */
    public static <T> boolean isNotEmpty(T[] t)
    {
        return !isEmpty(t);
    }
    
    /**
     * Description: 集合是否为NULL或为空<br>
     * 
     * @author 黄浩 <br>
     * @param col
     *            <br>
     * @return <br>
     */
    public static boolean isEmpty(Collection<?> col)
    {
        return col == null || col.isEmpty();
    }
    
    /**
     * <判断String类型是否为空>
     * <功能详细描述>
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String str)
    {
        if (str == null || str.length() == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Description:集合不为NULL也不为空 <br>
     * 
     * @author 黄浩 <br>
     * @param col
     *            <br>
     * @return <br>
     */
    public static boolean isNotEmpty(Collection<?> col)
    {
        return !isEmpty(col);
    }
    
    /**
     * Description: map是否为NULL或为空<br>
     * 
     * @author 黄浩 <br>
     * @param map
     *            <br>
     * @return <br>
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return map == null || map.isEmpty();
    }
    
    /**
     * Description:map不为NULL也不为空 <br>
     * 
     * @author 黄浩 <br>
     * @param map
     *            <br>
     * @return <br>
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }
    
    /**
     * 消息格式化
     * 
     * @param message
     *            message <br>
     * @param params
     *            params <br>
     * @return String <br>
     */
    public static String messageFormat(String message, Object... params)
    {
        return isNotEmpty(params) ? MessageFormat.format(message, params) : message;
    }
    
    /**
     * 判断是否是空对象
     * 
     * @param obj
     *            obj <br>
     * @return boolean <br>
     */
    public static boolean isNull(Object obj)
    {
        return null == obj;
    }
    
    /**
     * 获取事务ID
     * 
     * @return 事务ID <br>
     */
    public static String getTransactionID()
    {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Description: 获取指定位数的随机数<br>
     * 
     * @author 黄浩 <br>
     * @param size
     *            <br>
     * @return <br>
     */
    public static String getRandomNumber(int size)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            sb.append((char)('0' + RANDOM.nextInt(10)));
        }
        return sb.toString();
    }
    
    /**
     * Description: <br>
     * 
     * @author yang.zhipeng <br>
     * @taskId <br>
     * @param size
     *            <br>
     * @return <br>
     */
    public static String getRandomChar(int size)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            switch (RANDOM.nextInt(10) % 3)
            {
                case 0:
                    sb.append((char)('0' + RANDOM.nextInt(10)));
                    break;
                case 1:
                    sb.append((char)('a' + RANDOM.nextInt(26)));
                    break;
                case 2:
                    sb.append((char)('A' + RANDOM.nextInt(26)));
                    break;
                default:
                    ;
            }
        }
        return sb.toString();
    }
    
    /**
     * Description: <br>
     * 
     * @author yang.zhipeng <br>
     * @taskId <br>
     * @param obj
     *            <br>
     * @return <br>
     */
    public static String getString(Object obj)
    {
        String result = null;
        if (obj != null)
        {
            result = obj instanceof String ? (String)obj : obj.toString();
        }
        return result;
    }
    
    /**
     * 
     * Description:getDate <br>
     * 
     * @author 黄浩 <br>
     * @param time
     *            <br
     * @return <br>
     */
    public static Date getDate(Long time)
    {
        if (time != null)
        {
            return new Date(time);
        }
        return null;
    }
    
    /**
     * Description: 判断是不是邮箱<br>
     * 
     * @author hejiawen <br>
     * @taskId <br>
     * @param email
     *            <br>
     * @return <br>
     */
    public static boolean isEmail(String email)
    {
        boolean flag = false;
        try
        {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }
        catch (Exception e)
        {
            flag = false;
        }
        return flag;
    }
    
    /**
     * Description: 判断是不是手机号<br>
     * 
     * @author hejiawen <br>
     * @taskId <br>
     * @param mobiles
     *            <br>
     * @return <br>
     */
    public static boolean isMobileNo(String mobiles)
    {
        boolean flag = false;
        try
        {
            Pattern p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }
        catch (Exception e)
        {
            flag = false;
        }
        return flag;
    }
    
    /**
     * Description: 十进制转二进制<br>
     */
    public static String decimalToBinary(int decimalSource)
    {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource)); // 转换成BigInteger类型
        return bi.toString(2); // 参数2指定的是转化成X进制，默认10进制
    }
    
    /**
     * Description: 二进制转十进制<br>
     */
    public static int binaryToDecimal(String binarySource)
    {
        BigInteger bi = new BigInteger(binarySource, 2); // 转换为BigInteger类型
        return Integer.parseInt(bi.toString()); // 转换成十进制
    }
    
    public static String decimalToBinaryPar(int decimalSource)
    {
        String binaryString = Integer.toBinaryString(decimalSource);
        return binaryString;
    }
    
    /**
     * @param num:要获取二进制值的数
     * @param index:倒数第一位为0，依次类推
     */
    public static Integer get(int num, int index)
    {
        return (num & (0x1 << index)) >> index;
    }
    
    /**
     * 正整数正则判断
     * 
     * @param waits
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean positiveIntegerRegexpCheck(String waits)
    {
        String rex = "^\\+?[1-9][0-9]*$";
        Pattern p = Pattern.compile(rex);
        Matcher m = p.matcher(waits);
        return m.matches();
    }
    
    /**
     * 将时间转换成字符串格式
     * 
     * @param date
     *            时间
     * @param format
     *            格式，不传默认yyyyMMddHHmmss
     * @return 时间字符串
     */
    public static String formateDateToString(Date date, String format)
    {
        String result = null;
        
        if (null == format)
        {
            format = "yyyyMMddHHmmss";
        }
        
        if (null != date)
        {
            DateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(date);
        }
        return result;
    }
    
    /**
     * 
     * 将字符串时间转换成Date <功能详细描述>
     * 
     * @param str
     *            时间
     * @param format
     *            格式，不传默认yyyy-MM-dd HH:mm:ss
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date stringToDate(String str, String format)
    {
        format = (format == null || format.isEmpty()) ? "yyyy-MM-dd HH:mm:ss" : format;
        DateFormat f = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = f.parse(str);
            // date = java.sql.Date.valueOf(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    public static Long formCalendateTime(Date date)
    {
        if (null != date)
        {
            Calendar calendate = Calendar.getInstance();
            calendate.setTime(date);
            return calendate.getTimeInMillis() / 1000;
        }
        return null;
    }
    
    /**
     * 
     * 返回结果组装
     * 
     * @param result
     *            返回码
     * @param resultErrMsg
     *            返回信息
     * @param data
     *            返回数据
     * @return 返回对象
     */
    public static String resultInfo(int result, String resultErrMsg, Object data)
    {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        if (null != resultErrMsg && !"".equals(resultErrMsg))
        {
            baseResult.setResultErrMsg(resultErrMsg);
        }
        if (null != data)
        {
            baseResult.setData(data);
        }
        return JSONObject.toJSONString(baseResult);
    }
    
    /**
     * 
     * 返回结果组装
     * 
     * @param result
     *            返回码
     * @param resultErrMsg
     *            返回信息
     * @param data
     *            返回数据
     * @return 返回对象
     */
    public static String resultInfo1(int result, String resultErrMsg, Object data)
    {
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        if (null != resultErrMsg && !"".equals(resultErrMsg))
        {
            baseResult.setResultErrMsg(resultErrMsg);
        }
        if (null != data)
        {
            baseResult.setData(data);
        }
        return JSONObject.toJSONString(baseResult, features);
    }
    
    /**
     * @Title: index
     * @Description: 分页的
     * @param page
     * @param perpage
     * @return
     * @return: int[]
     */
    public static Integer[] index(Integer page, Integer perpage)
    {
        Integer pageIndex[] = new Integer[2];
        int begin = 0;// 开始条数
        int end = 0;// 末尾条数
        if (page == 1)
        {
            begin = page;
        }
        else
        {
            begin = (page - 1) * perpage + 1;
        }
        end = page * perpage;
        pageIndex[0] = begin;
        pageIndex[1] = end;
        return pageIndex;
        
    }
    
    /**
     * 验证用户名，支持中英文（包括全角字符）、数字、下划线和减号 （全角及汉字算两位）,长度为4-20位,中文按二位计数
     * @param userName
     * @param least 最小长度
     * @param most 最大长度
     * @return
     */
    public static boolean validateStrValue(String userName, int least, int most)
    {
        String validateStr = "^[\\w\\-－＿[0-9]\u4e00-\u9fa5\uFF21-\uFF3A\uFF41-\uFF5A]+$";
        boolean rs = false;
        rs = matcher(validateStr, userName);
        if (rs)
        {
            int strLenth = getStrLength(userName);
            if (strLenth < least || strLenth > most)
            {
                rs = false;
            }
        }
        return rs;
    }
    
    /**
     * 验证字符串长度
     * @param strValue
     * @param least
     * @param most
     * @return
     */
    public static boolean validateStrLength(String strValue, int least, int most)
    {
        boolean rs = true;
        int strLenth = getStrLength(strValue);
        if (strLenth < least || strLenth > most)
        {
            rs = false;
        }
        return rs;
    }
    
    /**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     *
     * @param value
     * @return
     */
    public static int getStrLength(String value)
    {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++)
        {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese))
            {
                valueLength += 2;
            }
            else
            {
                valueLength += 1;
            }
        }
        return valueLength;
    }
    
    private static boolean matcher(String reg, String string)
    {
        boolean tem = false;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(string);
        tem = matcher.matches();
        return tem;
    }
    
    /**
     * 获取运动线路文件地址
     * @param isRunning
     * @param isKms
     * @param filePath
     * @param motionId
     * @param finishTimeStr
     * @return
     */
    public static String motionFileLocationBuilder(boolean isRunning, boolean isKms, String filePath, Long motionId,
        String finishTimeStr)
    {
        //文件路径
        String path = null;
        String packages = null;
        if (isRunning)
        {
            //室外跑
            if (isKms)
            {
                //kms路径记录地址
                packages = "/" + finishTimeStr + "/user_run_path_kms";
            }
            else
            {
                //过滤后的路径记录地址
                packages = "/" + finishTimeStr + "/user_run_path_";
            }
        }
        else
        {
            //骑行
            if (isKms)
            {
                //kms路径记录地址
                packages = "/" + finishTimeStr + "/user_riding_path_kms";
            }
            else
            {
                //过滤后的路径记录地址
                packages = "/" + finishTimeStr + "/user_riding_path_";
            }
        }
        path = filePath + packages + motionId.toString();
        return path;
    }
    
    /**
     * 比较版本号
     * 
     * @param appVersion
     * @param baseVersion
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean compareVersion(String appVersion, String baseVersion)
    {
        if (appVersion == null || baseVersion == null)
        {
            return false;
        }
        int subVersionIndex = appVersion.indexOf('(');
        if (-1 != subVersionIndex)
        {
            appVersion = appVersion.substring(0, subVersionIndex);
        }
        String[] versionArray1 = appVersion.split("\\.");
        String[] versionArray2 = baseVersion.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值  
        int diff = 0;
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度  
            && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0)
        {
            //再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；  
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff >= 0;
    }
    
    /**
     * 比较当前app版本号是否为私有融云
     * 
     * @param appVersion
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isPrivateRongCloud(String appVersion)
    {
        return compareVersion(appVersion, "2.9.4");
    }
}
