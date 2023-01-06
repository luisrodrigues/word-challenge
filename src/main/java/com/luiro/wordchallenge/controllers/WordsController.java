package com.luiro.wordchallenge.controllers;

import com.luiro.wordchallenge.domain.RelationshipType;
import com.luiro.wordchallenge.services.WordsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/words")
public class WordsController {

    private final WordsService wordsService;

    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @PostMapping("/relationships")
    public ResponseEntity<Object> createRelationship(@RequestParam String word1,
                                                     @RequestParam String word2,
                                                     @RequestParam RelationshipType relationshipType) {
        if (word1.isEmpty() || word2.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        wordsService.createRelationship(word1, word2, relationshipType);
        return ResponseEntity.ok().build();
    }
}
