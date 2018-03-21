package com.leweiyou.cache.redis.queue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 中间传输回调对象
 * @author Zhangweican
 *
 * @param <T>
 */
public abstract class RedisCallbackObject<T> implements Serializable{
	protected Logger logger = Logger.getLogger(this.getClass());
	//扩展属相
	private Map<String,Object> extAttr = new HashMap<String,Object>();
	
	//定义中间传输对象
	private T t = null;
	
	public RedisCallbackObject(T t){
		this.t = t;
	}

	/**
	 * 定义回调函数
	 */
	public abstract void callback();

	
	
	public Map<String, Object> getExtAttr() {
		return extAttr;
	}

	public void setExtAttr(Map<String, Object> extAttr) {
		this.extAttr = extAttr;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}
