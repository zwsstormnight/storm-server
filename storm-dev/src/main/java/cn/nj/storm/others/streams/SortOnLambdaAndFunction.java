package cn.nj.storm.others.streams;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/9/3]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SortOnLambdaAndFunction
{
    
    public static void main(String[] args)
    {
        List<Map<String, Object>> resouces = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        map.put("type", "body");
        map.put("abstime", 00);
        map.put("id", 1);
        resouces.add(map);
        
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("type", "body");
        map1.put("abstime", 59);
        map1.put("id", 2);
        resouces.add(map1);
        
        Map<String, Object> map2 = Maps.newHashMap();
        map2.put("type", "face");
        map2.put("abstime", 59);
        map2.put("id", 3);
        resouces.add(map2);
        Map<String, Object> map3 = Maps.newHashMap();
        map3.put("type", "vehicle");
        map3.put("abstime", 58);
        map3.put("id", 4);
        resouces.add(map3);
        Map<String, Object> map4 = Maps.newHashMap();
        map4.put("type", "nomotor");
        map4.put("abstime", 59);
        map4.put("id", 5);
        resouces.add(map4);
        Map<String, Object> map5 = Maps.newHashMap();
        map5.put("type", "vehicle");
        map5.put("abstime", 57);
        map5.put("id", 6);
        resouces.add(map5);

        Map<String, Object> map6 = Maps.newHashMap();
        map6.put("type", "face");
        map6.put("abstime", null);
        map6.put("id", 7);
        resouces.add(map6);

        resouces.add(Maps.newHashMap());
        System.out.println(resouces);
//        System.out.println("------------------------------------ASC");
//        Comparator<Map<String, Object>> comparator =
//            Comparator.<Map<String, Object>, Integer> comparing((p) -> (Integer)p.get("abstime"));
//        resouces.sort(comparator);
//        System.out.println(resouces);
        System.out.println("------------------------------------DESC");
        Function<Map<String, Object>, Integer> function = p -> (Integer)p.get("abstime");
        Predicate<Map<String, Object>> isNotNull = (m) -> m != null && m.size() > 0;
        Predicate<Map<String, Object>> hasAbsTime = (i) -> {
            if(i.get("abstime") == null){
                i.put("abstime",-1);
            }
            return true;
        };
        resouces = resouces.stream().filter(isNotNull).filter(hasAbsTime).sorted(Comparator.comparing(function).reversed().thenComparing(a -> (Integer)a.get("id"))).collect(Collectors.toList());
        System.out.println(resouces);
    }
}
