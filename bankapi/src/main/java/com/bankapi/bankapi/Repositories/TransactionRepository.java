package com.bankapi.bankapi.Repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.Model.Transaction;
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
    
    public abstract ArrayList<Transaction> findBysourceAccount(Optional<Account> account);
}
