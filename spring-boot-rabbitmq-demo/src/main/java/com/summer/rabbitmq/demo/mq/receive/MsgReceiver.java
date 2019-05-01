package com.summer.rabbitmq.demo.mq.receive;

import com.rabbitmq.client.Channel;
import com.summer.rabbitmq.demo.mq.msg.MqMsg;
import com.summer.rabbitmq.demo.task.ThreadPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class MsgReceiver {

    private static Log log = LogFactory.getLog(MsgReceiver.class);


    @RabbitListener(queues = "#{queue1.name}")
    public void process(byte[] msg, Channel channel, @Headers Map<String, Object> map) {
        log.info("Receiver1 : " + new String(msg));
        long deliveryTag = (long) map.get(AmqpHeaders.DELIVERY_TAG);
        asyncService(new String(msg), channel, deliveryTag);
    }


    private void asyncService(String msg, Channel channel, long deliveryTag) {
        Thread r = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    channel.basicAck(deliveryTag, false);
                    log.info("消息处理完毕:" + deliveryTag + " " + msg);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadPool.addTask(r);
    }

}
