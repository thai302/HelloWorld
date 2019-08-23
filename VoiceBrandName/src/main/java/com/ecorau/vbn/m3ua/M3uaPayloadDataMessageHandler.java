package com.ecorau.vbn.m3ua;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.queue.Producer;
import org.apache.log4j.Logger;

public class M3uaPayloadDataMessageHandler implements M3uaMessageHandler {
    private final static Logger logger = Logger.getLogger(M3uaPayloadDataMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
//        PayloadData payloadData = (PayloadData) requestContex.getM3uaMessage();
//        M3uaUpperLayerMessageHandler m3uaUpperLayerMessageHandler = M3uaUpperLayerFactory.getM3uaUpperLayer(payloadData.getData());
//
//        if (m3uaUpperLayerMessageHandler != null)
//            m3uaUpperLayerMessageHandler.process(payloadData.getData(), requestContex);
//        else
//            logger.warn("M3ua upper layer message is not handled");
//        for (int i = 0; i < AppConfig.getInstance().getNumberOfThread(); i++) {
//            try {
//                Producer.addMessageToQueue((RequestContex) requestContex.clone());
//            } catch (Exception ex) {
//                logger.error(ex);
//            }
//        }
        Producer.addMessageToQueue(requestContex);
    }
}
