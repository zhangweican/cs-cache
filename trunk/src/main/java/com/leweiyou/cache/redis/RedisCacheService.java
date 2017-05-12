package com.leweiyou.cache.redis;

import java.util.Arrays;
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
    public static void put(final String key, final Object value, long timeout, TimeUnit unit) {  
        redisTemplate.opsForValue().set(key, value, timeout, unit);  
    }  
    /** 
     * 写入缓存 
     *  
     * @param key 
     * @param value 
     * @param expire 
     */  
    public static void put(final String key, final Object value) {  
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
    public static <T> T get(final String key, Class<T> clazz) {  
        return (T) redisTemplate.boundValueOps(key).get();  
    }  
      
    /** 
     * 读取缓存 
     * @param key 
     * @return 
     */  
    public static Object get(final String key){  
        return redisTemplate.boundValueOps(key).get();  
    }  
  
    /** 
     * 删除，根据key精确匹配 
     *  
     * @param key 
     */  
    public static void delete(final String... key) {  
        redisTemplate.delete(Arrays.asList(key));  
    }  
  
    /** 
     * 批量删除，根据key模糊匹配 
     *  
     * @param pattern 
     */  
    public static void deleteByPattern(final String... pattern) {  
        for (String kp : pattern) {  
            redisTemplate.delete(redisTemplate.keys(kp));  
        }  
    }  
  
    /** 
     * key是否存在 
     *  
     * @param key 
     */  
    public static boolean exists(final String key) {  
        return redisTemplate.hasKey(key);  
    } 

    /**
     * @param pattern
     * @return
     */
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);

    }

    /**
     * @return
     */
    public Object flushDB() {
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
    public Object dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return Long.valueOf(connection.dbSize() + "");
            }
        });
    }
    
    /**
     * @return
     */
    public Object ping() {
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
