package com.summer.springboot.stop.task;

import com.rabbitmq.client.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class ReceiveTask implements Runnable {

    private static Log logger = LogFactory.getLog(ReceiveTask.class);

    private String msg;
    private Channel channel;
    private long deliveryTag;

    public ReceiveTask(String msg, Channel channel, long deliveryTag) {
        this.msg = msg;
        this.channel = channel;
        this.deliveryTag = deliveryTag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1 * 1000);
            channel.basicAck(deliveryTag, false);
            logger.info("======消息处理完毕:" + deliveryTag + " " + msg);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
