package cn.nj.storm.service.sso.mapper;

import cn.nj.storm.service.sso.bean.DemoBean;
import org.springframework.stereotype.Repository;

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
@Repository
public interface DemoMapper
{
    List<DemoBean> selectDemoAll();


}
