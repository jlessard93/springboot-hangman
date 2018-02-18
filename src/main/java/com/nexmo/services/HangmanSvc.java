package com.nexmo.services;

import javax.servlet.http.HttpServletRequest;

import com.nexmo.dto.AnswerDto;
import com.nexmo.dto.HangmanDto;
import com.nexmo.exceptions.HangmanException;

public interface HangmanSvc {
	
	public HangmanDto defaultGame(HttpServletRequest request);
	public String getWordById(Long id);
	public void processAnswer(AnswerDto answerDto) throws HangmanException;
	
	public HangmanDto anotherGame(HangmanDto oldHangmanDto, HttpServletRequest request);
	public void updateHangman(HangmanDto oldHangmanDto);
}
