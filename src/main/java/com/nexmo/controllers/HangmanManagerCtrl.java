package com.nexmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexmo.dto.HangmanMgmtDto;
import com.nexmo.entities.HangmanWord;
import com.nexmo.services.HangmanManagerSvc;


@Controller
@RequestMapping("/api/v1/hangman/management")
public class HangmanManagerCtrl {
	
	@Autowired
	private HangmanManagerSvc hangmanManagerSvc;
	
	
	@RequestMapping(value={"allstartedgames"}, method = {RequestMethod.GET})
	@ResponseBody
	public HangmanMgmtDto getAllStartedGames() {
		HangmanMgmtDto hangmanMgmtDto = hangmanManagerSvc.getAllStartedGames();
		return hangmanMgmtDto;
	}
	
	@RequestMapping(value={"allstatistics"}, method = {RequestMethod.GET})
	@ResponseBody
	public HangmanMgmtDto getAllStatistics() {
		return hangmanManagerSvc.getAllGameStat();
	}
	
	@RequestMapping(value={"addguessword"}, method = {RequestMethod.POST})
	@ResponseBody
	public void createGuessWord(@RequestBody HangmanMgmtDto hangmanMgmtDto) {
		HangmanWord hangmanWord = hangmanMgmtDto.getHangmanWord();
		hangmanManagerSvc.createGuessWord(hangmanWord);
	}
}





