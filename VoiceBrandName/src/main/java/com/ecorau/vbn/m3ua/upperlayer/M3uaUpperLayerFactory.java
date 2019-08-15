package com.ecorau.vbn.m3ua.upperlayer;

import com.ecorau.vbn.constants.M3uaUpperLayer;
import com.ecorau.vbn.sccp.SccpMessageHandler;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;

public class M3uaUpperLayerFactory {
    public static M3uaUpperLayerMessageHandler getM3uaUpperLayer(ProtocolData protocolData) {
        if (protocolData.getSI() == M3uaUpperLayer.SCCP.value())
            return new SccpMessageHandler();
        else
            return null;
    }
}
