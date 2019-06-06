package com.hfzycj.util;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    public void set(String key, String value, long liveTime) {
        set(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")), liveTime);
    }

    private void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.set(key, value);
            if (liveTime > 0) {
                connection.expire(key, liveTime);
            }
            return 1L;
        });
    }

    public Boolean setNx(final String key, final String value, final long liveTime) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            Boolean res = connection.setNX(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")));
            if (liveTime > 0) {
                connection.expire(key.getBytes(Charset.forName("UTF-8")), liveTime);
            }
            return res;
        });
    }

    public Object get(final String key) {
        return redisTemplate.execute((RedisCallback<Object>) conn -> {
            byte[] type = conn.get(key.getBytes(Charset.forName("UTF-8")));
            if (null == type) {
                return null;
            } else {
                return redisTemplate.getStringSerializer().deserialize(type);
            }
        });
    }

    public void hSet(final String key, final String field, final String value) {
        redisTemplate.execute((RedisCallback<Object>) conn -> {
            conn.hSet(key.getBytes(Charset.forName("UTF-8")), field.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")));
            return null;
        });
    }

    public Object hGet(final String key, final String field) {
        return redisTemplate.execute((RedisCallback<Object>) conn -> {
            byte[] bvalue = conn.hGet(key.getBytes(Charset.forName("UTF-8")), field.getBytes(Charset.forName("UTF-8")));
            if (null == bvalue) {
                return null;
            } else {
                return redisTemplate.getStringSerializer().deserialize(bvalue);
            }
        });
    }

    // 往 Redis 中写入 Map 类型的数据
    public void hMSet(final String key, final Map<String, Object> map) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            Map byteMap = new HashMap(map.size());
            for (final Map.Entry entry : map.entrySet()) {
                byte[] mapKey = serializeValue(entry.getKey());
                byte[] mapValue = serializeValue(String.valueOf(entry.getValue()));
                byteMap.put(mapKey, mapValue);
            }
            connection.hMSet(serializeKey(key), byteMap);
            byteMap = null; // 释放内存中的map
            return byteMap;
        });
    }

    // 从 Redis 中获取 Map 类型中制定 key 的数据（如果 field 为空的话，则取整个 Map）
    public Object hMGet(final String key, final Object field) {
        return redisTemplate.execute((RedisCallback) connection -> {
            if (null == field || "".equals(String.valueOf(field))) {
                return hGetAll(key);
            } else {
                List<byte[]> list = connection.hMGet(serializeKey(key), serializeValue(field));
                if (0 < list.size()) {
                    return deserializeValue(list.get(0));
                } else {
                    return null;
                }
            }
        });
    }

    public Map<String, Object> hGetAll(final String key) {
        return (Map) redisTemplate.execute((RedisCallback) connection -> {
            Map<byte[], byte[]> map = connection.hGetAll(serializeKey(key));
            Map<String, Object> objectMap = new HashMap<>();
            for (final Map.Entry entry : map.entrySet()) {
                Object mapKey = deserializeValue((byte[]) entry.getKey());
                Object mapValue = deserializeValue((byte[]) entry.getValue());
                objectMap.put(String.valueOf(mapKey), mapValue);
            }
            return objectMap;
        });
    }

    // hdel 删除 Map 中 制定的 key


    public void rPush(final String key, final String value) {
        redisTemplate.execute((RedisCallback<Object>) conn -> {
            conn.rPush(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8")));
            return null;
        });
    }

    // 设置某一key的超时时间，单位秒
    public void expired(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void del(final String key) {
        redisTemplate.execute((RedisCallback<Object>) conn -> conn.del(key.getBytes(Charset.forName("UTF-8"))));
    }

    /**
     * key以秒为单位,返回给定 key 的剩余生存时间
     *
     * @param key
     */
    public Long ttl(final String key) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> connection.ttl(key.getBytes(Charset.forName("UTF-8"))));
    }

    /**
     * 对一个key的value加1
     *
     * @param key
     */
    public Long incr(final String key) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> connection.incr(key.getBytes(Charset.forName("UTF-8"))));
    }

    public Long getTtl(final String key) {
        return (Long) redisTemplate.execute((RedisCallback<Object>) conn -> {
            Long value = conn.ttl(key.getBytes(Charset.forName("UTF-8")));
            return value;
        });
    }

    public Object sAdd(final String key, final String value) {
        return redisTemplate.execute((RedisCallback<Object>) conn -> conn.sAdd(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8"))));
    }

    public Object sIsMember(final String key, final String value) {
        return redisTemplate.execute((RedisCallback<Object>) conn -> conn.sIsMember(key.getBytes(Charset.forName("UTF-8")), value.getBytes(Charset.forName("UTF-8"))));
    }

    private byte[] serializeKey(final String key) {
        return redisTemplate.getStringSerializer().serialize(key);
    }

    private byte[] serializeValue(final Object value) {
        return redisTemplate.getValueSerializer().serialize(value);
    }

    protected Object deserializeValue(final byte[] value) {
        return redisTemplate.getValueSerializer().deserialize(value);
    }

}
