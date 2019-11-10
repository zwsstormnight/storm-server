package cn.nj.storm.service.user.controller;

import cn.nj.storm.service.user.bean.DemoBean;
import cn.nj.storm.service.user.bean.User;
import cn.nj.storm.service.user.service.UserService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <用户>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping(path = {"/rest/user", "/api/user"})
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/demo")
    public List<DemoBean> demo() throws Exception {
        throw new Exception("随便抛个异常");
//        return userService.list();
    }

    @PostMapping(value = "/register")
    public String register(User user) {
        System.out.println("start register");
        return "register user";
    }

    @PostMapping(value = "/login")
    public String login(User user) {
        return null;
    }

    @PostMapping(value = "/logout")
    public String logout(User user) {
        return null;
    }

}
