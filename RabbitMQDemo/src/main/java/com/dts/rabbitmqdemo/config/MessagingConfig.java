package com.dts.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "rabbitMQ_queue";
    public static final String QUEUE2 = "rabbitMQ_queue2";
    public static final String EXCHANGE = "rabbitMQ_exchange";
    public static final String ROUTING_KEY = "rabbitMQ_routingKey";
    public static final String ROUTING_KEY2 = "rabbitMQ_routingKey2";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public Queue queue2() {return  new Queue(QUEUE2);}

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange){
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
    }

    @Bean
    public MessageConverter converter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
