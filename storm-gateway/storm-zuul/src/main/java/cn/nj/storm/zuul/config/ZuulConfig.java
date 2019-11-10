package cn.nj.storm.zuul.config;

import cn.nj.storm.zuul.filter.ZuulErrorFilter;
import cn.nj.storm.zuul.filter.ZuulAuthenticateFilter;
import cn.nj.storm.zuul.filter.processor.ExtFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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
public class ZuulConfig {

    @Value("${zuul.prefix}")
    private String zuulPrefix;

    /**
     * 设置启用自定义的核心处理器以完成我们的优化目标。
     */
    @PostConstruct
    public void init() {
        FilterProcessor.setProcessor(new ExtFilterProcessor());
    }

    /**
     *
     * @return
     */
    @Bean
    public ZuulAuthenticateFilter zuulTokenFilter() {
        return new ZuulAuthenticateFilter(zuulPrefix);
    }

    @Bean
    public ZuulErrorFilter zuulErrorFilter() {
        return new ZuulErrorFilter();
    }

}
