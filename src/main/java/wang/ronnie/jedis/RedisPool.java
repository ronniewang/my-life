package wang.ronnie.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import wang.ronnie.utils.Config;

import java.util.Properties;

public class RedisPool {
    private static volatile RedisPool sInstance = null;
    private JedisPool mPool;
    
    static {
        sInstance = new RedisPool();
    }
    
    private RedisPool() {
        Properties p = Config.load("/redis.properties");
        
        String maxIdle = p.getProperty("maxIdle", "100");
        String maxWaitMillis = p.getProperty("maxWaitMillis", "3000");
        String maxTotal = p.getProperty("maxTotal", "5000");
        String softMinEvictableIdleTimeMillis = p.getProperty("softMinEvictableIdleTimeMillis", "20000");
        String timeBetweenEvictionRunsMillis = p.getProperty("timeBetweenEvictionRunsMillis", "3000");
        
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(maxIdle));
        config.setMaxWaitMillis(Integer.valueOf(maxWaitMillis));
        config.setMaxTotal(Integer.valueOf(maxTotal));
        config.setSoftMinEvictableIdleTimeMillis(Integer.valueOf(softMinEvictableIdleTimeMillis));
        config.setTimeBetweenEvictionRunsMillis(Integer.valueOf(timeBetweenEvictionRunsMillis));
        
        String host = p.getProperty("host");
        Integer port = Integer.valueOf(p.getProperty("port"));
        String auth = p.getProperty("auth");
        mPool = new JedisPool(config, host, port, 2000, auth);
    }
    
    public static synchronized RedisPool getInstance() {
        return sInstance;
    }
    
    public synchronized Jedis get() {
        return mPool.getResource();
    }
    
}
