package cn.nj.storm.redis.repository.helpers.bean;

import cn.nj.storm.redis.repository.dto.ZSetItem;

/**
 * <ZSetItemS 涵盖key信息>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZSetItemS extends ZSetItem
{
    private static final long serialVersionUID = 1L;
    
    String key;
    
    public String getKey()
    {
        return key;
    }
    
    public void setKey(String key)
    {
        this.key = key;
    }
    
    @Override
    public String toString()
    {
        return "key:"+key+",member:"+this.getMember()+",score:"+this.getScore();
    }
}
