package cn.nj.storm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * <UserServer>
 * <用户模块服务启动类>
 *
 * @author zhengweishun
 * @version [版本号, 2017/8/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SpringBootApplication
@MapperScan("cn.nj.storm.**.mapper")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class UserServer
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserServer.class, args);
    }
}
