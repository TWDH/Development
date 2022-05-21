package WorkQueues.acknoledgement;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import util.RabbitMqUtils;
import util.SleepUtils;

/**
 * 消息应答
 *
 * @Author He Zhu
 * @Date 2022-05-02
 * @Version 0.1
 */
public class ConsumeWorker3 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("C2 等待接收消息，处理时间较长");

        //消息接受
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            // 执行 30 s
            System.out.println("================== C2 ==================");
            SleepUtils.sleep(5);

            String receivedMessage = new String(message.getBody());
            System.out.println("【接收】到消息:" + receivedMessage);

            // 手动应答
            /**
             * 1. 消息的标记 tag
             * 2. 是否批量应答
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        };

        //消息被取消
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消费者【取消】消费接口回调逻辑");
        };

        System.out.println("C2 消费者启动等待消费.................. ");
        // 消息接收: 第二个参数 false，非自动应答
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
