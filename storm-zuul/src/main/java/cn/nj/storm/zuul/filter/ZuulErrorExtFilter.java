package cn.nj.storm.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/8/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class ZuulErrorExtFilter extends SendErrorFilter
{
    
    @Override
    public String filterType()
    {
        return ERROR_TYPE;
    }
    
    /**
     * 这里的order值需要比ErrorFilter的order要大
     * @return
     */
    @Override
    public int filterOrder()
    {
        return SEND_RESPONSE_FILTER_ORDER;
    }
    
    @Override
    public boolean shouldFilter()
    {
        // TODO 判断：仅处理来自post过滤器引起的异常
        //问题来了：怎么判断引起异常的过滤器是来自什么阶段呢？（shouldFilter方法该如何实现）
        //答案：扩展原来的过滤器处理逻辑，当有异常抛出的时候，记录下抛出异常的过滤器，
        // 这样我们就可以在ErrorExtFilter过滤器的shouldFilter方法中获取并以此判断异常是否来自post阶段的过滤器了
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter)ctx.get("failed.filter");
        if (failedFilter != null && failedFilter.filterType().equals(POST_TYPE))
        {
            return true;
        }
        return false;
    }
}
