package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.TestCache;

import com.leweiyou.cache.CacheType;
import com.leweiyou.cache.CompositeCacheManager;
import com.leweiyou.cache.ehcache.EhCacheService;
import com.leweiyou.cache.map.ConcurrentMapCacheService;
import com.leweiyou.cache.redis.RedisCacheService;

public class CacheTest {

	private TestCache testService;
	private RedisCacheService redisCacheService;
	private EhCacheService ehCacheService;
	private ConcurrentMapCacheService concurrentMapCacheService;
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-service.xml","applicationContext-cache.xml","applicationContext-cache-test.xml");
		testService = applicationContext.getBean(TestCache.class);
		redisCacheService = applicationContext.getBean(RedisCacheService.class);
		ehCacheService = applicationContext.getBean(EhCacheService.class);
		concurrentMapCacheService = applicationContext.getBean(ConcurrentMapCacheService.class);
	}

	@Test
	public void test() {
		String math = testService.selectA();
		System.out.println(math);
		String math1 = testService.selectB();
		System.out.println(math1);
		
		testService.deleteA();
		//testService.deleteB();
		String math3 = testService.selectA();
		System.out.println(math3);
		String math2 = testService.selectB();
		System.out.println(math2);
		
		testService.deleteA();
		
		
	}
	@Test
	public void test2() {
		String math = testService.selectA();
		System.out.println(math);
		CompositeCacheManager.setCacheType(CacheType.MAP);
		testService.deleteA();
		String math1 = testService.selectA();
		System.out.println(math1);
		CompositeCacheManager.setCacheType(CacheType.REDIS);
		String math2 = testService.selectA();
		System.out.println(math2);
	}

	@Test
	public void testRedisService(){
		String key1 = "abc";
		String key2 = "def";
		redisCacheService.put(key1, "abc_123");
		redisCacheService.put(key2, "def_123");
		System.out.println("key1=" + (redisCacheService.get(key1) == null ? null : redisCacheService.get(key1).get()));
		System.out.println("key2=" + (redisCacheService.get(key2) == null ? null : redisCacheService.get(key2).get()));
		System.out.println("del key1");
		redisCacheService.evict(key1);
		System.out.println("key1=" + (redisCacheService.get(key1) == null ? null : redisCacheService.get(key1).get()));
		System.out.println("key2=" + (redisCacheService.get(key2) == null ? null : redisCacheService.get(key2).get()));
		System.out.println("del key2");
		//redisService.del(key2);
		System.out.println("key1=" + (redisCacheService.get(key1) == null ? null : redisCacheService.get(key1).get()));
		System.out.println("key2=" + (redisCacheService.get(key2) == null ? null : redisCacheService.get(key2).get()));
	}
	@Test
	public void testconcurrentMapCacheService(){
		String key1 = "abc";
		String key2 = "def";
		concurrentMapCacheService.put(key1, "666");
		concurrentMapCacheService.put(key2, "7777");
		System.out.println("key1=" + (concurrentMapCacheService.get(key1) == null ? null : concurrentMapCacheService.get(key1).get()));
		System.out.println("key2=" + (concurrentMapCacheService.get(key2) == null ? null : concurrentMapCacheService.get(key2).get()));
		System.out.println("del key1");
		concurrentMapCacheService.evict(key1);
		System.out.println("key1=" + (concurrentMapCacheService.get(key1) == null ? null : concurrentMapCacheService.get(key1).get()));
		System.out.println("key2=" + (concurrentMapCacheService.get(key2) == null ? null : concurrentMapCacheService.get(key2).get()));
		//System.out.println("del key2");
		//concurrentMapCacheService.del(key2);
		System.out.println("key1=" + (concurrentMapCacheService.get(key1) == null ? null : concurrentMapCacheService.get(key1).get()));
		System.out.println("key2=" + (concurrentMapCacheService.get(key2) == null ? null : concurrentMapCacheService.get(key2).get()));
	}
	@Test
	public void testEhCacheService(){
		String key1 = "abc";
		String key2 = "def";
		ehCacheService.put(key1, "333");
		ehCacheService.put(key2, "444");
		System.out.println("key1=" + (ehCacheService.get(key1) == null ? null : ehCacheService.get(key1).get()));
		System.out.println("key2=" + (ehCacheService.get(key2) == null ? null : ehCacheService.get(key2).get()));
		System.out.println("del key1");
		ehCacheService.evict(key1);
		System.out.println("key1=" + (ehCacheService.get(key1) == null ? null : ehCacheService.get(key1).get()));
		System.out.println("key2=" + (ehCacheService.get(key2) == null ? null : ehCacheService.get(key2).get()));
		//System.out.println("del key2");
		//ehCacheService.del(key2);
		System.out.println("key1=" + (ehCacheService.get(key1) == null ? null : ehCacheService.get(key1).get()));
		System.out.println("key2=" + (ehCacheService.get(key2) == null ? null : ehCacheService.get(key2).get()));
	}
}
