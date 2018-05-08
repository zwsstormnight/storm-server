package cn.nj.storm.persistence.mybatis.conf;

import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * <分页插件装配>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PageHelperConfig
{
    @Value("${pagehelper.helperDialect}")
    private String helperDialect;
    
    @Bean
    public PageInterceptor pageInterceptor()
    {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", helperDialect);
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
