package cn.nj.storm.service.sso.service.impl;

import cn.nj.storm.service.sso.bean.DemoBean;
import cn.nj.storm.service.sso.mapper.DemoMapper;
import cn.nj.storm.service.sso.service.RemoteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("remoteInterface")
public class RemoteInterfaceImpl implements RemoteInterface
{
    @Autowired
    private DemoMapper demoMapper;
    
    @Override
    public String getNameByProvider(String name)
    {
        return null;
    }
    
    @Override
    public String getName(String key)
    {
        List<DemoBean> lists = demoMapper.selectDemoAll();
        return lists.toString();
    }
    
    @Override
    public String postName()
    {
        return null;
    }
}
