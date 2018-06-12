package cn.nj.storm.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * <Http请求> <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/1/22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpClientUtil
{
    
    /**
     * httpClient的get请求方式
     *
     * @return
     * @throws Exception
     */
    public static String doGet(String url, String charset)
    {
        /*
         * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤: 1:生成一个 HttpClinet 对象并设置相应的参数。
         * 2:生成一个 GetMethod 对象并设置响应的参数。 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get
         * 方法。 4:处理响应状态码。 5:若响应正常，处理 HTTP 响应内容。 6:释放连接。
         */
        
        /* 1 生成 HttpClinet 对象并设置参数 */
        HttpClient httpClient = new HttpClient();
        // 设置 Http 连接超时为5秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        /* 2 生成 GetMethod 对象并设置参数 */
        GetMethod getMethod = new GetMethod(url);
        // 设置 get 请求超时为 5 秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
        // 设置请求重试处理，用的是默认的重试处理：请求三次
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        /* 3 执行 HTTP GET 请求 */
        try
        {
            int statusCode = httpClient.executeMethod(getMethod);
            /* 4 判断访问的状态码 */
            if (statusCode != HttpStatus.SC_OK)
            {
                // 请求出错
                return null;
            }
            /* 5 处理 HTTP 响应内容 */
            // HTTP响应头部信息，这里简单打印
            Header[] headers = getMethod.getResponseHeaders();
            String headerStr = "";
            for (Header h : headers)
            {
                if (StringUtils.isNotBlank(headerStr))
                {
                    headerStr = headerStr + "\\,h.getName()=" + h.getValue();
                }
                else
                {
                    headerStr = "h.getName()=" + h.getValue();
                }
            }
            // 读取 HTTP 响应内容，这里简单打印网页内容
            byte[] responseBody = getMethod.getResponseBody();
            if (StringUtils.isEmpty(charset))
            {
                charset = "UTF-8";
            }
            // 读取为字节数组
            response = new String(responseBody, charset);
            // 读取为 InputStream，在网页内容数据量大时候推荐使用
            // InputStream response = getMethod.getResponseBodyAsStream();
        }
        catch (HttpException e)
        {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
        }
        catch (IOException e)
        {
            // 发生网络异常
        }
        finally
        {
            /* 6 .释放连接 */
            getMethod.releaseConnection();
        }
        return response;
    }
    
    /**
     * Post发送请求
     * 
     * @param postMethod
     * @return
     * @throws HttpException
     * @throws IOException
     * @throws Exception
     */
    public static String doPost(PostMethod postMethod, String url, String xml)
        throws HttpException, IOException, Exception
    {
        if (postMethod == null)
        {
            postMethod = new PostMethod(url);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            // 设置请求头部
            postMethod.setRequestHeader("Content-Type", "text/xml");
            postMethod.setRequestHeader("charset", "utf-8");
            postMethod.setRequestEntity(new StringRequestEntity(xml, "text/xml", "utf-8"));
        }
        String responseString = null;
        // 构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        try
        {
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK)
                responseString = statusCode + ":" + postMethod.getResponseBodyAsString();
            else
                responseString = statusCode + ":";
        }
        finally
        {
            postMethod.releaseConnection();
        }
        return responseString;
    }
    
    /**
     * post请求
     * 
     * @param url
     * @param params
     *            - key:value
     * @return
     */
    public static String post2(String url, String key, String value)
    {
        // 不存在 包括iterator为空时不存在 和 非空时不存在
        HttpClient httpclient = new HttpClient();
        // 设置连接超时时间和读数据超时时间，单位毫秒
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(3000);
        
        String respCreateJson = "";
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("content-type", "application/x-www-form-urlencoded;charset=UTF-8");
        
        NameValuePair[] params = new NameValuePair[1];
        NameValuePair param = new NameValuePair();
        param.setName(key);
        param.setValue(value);
        
        params[0] = param;
        postMethod.setRequestBody(params);
        
        int statusCode = 0;
        try
        {
            statusCode = httpclient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK)
            {
                InputStream responseBody = postMethod.getResponseBodyAsStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody, "utf-8"));
                String line = reader.readLine();
                while (StringUtils.isNotBlank(line))
                {
                    respCreateJson += line;
                    line = reader.readLine();
                }
            }
            // 释放
            postMethod.releaseConnection();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return respCreateJson;
    }
    
    public static String jsonPost(String url, Object data)
    {
        HttpClient httpclient = new HttpClient();
        
        String respCreateJson = "";
        PostMethod postMethod = new PostMethod(url);
        try
        {
            postMethod.setRequestEntity(new StringRequestEntity(JSON.toJSONString(data), "application/json", ""));
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            respCreateJson = "";
        }
        try
        {
            if (httpclient.executeMethod(postMethod) == HttpStatus.SC_OK)
            {
                respCreateJson = new String(postMethod.getResponseBody(), "utf-8");
            }
            // 释放
            postMethod.releaseConnection();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return respCreateJson;
    }
    
    /**
     * post请求
     *
     * @param url
     * @param reqContent
     * @return
     * @throws IOException
     */
    public static String doPost2(String url, String reqContent, String timeOutConfig)
    {
        
        StringBuffer strBuffer = new StringBuffer("");
        try
        {
            // Post请求的url，与get不同的是不需要带参数
            URL postUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection)postUrl.openConnection();
            // 设置是否向connection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true
            connection.setDoOutput(true);
            // Read from the connection. Default is true.
            connection.setDoInput(true);
            // 默认是 GET方式
            connection.setRequestMethod("POST");
            // 超时时间
            connection.setConnectTimeout(Integer.valueOf(timeOutConfig));
            connection.setReadTimeout(Integer.valueOf(timeOutConfig));
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            
            connection.setInstanceFollowRedirects(true);
            
            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
            // 进行编码
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // The URL-encoded cont<a
            // href="https://www.baidu.com/s?wd=end&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YknHf4njc3PH7bn1KBn17b0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPHndnWcdPHns"
            // target="_blank" class="baidu-highlight">end</a>
            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
            out.writeBytes(reqContent);
            out.flush();
            out.close();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            
            while ((line = reader.readLine()) != null)
            {
                line = new String(line.getBytes("UTF-8"), "UTF-8");
                strBuffer.append(line);
            }
            
            reader.close();
            connection.disconnect();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return strBuffer.toString();
    }
    
}
