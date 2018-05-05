package cn.nj.storm.redis.repository.helpers.bean;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.connection.SortParameters.Range;
import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.core.query.SortQuery;

import java.io.Serializable;
import java.util.List;

/**
 * <redis排序业务实现类>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LocalSortQuery extends AbstractSortQuery<String> implements Serializable
{
    public static LocalSortQuery build(String key, String by, Boolean isDesc, Boolean alpha, Integer off, Integer num,
        List<String> gets)
    {
        Range limit = null;
        if (off == null || num == null)
        {
            new Range(0, -1);
        }
        else
        {
            new Range(off, num);
        }
        Order order = Order.ASC;
        if (isDesc)
            order = Order.DESC;
        return new LocalSortQuery(key, by, limit, order, alpha, gets);
    }
    
    private LocalSortQuery(String key, String by, Range limit, Order order, Boolean alpha, List<String> gets)
    {
        super(key, by, limit, order, alpha, gets);
    }

    @Override
    public String toString()
    {
        return "LocalSortQuery [alpha=" + alpha + ", by=" + by + ", gets=" + gets + ", key=" + key + ", limit=" + limit
            + ", order=" + order + "]";
    }
    
    private void validateParams()
    {
        assert StringUtils.isBlank(key);
        
    }
    
}
