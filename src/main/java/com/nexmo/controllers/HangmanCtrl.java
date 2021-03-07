package com.nexmo.controllers;

import com.nexmo.dto.AnswerDto;
import com.nexmo.dto.HangmanDto;
import com.nexmo.exceptions.HangmanException;
import com.nexmo.services.HangmanSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/v1/hangman")
public class HangmanCtrl {

	@Autowired
	HangmanSvc hangmanSvc;

	@GetMapping(value = {"newgame"})
	@ResponseBody
	public HangmanDto hangman(HttpServletRequest request, HttpSession session) {
		return hangmanSvc.defaultGame(request);
	}

	@PostMapping(value = {"anothergame"})
	@ResponseBody
	public HangmanDto anotherHangman(@RequestBody HangmanDto oldHangmanDto, HttpServletRequest request) {
		return hangmanSvc.anotherGame(oldHangmanDto, request);
	}

	@PostMapping(value = {"guessword"}, consumes = "application/json")
	@ResponseBody
	public AnswerDto guessWord(@RequestBody AnswerDto answerDto) throws HangmanException {
		hangmanSvc.processAnswer(answerDto);
		return answerDto;
	}

	@PostMapping(value = {"updategame"}, consumes = "application/json")
	@ResponseBody
	public void updateHangmanStat(@RequestBody HangmanDto oldHangmanDto) {
		hangmanSvc.updateHangman(oldHangmanDto);
	}


}









