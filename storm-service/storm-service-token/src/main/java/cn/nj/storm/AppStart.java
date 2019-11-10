package cn.nj.storm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 *
 * @author zwsst
 */
@EnableDiscoveryClient
@EnableAutoConfiguration
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
