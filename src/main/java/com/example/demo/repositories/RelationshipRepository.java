package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Relationship;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {
    @Query(value = "SELECT * FROM Account WHERE username NOT IN (SELECT checked_username FROM Relationship WHERE user_username = :username)", nativeQuery = true)
    public List<Object[]> getCandidates(@Param("username") String username);
    
    @Query(value = "SELECT * FROM Relationship WHERE user_username = :checked AND checked_username = :username", nativeQuery = true)
    public Relationship getCandidateRelationship(@Param("username") String username, @Param("checked") String checked);

    @Query(value = "SELECT acc2.* FROM (SELECT acc1.* FROM Account AS acc1 INNER JOIN Relationship AS rel1 ON acc1.username = rel1.checked_username WHERE rel1.user_username = :username AND rel1.liked = 'true') AS acc2 INNER JOIN Relationship AS rel2 ON acc2.username = rel2.user_username WHERE rel2.checked_username = :username AND rel2.liked = 'true'", nativeQuery = true)
    public List<Object[]> getMatches(@Param("username") String username);

    @Modifying
    @Query(value = "DELETE FROM Relationship WHERE user_username = :username OR checked_username = :username", nativeQuery = true)
    public void deleteRelationships(@Param("username") String username);
}