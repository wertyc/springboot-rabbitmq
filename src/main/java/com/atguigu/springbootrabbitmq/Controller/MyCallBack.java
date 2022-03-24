package com.atguigu.springbootrabbitmq.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback{
    //交换机无论是否收到消息的一个回调
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null? correlationData.getId():"";
        if (ack){
            log.info("交换机已经收到 id 为:{}的消息",id);
        }else {
            log.info("交换机未收到 id 为:{}的消息,由于原因:{}",id,cause);
        }

    }
}
