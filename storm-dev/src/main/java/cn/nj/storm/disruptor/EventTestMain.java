package cn.nj.storm.disruptor;

import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zwsst
 * @version [版本号, 2019/8/30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EventTestMain {

    public static void handleEvent(ProWorkEvent event, long sequence, boolean endOfBatch) {
        //TODO 处理事件的处理
        System.out.println(event);
    }

    public static void translate(ProWorkEvent event, long sequence, ByteBuffer buffer) {
        InputStream input = new ByteArrayInputStream(buffer.array());
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
        buffer.clear();
        event.setProWork(proWork);
    }

    public static void main(String[] args) throws Exception {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<ProWorkEvent> disruptor = new Disruptor<>(ProWorkEvent::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.MULTI,new YieldingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith(EventTestMain::handleEvent);

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<ProWorkEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lk", l);
            List<JSONObject> list = new ArrayList<>(1);
            list.add(jsonObject);
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(new ProWork().setId(l).setName("tp" + l).setParamList(list));
            out.flush();
            byte[] bytes = bout.toByteArray();
            bout.close();
            out.close();

            byteBuffer = ByteBuffer.wrap(bytes);
            ringBuffer.publishEvent(EventTestMain::translate, byteBuffer);
            System.out.println("produce:" + l);
//            Thread.sleep(1000);
        }
    }
}
