package com.ecorau.vbn.tcap;

import com.ecorau.vbn.PackageSender;
import com.ecorau.vbn.RequestContex;
import com.ecorau.vbn.sccp.SccpSender;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.restcomm.protocols.ss7.sccp.impl.SccpStackImpl;
import org.restcomm.protocols.ss7.sccp.impl.message.MessageFactoryImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.sccp.parameter.SccpAddress;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterface;
import org.restcomm.protocols.ss7.ss7ext.Ss7ExtInterfaceDefault;
import org.restcomm.protocols.ss7.tcap.asn.comp.TCEndMessage;

public class TcapEndMessageHandler implements PackageSender<TCEndMessage> {
    private static final Logger logger = Logger.getLogger(TcapEndMessageHandler.class);

    @Override
    public void send(TCEndMessage msg, RequestContex requestContex) {
        Ss7ExtInterface ss7ExtInterface = new Ss7ExtInterfaceDefault();
        SccpStackImpl sccpStackImpl = new SccpStackImpl(null, ss7ExtInterface);
        MessageFactoryImpl msgFactory = new MessageFactoryImpl(sccpStackImpl);
        try {
            SccpDataMessage oldMsg = (SccpDataMessage) requestContex.getSccpMessage();
            SccpAddress callingParty = oldMsg.getCalledPartyAddress();
            SccpAddress calledParty = oldMsg.getCallingPartyAddress();

            AsnOutputStream aos = new AsnOutputStream();
            msg.encode(aos);

            SccpDataMessage sccpDataMsg = msgFactory.createDataMessageClass1(calledParty, callingParty,
                    aos.toByteArray(), oldMsg.getSls(), 0, true, oldMsg.getHopCounter(), oldMsg.getImportance());
            logger.info("SccpSender send: " + sccpDataMsg.toString());

            PackageSender packageSender = new SccpSender();
            packageSender.send(sccpDataMsg, requestContex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
