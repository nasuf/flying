package com.flying.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {

    // 设置required=false，如果Spring容器中有就注入，没有忽略
    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    private <T> T execute(Function<ShardedJedis, T> fun) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return fun.callback(shardedJedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 执行SET操作
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    /**
     * 执行SET操作，并且指定生存时间
     * 
     * @param key
     * @param value
     * @param seconds 生存时间，单位为秒
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis jedis) {
                String str = jedis.set(key, value);
                // 设置生存时间
                jedis.expire(key, seconds);
                return str;
            }
        });
    }

    /**
     * 设置生存时间
     * 
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis jedis) {
                // 设置生存时间
                return jedis.expire(key, seconds);
            }
        });
    }

    /**
     * 执行GET操作
     * 
     * @param key
     * @return
     */
    public String get(final String key) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis jedis) {
                return jedis.get(key);
            }
        });
    }

    /**
     * 删除一个键
     * 
     * @param key
     * @return
     */
    public Long del(final String key) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis jedis) {
                return jedis.del(key);
            }
        });
    }

}
