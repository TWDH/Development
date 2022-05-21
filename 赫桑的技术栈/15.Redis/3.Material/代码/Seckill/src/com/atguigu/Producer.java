package com.atguigu;

/**
 * @Author He Zhu
 * @Date 2021-12-23 4:39 p.m.
 * @Version 1.0
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("182.92.234.71");
        factory.setUsername("admin");
        factory.setPassword("123");
        //channel实现了自动close接口 自动关闭 不需要显示关闭
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            /** * 生成一个队列
             * 1.队列名称
             * 2.队列里面的消息是否持久化 默认消息存储在内存中
             * 3.该队列是否只供一个消费者进行消费 是否进行共享 true可以多个消费者消费
             * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 true 自动删除
             * 5.其他参数
             * */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "hello world";
            /**
             ** 发送一个消息
             * * 1.发送到那个交换机
             * * 2.路由的key是哪个
             * * 3.其他的参数信息
             * * 4.发送消息的消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完毕");
        }
    }
}
