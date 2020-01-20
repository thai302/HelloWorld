package com.kitcut.helloworld.axoneventsourcing.event;

import com.kitcut.helloworld.axoneventsourcing.Status;

public class AccountActivatedEvent extends BaseEvent<String> {

    public final Status status;

    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
