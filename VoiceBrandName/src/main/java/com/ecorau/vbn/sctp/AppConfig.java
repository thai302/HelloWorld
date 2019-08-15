package com.ecorau.vbn.sctp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConfig {
    private String remoteIp;
    private int remotePort;
    private String localIp;
    private int localPort;
}
