package cn.nj.storm.redis.repository.helpers.bean;

import org.springframework.data.redis.core.query.SortQuery;

import org.springframework.data.redis.connection.SortParameters.Order;
import org.springframework.data.redis.connection.SortParameters.Range;

import java.util.List;

/**
 * <redis排序实现骨架类>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
abstract class AbstractSortQuery<K> implements SortQuery<K>
{
    protected final K key;

    protected final String by;

    protected final Range limit;

    protected final Order order;

    protected final Boolean alpha;

    protected final List<String> gets;

    AbstractSortQuery(K key, String by, Range limit, Order order, Boolean alpha, List<String> gets)
    {
        this.key = key;
        this.by = by;
        this.limit = limit;
        this.order = order;
        this.alpha = alpha;
        this.gets = gets;
    }

    @Override
    public String getBy()
    {
        return by;
    }

    @Override
    public Range getLimit()
    {
        return limit;
    }

    @Override
    public Order getOrder()
    {
        return order;
    }

    @Override
    public Boolean isAlphabetic()
    {
        return alpha;
    }

    @Override
    public K getKey()
    {
        return key;
    }

    @Override
    public List<String> getGetPattern()
    {
        return gets;
    }
}
