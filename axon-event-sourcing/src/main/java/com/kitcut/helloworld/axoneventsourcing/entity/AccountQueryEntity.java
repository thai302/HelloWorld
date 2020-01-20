package com.kitcut.helloworld.axoneventsourcing.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AccountQueryEntity {
    @Id
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    public AccountQueryEntity() {
    }
}
