package com.nexmo.services;

import com.nexmo.dto.HangmanMgmtDto;
import com.nexmo.entities.HangmanMgmt;
import com.nexmo.entities.HangmanWord;
import com.nexmo.entities.PlayerData;

import java.util.List;

public interface HangmanManagerSvc {
	
	public void createGuessWord(HangmanWord hangmanWord);
	public void deleteGuessWordById(Long id);
	public HangmanWord getGuessWordById(Long id);
	public List<HangmanWord> getGuessWordListByCategory(String category);
	public List<HangmanWord> getAllGuessWordList();
	public void deleteGuessWordByCategory(String category);
	
	public Long createGameStat(HangmanMgmt hangmanMgmt);
	public void deleteGameStatById(Long id);
	public void updateStopGameStat(HangmanMgmt hangmanMgmt);
	
	public HangmanMgmtDto getAllStartedGames();
	public HangmanMgmtDto getAllGameStat();
	
	public void createPlayerData(String sessionId, Long hangmanMgmtId);
	public PlayerData existingActivePlayer(String sessionId);
	public void registerGuessedLetters(String sessionId, String newGuessedLetter);
	public void registerUsedChances(String sessionId);
	public void registerCorrectIndices(String sessionId, String correctIndices);
}
