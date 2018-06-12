package cn.nj.storm.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.*;

import java.util.List;


public class XMLHelper
{
    public static final String XML2JSON_CONVERT_FAIL = "XML2JSON_CONVERT_FAIL";

    /**
     * <XML格式的字符串转JSON对象>
     * <功能详细描述>
     * @param xmlStr
     * @return
     * @throws DocumentException
     * @see [类、类#方法、类#成员]
     */
    public static JSONObject xml2Json(String xmlStr)
            throws DocumentException
    {
        Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * <根据XML元素逐层构建JSON对象>
     * <功能详细描述>
     * @param element
     * @param json
     * @see [类、类#方法、类#成员]
     */
    public static void dom4j2Json(Element element, JSONObject json)
    {
        //如果是属性
        for (Object o : element.attributes())
        {
            Attribute attr = (Attribute)o;
            if (StringUtils.isNotBlank(attr.getValue()))
            {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && StringUtils.isNotBlank(element.getText()))
        {//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl)
        {//有子元素
            if (!e.elements().isEmpty())
            {//子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null)
                {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject)
                    {//如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject)o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray)
                    {
                        jsona = (JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                }
                else
                {
                    if (!chdjson.isEmpty())
                    {
                        json.put(e.getName(), chdjson);
                    }
                }
            }
            else
            {//子元素没有子元素
                for (Object o : element.attributes())
                {
                    Attribute attr = (Attribute)o;
                    if (StringUtils.isNotBlank(attr.getValue()))
                    {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty())
                {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static void main(String[] args){
//        String xmlStr = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><response><status>000000</status><message>sendSmsCode success</message></response>";
        String xmlStr = "";
        JSONObject obj = null;
        try
        {
            obj = XMLHelper.xml2Json(xmlStr);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        System.out.println(obj);
    }
}
