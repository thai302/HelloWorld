package com.kitcut.helloworld.axoneventsourcing.event;

import com.kitcut.helloworld.axoneventsourcing.Status;

public class AccountHeldEvent extends BaseEvent<String> {

    public final Status status;

    public AccountHeldEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
