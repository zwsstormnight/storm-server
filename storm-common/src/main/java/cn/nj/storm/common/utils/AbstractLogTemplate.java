package cn.nj.storm.common.utils;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractLogTemplate implements LoggerInitializer
{
    public <T> String log(T t){
        return t.getClass().getSimpleName();
    }
}
