package cn.nj.storm.redis.repository.assemble;

import cn.nj.storm.redis.repository.constants.Constants;
import cn.nj.storm.redis.repository.dto.response.RedisResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class RedisBasicService
{
    private static final Logger logger = LoggerFactory.getLogger("interface");
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    public RedisResp get(String key)
    {
        String strValue = (String)stringRedisTemplate.opsForValue().get(key);
        logger.info("接口调用详情：参数K-V： " + key + "=" + strValue);
        RedisResp resultBean =
            new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
    public RedisResp set(String key, String value)
    {
        stringRedisTemplate.opsForValue().set(key, value);
        String strValue = (String)stringRedisTemplate.opsForValue().get(key);
        RedisResp resultBean =
                new RedisResp(Constants.RESULT_CODE_SUCCESS, Constants.RESULT_DESC_SUCCESS, strValue, key);
        return resultBean;
    }
    
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
}