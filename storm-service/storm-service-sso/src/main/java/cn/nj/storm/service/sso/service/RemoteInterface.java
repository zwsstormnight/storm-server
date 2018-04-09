package cn.nj.storm.service.sso.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
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
@FeignClient("storm-cache-memcache")
public interface RemoteInterface
{
    /**
     * 获取名称信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/memcache/base/getNameByProvider", method = RequestMethod.GET)
    String getNameByProvider(@RequestParam("name") String name);


    @RequestMapping(value = "/memcache/base/get", method = RequestMethod.POST)
    String getName(@RequestBody String key);

    @RequestMapping(value = "/memcache/base/postName", method = RequestMethod.POST)
    String postName();
}
