package com.ecorau.vbn.sccp;

import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.m3ua.upperlayer.M3uaUpperLayerMessageHandler;
import org.apache.log4j.Logger;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;
import org.restcomm.protocols.ss7.sccp.SccpProtocolVersion;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpMessage;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceDefault;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SccpMessageHandler implements M3uaUpperLayerMessageHandler {
    private static final Logger logger = Logger.getLogger(SccpMessageHandler.class);

    @Override
    public void process(ProtocolData protocolData, RequestContex requestContex) {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceDefault();
        SccpStackImpl sccpStackImpl = new SccpStackImpl(null, ss7ExtInterface);
        MessageFactoryImpl msgFactory = new MessageFactoryImpl(sccpStackImpl);
        try {
            InputStream inputStream = new ByteArrayInputStream(protocolData.getData());
            int sccpMessageType = inputStream.read();
            SccpMessage sccpMessage = msgFactory.createMessage(sccpMessageType, protocolData.getOpc(), protocolData.getDpc(),
                    protocolData.getSLS(), inputStream, SccpProtocolVersion.ITU, 0);
            requestContex.setSccpMessage(sccpMessage);

            SccpMessageChildHandler sccpMessageChildHandler = SccpMessageFactory.getSccpMessage(sccpMessage);
            if (sccpMessageChildHandler != null)
                sccpMessageChildHandler.process(requestContex);
            else
                logger.warn("Sccp message is not handled");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
