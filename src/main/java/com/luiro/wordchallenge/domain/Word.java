package com.luiro.wordchallenge.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "word")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "word")
    private List<WordRelationship> relationships;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WordRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<WordRelationship> relationships) {
        this.relationships = relationships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return id.equals(word.id) && name.equals(word.name) && relationships.equals(word.relationships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, relationships);
    }
}