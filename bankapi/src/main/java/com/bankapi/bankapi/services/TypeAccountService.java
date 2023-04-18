package com.bankapi.bankapi.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.bankapi.Model.TypeAccount;
import com.bankapi.bankapi.Repositories.TypeAccountRepository;

@Service
public class TypeAccountService {
    @Autowired
    public TypeAccountRepository typeAccountRepository;

    public ArrayList<TypeAccount> getTypes(){
        return (ArrayList<TypeAccount>) typeAccountRepository.findAll();
    }

    public Optional<TypeAccount> findById(Integer id){
        return typeAccountRepository.findById(id);
    }

    public TypeAccount saveType(TypeAccount type){
        return typeAccountRepository.save(type);
    }

    public Optional<TypeAccount> findType(String type){
        return typeAccountRepository.findByType(type);
    }
    
    public boolean DeleteById(Integer id){
        try {
            this.typeAccountRepository.deleteById(id);
            return true;

        } catch (Error e) {
            return false;
        }
    }
}
