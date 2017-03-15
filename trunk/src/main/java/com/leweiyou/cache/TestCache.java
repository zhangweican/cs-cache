package com.leweiyou.cache;

import org.jboss.logging.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TestCache {
	private static Logger logger = Logger.getLogger(TestCache.class);
	
	@Cacheable(value={"AAA"})
	public String selectA(){
		logger.info("read db AAA");
		return Math.random() * 10000 + "";
	}
	@Cacheable(value={"BBB"})
	public String selectB(){
		logger.info("read db BBB");
		return Math.random() * 10000 + "";
	}
	
	@CacheEvict(value={"AAA"},allEntries=true)
	public void deleteA(){
		logger.info("delete AAA");
	}
	@CacheEvict(value={"BBB"}, allEntries=true)
	public void deleteB(){
		logger.info("delete BBB");
	}
}
