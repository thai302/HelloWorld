package com.ecorau.vbn.m3ua.upperlayer;

import com.ecorau.vbn.RequestContex;
import org.restcomm.protocols.ss7.m3ua.parameter.ProtocolData;

public interface M3uaUpperLayerMessageHandler {
    void process(ProtocolData protocolData, RequestContex requestContex);
}
