package cn.nj.storm.service.sso.hystrics;

import cn.nj.storm.service.sso.hystrics.impl.HystrixClientConfigration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@FeignClient(name = "storm-cache-memcache", configuration = HystrixClientConfigration.class)
public interface HystrixClientRemoteInterface
{
    @RequestMapping(value = "/getTeamInfo", method = RequestMethod.GET)
    String getTeamInfo(@RequestParam("teamName") String teamName);
}
