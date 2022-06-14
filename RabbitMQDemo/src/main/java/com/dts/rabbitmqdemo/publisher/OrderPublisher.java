package com.dts.rabbitmqdemo.publisher;

import com.dts.rabbitmqdemo.config.MessagingConfig;
import com.dts.rabbitmqdemo.dto.Order;
import com.dts.rabbitmqdemo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder (@RequestBody Order order,@PathVariable String restaurantName){
        order.setId(UUID.randomUUID().toString());
        //restaurant service
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed sucessfully in restaurant" + restaurantName);
        template.convertAndSend(MessagingConfig.EXCHANGE,
                                MessagingConfig.ROUTING_KEY2,
                                orderStatus);
        return "Success!";
    }
}
