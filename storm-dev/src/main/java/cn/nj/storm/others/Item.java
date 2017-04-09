package cn.nj.storm.others;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2017/3/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Item
{
    /**
     * sku
     */
    private String itemsku;
    
    /**
     * 发运数量
     */
    private String qty_requested;
    
    /**
     * 备注
     */
    private String note;
    
    public String getItemsku()
    {
        return itemsku;
    }
    
    public void setItemsku(String itemsku)
    {
        this.itemsku = itemsku;
    }
    
    public String getQty_requested()
    {
        return qty_requested;
    }
    
    public void setQty_requested(String qty_requested)
    {
        this.qty_requested = qty_requested;
    }
    
    public String getNote()
    {
        return note;
    }
    
    public void setNote(String note)
    {
        this.note = note;
    }
}
