package com.leweiyou.cache.redis;

import java.util.Set;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * 基于Ehcache的缓存类
 * @author Zhangweican
 *
 */
public class RedisCacheService{
	private RedisTemplate redisTemplate;
	
	public RedisCache getCache(){
		return new RedisCache(redisTemplate);
	}
	
	public void clear() {
		getCache().clear();
	}

	public void evict(Object key) {
		getCache().evict(key);
	}

	public ValueWrapper get(Object key) {
		return getCache().get(key);
	}

	public <T> T get(Object key, Class<T> type) {
		return getCache().get(key, type);
	}

	public Object getNativeCache() {
		return getCache().getNativeCache();
	}

	public void put(Object key, Object value) {
		getCache().put(key, value);
	}

	public ValueWrapper putIfAbsent(Object key, Object value) {
		return getCache().putIfAbsent(key, value);
	}

    /**
     * 通过正则匹配keys
     * 
     * @param pattern
     * @return
     */
    public Set keys(String pattern){
    	return getCache().keys(pattern);
    }

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public boolean exists(String key){
    	return getCache().exists(key);
    }

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public String flushDB(){
    	return getCache().flushDB();
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize(){
    	return getCache().dbSize();
    }

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public String ping(){
    	return getCache().ping();
    }	

    public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
}
