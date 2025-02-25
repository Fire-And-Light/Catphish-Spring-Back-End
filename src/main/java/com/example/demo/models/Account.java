package com.example.demo.models;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Account {
    @Transient
    public static int MAX_NAM_LEN = 20;

    @Id
    @Column(length = 20)
    private String username;
    @Column(length = 32)
    private String passwordSalt;
    @Column(length = 384)
    private String passwordHash;
    private String picturePath;
    private String bio;
    private LocalDate dateCreated;

    @Transient
    private String password;
    @Transient
    private String pictureBlob;
}