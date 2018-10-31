package cn.nj.storm.common.conf;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/31]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class IpConvert extends ClassicConverter
{
    @Override
    public String convert(ILoggingEvent event)
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
