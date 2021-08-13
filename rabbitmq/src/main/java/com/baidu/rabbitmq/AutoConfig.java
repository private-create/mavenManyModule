package com.baidu.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource(ignoreResourceNotFound = true,value = "classpath:config/mq.properties")
@Slf4j
public class AutoConfig {

    @PostConstruct
    public void config(){
        log.info("mq配置完成");
    }
}
