package com.atguigu.springbootrabbitmq.Config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class TtlQueueConfig {
    public static final String X_EXCHANGE = "X";
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String QUEUE_C = "QC";
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String DEAD_LETTER_QUEUE = "QD";

    //定义直接与生产者接触的交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }
    //定义一个 ttl 为 10S 的 QA 队列,并配置其死信交换机
    @Bean("queueA")
    public Queue queueA(){
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }
    //将队列A与交换机 X 绑定
    @Bean
    public Binding queueBindingXExchange(
            @Qualifier("queueA") Queue queueA,
            @Qualifier("xExchange") DirectExchange xExchange
    ){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    //定义队列 QB 的 ttl 为 20S,并配置其死信交换机
    @Bean("queueB")
    public Queue queueB(){
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");
        args.put("x-message-ttl", 20000);
        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }

    //将队列 B 与交换机 X 绑定
    @Bean
    public Binding queueBindingXExchange2(
            @Qualifier("queueB") Queue queueB,
            @Qualifier("xExchange") DirectExchange xExchange
    ){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    //定义一个队列D,并与交换机 y 绑定
    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Binding bindingQueueDYExchange(
            @Qualifier("queueD") Queue queueD,
            @Qualifier("yExchange") DirectExchange yExchange
    ){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }

    //延迟队列优化之添加延迟消息

    @Bean("queueC")
    public Queue queueC(){
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(args).build();
    }

    //绑定交换机 x 和队列 C
    @Bean
    public Binding queueCYExchange(
            @Qualifier("xExchange") DirectExchange xExchange,
            @Qualifier("queueC") Queue queueC
    ){
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }




    }



