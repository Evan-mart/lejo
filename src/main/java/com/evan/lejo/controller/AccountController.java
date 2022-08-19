package com.evan.lejo.controller;

import com.evan.lejo.exception.ResourceNotFoundException;
import com.evan.lejo.model.Account;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    protected final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        accountRepository.save(account);

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {

        List<Account> accounts = new ArrayList<>(accountRepository.findAll());

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccounts(@PathVariable("id") Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found for id : " + id));

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        Account _account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found account for id : " + id));

        _account.setUserName(account.getUserName());
        _account.setPassword(account.getPassword());

        return new ResponseEntity<>(accountRepository.save(_account), HttpStatus.OK);
    }

}





































