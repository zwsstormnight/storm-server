package cn.nj.storm.redis.controller;

import org.springframework.web.bind.annotation.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("/redis")
public class RedisController
{
    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestBody String key)
    {
        return "redis 查询" + key;
    }

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("key") String key, @RequestParam("value") String value)
    {
        return "redis SET:" + key + ",value:" + value;
    }
}
