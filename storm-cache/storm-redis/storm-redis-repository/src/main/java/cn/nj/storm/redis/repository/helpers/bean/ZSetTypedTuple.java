package cn.nj.storm.redis.repository.helpers.bean;

import org.springframework.data.redis.core.DefaultTypedTuple;

import java.io.Serializable;

/**
 * <ZSET的value-score的对应类型元组类>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/5/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZSetTypedTuple<V> extends DefaultTypedTuple<V> implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructs a new <code>DefaultTypedTuple</code> instance.
     *
     * @param value
     * @param score
     */
    public ZSetTypedTuple(V value, Double score)
    {
        super(value, score);
    }
}
