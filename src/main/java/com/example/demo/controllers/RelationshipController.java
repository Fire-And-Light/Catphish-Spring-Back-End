package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Account;
import com.example.demo.models.Relationship;
import com.example.demo.services.RelationshipService;

@RestController 
@CrossOrigin
public class RelationshipController {
    private RelationshipService rs;

    @Autowired
    public RelationshipController(RelationshipService rs) {
        super();
        this.rs = rs;
    }

    @GetMapping("match/{username}")
    public Account getCandidate(@PathVariable("username") String username) {
        return this.rs.getCandidate(username);
    }

    @PostMapping("match/{username}")
    public String checkCandidate(@RequestBody Relationship rel) {
        return this.rs.checkCandidate(rel);
    }

    @GetMapping("matches/{username}")
    public List<Account> getMatches(@PathVariable("username") String username) {
        return this.rs.getMatches(username);
    }
}