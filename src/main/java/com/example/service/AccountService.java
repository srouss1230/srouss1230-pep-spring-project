package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerUser(Account acct){
        boolean usernameBlank = (acct.getUsername() == "");
        boolean passwordTooShort = (acct.getPassword().length() < 4);
        boolean userNameTaken = (accountRepository.findByUsername(acct.getUsername()) != null);

        if(usernameBlank || passwordTooShort || userNameTaken){
            return null;
        }

        return accountRepository.save(acct);
    }
}
