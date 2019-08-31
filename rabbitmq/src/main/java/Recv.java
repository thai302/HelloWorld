import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.SerializationUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            Model model = SerializationUtils.deserialize(delivery.getBody());
            try {
                String message = new String(delivery.getBody(), "UTF-8");

                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(message);
                System.out.println(" [x] Received '" + obj.toJSONString() + "'");
//            System.out.println(" [x] Received '" + model.getStr() + "'");
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
            };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}