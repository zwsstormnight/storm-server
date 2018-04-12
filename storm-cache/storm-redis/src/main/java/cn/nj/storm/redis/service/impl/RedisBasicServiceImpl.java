package cn.nj.storm.redis.service.impl;

import cn.nj.storm.redis.service.RedisBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class RedisBasicServiceImpl implements RedisBasicService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return stringRedisTemplate.opsForValue().get(key);
    }
}
