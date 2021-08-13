package com.example.demo.controller;

import com.baidu.rabbitmq.consumer.DemoConsumer;
import com.baidu.rabbitmq.producer.DemoProducer;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
//@Import(value = {DemoProducer.class, DemoConsumer.class})
public class UserController {

    @Autowired
    private DemoProducer demoProducer ;

    @Autowired
    private UserService userService;

    @GetMapping("/get/Users")
    public List<User> queryAll(){
        return userService.queryAll();
    }

    @GetMapping("/login/{userSeq}")
    public User login(@PathVariable("userSeq") String userSeq){
        User user = userService.findByUserSeq(userSeq);
        if(Objects.nonNull(user)){
            demoProducer.send(user);
        }
        return user;
    }
}
