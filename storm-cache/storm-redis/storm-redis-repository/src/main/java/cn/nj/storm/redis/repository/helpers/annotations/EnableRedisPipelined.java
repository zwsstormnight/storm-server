package cn.nj.storm.redis.repository.helpers.annotations;

import java.lang.annotation.*;

/**
 * <使可实现redis 动态生成pipeline执行的注解>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableRedisPipelined
{

}
