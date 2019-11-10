package cn.nj.storm.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/8/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ProWorkEventFactory implements EventFactory<ProWorkEvent> {


    @Override
    public ProWorkEvent newInstance() {
        return new ProWorkEvent();
    }
}
