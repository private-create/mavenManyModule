package com.baidu.rabbitmq.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface DemoSource {

    String output="streamOutputDemo";

    String input="streamInputDemo";

    @Output(DemoSource.output)
    MessageChannel output();

    @Input(DemoSource.input)
    SubscribableChannel input();



}
