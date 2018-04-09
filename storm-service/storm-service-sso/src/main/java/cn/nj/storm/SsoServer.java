package cn.nj.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <SsoServer>
 * <单点登录服务启动类>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class SsoServer
{
    public static void main( String[] args )
    {
        SpringApplication.run(SsoServer.class, args);
    }
}
