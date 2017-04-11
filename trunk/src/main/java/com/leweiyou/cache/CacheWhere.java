package com.leweiyou.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义缓存地方<br>
 * <font color=red>暂时只支持定义缓存一个地方，不支持两个地方缓存，需要缓存两个地方的，请重载方法</font>
 * @author Zhangweican
 *
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface CacheWhere {
	/**
	 * 定义缓存地方的名称枚举类
	 * @return
	 */
	CacheType value() default CacheType.REDIS;
	
}
