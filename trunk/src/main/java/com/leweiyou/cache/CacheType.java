package com.leweiyou.cache;

/**
 * 定义项目支持的缓存类型，<br>
 * 1.可以通过CacheWhere{@link CacheWhere}定义
 * 2.可以通过 CompositeCacheManager.setCacheType()设置当前想成的缓存类型<br>
 * @author Zhangweican
 *
 */
public enum CacheType{
	MAP,EHCACHE,REDIS,NOOP
}