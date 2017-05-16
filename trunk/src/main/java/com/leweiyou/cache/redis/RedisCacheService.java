package com.leweiyou.cache.redis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * 基于Redis的缓存类
 * @author Zhangweican
 *
 */
public class RedisCacheService{
	private static RedisTemplate<String,Object> redisTemplate;
	
	public void clear() {
		redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	public void evict(final String key) {
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.del(key.getBytes());
			}
		});
	}

    /** 
     * 写入缓存 
     *  
     * @param key 
     * @param value 
     * @param expire 
     */  
    public void put(final String key, final Object value, long timeout, TimeUnit unit) {  
        redisTemplate.opsForValue().set(key, value, timeout, unit);  
    }  
    /** 
     * 写入缓存 
     *  
     * @param key 
     * @param value 
     * @param expire 
     */  
    public void put(final String key, final Object value) {  
    	redisTemplate.opsForValue().set(key, value, 0, TimeUnit.SECONDS);  
    }  
  
    /** 
     * 读取缓存 
     *  
     * @param key 
     * @param clazz 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public <T> T get(final String key, Class<T> clazz) {  
        return (T) redisTemplate.boundValueOps(key).get();  
    }  
      
    /** 
     * 读取缓存 
     * @param key 
     * @return 
     */  
    public Object get(final String key){  
        return redisTemplate.boundValueOps(key).get();  
    }  
  
    /**
     * 判定是否存在键值
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
    	return redisTemplate.hasKey(key);
    }
    
    /** 
     * 删除，根据key精确匹配 
     *  
     * @param key 
     */  
    public void delete(final String... key) {  
        redisTemplate.delete(Arrays.asList(key));  
    }  
  
    /**
     * 删除keys集合的内容
     * @param keys
     */
    public void delete(Collection<String> keys){
    	redisTemplate.delete(keys);
    }
    
    /** 
     * 批量删除，根据key模糊匹配 
     *  
     * @param pattern 
     */  
    public void deleteByPattern(final String... pattern) {  
        for (String kp : pattern) {  
            redisTemplate.delete(redisTemplate.keys(kp));  
        }  
    }  
  
    /** 
     * key是否存在 
     *  
     * @param key 
     */  
    public boolean exists(final String key) {  
        return redisTemplate.hasKey(key);  
    } 

    /**
     * 根据正则表达式获取keys集合
     * @param pattern
     * @return
     */
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 重命名键值
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey){
    	redisTemplate.rename(oldKey, newKey);
    }
    
    /**
     * 当newkey 不存在的时候，才重命名
     * @param oldKey
     * @param newKey
     * @return
     */
    public Boolean renameIfAbsent(String oldKey, String newKey){
    	return redisTemplate.renameIfAbsent(oldKey, newKey);
    }
    
    /**
     * 定义键值多久以后过期(时间TimeUnit)
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
	public Boolean expire(String key, long timeout, TimeUnit unit){
		return redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 通过键值获取过期时间
	 * @param key
	 * @return
	 */
	public Long getExpire(String key){
		return redisTemplate.getExpire(key);
	}

	/**
	 * 通过键值获取过期时间
	 * @param key
	 * @param timeUnit
	 * @return
	 */
	public Long getExpire(String key, TimeUnit timeUnit){
		return redisTemplate.getExpire(key, timeUnit);
	}	
	
	/**
	 * 定义键值在哪个时间点过期
	 * @param key
	 * @param date
	 * @return
	 */
	public Boolean expireAt(String key, Date date){
		return redisTemplate.expireAt(key, date);
	}
    
    /**
     * @return
     */
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
    /**
     * @return
     */
    public Long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return Long.valueOf(connection.dbSize() + "");
            }
        });
    }
    
    /**
     * @return
     */
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
    	RedisCacheService.redisTemplate = redisTemplate;
	}
	
}
