package com.ecorau.vbn.m3ua;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.impl.message.asptm.ASPActiveImpl;
import org.restcomm.protocols.ss7.m3ua.impl.parameter.ParameterFactoryImpl;
import org.restcomm.protocols.ss7.m3ua.parameter.ParameterFactory;
import org.restcomm.protocols.ss7.m3ua.parameter.TrafficModeType;

public class M3uaAspUpAckMessageHandler implements M3uaMessageHandler {
    private final static Logger logger = Logger.getLogger(M3uaAspUpAckMessageHandler.class);

    @Override
    public void process(RequestContex requestContex) {
        logger.info("Handle m3ua asp up ack");
        sendM3uaAspActive();
    }

    private void sendM3uaAspActive() {
        ParameterFactory parameterFactory = new ParameterFactoryImpl();
        ASPActiveImpl m3uaMsg = new ASPActiveImpl();
        m3uaMsg.setTrafficModeType(parameterFactory.createTrafficModeType(TrafficModeType.Loadshare));

        PackageSender packageSender = new M3uaSender();
        packageSender.send(m3uaMsg, null);
    }
}
