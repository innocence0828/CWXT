package com.wh.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class MessageConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    @Autowired
    MessageProducer messageProducer;

    public void onMessage(Message message) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName(new String(message.getBody()));
        System.out.println(" [x] Sent '" + new String(message.getBody()) + "'");
        logger.info("consumer receive message------->:{}111111", message);
        try {
            messageProducer.sendMessage(new String(message.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
