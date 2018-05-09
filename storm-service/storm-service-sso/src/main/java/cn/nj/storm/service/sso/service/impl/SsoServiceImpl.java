package cn.nj.storm.service.sso.service.impl;

import cn.nj.storm.service.sso.bean.DemoBean;
import cn.nj.storm.service.sso.mapper.DemoMapper;
import cn.nj.storm.service.sso.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/9]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("ssoService")
public class SsoServiceImpl implements SsoService
{
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public String list()
    {
        List<DemoBean> lists = demoMapper.selectDemoAll();
        return lists.toString();
    }
}
