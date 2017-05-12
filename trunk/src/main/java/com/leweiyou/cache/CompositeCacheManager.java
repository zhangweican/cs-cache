package com.leweiyou.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;

import com.leweiyou.tools.cfg.EnvUtil;

/**
 * 可以通过静态方法setCacheType控制调用那个缓存<br>
 * 参照{@link org.springframework.cache.support.CompositeCacheManager}，再获取cache，根据CACHE_TYPE获取，如果没有，则按照顺序获取。
 * <br>1.追加了设置redisCacheManager 读取配置文件(key:redis.cache.live.time)设置默认缓存时间
 * @author Zhangweican
 *
 */
public class CompositeCacheManager implements CacheManager, InitializingBean{
	public static ThreadLocal<CacheType> ThreadLocal = new ThreadLocal<CacheType>();

	private final List<CacheManager> cacheManagers = new ArrayList<CacheManager>();

	private boolean fallbackToNoOpCache = false;


	/**
	 * Construct an empty CompositeCacheManager, with delegate CacheManagers to
	 * be added via the {@link #setCacheManagers "cacheManagers"} property.
	 */
	public CompositeCacheManager() {
	}

	/**
	 * Construct a CompositeCacheManager from the given delegate CacheManagers.
	 * @param cacheManagers the CacheManagers to delegate to
	 */
	public CompositeCacheManager(CacheManager... cacheManagers) {
		setCacheManagers(Arrays.asList(cacheManagers));
	}


	/**
	 * Specify the CacheManagers to delegate to.
	 */
	public void setCacheManagers(Collection<CacheManager> cacheManagers) {
		if(cacheManagers != null && cacheManagers.size() > 0){
			for(CacheManager cacheManager : cacheManagers){
				setLiveTime(cacheManager);
			}
		}
		this.cacheManagers.addAll(cacheManagers);
	}

	/**
	 * Indicate whether a {@link NoOpCacheManager} should be added at the end of the delegate list.
	 * In this case, any {@code getCache} requests not handled by the configured CacheManagers will
	 * be automatically handled by the {@link NoOpCacheManager} (and hence never return {@code null}).
	 */
	public void setFallbackToNoOpCache(boolean fallbackToNoOpCache) {
		this.fallbackToNoOpCache = fallbackToNoOpCache;
	}

	@Override
	public void afterPropertiesSet() {
		if (this.fallbackToNoOpCache) {
			this.cacheManagers.add(new NoOpCacheManager());
		}
	}


	@Override
	public Cache getCache(String name) {
		CacheType type = ThreadLocal.get();
		if(type != null){
			CacheManager cacheManager = getCacheManager(type);
			Cache cache = cacheManager.getCache(name);
			if (cache != null) {
				return cache;
			}else{
				//对于查询不到的缓存直接返回空，而不在缓存集合中查询。
				return null;
			}
		}
		for (CacheManager cacheManager : this.cacheManagers) {
			Cache cache = cacheManager.getCache(name);
			if (cache != null) {
				return cache;
			}
		}
		return null;
	}

	/**
	 * 提供类型查询相应的缓存管理类
	 * @param type
	 * @return
	 */
	private CacheManager getCacheManager(CacheType type){
		if(type == null){
			return null;
		}
		switch (type) {
		case REDIS:
			for (CacheManager cacheManager : this.cacheManagers){
				if(cacheManager instanceof RedisCacheManager){
					return cacheManager;
				}
			}
			break;
		case MAP:
			for (CacheManager cacheManager : this.cacheManagers){
				if(cacheManager instanceof ConcurrentMapCacheManager){
					return cacheManager;
				}
			}
			break;
		case EHCACHE:
			for (CacheManager cacheManager : this.cacheManagers){
				if(cacheManager instanceof EhCacheCacheManager){
					return cacheManager;
				}
			}
			break;
		case NOOP:
			for (CacheManager cacheManager : this.cacheManagers){
				if(cacheManager instanceof NoOpCacheManager){
					return cacheManager;
				}
			}
			break;

		default:
			return null;
		}
		return null;
	}
	@Override
	public Collection<String> getCacheNames() {
		Set<String> names = new LinkedHashSet<String>();
		for (CacheManager manager : this.cacheManagers) {
			names.addAll(manager.getCacheNames());
		}
		return Collections.unmodifiableSet(names);
	}
	
	/**
	 * 设置当前线程的缓存属性
	 * @param type
	 */
	public static void setCacheType(CacheType type){
		ThreadLocal.set(type);
	}
	
	/**
	 * 设置存活时间
	 * <br><font color=red>注：暂时只支持redis的的默认缓存时间</font>
	 * @param cacheManager
	 */
	private void setLiveTime(CacheManager cacheManager){
		if(cacheManager instanceof RedisCacheManager){
			//存活时间
			Long liveTime = StringUtils.isEmpty(EnvUtil.getValue("redis.cache.expire.time")) ? 0 : Long.valueOf(EnvUtil.getValue("redis.cache.expire.time"));
			RedisCacheManager redisCacheManager = (RedisCacheManager) cacheManager;
			redisCacheManager.setDefaultExpiration(liveTime);
		}

	}
}
