package com.ecorau.vbn.m3ua;

import com.ecorau.vbn.RequestContex;
import org.apache.log4j.Logger;

public class M3uaAspActiveAckMessageHandler implements M3uaMessageHandler{
    private final static Logger logger = Logger.getLogger(M3uaAspActiveAckMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
        logger.info("Handle m3ua asp active ack");
    }
}
