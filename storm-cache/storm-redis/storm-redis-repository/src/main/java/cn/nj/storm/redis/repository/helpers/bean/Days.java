package cn.nj.storm.redis.repository.helpers.bean;

import cn.nj.storm.redis.repository.dto.request.RedisReq;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/5/24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Days implements Serializable
{
    private static final long serialVersionUID = 1L;

    private static final Days INSTANCE = new Days();
    
    private Days()
    {
        
    }
    
    public static Days getInstance()
    {
        return INSTANCE;
    }
    
    public static Days getInstance(Long timeStamp)
    {
        Date curDate = new Date(timeStamp);
        INSTANCE.setDayStr(new SimpleDateFormat("yyyyMMdd").format(curDate));
        SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd");
        INSTANCE.setDayStamp(converStamp(e, e.format(curDate)));
        return INSTANCE;
    }
    
    public static Days getInstance(RedisReq rqst)
    {
        return INSTANCE;
    }
    
    private Long dayStamp;
    
    private String dayStr;
    
    public Long getDayStamp()
    {
        return dayStamp;
    }
    
    public void setDayStamp(Long dayStamp)
    {
        this.dayStamp = dayStamp;
    }
    
    public String getDayStr()
    {
        return dayStr;
    }
    
    public void setDayStr(String dayStr)
    {
        this.dayStr = dayStr;
    }
    
    private static Long converStamp(SimpleDateFormat e, String str)
    {
        try
        {
            return e.parse(str).getTime();
        }
        catch (ParseException e1)
        {
            return null;
        }
    }
}
