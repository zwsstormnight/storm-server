package cn.nj.storm.zuul.filter;

import cn.nj.storm.common.utils.LoggerInitializer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;

/**
 * <zuul网关通用异常过滤器>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZuulErrorFilter extends ZuulFilter implements LoggerInitializer
{
    @Override
    public String filterType()
    {
        return ERROR_TYPE;
    }
    
    @Override
    public int filterOrder()
    {
        return PRE_DECORATION_FILTER_ORDER;
    }
    
    @Override
    public boolean shouldFilter()
    {
        return true;
    }
    
    @Override
    public Object run()
        throws ZuulException
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        RUN_LOGGER.error("this is a ErrorFilter :" + throwable.getCause().getMessage(), throwable);
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }
}
