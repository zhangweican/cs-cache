package com.leweiyou.cache.map;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.concurrent.ConcurrentMapCache;
/**
 * spring包里面已经有实现该方法的功能，所以这里不再使用
 * @author Zhangweican
 *
 */
public class ConcurrentMapCacheService{
	private ConcurrentMapCache concurrentMapCache;
	
	public Cache getCache(){
		return concurrentMapCache;
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

	public void setConcurrentMapCache(ConcurrentMapCache concurrentMapCache) {
		this.concurrentMapCache = concurrentMapCache;
	}
	
}
