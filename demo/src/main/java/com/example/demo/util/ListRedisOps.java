package com.example.demo.util;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListRedisOps <K,V>{

    private  ListOperations<K, V> kvListOperations;

    private RedisTemplate<K,V> redisTemplate;


    public ListRedisOps(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<K,V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        kvListOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;
    }

    /***
     * 将集合保存到list
     * @param k
     * @param list
     */
    public void rightPushList(K k, List<V> list){
        kvListOperations.rightPushAll(k,list);
        redisTemplate.expire(k,60*1000, TimeUnit.MILLISECONDS);
    }


}
