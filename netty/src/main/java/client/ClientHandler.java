package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.RequestData;
import model.ResponseData;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {

//        RequestData msg = new RequestData();
//        msg.setIntValue(123);
//        msg.setStringValue(
//                "all work and no play makes jack a dull boy");
//        ChannelFuture future = ctx.writeAndFlush(msg);
        ByteBuf out = Unpooled.buffer();
        ChannelFuture future = ctx.writeAndFlush(out.writeBytes("Hello".getBytes()));
        send(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println((ResponseData)msg);
        ctx.close();
    }

    private static void send(Object obj) throws Exception {
        String QUEUE_NAME = "hello";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        ObjectMapper objectMapper = new ObjectMapper();
        channel.basicPublish("", QUEUE_NAME, null, objectMapper.writeValueAsBytes(obj));
    }
}
