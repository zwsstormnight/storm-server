package cn.nj.storm.zuul;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * <针对filter的基础动作>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ZuulFilterAction {

    /**
     * 当前的httpServletRequest
     *
     * @return
     */
    default HttpServletRequest currentRequest(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest();
    }
}
