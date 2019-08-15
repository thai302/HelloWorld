package com.ecorau.vbn.sccp;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.tcap.TcapMessageFactory;
import com.ecorau.vbn.tcap.TcapMessageHandler;
import org.apache.log4j.Logger;

public class SccpDataMessageHandler implements SccpMessageChildHandler {

    private static final Logger logger = Logger.getLogger(SccpDataMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
        TcapMessageHandler tcapMessageHandler = TcapMessageFactory.getTcapMessageHandler(requestContex);
        if (tcapMessageHandler != null) {
            tcapMessageHandler.process();
        } else
            logger.warn("Tcap message is not handled");
    }
}
