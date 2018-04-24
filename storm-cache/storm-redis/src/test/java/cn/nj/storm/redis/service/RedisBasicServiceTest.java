package cn.nj.storm.redis.service;

import cn.nj.storm.RedisServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/24]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisServer.class)
public class RedisBasicServiceTest
{
    @Autowired
    private RedisBasicService redisBasicService;
    
    @Test
    public void a()
    {
        //        String key = "A:ACTIVITY:TEMPLATE:PROJECT:USER:LOCK:" + activityId + ":" + projectId + ":" + userId;
        //        try
        //        {
        //            while (!redisService.setNx(key, "1", 1000L).getBool())
        //            {
        //                interfaceLogger.info("{}|{}|{}",
        //                        "info",
        //                        "TemplateUserProjectService setUserProjectRecord 未能获取到当前锁",
        //                        key);
        //                Thread.sleep(100L);
        //            }
        //            setUserProjectRecord(activityId, projectId, userId);
        //        }
        //        catch (Exception e)
        //        {
        //            interfaceLogger.error("{}|{}|{}", "error", "TemplateUserProjectService setUserProjectRecord 异常", e);
        //        }
        //        finally
        //        {
        //            redisService.delete(key);
        //        }
    }
    
    public void task()
    {
//        try
//        {
//            ExecutorService pool = Executors.newCachedThreadPool();
//            List<Callable<Object>> tasks = new ArrayList<>();
//            for (int i = 0; i < 5; i++)
//            {
//                final int index = i;
//                tasks.add(new Callable()
//                {
//                    @Override
//                    public Object call()
//                            throws Exception
//                    {
//                        return runPlanService.selectHomePageInfo(runPlanBaseReq);
//                    }
//                });
//            }
//            //Future用于异步线程获取返回结果
//            List<Future<Object>> futures = pool.invokeAll(tasks);
//            for (Future<Object> f : futures)
//            {
//                String resp = (String)f.get();
//                System.out.println(resp);
//            }
//            pool.shutdown();
//            pool.awaitTermination(10000, TimeUnit.MILLISECONDS);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}