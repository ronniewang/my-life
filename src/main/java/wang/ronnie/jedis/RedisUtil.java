package wang.ronnie.jedis;

import redis.clients.jedis.Jedis;
import wang.ronnie.global.Log;

import java.io.*;
import java.util.*;

public class RedisUtil {

    public static void set(String key, Object val) {

        set(key, val, 0);
    }

    public static void set(String key, Object val, int seconds) {

        try {
            set(key.getBytes("utf-8"), serialize(val), seconds);
        } catch (UnsupportedEncodingException e) {
            Log.ex(e);
        }
    }

    public static void set(byte[] key, byte[] val, int seconds) {

        try (Jedis j = RedisPool.getInstance().get()) {
            if (seconds > 0) {
                j.setex(key, seconds, val);
            } else {
                j.set(key, val);
            }
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void hSet(String key, String field, Object val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.hset(key.getBytes("utf-8"), field.getBytes("utf-8"), serialize(val));
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static Object hGet(String key, String field) {

        try (Jedis j = RedisPool.getInstance().get()) {
            byte[] bytes = j.hget(key.getBytes("utf-8"), field.getBytes("utf-8"));
            return serialize(bytes);
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static void hMSet(String key, Map<String, Object> m, int seconds) {

        try {
            Map<byte[], byte[]> serialize = new HashMap<>();
            for (String k : m.keySet()) {
                Object val = m.get(k);
                serialize.put(k.getBytes("utf-8"), serialize(val));
            }
            hMSet(key.getBytes("utf-8"), serialize, seconds);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void hMSet(byte[] key, Map<byte[], byte[]> m, int seconds) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.hmset(key, m);
            j.expire(key, seconds);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void setWithUnseriablize(String key, String val, int seconds) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.setex(key, seconds, val);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void hSetWithUnserialize(String key, String field, String val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.hset(key, field, val);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void hMSetWithUnserialize(String key, Map<String, String> m) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.hmset(key, m);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static String hGetWithUnserialize(String key, String field) {

        try (Jedis j = RedisPool.getInstance().get()) {
            String ret = j.hget(key, field);

            if (ret == null) {
                Log.warn("key:" + key + ", field:" + field + ", value is null");
            }
            return ret;
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static Map<String, String> hGetAllWithUnserialize(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            Map<String, String> map = j.hgetAll(key);

            if (map == null) {
                Log.warn("key:" + key + "'s value is null");
            }
            return map;
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static Object hGetAll(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            Map<byte[], byte[]> byteMap = j.hgetAll(key.getBytes("utf-8"));

            if (byteMap == null) {
                Log.warn("key:" + key + "'s value is null");
                return null;
            }

            Map<String, Object> strMap = new HashMap<String, Object>();
            for (byte[] bytes : byteMap.keySet()) {
                strMap.put(new String(bytes, "utf-8"), unserialize(byteMap.get(bytes)));
            }

            return strMap;
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static Object get(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            byte[] bytes = j.get(key.getBytes("utf-8"));
            if (bytes == null) {
                return null;
            }
            Object obj = unserialize(bytes);
            if (obj == null) {
                Log.warn("key:" + key + "'s value is null");
            }
            return obj;
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static String getWithUnserialize(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            String ret = j.get(key);
            if (ret == null) {
                Log.warn("key:" + key + "'s value is null");
            }
            return ret;
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static void del(String key) {

        Log.info("del key:" + key);
        try (Jedis j = RedisPool.getInstance().get()) {
            j.del(key);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    /**
     * 遍历删除所有keys,性能低
     *
     * @param key
     */
    public static void delKeys(String key) {

        Set<String> keys = keys(key + "*");
        if (keys != null) {
            keys.forEach(itemKey -> {
                del(itemKey);
            });
        }
    }

    public static Long inc(String key, long val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            return j.incrBy(key, val);
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static Long hInc(String key, String field, long val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            return j.hincrBy(key, field, val);
        } catch (Exception e) {
            Log.ex(e);
        }
        return -1L;
    }

    public static Set<String> keys(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            return j.keys(key);
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static boolean exists(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            return j.exists(key);
        } catch (Exception e) {
            Log.ex(e);
        }
        return false;
    }

    public static void rpush(String key, Object val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.rpush(key.getBytes("utf-8"), serialize(val));
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void lpush(String key, Object val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.lpush(key.getBytes("utf-8"), serialize(val));
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static void lrem(String key, Integer count, Object val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.lrem(key.getBytes("utf-8"), count, serialize(val));
        } catch (Exception e) {
            Log.ex(e);
        }
    }


    public static Long llen(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            return j.llen(key.getBytes("utf-8"));
        } catch (Exception e) {
            Log.ex(e);
        }
        return 0L;
    }

    public static Object lpop(String key) {

        try (Jedis j = RedisPool.getInstance().get()) {
            byte[] bytes = j.lpop(key.getBytes("utf-8"));
            if (bytes == null) {
                return null;
            }
            return unserialize(bytes);
        } catch (Exception e) {
            Log.ex(e);
        }
        return null;
    }

    public static void sAdd(String key, String... val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.sadd(key, val);
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static boolean sContain(String key, String val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            return j.sismember(key, val);
        } catch (Exception e) {
            Log.ex(e);
        }
        return false;
    }

    public static List lrange(String key, int start, int end) {

        List<Object> ret = new ArrayList<>();
        try (Jedis j = RedisPool.getInstance().get()) {
            List<byte[]> bytesList = j.lrange(key.getBytes("utf-8"), start, end);
            if (bytesList == null || bytesList.isEmpty()) {
                return ret;
            }

            for (byte[] bytes : bytesList) {
                Object obj = unserialize(bytes);
                ret.add(obj);
            }
        } catch (Exception e) {
            Log.ex(e);
        }
        return ret;
    }


    public static void lset(String key, int index, Object val) {

        try (Jedis j = RedisPool.getInstance().get()) {
            j.lset(key.getBytes("utf-8"), index, serialize(val));
        } catch (Exception e) {
            Log.ex(e);
        }
    }

    public static byte[] serialize(Object object) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            Log.warn("serialize object:" + object + " failed");
            Log.ex(e);
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais);) {
            return ois.readObject();
        } catch (Exception e) {
            Log.warn("unserialize bytes:" + bytes + " failed");
            Log.ex(e);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
