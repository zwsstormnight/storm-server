package cn.nj.storm.redis.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/12/2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class RedisConfig {

    @Bean(name = "loginScript")
    public RedisScript<String> redisScript() {
        String script = "local userKey, sessionId, sessionLimit = KEYS[1],ARGV[1],ARGV[2];" +
                "local size = redis.call('LPUSH', userKey,sessionId);" +
                "if tonumber(size) >= tonumber(sessionLimit) then local kick = redis.call('RPOP', userKey);" +
                "return kick;" +
                "end;" +
                "return nil;";
        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
        return redisScript;
    }
}
