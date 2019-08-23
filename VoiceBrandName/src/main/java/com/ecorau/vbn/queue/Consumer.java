package com.ecorau.vbn.queue;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerFactory;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final static Logger logger = Logger.getLogger(Consumer.class);

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RequestContex requestContex = (RequestContex) queue.take();
                logger.info(Thread.currentThread());

                PayloadData payloadData = (PayloadData) requestContex.getM3uaMessage();
                M3uaUpperLayerMessageHandler m3uaUpperLayerMessageHandler = M3uaUpperLayerFactory.getM3uaUpperLayer(payloadData.getData());

                if (m3uaUpperLayerMessageHandler != null)
                    m3uaUpperLayerMessageHandler.process(payloadData.getData(), requestContex);
                else
                    logger.warn("M3ua upper layer message is not handled");

                logger.info("End: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }
}
