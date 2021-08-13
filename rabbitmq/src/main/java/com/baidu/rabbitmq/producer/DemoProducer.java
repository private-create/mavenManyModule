package com.baidu.rabbitmq.producer;

import com.baidu.rabbitmq.source.DemoSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class DemoProducer {

    @Resource
    private DemoSource demoSource;

    public <T> void  send(T t){
        demoSource.output()
                .send(MessageBuilder
                .withPayload(t)
                .setHeader("name",t.getClass().getSimpleName())
                .build());
        log.info("发送消息.{}.{}","name",t.getClass().getSimpleName());
    }
}
