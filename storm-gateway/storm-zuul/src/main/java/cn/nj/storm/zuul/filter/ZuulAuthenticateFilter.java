package cn.nj.storm.zuul.filter;

import cn.nj.storm.common.utils.LoggerInitializer;
import cn.nj.storm.zuul.ZuulFilterAction;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * <zuul 身份验证过滤器>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZuulAuthenticateFilter extends ZuulFilter implements ZuulFilterAction, LoggerInitializer {

    @Value(value = "${spring.application.name}")
    private String appName;

    private String zuulPrefix;

    private final static String TRACE_ID = "trace_id";

    public ZuulAuthenticateFilter() {
    }

    public ZuulAuthenticateFilter(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix;
    }

    /**
     * 指定为pre类型的过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
     *
     * @return
     */
    @Override
    public int filterOrder() {
        //TODO 顺序统一把控
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = currentRequest();
        String traceId = (String) request.getAttribute(TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            //设置调用链
            request.setAttribute(TRACE_ID, traceId);
            RequestContext.getCurrentContext().set(TRACE_ID, traceId);
        }
        //TODO 是否需要在这里做路由判定权限的判定
        String requestUri = request.getRequestURI();
        RUN_LOGGER.info("{},{}", request.getMethod(), requestUri);
//        return requestUri.replaceAll(zuulPrefix, "").trim()
//                .equalsIgnoreCase("/user/register") ? false : true;
        return true;
    }

    @Override
    public Object run()
            throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 获取请求的参数
        String token = request.getParameter("token");
        String token2 = request.getHeader("token");
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(token2)) {
            //不对其进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("token is empty");
            ctx.set("isSuccess", false);
        }
        try {
//TODO            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
        }
        return null;
    }
}
