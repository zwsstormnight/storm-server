package cn.nj.storm.disruptor;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/8/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@Accessors(chain = true)
public class ProWork implements Serializable {

    private final static long SerializableID = 1L;

    private Long id;

    private String name;

    private List<JSONObject> paramList;
}
