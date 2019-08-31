package com.ecorau.vbn.queue;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.M3uaMessageFactory;
import com.ecorau.vbn.m3ua.M3uaMessageHandler;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerFactory;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerMessageHandler;
import com.ecorau.vbn.utils.JsonConverter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.transfer.PayloadDataImpl;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;
import org.restcomm.protocols.ss7.m3ua.message.MessageFactory;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final static Logger logger = Logger.getLogger(Consumer.class);

    private BlockingQueue queue;

    private MessageFactory messageFactory = new MessageFactoryImpl();

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            recvFromRabbitMqPayload();
//            recvFromRabbitMQByteBuf();
        } catch (Exception ex) {
            logger.error(ex);
        }

//        while (true) {
//            try {
//
//                RequestContex requestContex = (RequestContex) queue.take();
//                logger.info(Thread.currentThread());
//                process(requestContex);
//            } catch (Exception ex) {
//                logger.error(ex);
//            }
//        }
    }

    private void process(RequestContex requestContex) {
        PayloadData payloadData = (PayloadData) requestContex.getM3uaMessage();
        M3uaUpperLayerMessageHandler m3uaUpperLayerMessageHandler = M3uaUpperLayerFactory.getM3uaUpperLayer(payloadData.getData());

        if (m3uaUpperLayerMessageHandler != null)
            m3uaUpperLayerMessageHandler.process(payloadData.getData(), requestContex);
        else
            logger.warn("M3ua upper layer message is not handled");

        logger.info("End: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
    }

    private void recvFromRabbitMQByteBuf() throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            ByteBuf byteBuf = Unpooled.copiedBuffer(delivery.getBody());
            process(byteBuf);
        };

        recvFromRabbitMQ(deliverCallback);
    }

    private void recvFromRabbitMqPayload() throws Exception {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                PayloadData payloadData = JsonConverter.deserialize(delivery.getBody(), PayloadDataImpl.class);
                RequestContex requestContex = new RequestContex();
                requestContex.setM3uaMessage(payloadData);

                process(requestContex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };

        recvFromRabbitMQ(deliverCallback);
    }

    private void recvFromRabbitMQ(DeliverCallback deliverCallback) throws Exception {
        String QUEUE_NAME = "hello";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }

    private void process(ByteBuf byteBuf) {
        M3UAMessage m3uaMessage = messageFactory.createMessage(byteBuf);

        RequestContex requestContex = new RequestContex();
        requestContex.setM3uaMessage(m3uaMessage);
        process(requestContex);
    }
}
