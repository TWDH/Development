package com.atguigu.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * 演示redis集群操作
 */
public class RedisClusterDemo {

    public static void main(String[] args) {
        //创建对象
        HostAndPort hostAndPort = new HostAndPort("192.168.56.10", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        //进行操作
        jedisCluster.set("b1","value1");

        String value = jedisCluster.get("b1");
        System.out.println("value: "+value);

        jedisCluster.close();
    }
}
