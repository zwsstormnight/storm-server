package cn.nj.storm.others.concurrent;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TwoSync
{
    synchronized public void methodA()
    {
        try
        {
            System.out.println(System.currentTimeMillis()+":methodA");
            methodB();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    synchronized public void methodB()
    {
        try
        {
            System.out.println(System.currentTimeMillis()+":methodB");
            methodA();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        TwoSync t = new TwoSync();
        ThreadA a = new ThreadA(t);
        ThreadB b = new ThreadB(t);
        try
        {
            a.start();
            b.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
