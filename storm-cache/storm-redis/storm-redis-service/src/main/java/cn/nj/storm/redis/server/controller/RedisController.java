package cn.nj.storm.redis.server.controller;

import cn.nj.storm.redis.repository.assemble.impl.RedisBasicServiceImpl;
import cn.nj.storm.redis.repository.dto.request.RedisReq;
import cn.nj.storm.redis.repository.dto.response.RedisResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    @Autowired
    private RedisBasicServiceImpl redisBasicService;
    
    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestBody RedisReq redisReq)
    {
        RedisResp redisResp = redisBasicService.get(redisReq.getKey());
        System.out.println(redisResp.getData());
        return redisResp.getData();
    }
    
    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestBody(required = false) RedisReq redisReq)
    {
        RedisResp redisResp = redisBasicService.set(redisReq.getKey(), redisReq.getValue());
        return "redis SET:" + redisResp.getKey() + ",value:" + redisResp.getData();
    }
    
    private static Map<String, Object> values = new HashMap<>();
    
    private static Map<String, Map<String, Object>> map = new HashMap<>();
    
    @PostConstruct
    public void init()
        throws Exception
    {
        values.put("A", 1);
        values.put("b", 2);
        Map local = new HashMap<>();
        local.put("12", "asd");
        local.put("13", "asd1");
        local.put("14", "asd2");
        local.put("15", "asd3");
        map.put("c", local);
    }
    
    @PostMapping("/reactive/set")
    public Mono<ResponseEntity<RedisResp>> setValue(@RequestBody(required = false) RedisReq redisReq)
    {
        values.put(redisReq.getKey(), redisReq.getValue());
        RedisResp redisResp = redisBasicService.set(redisReq.getKey(), redisReq.getValue());
        System.out.println(redisResp.getData());
        return Mono.just(new ResponseEntity<>(redisResp, HttpStatus.OK));
    }
    
    @PostMapping("/reactive/keys")
    public Flux<RedisResp> keys(@RequestBody(required = false) RedisReq redisReq)
    {
        String key = redisReq.getKey();
        List<RedisResp> result = new ArrayList<>();
        if (key == null || key.equals(""))
        {
            result = map.entrySet().stream().map(entry -> {
                RedisResp temp = new RedisResp();
                temp.setKey(entry.getKey());
                temp.setMap(entry.getValue());
                return temp;
            }).collect(Collectors.toList());
            result.addAll(values.entrySet().stream().map(entry -> {
                RedisResp temp = new RedisResp();
                temp.setKey(entry.getKey());
                temp.setData("" + entry.getValue());
                return temp;
            }).collect(Collectors.toList()));
        }
        else
        {
            
        }
        return Flux.fromIterable(result);
    }
    
}
