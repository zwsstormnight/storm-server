package cn.nj.storm.others;

import java.util.Map;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/3/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DemoRequest
{
    /**
     * 组织ID
     */
    private String organization_id;
    
    /**
     * 入库单号
     */
    private String dev_no;
    
    /**
     * 来源单号
     */
    private String shipment_no;
    
    /**
     * 预计到达日期
     */
    private String schedule_date;
    
    /**
     * 预计发运日期
     */
    private String request_at;
    
    /**
     * 创建日期
     */
    private String order_date;
    
    /**
     * 仓库code
     */
    private String depot_code;
    
    private String depot_id;
    
    /**
     * 单据大类型
     */
    private String operation;
    
    /**
     * 单据类型
     */
    private String type;
    
    /**
     * 操作人ID
     */
    private String op_id;
    
    /**
     * 单据状态
     */
    private String status;
    
    /**
     * 发运方信息
     */
    private String shipper_data;
    
    /**
     * 发运产品详细信息
     */
    private Map<Integer, Item> items;
    
    /**
     * 来源系统
     */
    private String system_form;
    
    /**
     * 备注
     */
    private String note;
    
    public String getOrganization_id()
    {
        return organization_id;
    }
    
    public void setOrganization_id(String organization_id)
    {
        this.organization_id = organization_id;
    }
    
    public String getDev_no()
    {
        return dev_no;
    }
    
    public void setDev_no(String dev_no)
    {
        this.dev_no = dev_no;
    }
    
    public String getShipment_no()
    {
        return shipment_no;
    }
    
    public void setShipment_no(String shipment_no)
    {
        this.shipment_no = shipment_no;
    }
    
    public String getSchedule_date()
    {
        return schedule_date;
    }
    
    public void setSchedule_date(String schedule_date)
    {
        this.schedule_date = schedule_date;
    }
    
    public String getRequest_at()
    {
        return request_at;
    }
    
    public void setRequest_at(String request_at)
    {
        this.request_at = request_at;
    }
    
    public String getOrder_date()
    {
        return order_date;
    }
    
    public void setOrder_date(String order_date)
    {
        this.order_date = order_date;
    }
    
    public String getDepot_code()
    {
        return depot_code;
    }
    
    public void setDepot_code(String depot_code)
    {
        this.depot_code = depot_code;
    }
    
    public String getOperation()
    {
        return operation;
    }
    
    public void setOperation(String operation)
    {
        this.operation = operation;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getOp_id()
    {
        return op_id;
    }
    
    public void setOp_id(String op_id)
    {
        this.op_id = op_id;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getShipper_data()
    {
        return shipper_data;
    }
    
    public void setShipper_data(String shipper_data)
    {
        this.shipper_data = shipper_data;
    }
    
    public String getSystem_form()
    {
        return system_form;
    }
    
    public void setSystem_form(String system_form)
    {
        this.system_form = system_form;
    }
    
    public String getNote()
    {
        return note;
    }
    
    public void setNote(String note)
    {
        this.note = note;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Item> items) {
        this.items = items;
    }

    public String getDepot_id()
    {
        return depot_id;
    }
    
    public void setDepot_id(String depot_id)
    {
        this.depot_id = depot_id;
    }
}
