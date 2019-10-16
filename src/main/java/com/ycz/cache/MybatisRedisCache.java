package com.ycz.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache {

    private String id;
    private ReadWriteLock lock=new ReentrantReadWriteLock();

    public MybatisRedisCache(){}

    public MybatisRedisCache(String id){
        System.out.println("MybatisRedisCache-->id:"+id);
        this.id=id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("MybatisRedisCache--putObject-->key:"+key+",value:"+value);

        RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)
        ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");

        redisTemplate.opsForValue().set(key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("----------MybatisRedisCache--getObject-->key----------\n"+key);

        RedisTemplate<String,Object> redisTemplate = (RedisTemplate<String,Object>)
        ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");

        Object o = redisTemplate.opsForValue().get(key.toString());
        System.out.println("MybatisRedisCache--getObject-->get(key):"+o);

        return o;
    }

    @Override
    public Object removeObject(Object key) {
        RedisTemplate<String,Object> redisTemplate=
                (RedisTemplate<String, Object>) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");

        redisTemplate.delete(key.toString());
        System.out.println("Mybatis移除一个key:"+key);

        return null;
    }

    @Override
    public void clear() {

        RedisTemplate<String,Object> redisTemplate=
                (RedisTemplate<String, Object>) ContextLoader.getCurrentWebApplicationContext().getBean("redisTemplate");

        Set<String> keys = redisTemplate.keys("*" + this.getId() + "*");
        redisTemplate.delete(keys);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.lock;
    }
}
