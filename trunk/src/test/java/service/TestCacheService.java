package service;

import org.jboss.logging.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.leweiyou.cache.CacheType;
import com.leweiyou.cache.CacheWhere;

@Component
public class TestCacheService {
	private static Logger logger = Logger.getLogger(TestCacheService.class);
	
	@Cacheable(value={"Redis"})
	@CacheWhere(CacheType.MAP)
	public String testValueKey1(){
		logger.info("read db AAA");
		return Math.random() * 10000 + "";
	}
	@Cacheable(value={"Redis"})
	@CacheWhere(CacheType.REDIS)
	public String testValueKey2(){
		logger.info("read db AAA");
		return Math.random() * 10000 + "";
	}
	@CacheEvict(value={"Redis"},allEntries=true)
	public void deleteValueKey(){
		logger.info("delete ValueKey");
	}
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
