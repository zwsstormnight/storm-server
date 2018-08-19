package cn.nj.storm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AppTest
{
    public static void main(String[] args)
    {
        try
        {
            ExecutorService pool = Executors.newCachedThreadPool();
            List<Callable<List<? extends Dclass>>> tasks = new ArrayList<>();
            tasks.add((Callable) () -> {
                long t1 = System.currentTimeMillis();
                List<Aclass> alist = new ArrayList<>(1);
                Aclass aclass = new Aclass("A", 232);
                alist.add(aclass);
                System.out.println(System.currentTimeMillis() - t1 + "ms");
                return alist;
            });
            tasks.add((Callable) () -> {
                long t1 = System.currentTimeMillis();
                List<Bclass> blist = new ArrayList<>(1);
                Bclass bclass = new Bclass("A", "bbbbb");
                blist.add(bclass);
                System.out.println(System.currentTimeMillis() - t1 + "ms");
                return blist;
            });
//            ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            //Future用于异步线程获取返回结果
            List<Future<List<? extends Dclass>>> futures = pool.invokeAll(tasks);

            List<Aclass> a = (List<Aclass>)(futures.get(0).get());
            System.out.println(a.toString());
            pool.shutdown();
            pool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
class Dclass
{

}
class Aclass extends Dclass
{
    
    public Aclass()
    {
        
    }
    
    public Aclass(String id, int age)
    {
        this.id = id;
        this.age = age;
    }
    
    private String id;
    
    private Integer age;

    @Override
    public String toString(){
        return this.id + ":"+this.age;
    }
}

class Bclass
{
    public Bclass()
    {

    }

    public Bclass(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    private String id;
    
    private String name;
}