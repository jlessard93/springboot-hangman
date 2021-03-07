package com.nexmo.controllers;

import com.nexmo.dto.HangmanMgmtDto;
import com.nexmo.entities.HangmanWord;
import com.nexmo.services.HangmanManagerSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/v1/hangman/management")
public class HangmanManagerCtrl {

	@Autowired
	private HangmanManagerSvc hangmanManagerSvc;


	@GetMapping(value = {"allstartedgames"})
	@ResponseBody
	public HangmanMgmtDto getAllStartedGames() {
		return hangmanManagerSvc.getAllStartedGames();
	}

	@GetMapping(value = {"allstatistics"})
	@ResponseBody
	public HangmanMgmtDto getAllStatistics() {
		return hangmanManagerSvc.getAllGameStat();
	}

	@PostMapping(value = {"addguessword"})
	@ResponseBody
	public void createGuessWord(@RequestBody HangmanMgmtDto hangmanMgmtDto) {
		HangmanWord hangmanWord = hangmanMgmtDto.getHangmanWord();
		hangmanManagerSvc.createGuessWord(hangmanWord);
	}
}





