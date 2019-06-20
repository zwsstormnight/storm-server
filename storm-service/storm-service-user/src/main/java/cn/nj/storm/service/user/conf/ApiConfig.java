package cn.nj.storm.service.user.conf;

import cn.nj.storm.service.user.interceptor.TokenAuthorInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class ApiConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new TokenAuthorInterceptor());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(fastJsonHttpMessageConverter());
    }

    /**
     * 1、需要先定义一个 convert 转换消息对象；
     * 2、添加 fastJson 的配置信息，比如: 是否要格式化返回的Json数据；
     * 3、在 Convert 中添加配置信息;
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter()
    {
        //1、需要先定义一个 convert 转换消息对象；
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        //2、添加 fastJson 的配置信息，比如: 是否要格式化返回的Json数据；
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        ValueFilter valueFilter = (o, s, o1) -> {
            if (null == o1)
            {
                o1 = "";
            }
            return o1;
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        //3、在 Convert 中添加配置信息;
        converter.setFastJsonConfig(fastJsonConfig);
        return converter;
    }
}
