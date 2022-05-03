package WorkQueues;

import com.rabbitmq.client.Channel;
import util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @Author He Zhu
 * @Date 2022-05-02
 * @Version 0.1
 */
public class ProducerWorker2 {
    public static final String TASK_QUEUE_NAME = "ack_queue";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();

            // 交换机，队列名称，相关参数，发送内容
            // 生产消息
            channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println("消息发送完成：" + message);
        }
    }
}
