package com.kitcut.helloworld.axoneventsourcing.service.impl;

import com.kitcut.helloworld.axoneventsourcing.entity.AccountQueryEntity;
import com.kitcut.helloworld.axoneventsourcing.repository.AccountRepository;
import com.kitcut.helloworld.axoneventsourcing.service.AccountQueryService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final EventStore eventStore;

    @Autowired
    private AccountRepository accountRepository;

    public AccountQueryServiceImpl(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
    }

    @Override
    public AccountQueryEntity getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }
}
