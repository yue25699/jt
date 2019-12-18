package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@Configuration  //标识配置类
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	
	@Value("${redis.nodes}")
	private String nodes; //node1,node2,nod3....
	
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> setNodes = new HashSet<>(); 
		String[] arrayNode = nodes.split(",");
		//node{IP:PORT}
		for (String node : arrayNode) {
			String host = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			setNodes.add(new HostAndPort(host, port));
		}
		return new JedisCluster(setNodes);
	}
}
	
	
	//定义池对象
	/*
	 * @Bean public JedisSentinelPool jedisSentinelPool() { Set<String> sentinels =
	 * new HashSet<>(); sentinels.add(nodes); return new
	 * JedisSentinelPool("mymaster", sentinels); }
	 */
	
	/**
	 * @Bean:实例化对象时如果方法中添加了参数.首先会从spring容器中
	 * 查找该参数.如果有对象则直接注入.
	 * @param pool
	 * @return
	 * 池:100 0.75E  75 池的规则 100  
	 */
	/*
	 * @Bean
	 * 
	 * @Scope("prototype") //多例对象 用户使用时创建 public Jedis jedis(JedisSentinelPool pool)
	 * {
	 * 
	 * return pool.getResource(); }
	 */

	
	/*
	 * @Value("${redis.nodes}") private String nodes; //node,node...
	 * 
	 * @Bean public ShardedJedis shardedJedis() { List<JedisShardInfo> shards = new
	 * ArrayList<JedisShardInfo>(); String[] arrayNodes = nodes.split(",");
	 * //node={ip:port} for (String node : arrayNodes) { String host =
	 * node.split(":")[0]; int port = Integer.parseInt(node.split(":")[1]);
	 * JedisShardInfo info = new JedisShardInfo(host, port); shards.add(info); }
	 * return new ShardedJedis(shards); }
	 */
	
	/*
	 * @Test public void testShards() { String host = "192.168.38.128";
	 * List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); shards.add(new
	 * JedisShardInfo(host, 6379)); shards.add(new JedisShardInfo(host, 6380));
	 * shards.add(new JedisShardInfo(host, 6381)); ShardedJedis jedis = new
	 * ShardedJedis(shards); jedis.set("1905", "分片学习,为集群做准备!!!"); //6380服务器 set
	 * System.out.println(jedis.get("1905")); }
	 */
	
	
	
	
	
	
	
	
	
	/*
	 * @Value("${redis.host}") private String host;
	 * 
	 * @Value("${redis.port}") private int port;
	 */
	
	//方法的返回值,交给容器管理
	/*
	 * @Bean //@Lazy //懒加载 public Jedis jedis() {
	 * 
	 * return new Jedis(host, port); }
	 */

