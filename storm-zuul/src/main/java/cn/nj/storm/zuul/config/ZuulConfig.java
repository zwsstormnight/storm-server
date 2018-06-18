package cn.nj.storm.zuul.config;

import cn.nj.storm.zuul.filter.ZuulErrorFilter;
import cn.nj.storm.zuul.filter.ZuulTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <zuul配置项>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class ZuulConfig
{
    
    @Value("${zuul.prefix}")
    private String zuulPrefix;
    
    @Bean
    public ZuulTokenFilter zuulTokenFilter()
    {
        return new ZuulTokenFilter(zuulPrefix);
    }
    
    @Bean
    public ZuulErrorFilter zuulErrorFilter()
    {
        return new ZuulErrorFilter();
    }
}
