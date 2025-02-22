package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Message;
import com.example.demo.services.MessageService;

@RestController 
@CrossOrigin
public class MessageController {
    private MessageService ms;

    @Autowired
    public MessageController(MessageService ms) {
        super();
        this.ms = ms;
    }

    @GetMapping("messages/{author}-and-{recipient}")
    public List<Message> getMessages(@PathVariable("author") String author, @PathVariable("recipient") String recipient) {
        return this.ms.getMessages(author, recipient);
    }
}