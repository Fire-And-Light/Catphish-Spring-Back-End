package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Account;
import com.example.demo.models.Relationship;
import com.example.demo.repositories.RelationshipRepository;

@Service
public class RelationshipService {
    private RelationshipRepository rr;

    @Autowired
    public RelationshipService(RelationshipRepository rr) {
        super();
        this.rr = rr;
    }

    @Transactional
    public Account getCandidate(String username) {
        List<Object[]> candidates = this.rr.getCandidates(username);
        
        if (candidates.isEmpty()) {
            return null;

        } else {
            SecureRandom randy = new SecureRandom();
            Object[] obj = candidates.get(randy.nextInt(candidates.size()));
            String candidateName = (String) obj[0];
            String candidateBio = (String) obj[1];
            String candidatePic = (String) obj[5];

            Account candidate = new Account();

            candidate.setUsername(candidateName);
            candidate.setBio(candidateBio);

            try { 
                File file = new File(candidatePic);
                byte[] fileContent = Files.readAllBytes(file.toPath());
                candidate.setPictureBlob(Base64.getEncoder().encodeToString(fileContent));

            } catch (IOException e) {
                e.printStackTrace();
            }

            return candidate;
        }
    }

    @Transactional
    public String checkCandidate(Relationship rel) {
        this.rr.save(rel);

        boolean liked = rel.getLiked().equals("true");
        Relationship candidateRel = this.rr.getCandidateRelationship(rel.getUser().getUsername(), rel.getChecked().getUsername());
        
        if (candidateRel == null) {
            return "Pending";

        } else {
            boolean candidateLiked = candidateRel.getLiked().equals("true");

            if (liked && candidateLiked) {
                return "Matched";

            } else {
                return "Mismatch";
            }
        }
    }

    @Transactional
    public List<Account> getMatches(String username) {
        List<Object[]> objMatches = this.rr.getMatches(username);
        List<Account> matches = new ArrayList<Account>();

        for (Object[] matchObj : objMatches) {
            String matchName = (String) matchObj[0];
            String matchBio = (String) matchObj[1];
            String matchPic = (String) matchObj[5];

            Account match = new Account();

            match.setUsername(matchName);
            match.setBio(matchBio);

            try { 
                File file = new File(matchPic);
                byte[] fileContent = Files.readAllBytes(file.toPath());
                match.setPictureBlob(Base64.getEncoder().encodeToString(fileContent));

            } catch (IOException e) {
                e.printStackTrace();
            }

            matches.add(match);
        }

        return matches;
    }

    @Transactional
    public void unmatch(Relationship rel) {
        Relationship user = this.rr.getCandidateRelationship(rel.getUser().getUsername(), rel.getChecked().getUsername());
        Relationship checked = this.rr.getCandidateRelationship(rel.getChecked().getUsername(), rel.getUser().getUsername());

        user.setLiked("false");
        checked.setLiked("false");

        this.rr.save(user);
        this.rr.save(checked);
    }
}