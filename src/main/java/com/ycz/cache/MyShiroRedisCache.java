package com.ycz.cache;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

@Setter
@Getter
public class MyShiroRedisCache implements Cache {

    private String name;
    private RedisTemplate<String,Object> redisTemplate;


    public MyShiroRedisCache(){}

    public MyShiroRedisCache(String name){
        this.name=name;
    }

    @Override
    public Object get(Object o) throws CacheException {
        Object o1 = redisTemplate.opsForValue().get(o.toString());
        return o1;
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        redisTemplate.opsForValue().set(o.toString(), o2);
        return null;
    }

    @Override
    public Object remove(Object o) throws CacheException {

        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
