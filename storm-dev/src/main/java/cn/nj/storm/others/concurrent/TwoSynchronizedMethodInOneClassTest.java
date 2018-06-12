package cn.nj.storm.others.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <一句话功能简述>
 * <同一实例的两个synchronized方法不可以被两个线程同时访问，因为对象锁被占用。
 * 也就是说，同一时刻，同一实例（注意，不是同一个类）的多个synchronized方法最多只能有一个被访问。 >
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TwoSynchronizedMethodInOneClassTest
{
    private static int counter = 0;
    
    public synchronized void a()
    {
        while (true)
        {
            // Once this method is called by a thread, it will hold the lock, and not give way to other access thead.
            System.out.println("AA---" + counter++);
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public synchronized void b()
    {
        while (true)
        {
            System.out.println("---BB" + counter++);
            try
            {
                Thread.sleep(200);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    } // single instance will use the same lock object.
    
    public static TwoSynchronizedMethodInOneClassTest instance = new TwoSynchronizedMethodInOneClassTest();
    
    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread a = new Thread()
        {
            @Override
            public void run()
            {
                TwoSynchronizedMethodInOneClassTest.instance.a();
            }
        };
        Thread b = new Thread()
        {
            @Override
            public void run()
            {
                TwoSynchronizedMethodInOneClassTest.instance.b();
                // The same lock will not trun to state of idel, can method b() can never be accessed. // new T().b(); // new T() will ues a different lock object, so method b() can be accessed.
            }
        };
        a.start();
        b.start();
    }
    
}
