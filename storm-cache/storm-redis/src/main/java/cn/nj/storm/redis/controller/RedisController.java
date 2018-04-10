package cn.nj.storm.redis.controller;

import cn.nj.storm.redis.pojo.request.RedisReq;
import cn.nj.storm.redis.pojo.response.RedisResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
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
public class RedisController {
    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestBody String key) {
        return "redis 查询" + key;
    }

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("key") String key, @RequestParam("value") String value) {
        return "redis SET:" + key + ",value:" + value;
    }

    private static Map<String, Object> values = new HashMap<>();

    private static Map<String, Map<String, Object>> map = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
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
    public Mono<ResponseEntity<String>> setValue(@RequestBody(required = false) RedisReq redisReq) {
        String result = redisReq.getKey() + ":" + redisReq.getValue();
        return Mono.just(new ResponseEntity<>(result, HttpStatus.OK));
    }

    @PostMapping("/reactive/keys")
    public Flux<RedisResp> keys(@RequestBody(required = false) RedisReq redisReq) {
        String key = redisReq.getKey();

        if (key == null || key == "") {
            map.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
        } else {

        }


        return Flux.just();
    }


}
