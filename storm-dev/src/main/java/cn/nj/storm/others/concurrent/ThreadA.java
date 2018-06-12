package cn.nj.storm.others.concurrent;

import cn.nj.storm.others.concurrent.singleton.DbConnection;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ThreadA extends Thread
{
    private TwoSync obj;
    
    public ThreadA(TwoSync obj)
    {
        super();
        this.obj = obj;
    }

    @Override
    public void run()
    {
        super.run();
        obj.methodA();
        System.out.println(DbConnection.getConnection().hashCode());
    }
}
