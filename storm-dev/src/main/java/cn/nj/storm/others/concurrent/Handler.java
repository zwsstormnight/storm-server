package cn.nj.storm.others.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/2/2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Handler
{
    public void execute(List<String> channelIds)
    {
        TaskParam taskParam = new TaskParam();
        String channelId = taskParam.getChannelId();
        String jobId = "" + System.currentTimeMillis();//在JobHistory中记录任务开始时间
        
        if (channelId == null)
        {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            //            BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);
            //
            //            ThreadPoolExecutor executor =
            //                new ThreadPoolExecutor(3, 3, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
            //            ExecutorService pool = Executors.newCachedThreadPool();
            //            List<Runnable> tasks = new ArrayList<>();
            List<Callable<Object>> tasks = new ArrayList<>();
            for (String id : channelIds)
            {
                //遍历每一个渠道
                taskParam.setChannelId(id);
                taskParam.setJobId(jobId);
                tasks.add(new Callable()
                {
                    @Override
                    public Object call()
                        throws Exception
                    {
                        return handleData(TaskParam.setNewFields(taskParam));
                    }
                });
            }
            try
            {
                List<Future<Object>> futures = executorService.invokeAll(tasks);
                for (Future<Object> f : futures)
                {
                    Map resp = (Map)f.get();
                    System.out.println(resp.toString());
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (channelId != null)
        {
            this.handleData(taskParam);
        }
    }
    
    public Map<String, Object> handleData(final TaskParam taskParam)
    {
        String channelId = taskParam.getChannelId();
        Thread thread = Thread.currentThread();
        System.out.println("================" + thread.getName() + ":" + thread.getId() + "-->" + channelId + ":"
            + taskParam.getJobId() + "================");
        Map<String, Object> map = new HashMap();
        map.put("channelId", channelId);
        return map;
    }
    
    public static void main(String[] args)
    {
        Handler handler = new Handler();
        List<String> channelIds = new ArrayList<>();
        for (int i = 1; i <= 50; i++)
        {
            channelIds.add(i + "");
        }
        try
        {
            handler.execute(channelIds);
            Thread.sleep(1000000L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
