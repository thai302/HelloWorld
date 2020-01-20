package com.kitcut.helloworld.axoneventsourcing.service;

import com.kitcut.helloworld.axoneventsourcing.dto.AccountCreateDTO;
import com.kitcut.helloworld.axoneventsourcing.dto.MoneyCreditDTO;
import com.kitcut.helloworld.axoneventsourcing.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

public interface AccountCommandService {

    CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);

    CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);

    CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
