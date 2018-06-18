package cn.nj.storm.zuul.filter;

import cn.nj.storm.common.utils.LoggerInitializer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * <zuul token验证过滤器>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZuulTokenFilter extends ZuulFilter implements LoggerInitializer
{
    private String zuulPrefix;
    
    public ZuulTokenFilter()
    {
    }
    
    public ZuulTokenFilter(String zuulPrefix)
    {
        this.zuulPrefix = zuulPrefix;
    }
    
    @Override
    public String filterType()
    {
        // 可以在请求被路由之前调用
        return PRE_TYPE;
    }
    
    @Override
    public int filterOrder()
    {
        // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
        return PRE_DECORATION_FILTER_ORDER - 1;
    }
    
    @Override
    public boolean shouldFilter()
    {
        // 是否执行该过滤器，此处为true，说明需要过滤
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUri = request.getRequestURI().toString();
        RUN_LOGGER.info("StormZuulFilter {},{}", request.getMethod(), requestUri);
        return requestUri.replaceAll(zuulPrefix, "").trim().equalsIgnoreCase("/user/register") ? false : true;
    }
    
    @Override
    public Object run()
        throws ZuulException
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 获取请求的参数
        String token = request.getParameter("token");
        String token2 = request.getHeader("token");
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(token2))
        {
            //不对其进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
        }
        return null;
    }
}
