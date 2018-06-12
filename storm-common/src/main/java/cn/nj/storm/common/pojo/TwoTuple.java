package cn.nj.storm.common.pojo;

/**
 * <二元元组>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/2/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TwoTuple<A, B>
{
    private Object first;

    private Object second;

    public TwoTuple(Object a, Object b)
    {
        first = a;
        second = b;
    }

    public Object getFirst() {
        return first;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public Object getSecond() {
        return second;
    }

    public void setSecond(Object second) {
        this.second = second;
    }

    public static void main(String[] args)
    {
        TwoTuple<Integer, Integer> tt = new TwoTuple<Integer, Integer>(1, 2);
        System.out.println(tt.first);
        System.out.println(tt.second);
    }
}
