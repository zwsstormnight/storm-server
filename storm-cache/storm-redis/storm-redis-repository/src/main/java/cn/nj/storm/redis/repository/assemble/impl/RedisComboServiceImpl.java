package cn.nj.storm.redis.repository.assemble.impl;

import cn.nj.storm.redis.repository.assemble.RedisComboService;
import cn.nj.storm.redis.repository.dto.response.RedisResp;
import cn.nj.storm.redis.repository.helpers.bean.ZSetItemS;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <redis组合命令实现类>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("redisComboService")
public class RedisComboServiceImpl implements RedisComboService
{
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * <获取sortset对score增值后结果 按时长限定生存时间>
     *
     * @param key   键
     * @param member
     * @param score
     * @param timeout 第一次插入值可设定KEY存留的时间长度，如果传入为null则永久保存
     * @param unit    默认时间类型：秒
     * @return
     */
    @Override
    public RedisResp zscoreAftIncrInTimes(String key, String member, double score, Long timeout, TimeUnit unit)
    {
        return null;
    }
    
    /**
     * <获取sortset对score增值后结果 按日期限定生存时间>
     *
     * @param key
     * @param member
     * @param score
     * @param expireDate 第一次插入值可设定KEY存活的截至日期，如果传入为null则永久保存
     * @return
     */
    @Override
    public RedisResp zscoreAftIncrAtDay(String key, String member, double score, Date expireDate)
    {
        return null;
    }
    
    /**
     * <获取sortset对score新增或覆盖的结果 按日期限定生存时间>
     *
     * @param key
     * @param member
     * @param score
     * @param expireDate 第一次插入值可设定KEY存活的截至日期，如果传入为null则永久保存
     * @return
     */
    @Override
    public RedisResp zscoreAftAddAtDay(String key, String member, double score, Date expireDate)
    {
        return null;
    }
    
    /**
     * <设定当前key的值 并按日期限定生存时间>
     *
     * @param key
     * @param value
     * @param expireDate 第一次插入值可设定KEY存活的截至日期，如果传入为null则永久保存
     * @return
     */
    @Override
    public RedisResp setValAtDay(String key, String value, Date expireDate)
    {
        return null;
    }
    
    /**
     * <查询当前key的值 并返回剩余的生存时间>
     *
     * @param key
     * @return
     */
    @Override
    public RedisResp getValWithTTL(String key)
    {
        return null;
    }
    
    /**
     * <根据list插入的记录同步更新多个有序集>
     *
     * @param zsetMap 需要变更的第一个有序集
     * @param base    score变更的基数
     * @param key
     * @param member
     * @param live    @return
     */
    @Override
    public RedisResp zscoreAftIncrByList(List<ZSetItemS> zsetMap, Integer base, String key, String member, Date live)
    {
        return null;
    }
    
    /**
     * <同时将多个 field-value (域-值)对设置到哈希表 key中，并设置有效时间到具体日期>
     * <此命令会覆盖哈希表中已存在的域 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。>
     *
     * @param key
     * @param map
     * @param date
     * @return
     */
    @Override
    public RedisResp hmSetAtTime(String key, Map<?, ?> map, Date date)
    {
        return null;
    }
    
    /**
     * <根据目标score计算当前的增量>
     *
     * @param key
     * @param targetMem
     * @param targetScore
     * @param lastMem
     * @param expireDate
     * @return
     */
    @Override
    public RedisResp incrFromLastScore(String key, String targetMem, Long targetScore, String lastMem, Date expireDate)
    {
        return null;
    }
    
    /**
     * 基础功能验证
     * 乐观锁：事务
     * 管道流水执行：pipeline
     * Lua脚本
     *
     * @param userKey
     * @param storeKey
     * @param expireDate
     */
    @Override
    public RedisResp inventory(String userKey, String storeKey, Date expireDate)
    {
        final List<String> list = new ArrayList<>();
        list.add(userKey);
        list.add(storeKey);
        final RedisResp resp = new RedisResp();
        try
        {
            List<Object> obj = redisTemplate.executePipelined(new SessionCallback<List<Object>>()
            {
                @Override
                public List<Object> execute(RedisOperations operations)
                        throws DataAccessException
                {
                    System.out.println(operations.opsForValue().get(storeKey));
                    operations.watch(list);
                    operations.multi();
                    Long curStore = operations.opsForValue().increment(storeKey, -1);
                    Long curUser = operations.opsForValue().increment(userKey, 1);
                    List<Object> rs = operations.exec();
                    System.out.println("执行结果：" + rs);
                    System.out.println(storeKey + "执行结果：" + curStore);
                    System.out.println(userKey + "执行结果：" + curUser);
                    String result =
                            "执行结果：" + rs + "|" + storeKey + "执行结果：" + curStore + "|" + userKey + "执行结果：" + curUser;
                    resp.setNumObj(curStore);
                    resp.setResultDesc(result);
                    return rs;
                }
            });
            System.out.println(obj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return resp;
    }
    
    @Override
    public RedisResp pipeLineInventory(String userKey, String storeKey, Date expireDate)
    {
        List<Object> results = null;
        //        results = redisTemplate.executePipelined(new SessionCallback<List<Object>>()
        //        {
        //            @Override
        //            public List<Object> execute(RedisOperations operations)
        //                throws DataAccessException
        //            {
        //                operations.
        //                return operations.;
        //            }
        //        });
        //pipeline
        RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>()
        {
            @Override
            public List<Object> doInRedis(RedisConnection connection)
                    throws DataAccessException
            {
                StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
                stringRedisConn.openPipeline();
                stringRedisConn.get(userKey.getBytes());
                stringRedisConn.incrBy(userKey.getBytes(), 100);
                List<Object> list = stringRedisConn.closePipeline();
                for (Object item : list)
                {
                    if (item instanceof byte[])
                    {
                        item = new String((byte[])item);
                    }
                    System.out.println(item);
                }
                return list;
            }
        };
        results = (List<Object>)redisTemplate.execute(pipelineCallback);
        for (Object item : results)
        {
            System.out.println(item);
        }
        RedisResp resp = null;
        if (CollectionUtils.isNotEmpty(results))
        {
            resp = new RedisResp();
            resp.setData((String)results.get(0));
            resp.setNumObj((Long)results.get(1));
        }
        return resp;
    }

    @Autowired
    private RedisScript<String> loginScript;

    @Override
    public RedisResp scriptInventory(String... scripts)
    {
        List<String> keys = new ArrayList<>();
        keys.add("user:sessions:list:root");
        List<Object> vals = new ArrayList<>();
        vals.add(UUID.randomUUID().toString());
        vals.add("3");
        //建议在你的应用上下文中配置一个DefaultRedisScript 的单例，避免在每个脚本执行的时候重复创建脚本的SHA1.
//        RedisScript<String> script = new DefaultRedisScript<>(scripts[0], String.class);
        String rest = redisTemplate.execute(loginScript, keys, vals.toArray());
        System.out.println("sha1:" + loginScript.getSha1());
        System.out.println("Lua:" + loginScript.getScriptAsString());
        System.out.println(rest);
//        DefaultRedisScript<String> redisScript = new DefaultRedisScript<String>();
//        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("dubbo/scripts/HelloWorld.lua")));
//        redisScript.setResultType(String.class);
//        String result = redisTemplate.execute(redisScript, keys, new Object[] {});
//        System.out.println("sha1:" + redisScript.getSha1());
//        System.out.println("Lua:" + redisScript.getScriptAsString());
//        System.out.println("result:" + result);
        return null;
    }

    private List<Object> deserializeMixedResults(List<Object> rawValues, RedisSerializer valueSerializer,
                                                 RedisSerializer hashKeySerializer, RedisSerializer hashValueSerializer)
    {
        if (rawValues == null)
        {
            return null;
        }
        List<Object> values = new ArrayList<Object>();
        for (Object rawValue : rawValues)
        {
            if (rawValue instanceof byte[] && valueSerializer != null)
            {
                values.add(valueSerializer.deserialize((byte[])rawValue));
            }
            else if (rawValue instanceof List)
            {
                // Lists are the only potential Collections of mixed values....
                values.add(
                        deserializeMixedResults((List)rawValue, valueSerializer, hashKeySerializer, hashValueSerializer));
            }
            else
            {
                values.add(rawValue);
            }
        }
        return values;
    }
}
