package com.luiro.wordchallenge.controllers.dto;

import com.luiro.wordchallenge.domain.WordRelationship;

public class WordRelationshipDTO {

    private WordRelationship wordRelationship;
    private boolean isInverse;

    public WordRelationshipDTO(WordRelationship wordRelationship, boolean isInverse) {
        this.wordRelationship = wordRelationship;
        this.isInverse = isInverse;
    }

    public WordRelationship getWordRelationship() {
        return wordRelationship;
    }

    public void setWordRelationship(WordRelationship wordRelationship) {
        this.wordRelationship = wordRelationship;
    }

    public boolean isInverse() {
        return isInverse;
    }

    public void setInverse(boolean inverse) {
        isInverse = inverse;
    }
}
