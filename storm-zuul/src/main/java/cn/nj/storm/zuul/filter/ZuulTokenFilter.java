package cn.nj.storm.zuul.filter;

import cn.nj.storm.common.utils.LoggerInitializer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        /**
         * 当外部HTTP请求到达API网关服务的时候，
         * 首先它会进入第一个阶段pre，在这里它会被pre类型的过滤器进行处理，该类型的过滤器主要目的是在进行请求路由之前做一些前置加工，比如请求的校验等。
         *
         * 在完成了pre类型的过滤器处理之后，请求进入第二个阶段routing，也就是之前说的路由请求转发阶段，请求将会被routing类型过滤器处理，这里的具体处理内容就是将外部请求转发到具体服务实例上去的过程，
         * 当服务实例将请求结果都返回之后，routing阶段完成，请求进入第三个阶段post，此时请求将会被post类型的过滤器进行处理，这些过滤器在处理的时候不仅可以获取到请求信息，还能获取到服务实例的返回信息，所以在post类型的过滤器中，我们可以对处理结果进行一些加工或转换等内容。
         *
         * 另外，还有一个特殊的阶段error，该阶段只有在上述三个阶段中发生异常的时候才会触发，但是它的最后流向还是post类型的过滤器，因为它需要通过post过滤器将最终结果返回给请求客户端
         */
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
        try {
//            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
        }
        return null;
    }
}
