package cn.nj.storm.redis.repository.assemble;

import cn.nj.storm.redis.repository.dto.response.RedisResp;
import cn.nj.storm.redis.repository.helpers.bean.ZSetItemS;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RedisComboService
{
    /**
     * <获取sortset对score增值后结果 按时长限定生存时间>
     * @param key
     * @param member
     * @param score
     * @param timeout 第一次插入值可设定KEY存留的时间长度，如果传入为null则永久保存
     * @param unit 默认时间类型：秒
     * @return
     */
    RedisResp zscoreAftIncrInTimes(String key, String member, double score, Long timeout, TimeUnit unit);

    /**
     * <获取sortset对score增值后结果 按日期限定生存时间>
     * @param key
     * @param member
     * @param score
     * @param expireDate 第一次插入值可设定KEY存活的截至日期，如果传入为null则永久保存
     * @return
     */
    RedisResp zscoreAftIncrAtDay(String key, String member, double score, Date expireDate);

    /**
     * <获取sortset对score新增或覆盖的结果 按日期限定生存时间>
     * @param key
     * @param member
     * @param score
     * @param expireDate 第一次插入值可设定KEY存活的截至日期，如果传入为null则永久保存
     * @return
     */
    RedisResp zscoreAftAddAtDay(String key, String member, double score, Date expireDate);

    /**
     * <设定当前key的值 并按日期限定生存时间>
     * @param key
     * @param value
     * @param expireDate 第一次插入值可设定KEY存活的截至日期，如果传入为null则永久保存
     * @return
     */
    RedisResp setValAtDay(String key, String value, Date expireDate);

    /**
     * <查询当前key的值 并返回剩余的生存时间>
     * @param key
     * @return
     */
    RedisResp getValWithTTL(String key);

    /**
     * <根据list插入的记录同步更新多个有序集>
     * @param zsetMap 需要变更的第一个有序集
     * @param base score变更的基数
     * @return
     */
    RedisResp zscoreAftIncrByList(List<ZSetItemS> zsetMap, Integer base, String key, String member, Date live);

    /**
     * <同时将多个 field-value (域-值)对设置到哈希表 key中，并设置有效时间到具体日期>
     * <此命令会覆盖哈希表中已存在的域 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。>
     * @param key
     * @param map
     * @param date
     * @return
     */
    RedisResp hmSetAtTime(String key, Map<? extends Object, ? extends Object> map, Date date);

    /**
     * <根据目标score计算当前的增量>
     * @param key
     * @param targetMem
     * @param targetScore
     * @param lastMem
     * @param expireDate
     * @return
     */
    RedisResp incrFromLastScore(String key, String targetMem, Long targetScore, String lastMem, Date expireDate);

    /**
     * 基础功能验证
     * 乐观锁：事务
     * 管道流水执行：pipeline
     * Lua脚本
     */
    RedisResp inventory(String userKey, String storeKey, Date expireDate);

    RedisResp pipeLineInventory(String userKey, String storeKey, Date expireDate);

    RedisResp scriptInventory(String... scripts);
}
