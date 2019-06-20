package cn.nj.storm.service.user.service.impl;

import cn.nj.storm.common.utils.LoggerInitializer;
import cn.nj.storm.service.user.bean.DemoBean;
import cn.nj.storm.service.user.bean.User;
import cn.nj.storm.service.user.mapper.DemoMapper;
import cn.nj.storm.service.user.mapper.UserMapper;
import cn.nj.storm.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service("userService")
public class UserServiceImpl implements UserService, LoggerInitializer
{
    
    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private UserMapper userMapper;
    
    @Override
    @Transactional(propagation=Propagation.NESTED)
    public List<DemoBean> list()
    {
        return demoMapper.selectDemoAll();
    }

    @Override
    public User insert(User user){
        int rst = userMapper.insert(user);
        return user;
    }
}
