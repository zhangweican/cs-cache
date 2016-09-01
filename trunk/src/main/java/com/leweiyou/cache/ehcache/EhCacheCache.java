package com.leweiyou.cache.ehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;


public class EhCacheCache implements Cache {
	private EhCacheManagerFactoryBean cacheManagerFactory;
	private org.springframework.cache.ehcache.EhCacheCache cache = new org.springframework.cache.ehcache.EhCacheCache(cacheManagerFactory.getObject().getCache(getName()));
	private String name;
	
	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public void evict(Object key) {
		cache.evict(key);
	}

	@Override
	public ValueWrapper get(Object key) {
		return cache.get(key);
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		return cache.get(key, type);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return cache.getNativeCache();
	}

	@Override
	public void put(Object key, Object value) {
		cache.put(key, value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return cache.putIfAbsent(key, value);
	}

	public EhCacheManagerFactoryBean getCacheManagerFactory() {
		return cacheManagerFactory;
	}

	public void setCacheManagerFactory(EhCacheManagerFactoryBean cacheManagerFactory) {
		this.cacheManagerFactory = cacheManagerFactory;
	}
	
	
}
