package cn.nj.storm.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/8/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EventProducer {

    private final RingBuffer<ProWorkEvent> ringBuffer;

    public EventProducer(RingBuffer<ProWorkEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            ProWorkEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            InputStream input = new ByteArrayInputStream(byteBuffer.array());
            ProWork proWork = null;
            try {
                ObjectInputStream oi = new ObjectInputStream(input);
                proWork = (ProWork) oi.readObject();
                input.close();
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            byteBuffer.clear();
            event.setProWork(proWork);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
