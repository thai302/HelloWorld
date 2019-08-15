package com.ecorau.vbn.cap;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.tcap.upperlayer.TcapUpperLayerMessageBase;
import com.ecorau.vbn.tcap.upperlayer.TcapUpperLayerMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.tcap.asn.comp.Component;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

public class CapMessageHandler extends TcapUpperLayerMessageBase implements TcapUpperLayerMessageHandler {
    private static final Logger logger = Logger.getLogger(CapMessageHandler.class);

    public CapMessageHandler(RequestContex requestContex) {
        super(requestContex);
    }

    @Override
    public void process() {
        if (requestContex.getComponents().length > 0) {
            Component component = requestContex.getComponents()[0];
            Invoke invoke = (Invoke) component;
            CapMessageChildHandler capMessageChildHandler = CapMessageFactory.getCapMessageHandlers(invoke);

            if (capMessageChildHandler != null) {
                requestContex.setInvoke(invoke);
                capMessageChildHandler.process(requestContex);
            } else
                logger.warn("Cap message is not handled");
        }
    }
}
