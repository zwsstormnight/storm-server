package cn.nj.storm.controller;

import org.springframework.web.bind.annotation.*;

/**
 * <基本控制层>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/3/29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/memcache/base")
public class BaseController
{
    @RequestMapping("/get")
    @ResponseBody
    public String getName(@RequestBody String key)
    {
        return "memcache 查询" + key;
    }
    
    @RequestMapping("/set")
    @ResponseBody
    public String setName(@RequestParam("key") String key, @RequestParam("value") String value)
    {
        return "memcache SET:" + key + ",value:" + value;
    }
}
