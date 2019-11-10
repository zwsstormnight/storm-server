package cn.nj.storm.zuul.filter.processor;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <当前扩展的过滤器执行器>
 * <请求上下文RequestContext对象，可是在查阅文档和源码后发现其中并没有存储异常来源的内容，所以我们不得不扩展原来的过滤器处理逻辑>
 * 当有异常抛出的时候，记录下抛出异常的过滤器，
 * 这样我们就可以在ZuulErrorExtFilter过滤器的shouldFilter方法中获取并以此判断异常是否来自post阶段的过滤器了。
 *
 * @author zhengweishun
 * @version [版本号, 2018/8/19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExtFilterProcessor extends FilterProcessor {

    //TODO 设置为单例的


    /**
     * 该方法定义了用来执行filter的具体逻辑，包括对请求上下文的设置，判断是否应该执行，执行时一些异常处理等
     *
     * @param filter
     * @return
     * @throws ZuulException
     */
    @Override
    public Object processZuulFilter(ZuulFilter filter)
            throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            //存储抛出异常的过滤器实例
            ctx.set("failed.filter", filter);
            throw e;
        }
    }
}
