package WorkQueues.polling;

import com.rabbitmq.client.Channel;
import util.RabbitMqUtils;

import java.util.Scanner;

/**
 * @Author He Zhu
 * @Date 2022-05-02
 * @Version 0.1
 */
public class ProducerWorker1 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        // 生成队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();
            // 交换机，队列名称，相关参数，发送内容
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息发送完成：" + message);
        }
    }
}
