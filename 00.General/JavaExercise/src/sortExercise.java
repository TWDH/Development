import java.lang.reflect.Array;
import java.util.Arrays;

public class sortExercise {
    public static void main(String[] args) {
        int[] A = new int[]{3, 2, 4, 1};
        Integer[] B = new Integer[]{1, 2, 3, 4};
        Arrays.sort(B, (i, j) -> A[j - 1] - A[i - 1]);

        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));


    }

    private static JedisSentinelPool jedisSentinelPool = null;

    public static Jedis getJedisFromSentinel() {
        if (jedisSentinelPool == null) {
            Set<String> sentinelSet = new HashSet<>();
            sentinelSet.add("192.168.11.103:26379");
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong
            jedisSentinelPool = new JedisSentinelPool("mymaster", sentinelSet, jedisPoolConfig);
            return jedisSentinelPool.getResource();
        }
    }
