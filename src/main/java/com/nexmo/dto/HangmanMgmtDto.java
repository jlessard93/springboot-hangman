package com.nexmo.dto;

import java.util.List;

import com.nexmo.entities.HangmanMgmt;
import com.nexmo.entities.HangmanWord;

public class HangmanMgmtDto {
	
	public Long numberOfStartedGames;
	
	public Long numberOfAllGamesPlayed;
	
	public Long numberOfGamesWon;
	
	public Long numberOfGamesLost;
	
	public Long numberOfPlayerStatUnknown;
	
	List<HangmanMgmt> allReports;
	
	public HangmanWord hangmanWord;

	public Long getNumberOfStartedGames() {
		return numberOfStartedGames;
	}

	public void setNumberOfStartedGames(Long numberOfStartedGames) {
		this.numberOfStartedGames = numberOfStartedGames;
	}

	public Long getNumberOfAllGamesPlayed() {
		return numberOfAllGamesPlayed;
	}

	public void setNumberOfAllGamesPlayed(Long numberOfAllGamesPlayed) {
		this.numberOfAllGamesPlayed = numberOfAllGamesPlayed;
	}

	public Long getNumberOfGamesWon() {
		return numberOfGamesWon;
	}

	public void setNumberOfGamesWon(Long numberOfGamesWon) {
		this.numberOfGamesWon = numberOfGamesWon;
	}

	public Long getNumberOfGamesLost() {
		return numberOfGamesLost;
	}

	public void setNumberOfGamesLost(Long numberOfGamesLost) {
		this.numberOfGamesLost = numberOfGamesLost;
	}

	public Long getNumberOfPlayerStatUnknown() {
		return numberOfPlayerStatUnknown;
	}

	public void setNumberOfPlayerStatUnknown(Long numberOfPlayerStatUnknown) {
		this.numberOfPlayerStatUnknown = numberOfPlayerStatUnknown;
	}

	public List<HangmanMgmt> getAllReports() {
		return allReports;
	}

	public void setAllReports(List<HangmanMgmt> allReports) {
		this.allReports = allReports;
	}

	public HangmanWord getHangmanWord() {
		return hangmanWord;
	}

	public void setHangmanWord(HangmanWord hangmanWord) {
		this.hangmanWord = hangmanWord;
	}
}
