package com.kitcut.entity;

import java.io.Serializable;

public class Vt implements Serializable {
    private int hdId;
    private int vtId;
    private String tenVt;

//    public Vt(){}
//
//    public Vt(int hdId, int vtId, String tenVt) {
//        this.hdId = hdId;
//        this.vtId = vtId;
//        this.tenVt = tenVt;
//    }

    public int getHdId() {
        return hdId;
    }

    public void setHdId(int hdId) {
        this.hdId = hdId;
    }

    public int getVtId() {
        return vtId;
    }

    public void setVtId(int vtId) {
        this.vtId = vtId;
    }

    public String getTenVt() {
        return tenVt;
    }

    public void setTenVt(String tenVt) {
        this.tenVt = tenVt;
    }
}
