package com.springinaction.ch17;

import com.springinaction.common.Spittle;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjjfly on 2017/3/1.
 */
public class JmsReciever {
    private static Destination destination;

    private static ConnectionFactory cf;

    private static JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
        destination = new ActiveMQQueue("spittr.alert.queue");
        jmsTemplate = new JmsTemplate(cf);
        jmsTemplate.setDefaultDestination(destination);
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("JMSType",Spittle.class);
        mappingJackson2MessageConverter.setTypeIdPropertyName("JMSType");
        mappingJackson2MessageConverter.setTypeIdMappings(typeIdMappings);
        jmsTemplate.setMessageConverter(mappingJackson2MessageConverter);
        while (true) {
            Object o = jmsTemplate.receiveAndConvert();
            if (o != null) {
                System.out.println((Spittle) o);
            } else {
                continue;
            }
        }
    }
}
