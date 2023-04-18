package com.bankapi.bankapi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bankapi.bankapi.services.AccountService;
import com.bankapi.bankapi.services.TransactionService;
import com.bankapi.bankapi.services.UserService;
import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.Model.Transaction;
import com.bankapi.bankapi.Model.User;

@RestController
@RequestMapping(value= "/user", produces = "application/json")
@CrossOrigin(origins= "*")
public class UserController {
    @Autowired
    public UserService userService;

    @Autowired
    public AccountService accountService;

    @Autowired
    public TransactionService transactionService;

    @GetMapping() 
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "/{email}") 
    public User getByEmail(@PathVariable String email){
        return userService.findByEmail(email).get();
    }
    @PostMapping("/admins")
    public User saveUserAdmin(@RequestBody User user){
        user.setRole("ADMIN");
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userService.saveUser(user);
    }

    @PostMapping()
    public User saveUser(@RequestBody User user){
        user.setRole("USER");
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userService.saveUser(user);
    }
    @DeleteMapping(path ="/{id}")
    public String DeleteById (@PathVariable Integer id){
        List<Account> accounts = accountService.findByUser(userService.findById(id).get());
        for(Account account: accounts){
            List<Transaction> trans = transactionService.getTransaction(account.getAccountNumber());
            for(Transaction transaction: trans){
                transactionService.DeleteById(transaction.getIdTransaction());
            }
            accountService.DeleteById(account.getAccountNumber());
        }
        boolean ok = userService.DeleteById(id);
        if(ok){
            return "Se elimino el usuario con el id " + id;
        }else{
            return "Error al eliminar el usuario con el id " + id;
        }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
