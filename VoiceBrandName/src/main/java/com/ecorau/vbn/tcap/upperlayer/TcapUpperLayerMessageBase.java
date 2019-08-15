package com.ecorau.vbn.tcap.upperlayer;

import com.ecorau.vbn.RequestContex;

public class TcapUpperLayerMessageBase {
    protected RequestContex requestContex;

    public TcapUpperLayerMessageBase(RequestContex requestContex) {
        this.requestContex = requestContex;
    }
}
