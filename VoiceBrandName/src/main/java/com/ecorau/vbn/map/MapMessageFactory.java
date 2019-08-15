package com.ecorau.vbn.map;

import org.restcomm.protocols.ss7.map.api.MAPOperationCode;
import org.restcomm.protocols.ss7.tcap.asn.comp.Invoke;

public class MapMessageFactory {
    public static MapMessageChildHandler getMapMessageHandlers(Invoke invoke) {
        switch (invoke.getOperationCode().getLocalOperationCode().intValue()) {
            case MAPOperationCode.unstructuredSS_Request :
                return new MapUnstructuredSSRequestMessageHandler();
            default:
                return null;
        }
    }
}
