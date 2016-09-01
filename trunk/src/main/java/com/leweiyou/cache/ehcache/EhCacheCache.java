package com.leweiyou.cache.ehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
/**
 * spring包里面已经有实现该方法的功能，所以这里不再使用
 * @author Zhangweican
 *
 */
@Deprecated
public class EhCacheCache implements Cache {
	private EhCacheFactoryBean ehCacheFactoryBean;
	private String name;
	
	public Cache getCache(){
		Cache c = new org.springframework.cache.ehcache.EhCacheCache(ehCacheFactoryBean.getObject());
		return c;
	}
	
	@Override
	public void clear() {
		getCache().clear();
	}

	@Override
	public void evict(Object key) {
		getCache().evict(key);
	}

	@Override
	public ValueWrapper get(Object key) {
		return getCache().get(key);
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		return getCache().get(key, type);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return getCache().getNativeCache();
	}

	@Override
	public void put(Object key, Object value) {
		getCache().put(key, value);
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return getCache().putIfAbsent(key, value);
	}

	public EhCacheFactoryBean getEhCacheFactoryBean() {
		return ehCacheFactoryBean;
	}

	public void setEhCacheFactoryBean(EhCacheFactoryBean ehCacheFactoryBean) {
		this.ehCacheFactoryBean = ehCacheFactoryBean;
	}

	
	
}
