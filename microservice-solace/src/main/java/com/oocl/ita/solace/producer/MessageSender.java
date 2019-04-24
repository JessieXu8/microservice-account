package com.oocl.ita.solace.producer;

import com.solacesystems.jcsmp.*;
import com.solacesystems.jcsmp.impl.JCSMPGenericXMLMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private SpringJCSMPFactory solaceFactory;

    private DemoPublishEventHandler publishEventHandler = new DemoPublishEventHandler();

    public void sendMessage(String destination, String message) {
        try {
            JCSMPSession session = solaceFactory.createSession();
            Queue queue = JCSMPFactory.onlyInstance().createQueue(destination);
//            Topic topic = JCSMPFactory.onlyInstance().createTopic("tutorial/topic");
//            session.addSubscription(topic);
            XMLMessageProducer producer = session.getMessageProducer(publishEventHandler);
            TextMessage jcsmpMsg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
            jcsmpMsg.setText(message);
            jcsmpMsg.setDeliveryMode(DeliveryMode.PERSISTENT);

            System.out.println("sending=================" + message);
            producer.send(jcsmpMsg, queue);
        } catch (Exception ex) {
            System.out.println("error occur---------------");
        }

    }
}
