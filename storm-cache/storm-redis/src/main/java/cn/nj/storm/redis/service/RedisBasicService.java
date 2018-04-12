package cn.nj.storm.redis.service;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RedisBasicService {

    String get(String key);

    String set(String key, String value);
}
