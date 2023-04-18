package com.bankapi.bankapi.Repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.bankapi.bankapi.Model.Account;
import com.bankapi.bankapi.Model.TypeAccount;
import com.bankapi.bankapi.Model.User;
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    public abstract ArrayList<Account> findByTypeAcc(Optional<TypeAccount> type);

    public abstract ArrayList<Account> findByUser(User user);
}
