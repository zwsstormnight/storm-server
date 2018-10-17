package cn.nj.storm.service.user.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/17]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class AscFilter extends OncePerRequestFilter
{
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException
    {
        System.out.println("start filter");
        try
        {
            filterChain.doFilter(request, response);
        }
//        catch(MyException myException){
//            //TODO
//        }
        catch (Exception e)
        {
            // 自定义异常的类，用户返回给客户端相应的JSON格式的信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result_code", "500500");
            jsonObject.put("result_msg", e.getMessage());
            String userJson = jsonObject.toJSONString();
            OutputStream out = response.getOutputStream();
            out.write(userJson.getBytes("UTF-8"));
            out.flush();
        }
        System.out.println("end filter");
    }
}
