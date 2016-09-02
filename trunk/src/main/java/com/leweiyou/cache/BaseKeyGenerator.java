package com.leweiyou.cache;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.cache.interceptor.KeyGenerator;

import com.leweiyou.tools.utils.MD5;

/**
 * 基于class的类名，参数组成键值
 * @author Zhangweican
 *
 */
public class BaseKeyGenerator implements KeyGenerator {  
  
    @Override  
    public Object generate(Object target, Method method, Object... params) {  
  
        StringBuffer buffer = new StringBuffer();  
        Class entityClass = target.getClass();  
        buffer.append(entityClass.getName());  
        buffer.append(method.getName());
        if (params != null && params.length > 1) {  
            for (Object obj : params) {  
                if (obj != null) {  
                    if (obj instanceof AtomicInteger || obj instanceof AtomicLong || obj instanceof BigDecimal  
                            || obj instanceof BigInteger || obj instanceof Byte || obj instanceof Double  
                            || obj instanceof Float || obj instanceof Integer || obj instanceof Long  
                            || obj instanceof Short) {  
                        buffer.append(obj);  
                    } else if (obj instanceof List || obj instanceof Set || obj instanceof Map) {  
                        buffer.append(obj);  
                    } else {  
                        buffer.append(obj.hashCode());  
                    }  
                }  
            }  
        }  
        return buffer.toString();  
    }  
  
} 