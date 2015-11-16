package com.hummingbird.payment.util;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.util.PropertiesUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class JedisPoolUtils {
	static PropertiesUtil pu = new PropertiesUtil();
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(JedisPoolUtils.class);
	private static JedisPool jedisPool;

	/**
	 * 建立连接池 真实环境，一般把配置参数缺抽取出来。
	 * 
	 */
	private static void createJedisPool() {
		if (log.isDebugEnabled()) {
			log.debug("创建redis连接中");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		// 建立连接池配置参数
	/*	config.setMaxActive(1000);
		config.setMaxIdle(10000000);
		config.setMaxWait(10000);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379, 10000, "zengda");*/
		System.out.println(jedisPool);
		String maxActivity = pu.getProperty("maxActive");
		String maxWait = pu.getProperty("maxWait");
		String maxIdle = pu.getProperty("maxIdle");
		String port = pu.getProperty("redisPort");
		String redisUrl = pu.getProperty("redisUrl");
		String timeout = pu.getProperty("timeout");
		String password = pu.getProperty("password");
		Integer timeOut = Integer.parseInt(timeout);
		if (StringUtils.isNotBlank(maxActivity)) {
			Integer maxactivity = Integer.parseInt(maxActivity);
			if (maxactivity != null && maxactivity >= 0)
				config.setMaxActive(maxactivity);
		}
		// 设置最大阻塞时间，记住是毫秒数milliseconds
		if (StringUtils.isNotBlank(maxWait)) {
			Integer maxwait = Integer.parseInt(maxWait);
			if (maxwait != null && maxwait >= 0)
				config.setMaxWait(maxwait);
		}

		// 设置空间连接
		if (StringUtils.isNotBlank(maxIdle)) {
			Integer maxidle = Integer.parseInt(maxIdle);
			if (maxidle != null && maxidle >= 0)
				config.setMaxIdle(maxidle);
		}
		Integer porT =  null;
		if (port != null) {
			porT = Integer.parseInt(port);
		}else{
			log.error("端口号未设置");
			return;
		}
		if (timeOut == null) {
			timeOut = 10000;
			
		}
		try {
			// 创建连接池
			jedisPool = new JedisPool(config, redisUrl, porT, timeOut, password);
			if (log.isDebugEnabled()) {
				log.debug("redis连接池创建成功" + redisUrl + porT + timeOut + password);
			}
		} catch (Exception e) {
			log.error("redis连接池创建失败" + e);
		}

	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null)
			createJedisPool();
	}

	/**
	 * 获取一个jedis 对象
	 * 
	 * @return
	 */
	public static Jedis getJedis() throws JedisException {

		Jedis jedis = null;
		if (jedisPool == null) {
			poolInit();
		}
		  return jedisPool.getResource();
	}

	/**
	 * 归还一个连接
	 * 
	 * @param jedis
	 */
	public static void returnRes(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	public static void main(String[] args) {
		Jedis jpu = JedisPoolUtils.getJedis();
		System.out.println(jpu);
	}
	  
	   }

