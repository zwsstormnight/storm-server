package cn.nj.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <ZuulStart>
 * <zuul网关启动类>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
//@EnableZuulProxy
@SpringCloudApplication
public class ZuulStart
{
    public static void main( String[] args )
    {
        SpringApplication.run(ZuulStart.class, args);
    }
}
