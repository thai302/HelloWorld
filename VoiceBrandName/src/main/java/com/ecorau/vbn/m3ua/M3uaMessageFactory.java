package com.ecorau.vbn.m3ua;

import org.restcomm.protocols.ss7.m3ua.impl.message.aspsm.ASPUpAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.asptm.ASPActiveAckImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.mgmt.NotifyImpl;
import org.restcomm.protocols.ss7.m3ua.impl.message.transfer.PayloadDataImpl;
import org.restcomm.protocols.ss7.m3ua.message.M3UAMessage;

public class M3uaMessageFactory {
    public static M3uaMessageHandler getM3uaMessageHandler(M3UAMessage m3UAMessage) {
        if (m3UAMessage instanceof ASPUpAckImpl)
            return new M3uaAspUpAckMessageHandler();

        else if (m3UAMessage instanceof ASPActiveAckImpl)
            return new M3uaAspActiveAckMessageHandler();

        else if (m3UAMessage instanceof NotifyImpl)
            return new M3uaNotifyMessageHandler();

        else if (m3UAMessage instanceof PayloadDataImpl)
            return new M3uaPayloadDataMessageHandler();

        else
            return null;
    }
}
