package cn.nj.storm.redis.repository.helpers;

import org.apache.commons.lang.StringUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StringHelper
{
    public static String superaddStr(String value, String pattern)
    {
        //忽略大小写
        int index = StringUtils.lastIndexOfIgnoreCase(value, pattern);
        if (index == -1)
        {
            value = value + pattern;
        }
        return value;
    }

    public static String refactStr(String value, String pattern)
    {
        //忽略大小写
        boolean index = StringUtils.contains(value, pattern);
        if (!index)
        {
            value = value + pattern;
        }
        return value;
    }
}
