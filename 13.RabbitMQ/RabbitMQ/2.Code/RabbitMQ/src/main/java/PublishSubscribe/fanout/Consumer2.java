package PublishSubscribe.fanout;

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
    public  static  final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws  Exception{
        Channel channel = RabbitMqUtils.getChannel();

        //声明一个队列,名称随机，当消费者断开与队列的连接时，队列自动删除
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机与队列
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("等待接受消息，把接受到的消息打印在屏幕上...");


        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogs02控制台打印接受到的消息：" + new String(message.getBody()));
        };

        channel.basicConsume(queueName,true, deliverCallback, consumerTag -> {});
    }
}
