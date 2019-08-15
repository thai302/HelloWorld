package com.ecorau.vbn.sccp;

import org.restcomm.protocols.ss7.sccp.impl.message.SccpDataMessageImpl;
import org.restcomm.protocols.ss7.sccp.message.SccpMessage;

public class SccpMessageFactory {
    public static SccpMessageChildHandler getSccpMessage(SccpMessage sccpMessage) {
        if (sccpMessage instanceof SccpDataMessageImpl)
            return new SccpDataMessageHandler();
        else
            return null;
    }
}
