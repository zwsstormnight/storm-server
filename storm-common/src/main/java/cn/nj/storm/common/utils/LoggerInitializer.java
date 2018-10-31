package cn.nj.storm.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface LoggerInitializer
{
    Logger INTERFACE_LOGGER = LoggerFactory.getLogger("interface");

    Logger RUN_LOGGER = LoggerFactory.getLogger("run");

    Logger DEBUG_LOGGER = LoggerFactory.getLogger("debug");


}
