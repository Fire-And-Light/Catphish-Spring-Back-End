package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Query(value = "SELECT * FROM Message WHERE (author_username = :author AND recipient_username = :recipient) OR (author_username = :recipient AND recipient_username = :author)", nativeQuery = true)
    public List<Message> getMessages(@Param("author") String author, @Param("recipient") String recipient);

    @Modifying
    @Query(value = "DELETE FROM Message WHERE author_username = :username OR recipient_username = :username", nativeQuery = true)
    public void deleteMessages(@Param("username") String username);
}