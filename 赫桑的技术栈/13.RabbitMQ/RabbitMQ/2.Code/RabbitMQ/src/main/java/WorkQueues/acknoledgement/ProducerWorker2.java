package WorkQueues.acknoledgement;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import util.RabbitMqUtils;

import java.util.Scanner;

/**
 * 消息应答
 *
 * @Author He Zhu
 * @Date 2022-05-02
 * @Version 0.1
 */
public class ProducerWorker2 {
    public static final String TASK_QUEUE_NAME = "ack_queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();

            // 交换机，队列名称，相关参数，发送内容
            // 生产消息
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println("消息发送完成：" + message);
        }
    }
}
