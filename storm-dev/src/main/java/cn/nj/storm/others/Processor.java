package cn.nj.storm.others;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/4/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Processor
{
    public String name()
    {
        return getClass().getSimpleName();
    }
    
    Object process(Object input)
    {
        return "Processor "+input;
    }
}
