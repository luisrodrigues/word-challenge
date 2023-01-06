package com.luiro.wordchallenge.controllers.dto;

public class WordRelationshipDTO {
    private String wordOne;
    private String wordTwo;
    private String relationshipType;

    public WordRelationshipDTO(String wordOne, String wordTwo, String relationshipType) {
        this.wordOne = wordOne;
        this.wordTwo = wordTwo;
        this.relationshipType = relationshipType;
    }

    public String getWordOne() {
        return wordOne;
    }

    public String getWordTwo() {
        return wordTwo;
    }

    public String getRelationshipType() {
        return relationshipType;
    }
}
