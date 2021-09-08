package com.springinaction.ch17;

import com.springinaction.common.Spittle;
import com.springinaction.config.JmsConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.util.Date;

/**
 * Created by zjjfly on 2017/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JmsConfig.class)
public class JmsTest {
    @Autowired
    ConnectionFactory  cf;
    @Autowired
    Destination destination;
    @Autowired
    JmsOperations jmsOperations;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void withOutJmsTemplate() throws Exception {
        //发送消息
        Connection connection = cf.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();
        message.setText("Hello,World!");
        producer.send(message);
        session.close();
        connection.close();
        //接受消息
        Connection conn = cf.createConnection();
        Session connSession = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = connSession.createConsumer(destination);
        TextMessage receive = (TextMessage)consumer.receive();
        System.out.println("Got a message:"+receive.getText());
        connSession.close();
        conn.close();
    }

    @Test
    public void send() throws Exception {
        Spittle jjzi = new Spittle("jjzi", new Date());
//        jmsOperations.send((s)->{return s.createObjectMessage(jjzi);});
        jmsOperations.convertAndSend(jjzi);
    }
}
