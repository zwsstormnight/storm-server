package cn.nj.storm.memcache.controller;

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
public class MemcacheController
{
    @RequestMapping(value = "/get", method = RequestMethod.POST)
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
    
    @RequestMapping(value = "/getNameByProvider", method = RequestMethod.GET)
    @ResponseBody
    public String getNameByProvider(@RequestParam("name") String name)
    {
        // 这里是要和数据库交互， 这里为了操作简单直接返回一个数据。  
        return "provider 查询" + name + "公司信息";
    }
    
    @RequestMapping(value = "/getTeamInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getTeamInfo(@RequestParam("teamName") String teamName)
    {
        // 返回团队信息。  
        return "provider 查询" + teamName + "团队信息";
    }
    
    @RequestMapping(value = "/postName", method = RequestMethod.POST)
    @ResponseBody
    public String postName()
    {
        return "provider post test";
    }
}
