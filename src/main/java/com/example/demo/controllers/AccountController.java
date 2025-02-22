package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Account;
import com.example.demo.services.AccountService;

@RestController 
@CrossOrigin
public class AccountController {
    private AccountService as;

    @Autowired
    public AccountController(AccountService as) {
        super();
        this.as = as;
    }

    @PostMapping("users")
    public String signUp(@RequestBody Account user) {
        return this.as.signUp(user.getUsername(), user.getPassword());
    }

    @PostMapping("users/{username}")
    public String signIn(@RequestBody Account user) {
        return this.as.signIn(user.getUsername(), user.getPassword());
    }

    @GetMapping("users/{username}")
    public Account getProfile(@PathVariable("username") String username) {
        return this.as.getProfile(username);
    }
    
    @PutMapping("users/{username}")
    public void saveProfile(@RequestBody Account user) {
        this.as.saveProfile(user);
    }

    @DeleteMapping("users/{username}")
    public void deleteAccount(@PathVariable("username") String username) {
        this.as.deleteAccount(username);
    }
}