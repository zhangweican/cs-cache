package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		String math = testService.select();
		System.out.println(math);
		String math1 = testService.select();
		System.out.println(math1);
	}

}
