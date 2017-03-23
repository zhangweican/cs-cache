package com.leweiyou.cache;

/**
 * 定义项目支持的缓存类型，可以通过 CompositeCacheManager.setCacheType()设置当前想成的缓存类型
 * @author Zhangweican
 *
 */
public enum CacheType{
	MAP,EHCACHE,REDIS,NOOP
}