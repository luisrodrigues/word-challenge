package com.luiro.wordchallenge.repositories;

import com.luiro.wordchallenge.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    Word findByName(String name);

}
