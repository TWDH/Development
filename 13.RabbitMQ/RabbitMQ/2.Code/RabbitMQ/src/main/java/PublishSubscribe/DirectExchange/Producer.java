package PublishSubscribe.DirectExchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import util.RabbitMqUtils;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Author He Zhu
 * @Date 2022-05-03
 * @Version 0.1
 */
public class Producer {
    // 交换机的名称
    public  static  final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws  Exception {
        Channel channel = RabbitMqUtils.getChannel();

        // 交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出的消息：" + message);
        }
    }
}
