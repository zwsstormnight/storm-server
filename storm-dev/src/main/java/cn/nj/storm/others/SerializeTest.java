package cn.nj.storm.others;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/3/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SerializeTest
{
    private static final String s =
        "{\"organization_id\":1,\"dev_no\":1490167218,\"shipment_no\":\"2017-01-091483941408-1\",\"schedule_date\":\"2017-01-01\",\"request_at\":\"2017-01-0100:00:00\",\"note\":\"\",\"order_date\":\"2017-03-2207:20:18\",\"depot_code\":\"EW_CA_US\",\"depot_id\":1,\"operation\":2,\"type\":30,\"op_id\":81,\"status\":30,\"system_form\":2,\"shipper_data\":\"data\",\"items\":{\"457492\":{\"itemsku\":\"Viva08501\",\"qty_requested\":10,\"note\":\"\"},\"457493\":{\"itemsku\":\"Viva08501\",\"qty_requested\":10,\"note\":\"\"}}}";
    
    public static void main(String[] args)
    {
        DemoRequest req = JSON.parseObject(SerializeTest.s,DemoRequest.class);
        Map<Integer,Item> map = req.getItems();
        Collection<Item> itemCollection = map.values();
        System.out.println(itemCollection);
    }
}
