package com.ecorau.vbn.constants;

public enum M3uaUpperLayer {
    SCCP(3),
    ISUP(6);

    private int value;

    M3uaUpperLayer(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
