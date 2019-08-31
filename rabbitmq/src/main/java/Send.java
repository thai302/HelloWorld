import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);


        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNext()) {
                String input = scan.next();
//                JSONObject obj = new JSONObject();
//
//                obj.put("name", String.format("Person%s", 1));
//                obj.put("age", new Integer(37));
                Model model = new Model();
                model.setStr(input);
                ObjectMapper objectMapper = new ObjectMapper();
//                byte[] data = SerializationUtils.serialize(model);
//            byte[] data = message.getBytes("UTF-8");
                sendByteBuf(channel);
//                channel.basicPublish("", QUEUE_NAME, null, objectMapper.writeValueAsBytes(model));
                System.out.println(" [x] Sent '" + input + "'");
            }
        }
    }

    private static void sendByteBuf(Channel channel) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes("thaidoan".getBytes());
        List<Byte> byteList = new ArrayList<>();
        while (byteBuf.isReadable()) {
            byteList.add(byteBuf.readByte());
        }
        Byte[] bytes = byteList.toArray(new Byte[byteList.size()]);
//        byteBuf.readByte(bytes);
        channel.basicPublish("", QUEUE_NAME, null, ArrayUtils.toPrimitive(bytes));
    }
}