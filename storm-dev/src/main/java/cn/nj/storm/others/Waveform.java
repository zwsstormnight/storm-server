package cn.nj.storm.others;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/4/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Waveform
{
    private static long counter;
    
    private final long id = counter++;
    
    public String toString()
    {
        return "Waveform " + id;
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        long initialDelay = 10;
        long period = 2;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
//        service.scheduleAtFixedRate(new MyScheduledExecutor("job1"), initialDelay, period, TimeUnit.SECONDS);
        final long[] start = {System.currentTimeMillis()};
        // 从现在开始15分钟之后，每隔15分钟执行一次
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                long end = System.currentTimeMillis();
                System.out.println("---"+(end - start[0]));
                start[0] = end;
            }
        }, initialDelay, period, TimeUnit.SECONDS);
    }
}
