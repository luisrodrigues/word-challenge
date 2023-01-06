package com.luiro.wordchallenge.controllers;

import com.luiro.wordchallenge.domain.RelationshipType;
import com.luiro.wordchallenge.domain.WordRelationship;
import com.luiro.wordchallenge.services.WordsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordsController {

    private final WordsService wordsService;

    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    // Request params here should be an object passed to the body instead
    @PostMapping("/relationships")
    public ResponseEntity<Object> createRelationship(@RequestParam String w1,
                                                     @RequestParam String w2,
                                                     @RequestParam String r) {
        if (w1.isEmpty() || w2.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        wordsService.createRelationship(w1, w2, RelationshipType.valueOf(r));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relationships")
    public ResponseEntity<List<WordRelationship>> getAllWordRelationships(@RequestParam String r) {
        List<WordRelationship> wordRelationships = wordsService.getAllWordRelationships(r);
        if (wordRelationships.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wordRelationships, HttpStatus.OK);
    }
}
