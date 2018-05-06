package cn.nj.storm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

/**
 * <RedisServer>
 * <redis服务启动类>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SpringBootApplication
@EnableEurekaClient
@ImportResource({"classpath:spring-application.xml"})
public class RedisServer
{
    public static void main( String[] args )
    {
        SpringApplication.run(RedisServer.class, args);
    }
}