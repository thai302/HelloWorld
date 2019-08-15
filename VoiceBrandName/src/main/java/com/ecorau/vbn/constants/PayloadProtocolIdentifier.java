package com.ecorau.vbn.constants;

//https://www.iana.org/assignments/sctp-parameters/sctp-parameters.xhtml#sctp-parameters-25
public enum PayloadProtocolIdentifier {
    RESERVED_BY_SCTP(0),
    IUA(1),
    M2UA(2),
    M3UA(3),
    SUA(4),
    M2PA(5),
    V5UA(6);

    private int value;

    PayloadProtocolIdentifier(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
