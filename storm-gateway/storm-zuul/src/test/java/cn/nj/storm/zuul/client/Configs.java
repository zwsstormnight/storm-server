package cn.nj.storm.zuul.client;

import cn.nj.storm.api.token.TokenApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/7/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class Configs {

    @Reference
    private TokenApi tokenApi;
}
