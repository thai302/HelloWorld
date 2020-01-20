package com.kitcut.helloworld.axoneventsourcing.service.impl;

import com.kitcut.helloworld.axoneventsourcing.command.CreateAccountCommand;
import com.kitcut.helloworld.axoneventsourcing.command.CreditMoneyCommand;
import com.kitcut.helloworld.axoneventsourcing.command.DebitMoneyCommand;
import com.kitcut.helloworld.axoneventsourcing.dto.AccountCreateDTO;
import com.kitcut.helloworld.axoneventsourcing.dto.MoneyCreditDTO;
import com.kitcut.helloworld.axoneventsourcing.dto.MoneyDebitDTO;
import com.kitcut.helloworld.axoneventsourcing.service.AccountCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        CreateAccountCommand createAccountCommand = new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency());
        CompletableFuture<String> resutl = commandGateway.send(createAccountCommand);
        return resutl;
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}