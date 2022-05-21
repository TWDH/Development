package com.atguigu.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo1 {

    public static void main(String[] args) {
        // 创建 Jedis  对象
        Jedis jedis = new Jedis("192.168.56.10",6379);
        // 测试
        String value = jedis.ping();
        System.out.println(value);
        jedis.close();
    }

    //操作 zset
    @Test
    public void demo5() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.56.10",6379);

        jedis.zadd("china",100d,"shanghai");

        Set<String> china = jedis.zrange("china", 0, -1);
        System.out.println(china);

        jedis.close();
    }

    //操作 hash
    @Test
    public void demo4() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.56.10",6379);

        jedis.hset("users","age","20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
        jedis.close();
    }

    //操作 set
    @Test
    public void demo3() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.56.10",6379);

        jedis.sadd("names","lucy");
        jedis.sadd("names","mary");

        Set<String> names = jedis.smembers("names");
        System.out.println(names);
        jedis.close();
    }

    //操作 list
    @Test
    public void demo2() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.56.10",6379);

        jedis.lpush("key1","lucy", "mary", "jack");
        List<String> values = jedis.lrange("key1", 0, -1);
        System.out.println(values);
        jedis.close();
    }

    //操作 key string
    @Test
    public void demo1() {
        //创建Jedis对象
        Jedis jedis = new Jedis("192.168.56.10",6379);

        //添加
        jedis.set("name","lucy");

        //获取
        String name = jedis.get("name");
        System.out.println(name);

        //设置多个key-value
        jedis.mset("k1","v1","k2","v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

        Set<String> keys = jedis.keys("*");
        for(String key : keys) {
            System.out.println(key);
        }
        jedis.close();
    }
}
