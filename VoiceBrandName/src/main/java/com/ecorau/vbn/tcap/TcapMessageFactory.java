package com.ecorau.vbn.tcap;

import com.ecorau.vbn.RequestContex;
import org.apache.log4j.Logger;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.sccp.message.SccpDataMessage;
import org.restcomm.protocols.ss7.tcap.asn.TcapFactory;
import org.restcomm.protocols.ss7.tcap.asn.comp.*;

public class TcapMessageFactory {
    private static final Logger logger = Logger.getLogger(TcapMessageFactory.class);

    public static TcapMessageHandler getTcapMessageHandler(RequestContex requestContex) {
        try {
            SccpDataMessage sccpDataMessage = (SccpDataMessage) requestContex.getSccpMessage();
            byte[] data = sccpDataMessage.getData();

            AsnInputStream ais = new AsnInputStream(data);

            // this should have TC message tag :)
            int tag = ais.readTag();

            if (ais.getTagClass() != Tag.CLASS_APPLICATION) {
                return null;
            }

            switch (tag) {
                case TCContinueMessage._TAG:
                    break;

                case TCBeginMessage._TAG:
                    TCBeginMessage tcBeginMessage = TcapFactory.createTCBeginMessage(ais);
                    requestContex.setTcapMessage(tcBeginMessage);
                    return new TcapBeginMessageHandler(requestContex);

                case TCEndMessage._TAG:
                   break;

                case TCAbortMessage._TAG:
                    break;

                case TCUniMessage._TAG:
                    break;

                default:
//                    unrecognizedPackageType(message, localAddress, remoteAddress, ais, tag, message.getNetworkId());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error(String.format("Error while decoding Rx SccpMessage=%s", message), e);
        }
        return null;
    }
}
