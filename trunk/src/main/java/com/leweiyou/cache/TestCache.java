package com.leweiyou.cache;

import org.jboss.logging.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TestCache {
	private static Logger logger = Logger.getLogger(TestCache.class);
	
	@Cacheable(value={CacheType.MAP,CacheType.EHCACHE,CacheType.REDIS})
	public String select(){
		logger.info("retrun Real data,not read from cache");
		return Math.random() * 10000 + "";
	}
}
