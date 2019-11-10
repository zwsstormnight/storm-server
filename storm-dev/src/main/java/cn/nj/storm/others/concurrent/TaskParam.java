package cn.nj.storm.others.concurrent;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/2/2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TaskParam implements Cloneable
{
//    private TaskParam sd = new TaskParam();
    
    public TaskParam()
    {
    }
    
    public TaskParam(String id, String jobId)
    {
        this.channelId = id;
        this.jobId = jobId;
    }
    
//    public TaskParam(TaskParam taskParam)
//    {
//        Field[] newFields = sd.getClass().getDeclaredFields();
//        Field[] fields = taskParam.getClass().getDeclaredFields();
//        for (int i = 0; i < fields.length; i++)
//        {
//            try
//            {
//                fields[i].setAccessible(true);
//                newFields[i].setAccessible(true);
//                newFields[i].set(sd.getClass(), fields[i].get(taskParam.getClass()));
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
    
    private String channelId;
    
    private String orderNum;
    
    private String sku;
    
    private String contentStr;
    
    private String reportType;
    
    private String feedId;
    
    private Timestamp startDate;
    
    private Timestamp endDate;
    
    private List<String> marketplaces;
    
    //父任务ID
    private String jobId;
    
    public String getChannelId()
    {
        return channelId;
    }
    
    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }
    
    public String getOrderNum()
    {
        return orderNum;
    }
    
    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }
    
    public String getSku()
    {
        return sku;
    }
    
    public void setSku(String sku)
    {
        this.sku = sku;
    }
    
    public String getContentStr()
    {
        return contentStr;
    }
    
    public void setContentStr(String contentStr)
    {
        this.contentStr = contentStr;
    }
    
    public String getReportType()
    {
        return reportType;
    }
    
    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }
    
    public String getFeedId()
    {
        return feedId;
    }
    
    public void setFeedId(String feedId)
    {
        this.feedId = feedId;
    }
    
    public Timestamp getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(Timestamp startDate)
    {
        this.startDate = startDate;
    }
    
    public Timestamp getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(Timestamp endDate)
    {
        this.endDate = endDate;
    }
    
    public String getJobId()
    {
        return jobId;
    }
    
    public void setJobId(String jobId)
    {
        this.jobId = jobId;
    }
    
    public List<String> getMarketplaces()
    {
        return marketplaces;
    }
    
    public void setMarketplaces(List<String> marketplaces)
    {
        this.marketplaces = marketplaces;
    }
    
    @Override
    public Object clone()
    {
        Object obj = null;
        try
        {
            obj = this.clone();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return obj;
    }
    
    public static TaskParam setNewFields(TaskParam taskParam)
    {
        TaskParam sd = new TaskParam();
        Field[] newFields = sd.getClass().getDeclaredFields();
        Field[] fields = taskParam.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            try
            {
                fields[i].setAccessible(true);
                newFields[i].setAccessible(true);
                newFields[i].set(sd.getClass(), fields[i].get(taskParam));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return sd;
    }
}
