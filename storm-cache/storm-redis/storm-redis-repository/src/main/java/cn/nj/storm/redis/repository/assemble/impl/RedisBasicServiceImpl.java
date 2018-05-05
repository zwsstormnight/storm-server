package cn.nj.storm.redis.repository.assemble.impl;

import cn.nj.storm.redis.repository.assemble.RedisBasicService;
import cn.nj.storm.redis.repository.helpers.Constants;
import cn.nj.storm.redis.repository.dto.ZSetItem;
import cn.nj.storm.redis.repository.dto.response.RedisResp;
import cn.nj.storm.redis.repository.helpers.StringHelper;
import cn.nj.storm.redis.repository.helpers.bean.LocalSortQuery;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <redis基础业务>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("redisBasicService")
public class RedisBasicServiceImpl implements RedisBasicService
{
    private static final Logger logger = LoggerFactory.getLogger("interface");
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Override
    public RedisResp get(String key)
    {
        String strValue = (String)stringRedisTemplate.opsForValue().get(key);
        logger.info("接口调用详情：参数K-V： " + key + "=" + strValue);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }

    @Override
    public RedisResp set(String key, String value)
    {
        stringRedisTemplate.opsForValue().set(key, value);
        String strValue = (String)stringRedisTemplate.opsForValue().get(key);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }

    @Override
    public RedisResp set(String key, String value, Long timeout, TimeUnit unit)
    {
        logger.info("{}|{}|{}|{}|{}", "set方法入参：", "键:" + key, "值:" + value, "存活时间:" + timeout, "时间单位:" + unit);
        try
        {
            if (timeout != null)
            {
                stringRedisTemplate.opsForValue().set(key, value, timeout, unit != null ? unit : TimeUnit.SECONDS);
            }
            else
            {
                stringRedisTemplate.opsForValue().set(key, value);
            }
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:", e.getMessage());
            return new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
    }

    @Override
    public RedisResp keys(String pattern)
    {
        logger.info("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:", pattern);
        try
        {
            if (StringUtils.isNotBlank(pattern))
            {
                Set<String> keySets = stringRedisTemplate.keys(StringHelper.refactStr(pattern, "*"));
                return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, keySets, pattern);
            }
            else
            {
                logger.info("{}|{}", "[KEYS pattern]:", "键匹配的通配符参数为空");
            }
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:转入直连操作", e.getMessage());
        }
        return new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
    }

    @Override
    public RedisResp keysByJedis(String url, String pattern)
    {
        logger.info("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:", pattern);
        Set<String> keySets = new HashSet<>();
        try
        {
            if (StringUtils.isNotBlank(pattern))
            {
                //                keySets = this.keySetsBuilder(null, keySets, pattern);
                return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, keySets, pattern);
            }
            else
            {
                logger.info("{}|{}", "[KEYS pattern]:", "键匹配的通配符参数为空");
            }
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}", "ERROR", "直连操作[KEYS pattern]:", e.getMessage());
        }
        return new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
    }
    
    /**
     * 根据key删除缓存中的记录
     */
    @Override
    public RedisResp delete(String key)
    {
        stringRedisTemplate.delete(key);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        return resultBean;
    }
    
    /**
     * 根据多个key批量删除缓存中的value
     */
    @Override
    public RedisResp delete(Collection<String> keys)
    {
        stringRedisTemplate.delete(keys);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        return resultBean;
    }
    
    /**
     * 出栈
     *
     * @param key<br>
     * @param timeout
     *            出栈操作的连接阻塞保护时间,时间单位为秒
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp leftPop(String key, long timeout)
    {
        logger.info("接口调用详情：参数K： " + key);
        String strValue = (String)stringRedisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 压栈
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp leftPush(String key, String value)
    {
        logger.info("接口调用详情：参数K： " + key);
        String strValue = stringRedisTemplate.opsForList().leftPush(key, value).toString();
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 批量压栈
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp leftPushAll(String key, Collection<String> values)
    {
        logger.info("接口调用详情：参数K： " + key);
        String strValue = stringRedisTemplate.opsForList().leftPushAll(key, values).toString();
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 出队
     *
     * @param key<br>
     * @param timeout
     *            出队操作的连接阻塞保护时间,时间单位为秒
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp rightPop(String key, long timeout)
    {
        logger.info("接口调用详情：参数K： " + key);
        String strValue = (String)stringRedisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }

    @Override
    public RedisResp rightPop(String key)
    {
        logger.info("rightPop 接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            String strValue = (String)stringRedisTemplate.opsForList().rightPop(key);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        }
        catch (Exception e)
        {
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
            logger.error("{}|{}|{}", "rightPop操作异常：", e.getMessage(), e);
        }
        return resultBean;
    }
    
    /**
     * 压队
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp rightPush(String key, String value)
    {
        logger.info("接口调用详情：参数K-V： " + key + "=" + value);
        String strValue = stringRedisTemplate.opsForList().rightPush(key, value).toString();
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 批量压队
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp rightPushAll(String key, Collection<String> values)
    {
        logger.info("接口调用详情：参数K： " + key);
        String strValue = stringRedisTemplate.opsForList().rightPushAll(key, values).toString();
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 对Set的pop操作
     *
     * @param key
     * @return 返回的是承载执行结果和数据的对象
     */
    @Override
    public RedisResp pop(String key)
    {
        String strValue = stringRedisTemplate.opsForSet().pop(key);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 对Set的添加操作
     *
     * @param key
     * @param values
     *            插入Set的String数组
     * @return
     */
    @Override
    @Deprecated
    public RedisResp pop(String key, String... values)
    {
        String strValue = stringRedisTemplate.opsForSet().add(key, values).toString();
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }

    @Override
    public RedisResp sadd(String key, String... values)
    {
        RedisResp resultBean = null;
        try
        {
            Long counts = stringRedisTemplate.opsForSet().add(key, values);
            resultBean =
                new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, counts.toString(), key);
            resultBean.setNumObj(counts);
        }
        catch (Exception e)
        {
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
            logger.error("{}|{}|{}", "sadd操作异常：", e.getMessage(), e);
        }
        return resultBean;
    }
    
    /**
     * <移除集合key中的一个或多个member元素，不存在的member元素会被忽略。>
     * <功能详细描述>
     * @param key
     * @param values
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp sRemove(String key, Object... values)
    {
        Long nums = stringRedisTemplate.opsForSet().remove(key, values);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, nums, key);
        return resultBean;
    }
    
    /**
     * 根据hashKey获取对应key对应的HashMap,
     *
     * @param key
     * @param hashKey
     * @return
     */
    @Override
    public RedisResp hget(String key, String hashKey)
    {
        String strValue = (String)stringRedisTemplate.opsForHash().get(key, hashKey);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 根据hashKey获取对应key对应的HashMap,
     *
     * @param key
     * @return
     */
    @Override
    public RedisResp multiGet(String key, Collection<Object> hashKeys)
    {
        List<Object> list = stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, list, key);
        return resultBean;
    }
    
    /**
     * 根据hashkey向key对应的HashMap中添加value
     * @param key
     * @param hashKey 对应的HashMap中的
     * @param value
     * @return
     */
    @Override
    public RedisResp put(String key, Object hashKey, Object value)
    {
        if (logger.isDebugEnabled())
        {
            logger.info("接口调用详情：参数K： " + key + " HashMap中的key： " + hashKey);
        }
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        return resultBean;
    }
    
    /**
     * 根据key向缓存中插入整个HashMap
     * @param key
     * @param map 要插入的HashMap对象
     * @return
     */
    @Override
    public RedisResp putAll(String key, Map<? extends Object, ? extends Object> map)
    {
        if (logger.isDebugEnabled())
        {
            logger.info("接口调用详情：参数K： " + key);
        }
        stringRedisTemplate.opsForHash().putAll(key, map);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        return resultBean;
    }
    
    /**
     * 根据hashKeys批量删除key对应的HashMap中的记录
     * @param key
     * @param hashKeys
     * @return
     */
    @Override
    public RedisResp delete(String key, Object... hashKeys)
    {
        logger.info("接口调用详情：参数K： " + key);
        String strValue = stringRedisTemplate.opsForHash().delete(key, hashKeys).toString();
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    /**
     * 根据hashKeys批量删除key对应的HashMap中的记录
     * @param key
     * @param hashKeys
     * @return
     */
    @Override
    public RedisResp hDel(String key, String... hashKeys)
    {
        Long counts = 0L;
        if (hashKeys.length > 1)
        {
            Object[] objKeys = new Object[hashKeys.length];
            objKeys = hashKeys.clone();
            counts = stringRedisTemplate.opsForHash().delete(key, objKeys);
        }
        else
        {
            counts = stringRedisTemplate.opsForHash().delete(key, (Object)hashKeys[0]);
        }
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, counts, key);
        return resultBean;
    }
    
    /**
     * <根据key获取Map中所有的记录条数>
     * <功能详细描述>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp hLen(String key)
    {
        logger.info("接口调用详情：参数K： " + key);
        Long hashLength = stringRedisTemplate.opsForHash().size(key);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, hashLength, key);
        return resultBean;
    }
    
    /**
     * ZADD操作
     * @param key
     * @param value
     * @param score
     * @return
     */
    @Override
    public RedisResp zadd(String key, String value, double score)
    {
        logger.info("接口调用详情：参数K： " + key + "值： " + value + "");
        Boolean aBoolean = stringRedisTemplate.opsForZSet().add(key, value, score);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, aBoolean);
        resultBean.setKey(key);
        if (aBoolean != null)
        {
            resultBean.setData(aBoolean.toString());
        }
        return resultBean;
    }
    
    /**
     * ZREM操作
     * @param key
     * @return
     */
    @Override
    public RedisResp zRem(String key, Object... values)
    {
        logger.info("接口调用详情：参数K： " + key + "值： " + values + "");
        Long numValue = stringRedisTemplate.opsForZSet().remove(key, values);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, numValue, key);
        return resultBean;
    }
    
    /**
     * 由索引返回一个成员范围的有序集合。
     */
    @Override
    public RedisResp range(String key, long start, long end)
    {
        logger.info("接口调用详情：参数K： " + key);
        Set<String> set = stringRedisTemplate.opsForZSet().range(key, start, end);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, set, key);
        return resultBean;
    }
    
    /**
     * 返回一个成员范围的有序集合（由字典范围）
     * @param key
     * @param range
     * @param limit
     * @return
     */
    @Override
    public RedisResp rangeByLex(String key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit)
    {
        logger.info("接口调用详情：参数K： " + key);
        Set<String> set = stringRedisTemplate.opsForZSet().rangeByLex(key, range, limit);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, set, key);
        return resultBean;
    }
    
    /**
     * 按分数返回一个成员范围的有序集合。
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    @Override
    public RedisResp rangeByScore(String key, double min, double max, long offset, long count)
    {
        logger.info("接口调用详情：参数K： " + key);
        Set<String> set = stringRedisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, set, key);
        return resultBean;
    }
    
    /**
     * 返回一个成员范围的有序集合，通过索引，以分数排序，从高分到低分
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public RedisResp reverseRange(String key, long start, long end)
    {
        logger.info("接口调用详情：参数K： " + key);
        Set<String> set = stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, set, key);
        return resultBean;
    }
    
    /**
     * 返回一个成员范围的有序集合，按分数，以分数排序从高分到低分
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    @Override
    public RedisResp reverseRangeByScore(String key, double min, double max, long offset, long count)
    {
        logger.info("接口调用详情：参数K： " + key);
        Set<String> set = stringRedisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, set, key);
        return resultBean;
    }
    
    /**
     * <返回有序集key中成员member的排名，其中有序集成员按score值从大到小排列>
     * <排名以0为底，也就是说，score值最大的成员排名为0。>
     * @param key
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp zRevRank(String key, String member)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Long obj = stringRedisTemplate.opsForZSet().reverseRank(key, member);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, obj, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * <返回有序集key中成员member的排名。><br>
     * <其中有序集成员按score值递增(从小到大)顺序排列。排名以0为底，也就是说，score值最小的成员排名为0。使用ZREVRANK命令可以获得成员按score值递减(从大到小)排列的排名。>
     * @param key
     * @param member
     * @return  如果member是有序集key的成员，返回integer-reply：member的排名。<br>
     *  如果member不是有序集key的成员，返回bulk-string-reply: nil。
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp zRank(String key, String member)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Long obj = stringRedisTemplate.opsForZSet().rank(key, member);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, obj, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * <返回有序集key中，成员member的score值。><br>
     * <如果member元素不是有序集key的成员，或key不存在，返回nil。>
     * @param key
     * @param member
     * @return member成员的score值（double型浮点数）
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp zScore(String key, String member)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Double obj = stringRedisTemplate.opsForZSet().score(key, member);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, obj, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * <有序集key中给指定成员member增加score值>
     * <如果member元素不是有序集key的成员或key不存在则返回nil>
     * @param key
     * @param member
     * @return member成员的score值（double型浮点数）
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp incrementScore(String key, String member, double score)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Double obj = stringRedisTemplate.opsForZSet().incrementScore(key, member, score);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, obj, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp zRangeWithScores(String key, long start, long end)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            List<ZSetItem> result = new ArrayList<ZSetItem>();
            Set<ZSetOperations.TypedTuple<String>> sset =
                stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
            
            Iterator<ZSetOperations.TypedTuple<String>> it = sset.iterator();
            result = this.buildZSetList(result, it);
            
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, result);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp zRevRangeWithScores(String key, long start, long end)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            List<ZSetItem> result = new ArrayList<ZSetItem>();
            Set<ZSetOperations.TypedTuple<String>> sset =
                stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
            
            Iterator<ZSetOperations.TypedTuple<String>> it = sset.iterator();
            result = this.buildZSetList(result, it);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, result);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * <判定当前key是否存在>
     * <功能详细描述>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp exists(String key)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Boolean bool = stringRedisTemplate.hasKey(key);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, bool);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp zCard(String key)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Long obj = stringRedisTemplate.opsForZSet().zCard(key);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, obj, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * 返回列表key的长度。<br>
     * 如果key不存在，则key被解释为一个空列表，返回0. 如果key不是列表类型，返回一个错误。<br>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp lLen(String key)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Long obj = stringRedisTemplate.opsForList().size(key);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, obj, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    /**
     * 返回列表key中指定区间内的元素，区间以偏移量start和stop指定。
     * <下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。 你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。>
     * @param key
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public RedisResp lRange(String key, long start, long end)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            List list = stringRedisTemplate.opsForList().range(key, start, end);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, list, key);
        }
        catch (Exception e)
        {
            logger.error("接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp setNx(String key, String value, long timeout)
    {
        Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        if (b)
        {
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, b);
    }

    @Override
    public RedisResp delKeysInPattern(String pattern)
    {
        logger.info("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:", pattern);
        RedisResp resultBean = null;
        try
        {
            Set<String> keySets = stringRedisTemplate.keys(pattern);
            if (CollectionUtils.isNotEmpty(keySets))
            {
                stringRedisTemplate.delete(keySets);
            }
            else
            {
                logger.info("{}|{}|{}", "根据匹配pattern参数获取的Keys集合为空", "[KEYS pattern]:" + pattern, keySets.size());
            }
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, keySets, pattern);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}", "ERROR", "delKeysInPattern：删除模糊匹配的KEY执行异常：", e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp delKeysInPatternByJedis(String url, String pattern)
    {
        logger.info("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:", pattern);
        RedisResp resultBean = null;
        Set<String> keySets = null;
        try
        {
            if (StringUtils.isNotBlank(pattern))
            {
                //                keySets = this.keySetsBuilder(url, new HashSet<String>(), pattern);
                if (CollectionUtils.isNotEmpty(keySets))
                {
                    stringRedisTemplate.delete(keySets);
                }
                else
                {
                    logger.info("{}|{}|{}", "根据匹配pattern参数获取的Keys集合为空", "[KEYS pattern]:" + pattern, keySets.size());
                }
                return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, keySets, pattern);
            }
            else
            {
                logger.info("{}|{}", "[KEYS pattern]:", "键匹配的通配符参数为空");
            }
            
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL, keySets, pattern);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}", "ERROR", "delKeysInPattern：删除模糊匹配的KEY执行异常：", e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp hGetAll(String key)
    {
        logger.info("接口调用详情：参数K： " + key);
        RedisResp resultBean = null;
        try
        {
            Map<Object, Object> map = stringRedisTemplate.opsForHash().entries(key);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, map, key);
        }
        catch (Exception e)
        {
            logger.error("hGetAll接口调用出现异常： " + e.getMessage());
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }
    
    private List<ZSetItem> buildZSetList(List<ZSetItem> result, Iterator<ZSetOperations.TypedTuple<String>> it)
    {
        if (result == null || it == null)
        {
            return null;
        }
        while (it.hasNext())
        {
            ZSetItem item = new ZSetItem();
            ZSetOperations.TypedTuple<String> typedTuple = it.next();
            String value = typedTuple.getValue();
            Double score = typedTuple.getScore();
            item.setMember(value);
            item.setScore(score);
            result.add(item);
        }
        return result;
    }
    
    //    private Set<String> keySetsBuilder(String url, Set<String> keySets, String pattern)
    //    {
    //        if (StringUtils.isNotBlank(url))
    //        {
    //            keysInJedis(url, keySets, pattern);
    //        }
    //        else
    //        {
    //            String[] nodes = redisInfo.getRedisNodes();
    //            for (int i = 0; i < nodes.length; i++)
    //            {
    //                keysInJedis(nodes[i], keySets, pattern);
    //            }
    //        }
    //        return keySets;
    //    }
    
    //    private Set<String> keysInJedis(String url, Set<String> keySets, String pattern)
    //    {
    //        Jedis jedis = JedisUtil.jedisBuilder(url);
    //        Set<String> keys = jedis.keys(CommonUtils.refactStr(pattern, "*"));
    //        keySets.addAll(keys);
    //        jedis.close();
    //        return keySets;
    //    }

    @Override
    public RedisResp increment(String key, long value)
    {
        logger.info("{}|{}|{}", "increment接口开始调用：", "key:" + key, "value:" + value);
        try
        {
            Long result = stringRedisTemplate.opsForValue().increment(key, value);
            return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, result, key);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}", "increment接口调用异常：", "key:" + key, "value:" + value, e.getMessage());
        }
        return new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
    }

    @Override
    public RedisResp expire(String key, long timeout, TimeUnit timeUnit)
    {
        logger.info("{}|{}|{}|{}", "expire接口开始调用：", "key:" + key, "timeout:" + timeout, "timeUnit:" + timeUnit);
        try
        {
            String result = String.valueOf(stringRedisTemplate.expire(key, timeout, timeUnit));
            return new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, result, key);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}|{}",
                "expire接口调用异常：",
                "key:" + key,
                "timeout:" + timeout,
                "timeUnit:" + timeUnit,
                e.getMessage());
        }
        return new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
    }

    @Override
    public RedisResp smembers(String key)
    {
        Set<String> stringSet = stringRedisTemplate.opsForSet().members(key);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, stringSet, key);
        return resultBean;
    }

    @Override
    public RedisResp zadd(String key, Set<? extends ZSetOperations.TypedTuple> typedTuples)
    {
        RedisResp resultBean = null;
        try
        {
            Set<ZSetOperations.TypedTuple<String>> tupleSet = new HashSet<>();
            for (ZSetOperations.TypedTuple<String> zSetTypedTuple : typedTuples)
            {
                if (zSetTypedTuple instanceof ZSetOperations.TypedTuple)
                {
                    DefaultTypedTuple typedTuple = (DefaultTypedTuple)zSetTypedTuple;
                    tupleSet.add(typedTuple);
                }
            }
            Long num = stringRedisTemplate.opsForZSet().add(key, tupleSet);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, num, key);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}|{}",
                "zadd批处理接口调用异常：",
                "key:" + key,
                "typedTuples:" + typedTuples,
                e.getMessage(),
                e);
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp zRemRangeByScore(String key, double min, double max)
    {
        RedisResp resultBean = null;
        try
        {
            Long num = stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, num, key);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}|{}",
                "zRemRangeByScore接口调用异常：" + e.getMessage(),
                "key:" + key,
                "min:" + min,
                "max:" + max,
                e);
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp zRemRange(String key, Long start, Long end)
    {
        RedisResp resultBean = null;
        try
        {
            long startValue = start != null ? start.longValue() : 0;
            long endValue = end != null ? end.longValue() : -1;
            Long num = stringRedisTemplate.opsForZSet().removeRange(key, startValue, endValue);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, num, key);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}|{}",
                "zRemRange接口调用异常：" + e.getMessage(),
                "key:" + key,
                "start:" + start,
                "end:" + end,
                e);
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp sort(String key, String by, Boolean isDesc, Boolean alpha, Integer off, Integer num,
        List<String> gets)
    {
        RedisResp resultBean = null;
        try
        {
            SortQuery<String> query = LocalSortQuery.build(key, by, isDesc, alpha, off, num, gets);
            List<? extends Object> list = stringRedisTemplate.sort(query);
            resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
            resultBean.setList(list);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}|{}",
                "sort接口调用异常：" + e.getMessage(),
                "key:" + key,
                "by:" + by,
                "alpha:" + alpha,
                e);
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp sort(SortQuery<String> query)
    {
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        List<? extends Object> list = stringRedisTemplate.sort(query);
        resultBean.setList(list);
        return resultBean;
    }

    @Override
    public RedisResp sortPageList(String key, String subKey, String by, boolean isDesc, boolean isAlpha, int off,
        int num)
    {
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        
        try
        {
            SortQueryBuilder<String> builder = SortQueryBuilder.sort(key);
            builder.by(subKey + "*->" + by);
            builder.get("#");
            builder.alphabetical(isAlpha);
            if (isDesc)
            {
                builder.order(SortParameters.Order.DESC);
            }
            
            builder.limit(off, num);
            SortQuery<String> query = builder.build();
            List<String> cks = stringRedisTemplate.sort(builder.build());
            resultBean.setList(cks);
        }
        catch (Exception e)
        {
            logger.error("{}|{}|{}|{}|{}",
                "sortPageList接口调用异常：" + e.getMessage(),
                "key:" + key,
                "subKey:" + subKey,
                "by:" + by,
                e);
            resultBean = new RedisResp(Constants.RESULT_CODE_FAIL, Constants.RESULT_DESC_FAIL);
        }
        return resultBean;
    }

    @Override
    public RedisResp incrByValue(String key, Long value)
    {
        RedisResp resultBean = new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS);
        Long resultValue = stringRedisTemplate.opsForValue().increment(key, value);
        resultBean.setNumObj(resultValue);
        resultBean.setKey(key);
        return resultBean;
    }

    public static void main(String args[])
    {
        try
        {
            //断言1结果为true，则继续往下执行
            assert true;
            System.out.println("断言1没有问题，Go！");
            
            System.out.println("\n-----------------\n");
            
            //断言2结果为false,程序终止
            assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
            System.out.println("断言2没有问题，Go！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}