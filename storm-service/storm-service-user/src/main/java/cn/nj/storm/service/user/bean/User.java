package cn.nj.storm.service.user.bean;

import lombok.Data;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/6/17]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class User {

    private Long userId;

    private String userName;

    private String loginName;

    private String password;

    private String userToken;

}
