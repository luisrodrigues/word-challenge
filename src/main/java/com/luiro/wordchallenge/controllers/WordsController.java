package com.luiro.wordchallenge.controllers;

import com.luiro.wordchallenge.controllers.dto.CreateRelationshipDTO;
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
    public ResponseEntity<Object> createRelationship(@RequestBody CreateRelationshipDTO createRelationshipDTO) throws InvalidCharactersException, InvalidRelationshipException {
        if (createRelationshipDTO.getWordOne().isEmpty() || createRelationshipDTO.getWordTwo().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        wordsService.createRelationship(createRelationshipDTO.getWordOne(), createRelationshipDTO.getWordTwo(), RelationshipType.valueOf(createRelationshipDTO.getRelationshipType()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/relationships")
    public ResponseEntity<List<?>> getAllWordRelationships(@RequestParam String r, @RequestParam boolean showInverse) {
        List<WordRelationship> wordRelationships = wordsService.getAllWordRelationships(r);
        if (wordRelationships.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if(showInverse) {
            return new ResponseEntity<>(addInverseRelationships(wordRelationships), HttpStatus.OK);
        }

        return new ResponseEntity<>(wordRelationships, HttpStatus.OK);
    }

    private List<WordRelationshipDTO> addInverseRelationships(List<WordRelationship> wordRelationships) {
        /*
        This is a desperate attempt to finish the challenge so not the most elegant solution
        What I would do here:
        - Create a new list (output list) made of  WordRelationshipDTO objects (returned at the end of this method)
        - Loop every item on the input list
        - For every item on the input list, if the relationship type is either 'synonym' or 'antonym' I would add a copy item to the
        output list which would be the counterpart (reverse the word order, reverse relationship type and add 'true' to is inverse attribute)
        - Return list
        */

        return null;
    }
}
