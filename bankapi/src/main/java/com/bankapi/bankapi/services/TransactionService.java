package com.bankapi.bankapi.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.bankapi.Model.Transaction;
import com.bankapi.bankapi.Repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    public TransactionRepository transactionRepository;
    @Autowired
    public AccountService accountService;

    public ArrayList<Transaction> getTransaction(Long accountNumber) {
        return (ArrayList<Transaction>) this.transactionRepository
                .findBysourceAccount(accountService.findAccount(accountNumber));
    }

    public Transaction saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        return transaction;
    }

    public boolean makeTransaction(Transaction transaction) {
        transaction.setDepositAccount(accountService.findAccount(transaction.getDepositAccount().getAccountNumber()).get());
        transaction.setSourceAccount(accountService.findAccount(transaction.getSourceAccount().getAccountNumber()).get());
        if(accountService.removeMoney(transaction.getSourceAccount(), transaction.getAmount())){
            accountService.deposite(transaction.getDepositAccount(), transaction.getAmount());
            saveTransaction(transaction);
            return true;
        }else{
            return false;
        }
    }
    public boolean DeleteById(Integer id) {
        try {
            this.transactionRepository.deleteById(id);
            return true;

        } catch (Error e) {
            return false;
        }
    }
}
