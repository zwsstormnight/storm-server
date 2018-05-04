package cn.nj.storm.redis.repository.dto.request;

import java.io.Serializable;
import java.util.Collection;

/**
 * <redis 请求实力化对象>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisReq implements Serializable {

    private static final long serialVersionUID = 1L;

    public RedisReq(){

    }

    public RedisReq(String key, String hashKey, String value) {
        this.key = key;
        this.hashKey = hashKey;
        this.value = value;
    }

    public RedisReq(String url, String key, String keyType, String hashKey) {
        this.url = url;
        this.key = key;
        this.keyType = keyType;
        this.hashKey = hashKey;
    }

    public RedisReq(String ipAddress, Integer port, String key, String keyType, String hashKey) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.key = key;
        this.keyType = keyType;
        this.hashKey = hashKey;
    }

    public RedisReq(String url, String key, String keyType, Collection<String> keys) {
        this.url = url;
        this.key = key;
        this.keyType = keyType;
        this.keys = keys;
    }

    public RedisReq(String key, String keyType, Long score, String value) {
        this.key = key;
        this.keyType = keyType;
        this.score = score;
        this.value = value;
    }

    private String url;

    private String ipAddress;

    private Integer port;

    private String key;

    private String keyType;

    private String hashKey;

    private Long score;

    private Long start;

    private Long end;

    private Collection<String> keys;

    private String value;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Collection<String> getKeys() {
        return keys;
    }

    public void setKeys(Collection<String> keys) {
        this.keys = keys;
    }
}
