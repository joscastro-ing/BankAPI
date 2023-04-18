package com.bankapi.bankapi.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bankapi.bankapi.Model.Transaction;
import com.bankapi.bankapi.services.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins= "*")
public class TransacctionController {
    @Autowired
    public TransactionService transactionService;

    @GetMapping(path = "/{accountNumber}")
    public String getTransaction(@PathVariable Long accountNumber) {
        String transactions = "";
        for(Transaction transaction: transactionService.getTransaction(accountNumber)){
            transactions = transactions + transaction.toString() + "\n";
        }
        return transactions;
        
    }

    @PostMapping()
    public String makeTransaction(@RequestBody Transaction transaction) {
        boolean succesful = transactionService.makeTransaction(transaction);
        if(succesful){
            return "Transaction succesfull \n" + transaction.toString();
        }else{
            return "Invalid Transaction";
        }
    }
}
