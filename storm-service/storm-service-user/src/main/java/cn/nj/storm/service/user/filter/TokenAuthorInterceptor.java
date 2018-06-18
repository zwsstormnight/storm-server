package cn.nj.storm.service.user.filter;

import cn.nj.storm.common.utils.LoggerInitializer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TokenAuthorInterceptor implements HandlerInterceptor, LoggerInitializer {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("---------------拦截器正在处理- preHandle ---------------");
        System.out.println("拦截的该请求为:"+httpServletRequest.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("---------------拦截器正在处理- postHandle ---------------");
        //当controller的返回类型为ModelAndView的时候，可以在postHandle方法中获得返回去的值
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("---------------拦截器正在处理- afterCompletion ---------------");
        System.out.println(e);
    }
}
