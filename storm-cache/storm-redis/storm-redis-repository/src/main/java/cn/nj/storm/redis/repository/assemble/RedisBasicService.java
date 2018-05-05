package cn.nj.storm.redis.repository.assemble;

import cn.nj.storm.redis.repository.dto.response.RedisResp;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.query.SortQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RedisBasicService
{
    /**
     * <命令[KEYS pattern]:根据pattern匹配查询全部的key>
     * <获取所有匹配pattern参数的Keys。该命令是非常耗时的，对Redis服务器的性能打击也是比较大的。>
     * @param pattern 支持glob-style的通配符格式，如*表示任意一个或多个字符，?表示任意字符，[abc]表示方括号中任意一个字母。
     * @return 匹配模式的键列表。
     * @see [类、类#方法、类#成员]
     */
    @Deprecated
    RedisResp keys(String pattern);

    /**
     * <命令[KEYS pattern]:根据pattern匹配查询全部的key>
     * <Jedis直联获取所有匹配pattern参数的Keys>
     * @param pattern 支持glob-style的通配符格式，如*表示任意一个或多个字符，?表示任意字符，[abc]表示方括号中任意一个字母。
     * @return 匹配模式的键列表。
     * @see [类、类#方法、类#成员]
     */
    @Deprecated
    RedisResp keysByJedis(String url, String pattern);

    /**
     * <通过key查询缓存中对应的String类型value>
     * <功能详细描述>
     * @param key 键
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp get(String key);

    /**
     * <将字符串值 value 关联到 key 。如果 key 已经持有其他值， SET 就覆写旧值，无视类型。>
     * @param key
     * @param value
     * @return
     */
    RedisResp set(String key, String value);

    /**
     * <将字符串值 value 关联到 key 。如果 key 已经持有其他值， SET 就覆写旧值，无视类型。>
     * <命令：SET key value [EX seconds] [PX milliseconds] [NX|XX]>
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    RedisResp set(String key, String value, Long timeout, TimeUnit unit);

    /**
     * 删除数据
     * @param key 键
     */
    RedisResp delete(String key);

    /**
     * 批量删除数据
     * @param keys 承载String类型的键的容器对象
     * @return
     */
    RedisResp delete(Collection<String> keys);

    /**
     * 出栈
     * @param key<br>
     * @param timeout 出栈操作的连接阻塞保护时间,时间单位为秒
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp leftPop(String key, long timeout);

    /**
     * 压栈
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp leftPush(String key, String value);

    /**
     * 批量压栈
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp leftPushAll(String key, Collection<String> values);

    /**
     * 出队
     *
     * @param key<br>
     * @param timeout
     *            出队操作的连接阻塞保护时间,时间单位为秒
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp rightPop(String key, long timeout);

    /**
     * 压队
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp rightPush(String key, String value);

    /**
     * 批量压队
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp rightPushAll(String key, Collection<String> values);

    /**
     * 对Set的pop操作
     *
     * @param key
     * @return 返回的是承载执行结果和数据的对象
     */
    RedisResp pop(String key);

    /**
     * <对Set的添加操作>
     * <sadd>
     * @param key
     * @param values
     *            插入Set的String数组
     * @return
     */
    @Deprecated
    RedisResp pop(String key, String... values);

    /**
     * <SADD>
     * @param key
     * @param values
     * @return
     */
    RedisResp sadd(String key, String... values);

    /**
     * 根据hashKey获取对应key对应的HashMap,
     *
     * @param key
     * @param hashKey
     * @return
     */
    RedisResp hget(String key, String hashKey);

    /**
     * <根据hashKey获取对应key对应的HashMap>
     *
     * @param key
     * @param hashKeys
     * @return
     */
    RedisResp multiGet(String key, Collection<Object> hashKeys);

    /**
     * <根据key获取Map中所有的记录条数>
     * <功能详细描述>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp hLen(String key);

    /**
     * <根据hashkey向key对应的HashMap中添加value>
     * @param key
     * @param hashKey 对应的HashMap中的
     * @param value
     * @return
     */
    RedisResp put(String key, Object hashKey, Object value);

    /**
     * 根据key向缓存中插入整个HashMap
     * @param key
     * @param map 要插入的HashMap对象
     * @return
     */
    RedisResp putAll(String key, Map<? extends Object, ? extends Object> map);

    /**
     * 根据hashKeys批量删除key对应的HashMap中的记录
     * @param key
     * @param hashKeys
     * @return
     */
    RedisResp delete(String key, Object... hashKeys);

    /**
     * <根据字符串类型的hashKeys批量删除key对应的HashMap中的记录>
     * <功能详细描述>
     * @param key
     * @param hashKeys
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp hDel(String key, String... hashKeys);

    /**
     * <hGetAll:根据Key获取整个hashMap 返回的是整个map>
     * <功能详细描述>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp hGetAll(String key);

    /**
     * ZADD操作
     * @param key
     * @param value
     * @param score
     * @return
     */
    RedisResp zadd(String key, String value, double score);

    /**
     * RANGE操作
     * @param key
     * @param start
     * @param end
     * @return
     */
    RedisResp range(String key, long start, long end);

    /**
     * 返回一个成员范围的有序集合（由字典范围）
     * @param key
     * @param range
     * @param limit
     * @return
     */
    RedisResp rangeByLex(String key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit);

    /**
     * 按分数返回一个成员范围的有序集合。
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    RedisResp rangeByScore(String key, double min, double max, long offset, long count);

    /**
     * 返回一个成员范围的有序集合，通过索引，以分数排序，从高分到低分
     * @param key
     * @param start
     * @param end
     * @return
     */
    RedisResp reverseRange(String key, long start, long end);

    /**
     * 返回一个成员范围的有序集合，按分数，以分数排序从高分到低分
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    RedisResp reverseRangeByScore(String key, double min, double max, long offset, long count);

    /**
     *
     * <返回有序集key中成员member的排名，其中有序集成员按score值从大到小排列>
     * <排名以0为底，也就是说，score值最大的成员排名为0。>
     * @param key
     * @param member 有序集key的成员
     * @return 如果member是有序集key的成员，返回integer-reply:member的排名。<br>
     *     如果member不是有序集key的成员，返回bulk-string-reply: nil。
     * @see [类、类#方法、类#成员]
     */
    RedisResp zRevRank(String key, String member);

    /**
     *
     * <一句话功能简述>返回有序集key中成员member的排名。<br>
     * <功能详细描述>其中有序集成员按score值递增(从小到大)顺序排列。排名以0为底，也就是说，score值最小的成员排名为0。
     *     使用ZREVRANK命令可以获得成员按score值递减(从大到小)排列的排名。
     * @param key
     * @param member
     * @return  如果member是有序集key的成员，返回integer-reply：member的排名。<br>
     *  如果member不是有序集key的成员，返回bulk-string-reply: nil。
     * @see [类、类#方法、类#成员]
     */
    RedisResp zRank(String key, String member);

    /**
     * <一句话功能简述>返回有序集key中，成员member的score值。<br>
     * <功能详细描述>如果member元素不是有序集key的成员，或key不存在，返回nil。
     * @param key
     * @param member
     * @return member成员的score值（double型浮点数）
     * @see [类、类#方法、类#成员]
     */
    RedisResp zScore(String key, String member);

    /**
     * <一句话功能简述>有序集key中，给指定成员member增加score值。<br>
     * <功能详细描述>如果member元素不是有序集key的成员，或key不存在，返回nil。
     * @param key
     * @param member
     * @return member成员的score值（double型浮点数）
     * @see [类、类#方法、类#成员]
     */
    RedisResp incrementScore(String key, String member, double score);

    /**
     * <一句话功能简述>返回一个成员范围的有序集合，按分数，以分数排序从低分到高分,连同分数一起。
     * <功能详细描述>
     * @param key
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp zRangeWithScores(String key, long start, long end);

    /**
     * <返回一个成员范围的有序集合，按分数，以分数排序从高分到低分,连同分数一起。>
     * <功能详细描述>
     * @param key
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp zRevRangeWithScores(String key, long start, long end);

    /**
     * <一句话功能简述>判断当前key是否存在
     * <功能详细描述>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp exists(String key);

    /**
     * <返回key的有序集元素个数>
     * <功能详细描述>
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp zCard(String key);

    /**
     * 返回列表key的长度。<br>
     * 如果key不存在，则key被解释为一个空列表，返回0. 如果key不是列表类型，返回一个错误。<br>
     * @param key 键
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp lLen(String key);

    /**
     * 返回列表key中指定区间内的元素，区间以偏移量start和stop指定。
     * <下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。 你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。>
     * @param key
     * @param start
     * @param end
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp lRange(String key, long start, long end);

    RedisResp rightPop(String key);

    /**
     * <移除set集合key中的一个或多个member元素，不存在的member元素会被忽略。>
     * <功能详细描述>
     * @param key
     * @param values
     * @return
     * @see [类、类#方法、类#成员]
     */
    RedisResp sRemove(String key, Object... values);

    /**
     * <移除sortset集合key中的一个或多个member元素，不存在的member元素会被忽略。>
     * @param key
     * @param values
     * @return
     */
    RedisResp zRem(String key, Object... values);

    /**
     * <通过SETNX 和 expire 实现简单的分布式锁>
     * <命令[SETNX KEY VALUE]:将 key 的值设为 value ，当且仅当 key 不存在。若给定的 key 已经存在，则 SETNX 不做任何动作。>
     * <命令[EXPIRE KEY]:为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。>
     * @param key
     * @param value
     * @param timeout 这是锁的失效时间
     * @return
     */
    RedisResp setNx(String key, String value, long timeout);

    /**
     * <可批量删除与通配符相匹配的key>
     * <通过命令[KEYS pattern]获取所有匹配pattern的Keys。而后[DEL keys...]批量删除>
     * @param pattern
     * @return
     */
    @Deprecated
    RedisResp delKeysInPattern(String pattern);

    /**
     * <通过Jedis直连批量删除与通配符相匹配的key>
     * <Jedis直连通过命令[KEYS pattern]获取所有匹配pattern的Keys。而后[DEL keys...]批量删除>
     * @param pattern
     * @return
     */
    @Deprecated
    RedisResp delKeysInPatternByJedis(String url, String pattern);

    /**
     * 增加String类型值
     * @param key
     * @param value
     * @return
     */
    RedisResp increment(String key, long value);

    /**
     * 设置失效时间
     * @param key
     * @param timeout
     * @param timeUnit
     * @return 是否设置成功
     */
    RedisResp expire(String key, long timeout, TimeUnit timeUnit);

    /**
     * Set类型查询操作
     * SMEMBERS方法
     * @param key
     * @return
     */
    RedisResp smembers(String key);

    /**
     * <批量插入ZSET>
     * <因为TypedTuple类无法序列化 可使用其子类ZSetTypedTuple>
     * @param key
     * @param tuples
     * @return
     */
    RedisResp zadd(String key, Set<? extends ZSetOperations.TypedTuple> tuples);

    /**
     * <移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。>
     * <自版本2.1.6开始,score 值等于 min 或 max 的成员也可以不包括在内>
     * @param key
     * @param min   score要大于的最小值
     * @param max   score要小于的最大值
     * @return
     */
    RedisResp zRemRangeByScore(String key, double min, double max);

    /**
     * <移除有序集 key 中，从下标start到end之间(包括等于 min 或 max )的成员。>
     * @param key
     * @param start
     * @param end
     * @return
     */
    RedisResp zRemRange(String key, Long start, Long end);

    /**
     * <SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern ...]] [ASC | DESC] [ALPHA] [STORE destination]>
     * <返回或保存给定列表、集合、有序集合 key 中经过排序的元素。排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。>
     * @return
     */
    RedisResp sort(String key, String by, Boolean isDesc, Boolean alpha, Integer off, Integer num, List<String> gets);

    RedisResp sort(SortQuery<String> query);

    RedisResp sortPageList(String key, String subKey, String by, boolean isDesc, boolean isAlpha, int off, int num);

    RedisResp incrByValue(String key, Long value);
}
