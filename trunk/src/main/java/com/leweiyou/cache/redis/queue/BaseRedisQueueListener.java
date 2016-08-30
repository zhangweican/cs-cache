package com.leweiyou.cache.redis.queue;


/**
 * 基本队列监听器
 * @author Zhangweican
 * 
 */
public class BaseRedisQueueListener implements RedisQueueListener{  
  
    public void onMessage(RedisCallbackObject<?> value){
    	value.callback();
    }
}