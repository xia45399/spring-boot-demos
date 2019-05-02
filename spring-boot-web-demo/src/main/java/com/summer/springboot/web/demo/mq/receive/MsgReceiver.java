package com.summer.springboot.web.demo.mq.receive;

import com.rabbitmq.client.Channel;
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

    @RabbitListener(queues = "#{testQueue.name}")
    public void process(String msg, Channel channel, @Headers Map<String, Object> map) {
        long deliveryTag = (long) map.get(AmqpHeaders.DELIVERY_TAG);
        dealMsg(msg, channel, deliveryTag);
    }

    private void dealMsg(String msg, Channel channel, long deliveryTag) {
        new Thread() {
            @Override
            public void run() {
                try {
                    log.info("ReceiverMsg:" + msg);
                    Thread.sleep(1000);
                    channel.basicAck(deliveryTag, false);
                } catch (IOException e) {
                    log.error("回复ack异常!", e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
