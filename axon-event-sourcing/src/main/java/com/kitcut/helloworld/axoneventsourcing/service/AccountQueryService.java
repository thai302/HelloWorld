package com.kitcut.helloworld.axoneventsourcing.service;

import java.util.List;

public interface AccountQueryService {
    List<Object> listEventsForAccount(String accountNumber);
}
