package com.ecorau.vbn.cap;

import org.restcomm.protocols.ss7.cap.api.CAPOperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

public class CapMessageFactory {
    public static CapMessageChildHandler getCapMessageHandlers(Invoke invoke) {
        switch (invoke.getOperationCode().getLocalOperationCode().intValue()) {
            case CAPOperationCode.initialDP:
                return new CapInitialDPRequestMessageHandler();
            default:
                return null;
        }
    }
}
