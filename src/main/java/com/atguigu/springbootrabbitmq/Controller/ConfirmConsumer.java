package com.atguigu.springbootrabbitmq.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConfirmConsumer {
    public static final String CONFIRM_QUEUE_NAME = "confirm.queue";
    @RabbitListener(queues = CONFIRM_QUEUE_NAME)
    public void receiveMessage(Message message){
        String s = new String(message.getBody());
        log.info("接受到队列 confirm.queue 消息:{}",s);
    }
}
