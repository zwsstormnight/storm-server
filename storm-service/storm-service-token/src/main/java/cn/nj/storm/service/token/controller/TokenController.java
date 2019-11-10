package cn.nj.storm.service.token.controller;

import cn.nj.storm.api.token.TokenApi;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/17]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(value = "/token")
@Service
public class TokenController implements TokenApi {

    @RequestMapping(value = "/create")
    @Override
    public String createRandom() {
        return UUID.randomUUID().toString();
    }
}
