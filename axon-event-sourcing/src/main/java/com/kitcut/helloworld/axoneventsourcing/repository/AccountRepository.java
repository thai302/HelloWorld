package com.kitcut.helloworld.axoneventsourcing.repository;

import com.kitcut.helloworld.axoneventsourcing.entity.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
