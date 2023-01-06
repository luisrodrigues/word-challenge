package com.luiro.wordchallenge.repositories;

import com.luiro.wordchallenge.domain.RelationshipType;
import com.luiro.wordchallenge.domain.WordRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRelationshipRepository extends JpaRepository<WordRelationship, Long> {
    List<WordRelationship> findAllByRelationshipType(RelationshipType type);
}
