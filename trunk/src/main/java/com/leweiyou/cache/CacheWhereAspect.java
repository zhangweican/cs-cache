package com.leweiyou.cache;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

/**
 * 定义AOP设置缓存的地方的拦截器
 * @author Zhangweican
 *
 */
@Controller
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CacheWhereAspect {
	
	@Around("@annotation(com.leweiyou.cache.CacheWhere)")
	public Object doValid(ProceedingJoinPoint pjp) throws Throwable{
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		Object[] args = pjp.getArgs();

		CacheWhere valid = method.getAnnotation(CacheWhere.class);
		CompositeCacheManager.setCacheType(valid.value());
		
		return pjp.proceed();
	}

}
