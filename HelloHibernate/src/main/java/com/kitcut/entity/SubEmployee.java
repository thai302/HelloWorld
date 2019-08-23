package com.kitcut.entity;


import javax.persistence.*;

//@Table(name = "SUB_EMPLOYEE")
public class SubEmployee {
    @Column(name = "sub_id")
    private int subId;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }
}
