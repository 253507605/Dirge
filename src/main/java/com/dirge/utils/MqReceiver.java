/*
package com.dirge.utils;

import com.dirge.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqReceiver {

    private final Logger logger = LoggerFactory.getLogger(MqReceiver.class);

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void receiveMessage_One(String content){
        logger.info("处理器one处理A队列："+content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_B)
    public void receiveMessage_Two(String content){
        logger.info("处理器two处理B队列："+content);
    }
}
*/
