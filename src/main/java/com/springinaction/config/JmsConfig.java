package com.springinaction.config;

import com.springinaction.common.Spittle;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjjfly on 2017/3/1.
 */
@Configuration
@EnableJms
public class JmsConfig {
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public ActiveMQQueue activeMQQueue(){
        return new ActiveMQQueue("spittr.alert.queue");
    }

    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("JMSType",Spittle.class);
        mappingJackson2MessageConverter.setTypeIdPropertyName("JMSType");
        mappingJackson2MessageConverter.setTypeIdMappings(typeIdMappings);
        return mappingJackson2MessageConverter;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination destination,MessageConverter messageConverter){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);
        jmsTemplate.setMessageConverter(messageConverter);
//        jmsTemplate.setDefaultDestinationName("spittr.alert.queue");
        return jmsTemplate;
    }
}
