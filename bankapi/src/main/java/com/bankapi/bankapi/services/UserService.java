package com.bankapi.bankapi.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.bankapi.Model.User;
import com.bankapi.bankapi.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        return this.userRepository.findOneByEmail(email);
    }

    public Optional<User> findById(Integer id){
        return this.userRepository.findById(id);
    }

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public boolean DeleteById(Integer id) {
        try {
            this.userRepository.deleteById(id);
            return true;

        } catch (Error e) {
            return false;
        }
    }

}
