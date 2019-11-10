package cn.nj.storm.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/7/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZuulContentFilter extends ZuulFilter {

    @Override
    public String filterType() {
        //内容过滤可获取全量返回值
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //TODO 统一编排一个过程
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //TODO 哪些请求不予过滤
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //TODO 获取返回值，加密，打日志
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        String value = "";
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    value = new String(buf, 0, buf.length, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    value = "[unknown]";
                }
            }
        }
        System.out.println(value);
        return null;
    }
}
