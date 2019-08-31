package com.ecorau.vbn.queue;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.utils.JsonConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Producer {
    private final static Logger logger = Logger.getLogger(Consumer.class);

    public static BlockingQueue queue = new LinkedBlockingQueue<RequestContex>();

    public static void addMessageToQueue(RequestContex requestContex) {
        try {
            queue.put(requestContex);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public static void addMessageToQueue(ByteBuf byteBuf) {
        try {
            sendToRabbitMQ(byteBuf);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public static void addMessageToQueue(PayloadData payloadData) {
        try {
            sendToRabbitMQ(payloadData);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    private static void sendToRabbitMQ(ByteBuf byteBuf) throws Exception {
        List<Byte> byteList = new ArrayList<>();
        while (byteBuf.isReadable()) {
            byteList.add(byteBuf.readByte());
        }
        Byte[] bytes = byteList.toArray(new Byte[byteList.size()]);
        byte[] data = ArrayUtils.toPrimitive(bytes);

        sendToRabbitMQ(data);
    }

    private static void sendToRabbitMQ(PayloadData payloadData) throws Exception {
        byte[] data = JsonConverter.serialize(payloadData);
        sendToRabbitMQ(data);
    }

    private static void sendToRabbitMQ(byte[] data) throws Exception {
        String QUEUE_NAME = "hello";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicPublish("", QUEUE_NAME, null, data);
    }
}
