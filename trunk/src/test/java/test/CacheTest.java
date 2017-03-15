package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.leweiyou.cache.CacheType;
import com.leweiyou.cache.CompositeCacheManager;
import com.leweiyou.cache.TestCache;

public class CacheTest {

	private TestCache testService;
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-service.xml","applicationContext-cache.xml");
		testService = applicationContext.getBean(TestCache.class);
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

}
