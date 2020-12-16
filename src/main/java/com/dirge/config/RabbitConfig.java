package com.dirge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_A = "exchange_a";
    public static final String EXCHANGE_B = "exchange_b";

    public static final String QUEUE_A = "queue_a";
    public static final String QUEUE_B = "queue_b";

    public static final String ROUTINGKEY_A = "routingkey_a";
    public static final String ROUTINGKEY_B = "routingkey_b";

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(host);
        return connectionFactory;
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        return rabbitTemplate;
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public Queue queuA(){
        return new Queue(QUEUE_A,true);
    }

    @Bean
    public Queue queuB(){
        return new Queue(QUEUE_B,true);
    }

    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(queuA()).to(directExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(queuB()).to(directExchange()).with(RabbitConfig.ROUTINGKEY_B);
    }
}
