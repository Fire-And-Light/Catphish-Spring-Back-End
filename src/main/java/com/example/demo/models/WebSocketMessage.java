package com.example.demo.models;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WebSocketMessage {
    private Relationship relationship;
    private Message message;
    private String choice;
}