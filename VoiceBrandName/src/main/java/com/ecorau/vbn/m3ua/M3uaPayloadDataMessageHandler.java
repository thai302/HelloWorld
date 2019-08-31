package com.ecorau.vbn.m3ua;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerFactory;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerMessageHandler;
import com.ecorau.vbn.queue.Producer;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;

import java.text.SimpleDateFormat;
import java.util.Date;

public class M3uaPayloadDataMessageHandler implements M3uaMessageHandler {
    private final static Logger logger = Logger.getLogger(M3uaPayloadDataMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
//        Producer.addMessageToQueue(requestContex);
        Producer.addMessageToQueue((PayloadData) requestContex.getM3uaMessage());
    }
}
