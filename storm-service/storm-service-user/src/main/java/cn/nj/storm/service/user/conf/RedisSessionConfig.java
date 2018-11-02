package cn.nj.storm.service.user.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * <一句话功能简述>
 * <EnableRedisHttpSession 注解 默认是session有效1800秒即30分钟>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 5400)
public class RedisSessionConfig
{
//    @Bean
//    public static ConfigureRedisAction configureRedisAction()
//    {
//        return ConfigureRedisAction.NO_OP;
//    }
}
