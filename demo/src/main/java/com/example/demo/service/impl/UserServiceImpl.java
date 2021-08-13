package com.example.demo.service.impl;

import com.baidu.rabbitmq.producer.DemoProducer;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private DemoProducer demoProducer;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public User findByUserSeq(String userSeq) {
        Optional<User> optionalUser = userMapper.findByUserSeq(userSeq);
        return optionalUser.isPresent()?optionalUser.get():null;
    }
}
