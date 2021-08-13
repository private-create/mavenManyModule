package com.example.demo.job;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.ListRedisOps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SchdueDemo extends ListRedisOps<String,User> {


    public SchdueDemo(RedisTemplate redisTemplate, RedisLockRegistry redisLockRegistry) {
        super(redisTemplate.getConnectionFactory());
    }

    @Autowired
    private UserService userService;



    @Scheduled(fixedDelayString = "20000")
    public void demo(){
        log.info("自动任务开始运行");
        long start = System.currentTimeMillis();
        List<User> users = userService.queryAll();
        this.rightPushList("users",users);
        log.info("自动任务运行结束耗时.{}.{}",System.currentTimeMillis()-start,"毫秒");
    }

}
