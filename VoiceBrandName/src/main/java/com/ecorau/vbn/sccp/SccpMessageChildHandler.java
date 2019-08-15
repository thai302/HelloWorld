package com.ecorau.vbn.sccp;

import com.ecorau.vbn.RequestContex;

public interface SccpMessageChildHandler {
    void process(RequestContex requestContex);
}
