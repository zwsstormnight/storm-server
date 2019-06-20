package cn.nj.storm.service.user.controller;

import cn.nj.storm.common.utils.LoggerInitializer;
import cn.nj.storm.service.user.bean.DemoBean;
import cn.nj.storm.service.user.bean.User;
import cn.nj.storm.service.user.service.UserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/user")
public class UserController implements LoggerInitializer
{
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/demo")
    public List<DemoBean> demo() throws Exception {
        throw new Exception("随便抛个异常");
//        return userService.list();
    }

    @PostMapping(value = "/register")
    public String register(User user){
        System.out.println("start register");
        return "register user";
    }

    @GetMapping(value = "/first")
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>(1);
        map.put("request Url", request.getRequestURL());
        request.getSession().setAttribute("map", map);
        map.put("ttl",request.getSession().getMaxInactiveInterval());
        map.put("sessionid",request.getSession().getId());
        return map;
    }

    @GetMapping(value = "/user")
    public String userinfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>(2);
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return JSON.toJSONString(map);
    }

    @PostMapping(value = "/login")
    public String login(User user){
        return null;
    }

    @PostMapping(value = "/logout")
    public String logout(User user){
        return null;
    }
    
}
