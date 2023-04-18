package com.bankapi.bankapi.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bankapi.bankapi.Model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findOneByEmail(String email);

    // Optional<User> findByUsername(String email);

}
