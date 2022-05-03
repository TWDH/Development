package PublishConfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;
import util.RabbitMqUtils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 发布确认
 *
 * @Author He Zhu
 * @Date 2022-05-02
 * @Version 0.1
 */
public class Producer {
    public static final String TASK_QUEUE_NAME = "ack_queue";
    private static int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
        // 1. 单个确认
        // publishMessageIndividually();

        // 2. 批量确认
        publishMessageBatch();
    }

    /**
     * 单个发送
     */
    public static void publishMessageIndividually() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //队列声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        //开启发布确认
        channel.confirmSelect();

        long begin = System.currentTimeMillis();

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";

            // 发消息
            channel.basicPublish("", queueName, null, message.getBytes());

            // 单个消息，马上发布确认
            // 发布确认：服务端返回 false 或超时时间内未返回，生产者可以消息重发
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功");
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("发布 " + MESSAGE_COUNT + " 个单独确认消息,耗时" + (end - begin) + "ms");
    }

    /**
     * 批量
     */
    public static void publishMessageBatch() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        // 队列声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        // 开启发布确认
        channel.confirmSelect();

        // 批量确认消息大小
        int batchSize = 100;

        // 未确认消息个数
        int outstandingMessageCount = 0;
        long begin = System.currentTimeMillis();

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes());
            // 手动批量确认
            outstandingMessageCount++;

            if (outstandingMessageCount == batchSize) {
                // 发布确认
                channel.waitForConfirms();
                outstandingMessageCount = 0;
                System.out.println("Confirm: " + (i - batchSize + 1) + " ~ " + (i));
            }
        }

        //为了确保还有剩余没有确认消息 再次确认
        if (outstandingMessageCount > 0) {
            channel.waitForConfirms();
        }

        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息,耗时" + (end - begin) + "ms");
    }

    /**
     * 异步
     */
    public static void publicMessageAsync() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);

        // 开启发布确认
        channel.confirmSelect();
        // 开始时间
        long begin = System.currentTimeMillis();

        // 消息确认成功回调函数
        ConfirmCallback ackCallback = (deliveryTag, multiply) -> {
            System.out.println("确认的消息：" + deliveryTag);
        };

        // 消息确认失败回调函数
        /*
         * 参数1：消息的标记
         * 参数2：是否为批量确认
         * */
        ConfirmCallback nackCallback = (deliveryTag, multiply) -> {
            System.out.println("未确认的消息：" + deliveryTag);
        };


        /*
         * 参数1：监听哪些消息成功
         * 参数2：监听哪些消息失败
         * */
        // 异步通知 (开启多线程)
        // 准备消息的监听器，监听哪些消息成功，哪些消息失败
        channel.addConfirmListener(ackCallback, nackCallback);

        // 批量发送消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "消息" + i;
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        }

        // 结束时间
        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"个异步确认消息，耗时"+ (end - begin) + "ms");
    }

    /**
     * Advanced
     * @throws Exception
     */
    public static void publicMessageAsync2() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,false,false,false,null);

        // 开启发布确认
        channel.confirmSelect();

        /*
         * 线程安全有序的一个哈希表 适用于高并发的情况下
         * 1、轻松地将序号与消息进行关联
         * 2、轻松地批量删除，只要给到序号
         * 3、支持高并发
         * */
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

        // 消息确认成功回调函数
        ConfirmCallback ackCallback = (deliveryTag,multiply) -> {
            // 2、删除到已经确认的消息，剩下的就是未确认的消息
            if(multiply){
                // headMap是保留key小于delivertag的部分tailMap是保留key大于delivertag的部分
                ConcurrentNavigableMap<Long, String> confiremed = outstandingConfirms.headMap(deliveryTag);
                confiremed.clear();
            }else {
                outstandingConfirms.remove(deliveryTag);
            }

            System.out.println("确认的消息："+deliveryTag);
        };

        // 消息确认失败回调函数
        /*
         * 参数1：消息的标记
         * 参数2：是否为批量确认
         * */
        ConfirmCallback nackCallback = (deliveryTag,multiply) -> {
            // 3、打印一下未确认的消息都有哪些
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息是：" + message +"未确认的消息tag：" + deliveryTag);
        };

        // 准备消息的监听器，监听哪些消息成功，哪些消息失败
        /*
         * 参数1：监听哪些消息成功
         * 参数2：监听哪些消息失败
         * */
        channel.addConfirmListener(ackCallback,nackCallback);

        // 开始时间
        long begin = System.currentTimeMillis();

        // 批量发送消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "消息" + i;
            channel.basicPublish("",queueName,null,message.getBytes(StandardCharsets.UTF_8));

            // 1、此处记录下所有要发送的消息的总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }

        // 结束时间
        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"个异步确认消息，耗时"+ (end - begin) + "ms");
    }
}
