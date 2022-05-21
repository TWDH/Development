package PublishSubscribe.DirectExchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import util.RabbitMqUtils;

/**
 * @Author He Zhu
 * @Date 2022-05-03
 * @Version 0.1
 */
public class Consumer2 {
    // 交换机的名称
    public  static  final String EXCHANGE_NAME = "direct_logs";
    public  static  final String QUEUE_NAME = "disk";

    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtils.getChannel();

        // 交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // 队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定交换机与队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");

        System.out.println("等待接受消息，把接受到的消息打印在屏幕上...");


        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogs01控制台打印接受到的消息：" + new String(message.getBody()));
        };

        channel.basicConsume(QUEUE_NAME,true, deliverCallback, consumerTag -> {});
    }
}
