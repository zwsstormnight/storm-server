package cn.nj.storm.others;

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
}
