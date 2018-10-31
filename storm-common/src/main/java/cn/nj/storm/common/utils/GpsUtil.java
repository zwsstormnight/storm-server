package cn.nj.storm.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.nj.storm.common.bean.Gps;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GpsUtil
{
    
    // logger日志
    private static final Logger interfaceLogger = LoggerFactory.getLogger("interface");
    
    /** google请求地址 */
    private String googleGeoUrl = "https://www.google.cn/maps/api/geocode/json?latlng=";
    
    private Random random = new Random();
    
    private List<String> keys;
    
    // 连接超时时间
    private static final int CONNECTION_TIMEOUT = 3000;
    
    // 参数编码
    private static final String ENCODE_CHARSET = "utf-8";
    
    // 百度地图密钥key
    private static final String BAIDU_KEY = "78a4dc5c064749a7c1cd7e845b8dcdca";
    
    //其他key：
    //78a4dc5c064749a7c1cd7e845b8dcdca,K4EOhDwoQsBjrjRwqyCui57XG6XcnwfT,
    //95xb4W4jS4pRU0ybu4i0A1dgQhf7aotU,pdqLcDz0cdjkbrOMx9pFus3syqTAXoBD,
    //G6YHFZaBCrT8aE5bcM7ykRRyHN9Huqms,z9smriF4GrPPiWCuaep09t2F1XaT1PsH,
    //PnxK2kSmmVHuLD3gMSLCBWfup58TbGYG,Sxh25NyLZATWNSGKIuqoHcHeBbMjddVB,
    //jYYUNB5L18C0ysGfhqEsTVweVCqlCejO";
    
    /**
     * 通过GPS返回城市信息此信息为
     * 
     * @param gps
     * @return map key:cityCode 城市编码；cityName:城市名
     */
    public Map<String, String> getCityByGoogleMap(Gps gps)
    {
        if (keys == null || keys.size() == 0)
        {
            return null;
        }
        String url = googleGeoUrl + gps.toString() + "&key=" + keys.get(random.nextInt(keys.size()));
        String result = HttpClientUtil.doGet(url, "UTF-8");
        if (StringUtils.isNotBlank(result))
        {
            JSONObject jsonObject = JSON.parseObject(result.replaceAll("\\s*|\t|\r|\n", ""));
            if ("OK".equals(jsonObject.getString("status")))
            {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.size(); i++)
                {
                    if ("[\"locality\",\"political\"]".equals(jsonArray.getJSONObject(i).getString("types")))
                    {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("cityCode", jsonArray.getJSONObject(i).getString("place_id"));
                        map.put("cityName", jsonArray.getJSONObject(i)
                            .getJSONArray("address_components")
                            .getJSONObject(0)
                            .getString("long_name"));
                        return map;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 通过GPS返回省份信息此信息为
     * 
     * @param gps
     * @return map key:cityCode 城市编码；cityName:城市名
     */
    public Map<String, String> getProvinceByGoogleMap(Gps gps)
    {
        if (keys == null || keys.size() == 0)
        {
            return null;
        }
        String url = googleGeoUrl + gps.toString() + "&key=" + keys.get(random.nextInt(keys.size()));
        String result = HttpClientUtil.doGet(url, "UTF-8");
        if (StringUtils.isNotBlank(result))
        {
            JSONObject jsonObject = JSON.parseObject(result.replaceAll("\\s*|\t|\r|\n", ""));
            if ("OK".equals(jsonObject.getString("status")))
            {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.size(); i++)
                {
                    if ("[\"administrative_area_level_1\",\"political\"]".equals(jsonArray.getJSONObject(i)
                        .getString("types")))
                    {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("privinceCode", jsonArray.getJSONObject(i).getString("place_id"));
                        map.put("provinceName", jsonArray.getJSONObject(i)
                            .getJSONArray("address_components")
                            .getJSONObject(0)
                            .getString("long_name"));
                        return map;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 计算两经纬度点之间的距离（单位：米）
     * 
     * @param lng1
     *            经度
     * @param lat1
     *            纬度
     * @param lng2
     * @param lat2
     * @return
     */
    public double getDistance(double lng1, double lat1, double lng2, double lat2)
    {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s =
            2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    
    /**
     * 计算两经纬度点之间的距离（单位：米）
     * 
     * @param lng1
     *            经度
     * @param lat1
     *            纬度
     * @param lng2
     * @param lat2
     * @return
     */
    public static double calDistance(double lng1, double lat1, double lng2, double lat2)
    {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s =
            2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;// 取WGS84标准参考椭球中的地球长半径(单位:m)
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    
    public void setKeys(List<String> keys)
    {
        this.keys = keys;
    }
    
    /**
     * 判断一个GPS坐标是否在一个区域内。
     * 
     * @param gpsGis 上报的GPS坐标
     * @param point 区域或者线路经纬度
     * @param polygonPoints 区域范围
     * @return true：在区域（线路）内或者边上； false：在区域（线路）外
     */
    public boolean isPoiInPolygon(Gps point, List<Gps> polygonPoints)
    {
        boolean result = false;
        
        if (point == null)
        {
            return false;
        }
        if (CommonUtil.isEmpty(polygonPoints))
        {
            return false;
        }
        
        // 结果。0:点在边上 1：点在多边形内 -1：点在多边形外
        int res = GpsUtil.pointIsInPolygon(point, polygonPoints);
        
        if (res == -1)
        {
            result = false;
        }
        else if (res == 1 || res == 0)
        {
            result = true;
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    /**
     * 判断点是否在多边形内 根据射线法判断
     * 方法是：从点p向x轴负方向做与x轴平行射线 判断射线与多边形的交点个数
     * 如果交点为奇数则在多边形内，如果交点为偶数则在多边形外
     * @param point 点
     * @param polygonPoints 多边形的各个顶点集合
     * @return 0:点在边上 1：点在多边形内 -1：点在多边形外
     * @see [类、类#方法、类#成员]
     */
    public static int pointIsInPolygon(Gps point, List<Gps> polygonPoints)
    {
        //多边形点数
        int pointCount = polygonPoints.size();
        //是否在多边形边上
        boolean isBeside = false;
        //坐标点的最大、最小坐标
        double maxX = 0, minX = 0, maxY = 0, minY = 0;
        //初始化为第一个点
        if (pointCount > 0)
        {
            maxX = polygonPoints.get(0).getLng();
            minX = polygonPoints.get(0).getLng();
            maxY = polygonPoints.get(0).getLat();
            minY = polygonPoints.get(0).getLat();
        }
        //取到最小、最大坐标点
        for (int i = 1; i < pointCount; i++)
        {
            if (polygonPoints.get(i).getLng() > maxX)
            {
                maxX = polygonPoints.get(i).getLng();
            }
            else if (polygonPoints.get(i).getLng() < minX)
            {
                minX = polygonPoints.get(i).getLng();
            }
            if (polygonPoints.get(i).getLat() > maxY)
            {
                maxY = polygonPoints.get(i).getLat();
            }
            else if (polygonPoints.get(i).getLat() < minY)
            {
                minY = polygonPoints.get(i).getLat();
            }
            
        }
        //如果点在最大最小范围之外则直接排除
        if (point.getLng() > maxX || point.getLng() < minX || point.getLat() > maxY || point.getLat() < minY)
        {
            return -1;
        }
        
        //以下射线法判断点是否在多边形区域内
        //交叉点数
        int ncross = 0;
        for (int i = 0; i < pointCount; i++)
        {
            Gps p1 = polygonPoints.get(i);
            Gps p2 = polygonPoints.get((i + 1) % pointCount);
            
            //判断p1p2连线与 y=point.getLat()的交叉点
            //两条直线的关系有平行或者交叉两种情形
            //如果平行则判断是否在一条线上
            //如果交叉判断交叉点是否在延长线上
            
            //p1p2与y=point.getLat()平行
            if (p1.getLat() == p2.getLat())
            {
                //在边上
                if (point.getLat() == p1.getLat() && point.getLng() <= max(p1.getLng(), p2.getLng())
                    && point.getLng() >= min(p1.getLng(), p2.getLng()))
                {
                    isBeside = true;
                    break;
                }
                continue;
            }
            //交叉点在延长线上
            if (point.getLat() < min(p1.getLat(), p2.getLat()) || point.getLat() > max(p1.getLat(), p2.getLat()))
            {
                continue;
            }
            
            //求point点与p1p2交点的x坐标
            double x =
                (point.getLat() - p1.getLat()) * (p2.getLng() - p1.getLng()) / (p2.getLat() - p1.getLat())
                    + p1.getLng();
            //            System.out.println(p1.getLon()+","+p1.getLat()+":"+p2.getLon()+","+p2.getLat());
            //            System.out.println(x);
            //交叉点在左边 则交叉点数加1
            if (x < point.getLng())
            {
                ncross++;
                continue;
            }
            //相等表示点在线上
            else if (x == point.getLng())
            {
                isBeside = true;
                break;
            }
        }
        //在多边形边上
        if (isBeside)
        {
            return 0;
        }
        //交点为奇数 点在多边形内
        else if (ncross % 2 == 1)
        {
            return 1;
        }
        return -1;
    }
    
    //判断两个值中最小
    private static double min(double x, double y)
    {
        if (x > y)
            return y;
        else
            return x;
    }
    
    //判断两个值中最大
    private static double max(double x, double y)
    {
        if (x > y)
            return x;
        else
            return y;
    }
    
    /**
     * 根据经纬度获取省份和城市名称
     * 
     * @param lon
     *            经度
     * @param lat
     *            纬度
     * @return
     * @throws IOException 
     * @throws UnsupportedEncodingException 
     * @see [类、类#方法、类#成员]
     */
    public static JSONObject getProvinceCity(String lon, String lat)
        throws Exception
    {
        /*有个问题就是如果经纬度传负数应该是国外地理位置，结果还是返回正数对应的国内地理位置，所以暂不采用
        // 从阿里云获取数据
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + lon + "&type=010";
        String res = "";
        JSONObject pc = new JSONObject();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null)
        {
            res += line + "\n";
        }
        in.close();
        
        // 解析省份和城市
        JSONObject jsonObject = JSON.parseObject(res);
        if (null != jsonObject)
        {
            JSONArray jsonArray = JSON.parseArray(jsonObject.getString("addrList"));
            if (null != jsonArray && jsonArray.size() > 0)
            {
                JSONObject j_2 = new JSONObject();
                for (int i = 0; i < jsonArray.size(); i++)
                {
                    if (((JSONObject)jsonArray.get(i)).getString("type").equalsIgnoreCase("poi"))
                    {
                        j_2 = (JSONObject)jsonArray.get(i);
                    }
                }
                String allAdd = j_2.getString("admName");
                String arr[] = allAdd.split(",");
                
                if (arr.length > 1)
                {
                    //如果城市名称中最后一个字有“市”，去掉
                    String cityName = arr[1];// 城市
                    if (cityName.length() > 0 && cityName.substring(arr[1].length() - 1).equals("市"))
                    {
                        cityName = cityName.substring(0, arr[1].length() - 1);
                    }
                    String provName = arr[0];// 省份
                    pc.put("province", provName);
                    pc.put("city", cityName);
                }
            }
        }
        
        return pc;*/
        
        JSONObject pc = new JSONObject();
        //使用百度查询地理位置
        String ak = "78a4dc5c064749a7c1cd7e845b8dcdca"; // PHP重构过来时的开发者钥匙
        String baiduUrl =
            "http://api.map.baidu.com/geocoder/v2/?allback=renderReverse&ak=" + ak + "&location=" + lat + "," + lon
                + "&output=json&pois=1";
        StringBuffer strBuf;
        
        strBuf = new StringBuffer();
        
        URL url = new URL(baiduUrl);
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));//转码。  
        String line = null;
        while ((line = reader.readLine()) != null)
            strBuf.append(line + " ");
        reader.close();
        
        JSONObject json = JSON.parseObject(strBuf.toString());
        if (null != json && json.getString("status").equals("0"))
        {
            JSONObject result = json.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            String city = addressComponent.getString("city");
            String province = addressComponent.getString("province");
            pc.put("province", province);
            if (!CommonUtil.isNull(city) && city.length() > 0
                && city.substring(city.length() - 1, city.length()).equals("市"))
            {
                city = city.substring(0, city.length() - 1);
            }
            pc.put("city", city);
        }
        
        return pc;
        
    }
    
    /**
     * 根据经纬度获取所在的城市代码
     * @param lon
     * @param lat
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public static String getCityCode(JSONObject pc)
        throws Exception
    {
        String cityCode = null;
        
        InputStream input = (InputStream)ClassLoader.getSystemResourceAsStream("cityJson/city.json");
        String data = IOUtils.toString(input, "UTF-8");
        IOUtils.closeQuietly(input);
        JSONObject city = JSON.parseObject(data);
        JSONArray array = city.getJSONArray("ps");
        
        String cityName = pc.getString("city");//城市名（去除市）
        String provName = pc.getString("province");//省份名
        
        for (int i = 0; i < array.size(); i++)
        {
            JSONObject item = array.getJSONObject(i);
            if (item.getString("proName").equals(provName))//匹配省份
            {
                JSONArray itemArray = item.getJSONArray("citys");
                for (int j = 0; j < itemArray.size(); j++)
                {
                    JSONObject item2 = itemArray.getJSONObject(j);
                    if (item2.getString("proName").equals(cityName))//匹配城市
                    {
                        cityCode = item2.getString("pcode");//城市代码
                    }
                }
            }
        }
        
        return cityCode;
    }
    
    /**
     * 发送HTTP_GET请求
     * 
     * @see 本方法默认的连接和读取超时均为30秒
     * @param httpUrl请求地址
     * @return String
     */
    public static String getRequest(String httpUrl)
        throws Exception
    {
        StringBuilder resultData = new StringBuilder();
        URL url = null;
        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        try
        {
            url = new URL(httpUrl);
            if (url != null)
            {
                urlConn = (HttpURLConnection)url.openConnection();
                urlConn.setRequestMethod("GET");
                urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
                in = new InputStreamReader(urlConn.getInputStream(), ENCODE_CHARSET);
                buffer = new BufferedReader(in);
                if (urlConn.getResponseCode() == 200)
                {
                    while ((inputLine = buffer.readLine()) != null)
                    {
                        resultData.append(inputLine);
                    }
                }
            }
        }
        catch (Exception e)
        {
            interfaceLogger.error("{}|{}", "GpsUtil.getRequest方法出现异常!", e);
        }
        finally
        {
            try
            {
                IOUtils.closeQuietly(buffer);
                IOUtils.closeQuietly(in);
                IOUtils.close(urlConn);
            }
            catch (Exception e)
            {
                interfaceLogger.error("{}|{}", "GpsUtil.getRequest关闭流失败", e);
            }
        }
        return resultData.toString();
    }
    
    /**
     * 根据经纬度查询详细的地址（上面的那个方法在连续调用2次时，第二次查询不到数据，所以用这个）
     * @param lat
     * @param lng
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static JSONObject getAddress(String lat, String lng)
        throws Exception
    {
        JSONObject pc = new JSONObject();
        String url =
            "http://api.map.baidu.com/geocoder/v2/?location=" + lat + "," + lng + "&output=json&ak=" + BAIDU_KEY
                + "&pois=0";
        String str = getRequest(url);
        JSONObject json = JSONObject.parseObject(str);
        if (null != json && "0".equals(json.getString("status")))
        {
            JSONObject result = json.getJSONObject("result");
            if (null != result)
            {
                JSONObject addressComponent = result.getJSONObject("addressComponent");
                String formattedAddress = result.getString("formatted_address");//获取详细地址（例：江苏省南京市建邺区黄山路166号）,add by 2017-11-21 UGC路线需求 zhangyongkui
                pc.put("address", formattedAddress);//在原有方法基础上多返回这个参数
                String city = addressComponent.getString("city");
                String province = addressComponent.getString("province");
                pc.put("province", province);
                pc.put("city", city);
            }
        }
        return pc;
    }
}
