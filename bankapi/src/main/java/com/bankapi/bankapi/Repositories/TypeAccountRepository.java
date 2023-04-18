package com.bankapi.bankapi.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bankapi.bankapi.Model.TypeAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAccountRepository extends CrudRepository<TypeAccount, Integer> {
    public abstract Optional<TypeAccount> findByType(String type);
}
