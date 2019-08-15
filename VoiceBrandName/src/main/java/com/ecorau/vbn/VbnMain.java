package com.ecorau.vbn;

import com.ecorau.vbn.sctp.SctpClient;

public class VbnMain {
    public static void main(String[] args) throws Exception{
        SctpClient.getInstance().connect();
    }
}
