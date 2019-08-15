package com.ecorau.vbn.tcap;

import com.ecorau.vbn.RequestContex;

public class TcapMessageBase {
    protected RequestContex requestContex;

    public TcapMessageBase(RequestContex requestContex) {
        this.requestContex = requestContex;
    }
}
