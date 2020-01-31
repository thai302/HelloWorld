package com.kitcut.helloworld.axoneventsourcing.repository;

import com.kitcut.helloworld.axoneventsourcing.entity.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
