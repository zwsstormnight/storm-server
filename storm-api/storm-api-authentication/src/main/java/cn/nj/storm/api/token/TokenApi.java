package cn.nj.storm.api.token;

import org.apache.dubbo.config.annotation.Service;
/**
 * <token api>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/7/12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface TokenApi {

    /**
     * 随机创建token
     *
     * @return
     */
    String createRandom();


}
