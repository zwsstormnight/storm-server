package cn.nj.storm.redis.pojo.response;

import cn.nj.storm.redis.pojo.ZSetItem;
import cn.nj.storm.redis.pojo.request.RedisReq;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisResp implements Serializable {

    private static final long serialVersionUID = 1L;

    public RedisResp()
    {

    }

    public RedisResp(Integer resultCode, String resultDesc)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public RedisResp(Integer resultCode, String resultDesc, String data, String key)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.data = data;
        this.key = key;
    }

    public RedisResp(Integer resultCode, String resultDesc, Long numObj, String key)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.numObj = numObj;
        this.key = key;
    }

    public RedisResp(Integer resultCode, String resultDesc, Double doubleObj, String key)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.doubleObj = doubleObj;
        this.key = key;
    }

    public RedisResp(Integer resultCode, String resultDesc, List<Object> list, String key)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.list = list;
        this.key = key;
    }

    public RedisResp(Integer resultCode, String resultDesc, List<RedisReq> redisReqs, RedisReq redisReq)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.redisReqs = redisReqs;
        this.redisReq = redisReq;
    }

    public RedisResp(Integer resultCode, String resultDesc, Set<String> set, String key)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.set = set;
        this.key = key;
    }

    public RedisResp(Integer resultCode, String resultDesc, List<ZSetItem> sset)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.sset = sset;
    }

    public RedisResp(Integer resultCode, String resultDesc, Boolean bool)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.bool = bool;
    }

    public RedisResp(Integer resultCode, String resultDesc, Map<Object, Object> map, String key)
    {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.map = map;
        this.key = key;
    }

    /*
     * 返回结果标识码
     */
    private Integer resultCode;

    /*
     * 返回结果描述
     */
    private String resultDesc;

    /*
     * 缓存记录键
     */
    private String key;

    /*
     * 返回String数据
     */
    private String data;

    /*
     * 返回Long数据
     */
    private Long numObj;

    /*
     * 返回Double数据
     */
    private Double doubleObj;

    /*
     * 返回List数据
     */
    private List<Object> list;

    /*
     * 查询返回的Set
     */
    private Set<String> set;

    /*
     * 有序序列封装类对象
     */
    private List<ZSetItem> sset;

    /*
     * 和缓存相关的参数对象
     */
    private RedisReq redisReq;

    /*
     * 和缓存相关的参数对象集合
     */
    private List<RedisReq> redisReqs;

    /*
     * 布尔类型
     */
    private Boolean bool;

    /*
     * 返回Map持有化对象
     */
    private Map<Object, Object> map;

    public Integer getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(Integer resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getResultDesc()
    {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc)
    {
        this.resultDesc = resultDesc;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public Set<String> getSet()
    {
        return set;
    }

    public void setSet(Set<String> set)
    {
        this.set = set;
    }

    public List<Object> getList()
    {
        return list;
    }

    public void setList(List<Object> list)
    {
        this.list = list;
    }

    public Long getNumObj()
    {
        return numObj;
    }

    public void setNumObj(Long numObj)
    {
        this.numObj = numObj;
    }

    public Double getDoubleObj()
    {
        return doubleObj;
    }

    public void setDoubleObj(Double doubleObj)
    {
        this.doubleObj = doubleObj;
    }

    public List<ZSetItem> getSset()
    {
        return sset;
    }

    public void setSset(List<ZSetItem> sset)
    {
        this.sset = sset;
    }

    public Boolean getBool()
    {
        return bool;
    }

    public void setBool(Boolean bool)
    {
        this.bool = bool;
    }

    public Map<Object, Object> getMap()
    {
        return map;
    }

    public void setMap(Map<Object, Object> map)
    {
        this.map = map;
    }

    public RedisReq getRedisReq()
    {
        return redisReq;
    }

    public void setRedisReq(RedisReq redisReq)
    {
        this.redisReq = redisReq;
    }

    public List<RedisReq> getRedisReqs()
    {
        return redisReqs;
    }

    public void setRedisReqs(List<RedisReq> RedisReqs)
    {
        this.redisReqs = RedisReqs;
    }
}
