package com.luiro.wordchallenge.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "word_relationship")
public class WordRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    @ManyToOne
    @JoinColumn(name = "related_word_id")
    private Word relatedWord;

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship_type")
    private RelationshipType relationshipType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getRelatedWord() {
        return relatedWord;
    }

    public void setRelatedWord(Word relatedWord) {
        this.relatedWord = relatedWord;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRelationship that = (WordRelationship) o;
        return id.equals(that.id) && word.equals(that.word) && relatedWord.equals(that.relatedWord) && relationshipType == that.relationshipType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, relatedWord, relationshipType);
    }
}
