package cn.nj.storm.token.interceptor;

import cn.nj.storm.redis.repository.assemble.RedisBasicService;
import cn.nj.storm.token.annotation.FormToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <拦截器：防止重复提交>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class SubmitTokenInterceptor extends HandlerInterceptorAdapter
{
    
    @Autowired
    private RedisBasicService redisBasicService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null)
            {
                if (annotation.save())
                {
                    String uuid = UUID.randomUUID().toString();
                    //插入到redis
                    redisBasicService.setNx("formToken_" + uuid, "1", 60 * 60);
                    request.getSession(true).setAttribute("formToken", uuid);
                    log.warn(request.getServletPath() + "---->formToken:" + uuid);
                }
                if (annotation.remove())
                {
                    if (isRepeatSubmit(request))
                    {
                        log.warn("please don't repeat submit,url:" + request.getServletPath());
                        boolean pass = annotation.pass();
                        request.setAttribute("formToken_pass_repeat", "true");
                        return pass;
                    }
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }
    
    /**
     * 判定是否重复提交
     * @param request
     * @return
     */
    private boolean isRepeatSubmit(HttpServletRequest request)
    {
        String clinetToken = request.getParameter("formToken");
        if (clinetToken == null)
        {
            return true;
        }
        boolean r = redisBasicService.exists("formToken_" + clinetToken).getBool();
        if (r)
        {
            Long upCount = redisBasicService.increment("formToken_" + clinetToken, -1).getNumObj();
            //如果对更新标记做减减操作后不等0,表示是重复提交
            if (upCount != 0)
            {
                redisBasicService.delete("formToken_" + clinetToken);
                return true;
            }
            else
            {
                redisBasicService.delete("formToken_" + clinetToken);
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}