package cn.nj.storm.others.concurrent;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/2/2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TaskParamS extends TaskParam implements Cloneable
{
    @Override
    public Object clone()
    {
        Object obj = null;
        try
        {
            obj = super.clone();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return obj;
    }
}
