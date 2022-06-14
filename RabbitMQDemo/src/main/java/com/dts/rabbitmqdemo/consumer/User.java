package com.dts.rabbitmqdemo.consumer;

import com.dts.rabbitmqdemo.config.MessagingConfig;
import com.dts.rabbitmqdemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue (OrderStatus orderStatus){
        System.out.println("Message received from queue: "+ orderStatus);
    }

    @RabbitListener(queues = MessagingConfig.QUEUE2)
    public void consumeMessageFromQueue2 (OrderStatus orderStatus){
        System.out.println("Message received from queue2: "+ orderStatus);
    }
}
