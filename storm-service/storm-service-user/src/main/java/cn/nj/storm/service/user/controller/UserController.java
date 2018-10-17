package cn.nj.storm.service.user.controller;

import cn.nj.storm.common.utils.LoggerInitializer;
import cn.nj.storm.service.user.bean.DemoBean;
import cn.nj.storm.service.user.bean.User;
import cn.nj.storm.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/user")
public class UserController implements LoggerInitializer
{
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/demo")
    public List<DemoBean> demoList() throws Exception {
        throw new Exception("随便抛个异常");
//        return userService.list();
    }

    @PostMapping(value = "/register")
    public String register(User user){
        System.out.println("start register");
        return "register user";
    }

    @GetMapping(value = "/register/user")
    public String register(HttpServletRequest request){
        return "user";
    }
    
}
