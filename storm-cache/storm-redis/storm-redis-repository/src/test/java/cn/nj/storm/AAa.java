package cn.nj.storm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/7/17]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AAa {

    private Long absTime;

    public Long getAbsTime() {
        return absTime;
    }

    public void setAbsTime(Long absTime) {
        this.absTime = absTime;
    }

    public static void main(String[] args) {
        Long a1 = 2L;
        Long a2 = 19L;
        Long a3 = 10L;
        List<Long> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        System.out.println(list);
        System.out.println(a1.compareTo(a2));
        Collections.sort(list);
        System.out.println(list);
        Collections.sort(list, (o1, o2) -> o1.compareTo(o2) * -1);
        System.out.println(list);

        AAa ds = new AAa();
        AAa ds3 = new AAa();
        ds.setAbsTime(1000L);
        ds3.setAbsTime(2L);
        try {
            Field field = ds.getClass().getDeclaredField("absTime");
            field.setAccessible(true);
            Object absTime1 = field.get(ds);
            field = ds3.getClass().getDeclaredField("absTime");
            field.setAccessible(true);
            Object absTime3 = field.get(ds3);
            if(absTime1 instanceof Long && absTime3 instanceof Long){
                System.out.println(((Long)absTime1).compareTo((Long)absTime3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
