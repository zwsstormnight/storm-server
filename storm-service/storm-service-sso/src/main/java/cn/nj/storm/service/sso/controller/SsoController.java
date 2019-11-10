package cn.nj.storm.service.sso.controller;

import cn.nj.storm.service.sso.hystrics.HystrixClientRemoteInterface;
import cn.nj.storm.service.sso.service.RemoteInterface;
import cn.nj.storm.service.sso.service.SsoService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/sso")
public class SsoController
{
    @Autowired
    private RemoteInterface remoteInterface;

    @Autowired
    private SsoService ssoService;
    
    @Autowired
    private HystrixClientRemoteInterface hystrixClientRemoteInterface;
    
    /**
     * 容错方法在接口实现类中
     * @param name
     * @return
     */
    @RequestMapping(value = "/teamInfo")
    @ResponseBody
    public String getTeamInfo(String name)
    {
        return hystrixClientRemoteInterface.getTeamInfo(name);
    }

    /**
     * 容错方法不在接口实现 而在controller本地
     * @param name
     * @return
     */
//    @HystrixCommand(fallbackMethod = "getNameFallback")
    @RequestMapping(value = "/name")
    @ResponseBody
    public String getCompanyInfo(String name)
    {
        String result = remoteInterface.getNameByProvider(name);
        System.out.println(result);
        return result;
    }
    
    /**
     * 主要注意的是： 容错的方法， 参数要与请求的一致。
     * 容错返回的方法
     * @return
     */
    public String getNameFallback(String name)
    {
        return "this is fallBack, real name is " + name;
    }

//    @HystrixCommand(fallbackMethod = "getPostFallback")
    @RequestMapping(value = "/post")
    @ResponseBody
    public String getPost()
    {
        return remoteInterface.postName();
    }

    public String getPostFallback()
    {
        return "this is fallBack, post has failed ";
    }


    @RequestMapping(value = "/list")
    @ResponseBody
    public String list()
    {
        return ssoService.list();
    }
}
