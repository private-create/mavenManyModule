package com.baidu.rabbitmq.consumer;

import com.baidu.rabbitmq.entity.User;
import com.baidu.rabbitmq.source.DemoSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DemoConsumer {



    @StreamListener(value = DemoSource.input,condition = "headers['name']=='User'")
    public void consumerMessage(@Payload User user){
        log.info("监听到用户消息{}",user.getUserName());
        log.info(user.toString());
    }
}
