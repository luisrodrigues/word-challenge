package com.luiro.wordchallenge.controllers;

import com.luiro.wordchallenge.controllers.dto.WordRelationshipDTO;
import com.luiro.wordchallenge.domain.RelationshipType;
import com.luiro.wordchallenge.domain.WordRelationship;
import com.luiro.wordchallenge.domain.exceptions.InvalidCharactersException;
import com.luiro.wordchallenge.domain.exceptions.InvalidRelationshipException;
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

    @PostMapping("/relationships")
    public ResponseEntity<Object> createRelationship(@RequestBody WordRelationshipDTO wordRelationshipDTO) throws InvalidCharactersException, InvalidRelationshipException {
        if (wordRelationshipDTO.getWordOne().isEmpty() || wordRelationshipDTO.getWordTwo().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        wordsService.createRelationship(wordRelationshipDTO.getWordOne(), wordRelationshipDTO.getWordTwo(), RelationshipType.valueOf(wordRelationshipDTO.getRelationshipType()));
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
