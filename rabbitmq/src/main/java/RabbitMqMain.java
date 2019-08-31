import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.rabbitmq.utility.BlockingCell;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class RabbitMqMain {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        BlockingCell<Object> blockingCell = new BlockingCell();
        recv(blockingCell);
        send();
        Object response;
        String str;
        try {
            response =  blockingCell.uninterruptibleGet(1000);
        } catch (TimeoutException var13) {
            System.out.println(var13.getMessage());
        }

        System.out.println("after blocking cell");
    }

    private static void send() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Model model = new Model();
        model.setStr("input1");
        ObjectMapper objectMapper = new ObjectMapper();
//                byte[] data = SerializationUtils.serialize(model);
//            byte[] data = message.getBytes("UTF-8");
        channel.basicPublish("", QUEUE_NAME, null, objectMapper.writeValueAsBytes(model));
    }

    private static void recv(BlockingCell<Object> blockingCell) throws Exception {
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

                Model model = new ObjectMapper().readValue(message, Model.class);
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(message);
                System.out.println(" [x] Received '" + obj.toJSONString() + "'");
                Thread.sleep(1000);
                blockingCell.set(message);
//            System.out.println(" [x] Received '" + model.getStr() + "'");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
