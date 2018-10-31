package cn.nj.storm.common.bean;

import lombok.Data;

import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/10/29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class BaseBean {

    private Date createTime;

    private Date updateTime;

    private Long createTimestamp;

    private Long updateTimestamp;

    private String formToken;

}