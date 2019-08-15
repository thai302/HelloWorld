package com.ecorau.vbn.m3ua;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerFactory;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.message.transfer.PayloadData;

public class M3uaPayloadDataMessageHandler implements M3uaMessageHandler {
    private final static Logger logger = Logger.getLogger(M3uaPayloadDataMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
        PayloadData payloadData = (PayloadData) requestContex.getM3uaMessage();
        M3uaUpperLayerMessageHandler m3uaUpperLayerMessageHandler = M3uaUpperLayerFactory.getM3uaUpperLayer(payloadData.getData());

        if (m3uaUpperLayerMessageHandler != null)
            m3uaUpperLayerMessageHandler.process(payloadData.getData(), requestContex);
        else
            logger.warn("M3ua upper layer message is not handled");
    }
}
