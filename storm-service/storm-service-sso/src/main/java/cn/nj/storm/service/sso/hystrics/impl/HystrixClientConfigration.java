package cn.nj.storm.service.sso.hystrics.impl;

import cn.nj.storm.service.sso.hystrics.HystrixClientRemoteInterface;
import org.springframework.stereotype.Service;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("hystrixClientRemoteInterface")
public class HystrixClientConfigration implements HystrixClientRemoteInterface
{
    @Override
    public String getTeamInfo(String teamName)
    {
        return "获取" + teamName + "失败。";
    }
}
