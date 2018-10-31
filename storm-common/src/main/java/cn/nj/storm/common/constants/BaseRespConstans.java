package cn.nj.storm.common.constants;

import cn.nj.storm.common.bean.BaseResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public enum BaseRespConstans
{
    
    SUCCESS(10000, "操作成功"),
    ERROR(99999, "系统错误"),
    HTTP_ERROR(10100, "http协议、请求地址或返回的内容存在异常"),
    IO_ERROR(10101, "内容读写异常"),
    DOCUMENT_ERROR(10102, "XML转对象操作异常！");
    
    private int code;
    
    private String msg;
    
    public int getCode()
    {
        return code;
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    BaseRespConstans(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    
    public String getResult()
    {
        return JSONObject.toJSONString(new BaseResult(this.code, this.msg, null));
    }
    
    public String getResult(Object data)
    {
        return JSONObject.toJSONString(new BaseResult(this.code, this.msg, data));
    }
    
    public String getResultWithNull(Object data)
    {
        return JSONObject.toJSONString(new BaseResult(this.code, this.msg, data),
            SerializerFeature.WriteNullStringAsEmpty);
    }
    
    public String getResultWithOutRef(Object data)
    {
        return JSONObject.toJSONString(new BaseResult(this.code, this.msg, data),
            SerializerFeature.DisableCircularReferenceDetect);
    }
}
