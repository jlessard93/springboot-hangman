package com.nexmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexmo.entities.HangmanWord;

public interface HangmanWordRepository extends JpaRepository<HangmanWord, Long>{

}
