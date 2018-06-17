package cn.nj.storm.service.user.mapper;

import cn.nj.storm.service.user.bean.DemoBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Repository
public interface DemoMapper
{
    /**
     * demo全量查询
     * @return
     */
    List<DemoBean> selectDemoAll();
}
