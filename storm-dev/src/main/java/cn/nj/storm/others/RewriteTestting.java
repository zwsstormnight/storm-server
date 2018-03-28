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
public class RewriteTestting
{
    public static void main(String[] args)
    {
        Upcase up = new Upcase();
        System.out.println(up.process("sda"));
        Apply.process(up,Apply.s);
        Processor p = new Upcase();
        System.out.println(p.process("sda"));
        Apply.process(p,Apply.s);
        Lowcase lw = new Lowcase();
        System.out.println(lw.process("sda"));
        Apply.process(lw,Apply.s);
        p = new Lowcase();
        System.out.println(p.process("sda"));
        Apply.process(p,Apply.s);
        System.out.println("============================");
        Filter filter = new Filter();
        System.out.println(filter.process(new Waveform()));
//        Apply.process(new Filter(),Apply.s);
    }
}
