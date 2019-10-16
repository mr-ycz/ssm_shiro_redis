package com.ycz.cache;

import lombok.Setter;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

@Setter
public class MyShiroCacheManager extends AbstractCacheManager {

    private RedisTemplate<String,Object> redisTemplate;

    @Override
    protected Cache createCache(String s) throws CacheException {

        System.out.println("MyShiroCacheManager:"+s);

        MyShiroRedisCache myShiroRedisCache=new MyShiroRedisCache(s);
        myShiroRedisCache.setRedisTemplate(redisTemplate);

        return myShiroRedisCache;
    }
}
