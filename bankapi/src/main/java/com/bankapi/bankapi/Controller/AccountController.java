package com.bankapi.bankapi.Controller;

import org.springframework.web.bind.annotation.*;

import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.services.AccountService;
import com.bankapi.bankapi.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins= "*")
public class AccountController {
    @Autowired
    public AccountService accountService;

    @Autowired
    public UserService userService;
    

    @GetMapping()
    public ArrayList<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping()
    public Account saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }
    @GetMapping(path= "/{number}")
    public Optional<Account> findAccount(@PathVariable Long number){
        return  accountService.findAccount(number);
    }

    @GetMapping(path = "/type/{type}")
    public ArrayList<Account> findByTypeAccount(@PathVariable String type){
        return accountService.findByTypeAccount(type);
    }
    @GetMapping(path = "/user/{id}")
    public List<Account> findByUser(@PathVariable Integer id){
        return accountService.findByUser(userService.findById(id).get());
    }

    @DeleteMapping(path ="/{id}")
    public String DeleteById (@PathVariable Long id){
        boolean ok = accountService.DeleteById(id);
        if(ok){
            return "The Account with number " + id + " was eliminated";
        }else{
            return "Error to eliminate Account with number " + id;
        }
    }
    

}
