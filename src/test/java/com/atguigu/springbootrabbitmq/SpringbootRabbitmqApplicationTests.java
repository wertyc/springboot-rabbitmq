package com.atguigu.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
    }
    @Test
    public void testRabbit(){
        System.out.println(rabbitTemplate);
    }
@Test
    public void test(){
    System.out.println("jagjajgakj");
}
    @Test
    public void test05(){
        System.out.println("jagjajgakj");
    }
}
