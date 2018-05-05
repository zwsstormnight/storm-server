package cn.nj.storm.redis.repository.helpers;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Constants
{
    /** result code */
    public static final Integer RESULT_CODE_SUCCESS = 00000;
    public static final Integer RESULT_CODE_FAIL = 99999;
    /** 具体失败编码 30开头的5位 */
    public static final Integer RESULT_CODE_IDENTITY_FAIL = 30001;

    /** result description */
    public static final String RESULT_DESC_SUCCESS = "SUCCESS";
    public static final String RESULT_DESC_FAIL = "FAIL";

    public static final String RESULT_DESC_IDENTITY_FAIL = "未获取有效的消费者信息！";

    public static final String KEY_TYPE_HASH = "hash";

    public static final String KEY_TYPE_LIST = "list";

    public static final String KEY_TYPE_SET = "set";

    public static final String KEY_TYPE_ZSET = "zset";

    public static final String KEY_TYPE_STRING = "string";
}
