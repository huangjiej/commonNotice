package com.hummingbird.babyspace.util.Jedis;

import com.hummingbird.common.util.PropertiesUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	static PropertiesUtil pu = new PropertiesUtil();
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(JedisPoolUtils.class);	

    private static JedisPool pool;

    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     * 
     */
    private static void createJedisPool() {
        if(log.isDebugEnabled()){
        	log.debug("创建redis连接中");
        }
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        
        String maxActivity = pu.getProperty("maxActive");  
        String maxWait = pu.getProperty("maxWait");
        String maxIdle = pu.getProperty("maxIdle");
        String port = pu.getProperty("redisPort");
        String redisUrl = pu.getProperty("redisUrl");
        // 设置最大连接数
        if(maxActivity!=null){
        	Integer maxactivity = Integer.parseInt(maxActivity);
        	if(maxactivity!=null&&maxactivity>=0)
        	config.setMaxActive(maxactivity);
        }
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        if(maxWait!=null){
        	Integer maxwait= Integer.parseInt(maxWait);
        	if(maxwait!=null&&maxwait>=0)
        		config.setMaxWait(maxwait);
        }
       
        // 设置空间连接
        if(maxIdle!=null){
        	Integer maxidle= Integer.parseInt(maxIdle);
        	if(maxidle!=null&&maxidle>=0)
        		config.setMaxIdle(maxidle);
        }
        Integer porT = Integer.parseInt(port);
         try{        
        // 创建连接池
         pool = new JedisPool(config, redisUrl, porT);
         }catch(Exception e){
        	 log.error("redis连接池创建失败"+e);
         }
         if(log.isDebugEnabled()){
        	 log.debug("redis创建连接池成功");
         }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     * 
     * @return
     */
    public static Jedis getJedis() {

        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    /**
     * 归还一个连接
     * 
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }
    public static void main(String[] args) {
    	Jedis jpu = JedisPoolUtils.getJedis();
    	System.out.println(jpu);
	}
}
