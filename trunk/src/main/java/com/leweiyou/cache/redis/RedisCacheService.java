package com.leweiyou.cache.redis;

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
	
	public Cache getCache(){
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

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
