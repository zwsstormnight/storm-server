package cn.nj.storm.redis.repository.helpers.interceptors;

import cn.nj.storm.redis.repository.helpers.annotations.EnableRedisPipelined;
import cn.nj.storm.redis.repository.helpers.annotations.EnableRedisTransactional;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * <redis操作执行拦截器策略>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisOperationInterceptor implements MethodInterceptor
{
    @Override
    public Object invoke(MethodInvocation methodInvocation)
        throws Throwable
    {
        Object[] ars = methodInvocation.getArguments();
        
        for (Object o : ars)
        {
            System.out.println(o);
        }
        // 判断该方法是否加了@EnableRedisPipelined 注解
        if (methodInvocation.getMethod().isAnnotationPresent(EnableRedisPipelined.class))
        {
            System.out.println("----------this method is added @EnableRedisPipelined-------------------------");
            EnableRedisPipelined pipelined = methodInvocation.getMethod().getAnnotation(EnableRedisPipelined.class);
            String[] methods = pipelined.methods();
            System.out.println(methods);
        }
        // 判断该方法是否加了@EnableRedisTransactional 注解
        if (methodInvocation.getMethod().isAnnotationPresent(EnableRedisTransactional.class))
        {
            System.out.println("----------this method is added @EnableRedisTransactional-------------------------");
        }
        //执行被拦截的方法，切记，如果此方法不调用，则被拦截的方法不会被执行。
        return methodInvocation.proceed();
    }
}
