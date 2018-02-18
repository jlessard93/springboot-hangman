package com.nexmo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexmo.dto.AnswerDto;
import com.nexmo.dto.HangmanDto;
import com.nexmo.exceptions.HangmanException;
import com.nexmo.services.HangmanSvc;

@Controller
@RequestMapping("/api/v1/hangman")
public class HangmanCtrl {
	
	@Autowired
	HangmanSvc hangmanSvc;
	
	@RequestMapping(value={"newgame"}, method = {RequestMethod.GET})
	@ResponseBody
	public HangmanDto hangman(HttpServletRequest request, HttpSession session) {
		return hangmanSvc.defaultGame(request);
	}
	
	@RequestMapping(value={"anothergame"}, method = {RequestMethod.POST})
	@ResponseBody
	public HangmanDto anotherHangman(@RequestBody HangmanDto oldHangmanDto, HttpServletRequest request) {
		return hangmanSvc.anotherGame(oldHangmanDto, request);
	}
	
	@RequestMapping(value={"guessword"}, method = {RequestMethod.POST}, consumes="application/json")
	@ResponseBody
	public AnswerDto guessWord(@RequestBody AnswerDto answerDto) throws HangmanException {
		hangmanSvc.processAnswer(answerDto);
		return answerDto;
	}
	
	@RequestMapping(value={"updategame"}, method = {RequestMethod.POST}, consumes="application/json")
	@ResponseBody
	public void updateHangmanStat(@RequestBody HangmanDto oldHangmanDto) {
		hangmanSvc.updateHangman(oldHangmanDto);
	}
	
	
}









