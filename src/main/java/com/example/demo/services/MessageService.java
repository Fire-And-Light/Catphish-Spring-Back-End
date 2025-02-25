package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Account;
import com.example.demo.models.Message;
import com.example.demo.repositories.MessageRepository;

@Service
public class MessageService {
    private MessageRepository mr;

    @Autowired
    public MessageService(MessageRepository mr) {
        super();
        this.mr = mr;
    }

    @Transactional
    public List<Message> getMessages(String author, String recipient) {
        List<Message> messages = new ArrayList<Message>();
        
        for (Message message : this.mr.getMessages(author, recipient)) {
            Message messageJSON = new Message();
            Account authorJSON = new Account();
            Account recipientJSON = new Account();
            
            authorJSON.setUsername(message.getAuthor().getUsername());
            recipientJSON.setUsername(message.getAuthor().getUsername());
            messageJSON.setAuthor(authorJSON);
            messageJSON.setRecipient(recipientJSON);
            messageJSON.setMessage(message.getMessage());

            messages.add(messageJSON);
        }

        return messages;
    }

    @Transactional
    public void sendMessage(Message message) {
        this.mr.save(message);
    }
}