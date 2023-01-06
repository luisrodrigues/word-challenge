package com.luiro.wordchallenge.services;

import com.luiro.wordchallenge.domain.RelationshipType;
import com.luiro.wordchallenge.domain.Word;
import com.luiro.wordchallenge.domain.WordRelationship;
import com.luiro.wordchallenge.domain.exceptions.InvalidCharactersException;
import com.luiro.wordchallenge.domain.exceptions.InvalidRelationshipException;
import com.luiro.wordchallenge.repositories.WordRelationshipRepository;
import com.luiro.wordchallenge.repositories.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsService {

    public static final String WORD_REGEX = "[a-zA-Z ]*";

    private final WordRepository wordRepository;
    private final WordRelationshipRepository wordRelationshipRepository;

    public WordsService(WordRepository wordRepository, WordRelationshipRepository wordRelationshipRepository) {
        this.wordRepository = wordRepository;
        this.wordRelationshipRepository = wordRelationshipRepository;
    }

    public void createRelationship(String word1, String word2, RelationshipType relationshipType) throws InvalidCharactersException, InvalidRelationshipException {
        if(!word1.matches(WORD_REGEX) || !word2.matches(WORD_REGEX)) {
            throw new InvalidCharactersException();
        }

        Word w1 = wordRepository.findByName(word1);
        if (w1 == null) {
            w1 = new Word();
            w1.setName(word1.toLowerCase().trim());
        }

        Word w2 = wordRepository.findByName(word2);
        if (w2 == null) {
            w2 = new Word();
            w2.setName(word2.toLowerCase().trim());
        }

        if(wordRelationshipRepository.findByWordAndRelatedWord(w1, w2) != null
                || wordRelationshipRepository.findByWordAndRelatedWord(w2, w1) != null ) {
            throw new InvalidRelationshipException();
        }

        wordRepository.save(w1);
        wordRepository.save(w2);

        WordRelationship relationship = new WordRelationship();
        relationship.setWord(w1);
        relationship.setRelatedWord(w2);
        relationship.setRelationshipType(relationshipType);
        wordRelationshipRepository.save(relationship);
    }

    public List<WordRelationship> getAllWordRelationships() {
        return wordRelationshipRepository.findAll();
    }

    public List<WordRelationship> getAllWordRelationships(String relationshipType) {
        if (relationshipType.isEmpty()) {
            return getAllWordRelationships();
        }
        return wordRelationshipRepository.findAllByRelationshipType(RelationshipType.valueOf(relationshipType));
    }
}
