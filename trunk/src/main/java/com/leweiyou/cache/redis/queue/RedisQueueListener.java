package com.leweiyou.cache.redis.queue;


/**
 * 队列监听器
 * @author Zhangweican
 * 
 */
public interface RedisQueueListener {  
  
    public void onMessage(RedisCallbackObject<?> value);
}