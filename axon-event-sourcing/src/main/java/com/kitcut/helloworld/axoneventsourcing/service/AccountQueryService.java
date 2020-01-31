package com.kitcut.helloworld.axoneventsourcing.service;

import com.kitcut.helloworld.axoneventsourcing.entity.AccountQueryEntity;

import java.util.List;

public interface AccountQueryService {
    List<Object> listEventsForAccount(String accountNumber);

    AccountQueryEntity getAccount(String accountNumber);
}
