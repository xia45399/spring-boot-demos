package com.summer.springboot.stop.mq;

import com.rabbitmq.client.Channel;
import com.summer.springboot.stop.task.ReceiveTask;
import com.summer.springboot.stop.task.ThreadPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Receive {

    private static Log logger = LogFactory.getLog(Receive.class);

    @RabbitListener(queues = "#{listenQueue.name}")
    public void process(String msg, Channel channel, @Headers Map<String, Object> map) {
        logger.info("======Receiver Msg: " + msg);
        long deliveryTag = (long) map.get(AmqpHeaders.DELIVERY_TAG);
        asyncService(msg, channel, deliveryTag);
    }

    private void asyncService(String msg, Channel channel, long deliveryTag) {
        ReceiveTask r = new ReceiveTask(msg, channel, deliveryTag);
        ThreadPool.addTask(r);
    }
}
