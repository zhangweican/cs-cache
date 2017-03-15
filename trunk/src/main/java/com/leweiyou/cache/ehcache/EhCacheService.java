package com.leweiyou.cache.ehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCache;
/**
 * spring包里面已经有实现该方法的功能，所以这里不再使用
 * @author Zhangweican
 *
 */
public class EhCacheService{
	private EhCacheCache ehCacheCache;
	
	public Cache getCache(){
		return ehCacheCache;
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

	public void setEhCacheCache(EhCacheCache ehCacheCache) {
		this.ehCacheCache = ehCacheCache;
	}

}
