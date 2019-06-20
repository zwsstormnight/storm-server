package cn.nj.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <EurekaServer>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer
{
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServer.class, args);
    }
}
