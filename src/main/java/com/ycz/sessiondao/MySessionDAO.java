package com.ycz.sessiondao;

import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
public class MySessionDAO extends AbstractSessionDAO {

    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 当用户第一次访问时，创建一个新的session
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {//此处参数就是新创建的session
        System.out.println("----------MySessionDAO doCreate----------");

        //为此session产生一个sessionID
        Serializable sessionId = this.generateSessionId(session);
        //将此sessionID赋值给session中的参数
        this.assignSessionId(session, sessionId);
        //将此session传入到redis缓存
        redisTemplate.opsForValue().set("session:"+sessionId, session);
        return sessionId;
    }

    /**
     * 持有session的用户访问时，利用自己的sessionID去获得自己的session
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {//用户传来的Cookie中携带的sessionID
        System.out.println("----------MySessionDAO doReadSession----------");

        SimpleSession simpleSession = (SimpleSession) redisTemplate.opsForValue().get("session:" + sessionId);

        return simpleSession;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("----------MySessionDAO update----------");

        redisTemplate.opsForValue().set("session:"+session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        System.out.println("----------MySessionDAO delete----------");

        redisTemplate.delete("session:"+session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        System.out.println("----------getActiveSessions----------");
        Set keys = redisTemplate.keys("session:*");

        List<Session> sessions = redisTemplate.opsForValue().multiGet(keys);

        return sessions;
    }
}
