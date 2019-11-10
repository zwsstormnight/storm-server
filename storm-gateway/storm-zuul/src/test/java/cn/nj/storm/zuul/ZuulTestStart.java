package cn.nj.storm.zuul;

import cn.nj.storm.api.token.TokenApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/7/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@EnableDiscoveryClient
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/zuul")
public class ZuulTestStart {

    @Reference
    private TokenApi tokenApi;

    @GetMapping("/echo")
    public String echo() {
        return tokenApi.createRandom();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulTestStart.class);
    }
}
