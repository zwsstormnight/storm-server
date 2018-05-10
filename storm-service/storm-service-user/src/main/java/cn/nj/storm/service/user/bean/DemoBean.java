package cn.nj.storm.service.user.bean;

import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DemoBean
{
    
    private Long id;
    
    private String name;
    
    private Date createTime;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
