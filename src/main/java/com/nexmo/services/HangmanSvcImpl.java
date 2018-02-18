package com.nexmo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexmo.dto.AnswerDto;
import com.nexmo.dto.HangmanDto;
import com.nexmo.dto.HangmanSessionBean;
import com.nexmo.entities.HangmanMgmt;
import com.nexmo.entities.HangmanWord;
import com.nexmo.entities.PlayerData;
import com.nexmo.exceptions.HangmanException;
import com.nexmo.util.HangmanConstants;

@Service
public class HangmanSvcImpl implements HangmanSvc {
	
	private static final Logger logger = LoggerFactory.getLogger(HangmanSvcImpl.class);
	
	@Autowired
	private HangmanManagerSvc hangmanManagerSvc;
	
	@Autowired
	private HangmanSessionBean hangmanSessionBean;
	
	@Override
	public HangmanDto defaultGame(HttpServletRequest request) {
		
		
		//check if there is an existing mgmtData
		PlayerData data = hangmanManagerSvc.existingActivePlayer(request.getSession().getId());
		if(null != data) {
			return getExistingGame(data);
		}
		
		//RETRIEVE WITH NO CATEGORY
		List<HangmanWord> retrievedHangmanWordList = hangmanManagerSvc.getAllGuessWordList();
		int selectedGuessWordId = randomSelectFromDb(retrievedHangmanWordList);
		HangmanWord selectedWord = retrievedHangmanWordList.get(selectedGuessWordId);
		logger.debug(">>>>>> selectedWord: " + selectedWord.getValue());
		
		HangmanDto hangman = new HangmanDto();
		hangman.setGuessWordId(selectedWord.getId());
		hangman.setGuessWordlength(selectedWord.getValue().length());
		hangman.setChances(HangmanConstants.DEFAULT_CHANCES);
		hangman.setTimeLimit(HangmanConstants.DEFAULT_TIME_LIMIT);
		
		//CREATE GAME STAT
		HangmanMgmt hangmanMgmt = new HangmanMgmt();
		hangmanMgmt.setHangmanValue(selectedWord.getValue());
		hangmanMgmt.setHangmanCategory(selectedWord.getCategory());
		hangmanMgmt.setState(HangmanConstants.HANGMAN_STARTED);
		hangmanMgmt.setHangmanWord(selectedWord);
		if(null != request) {
			hangmanMgmt.setPlayerIpAddress(request.getRemoteAddr());
		}
		Long gameId = hangmanManagerSvc.createGameStat(hangmanMgmt);
		hangmanManagerSvc.createPlayerData(request.getSession().getId(), gameId);
		hangman.setGameId(gameId);
		
		return hangman;
	}
	
	private HangmanDto getExistingGame(PlayerData playerData) {
		
		logger.debug(">>>>>> Retrieving existing active player data");
		
		HangmanMgmt hangmanMgmt = playerData.getHangmanMgmt();
		String wordToGuess = hangmanMgmt.getHangmanValue();
		
		HangmanDto hangman = new HangmanDto();
		hangman.setGuessWordId(hangmanMgmt.getHangmanWord().getId());
		hangman.setGuessWordlength(wordToGuess.length());
		hangman.setChances(HangmanConstants.DEFAULT_CHANCES - playerData.getChancesUsed());
		hangman.setTimeLimit(HangmanConstants.DEFAULT_TIME_LIMIT);
		hangman.setGameId(hangmanMgmt.getId());
		
		hangman.setCorrectGuessedLetters(playerData.getGuessedLetters());
		hangman.setCorrectIndices(playerData.getCorrectIndices());
		
		return hangman;
	}
	
	private int randomSelectFromDb(List<HangmanWord> listFromDb) {
		int listFromDbSize = listFromDb.size();
		Random random = new Random();
		int selectWordInt = random.ints(0, (listFromDbSize-1)).findFirst().getAsInt();
		return selectWordInt;
	}
	
	@Override
	public String getWordById(Long id) {
		HangmanWord hangmanWord = hangmanManagerSvc.getGuessWordById(id);
		return hangmanWord.getValue();
	}

	@Override
	public void processAnswer(AnswerDto answerDto) throws HangmanException {
		
		if(null == answerDto || null == answerDto.getGuessLetter() || answerDto.getGuessLetter().equals("")) {
			throw new HangmanException("Cannot process request. There is no answer to process");
		}
		
		String guessWord = getWordById(answerDto.getGuessWordId());
		String[] guessWordArr = guessWord.split("");
		Map<Integer, String> guessWordMap = getLetterIndexMap(guessWordArr);
		
		String userGuessLetter = answerDto.getGuessLetter();
		
		List<Integer> prevLetterIndexList;
		if(null == answerDto.getPrevLetterIndexList()) {
			prevLetterIndexList = new ArrayList<>();
		} else {
			prevLetterIndexList = answerDto.getPrevLetterIndexList();
		}
		
		List<Integer> newLetterIndexList = new ArrayList<>();
		
		for(Integer key : guessWordMap.keySet()) {
			if(null != prevLetterIndexList && prevLetterIndexList.contains(key.intValue())) {
				continue;
			} else {
				
				if(guessWordMap.get(key).equalsIgnoreCase(userGuessLetter)) {
					newLetterIndexList.add(key);
					prevLetterIndexList.add(key);
				}
			}
		}
		
		if(newLetterIndexList.size() == 0) {
			answerDto.setCorrectAnswer(false);
			hangmanManagerSvc.registerUsedChances(hangmanSessionBean.getSessionId());
		} else {
			answerDto.setCorrectAnswer(true);
			answerDto.setCorrectLetter(userGuessLetter);
			
			hangmanManagerSvc.registerGuessedLetters(hangmanSessionBean.getSessionId(), answerDto.getGuessLetter());
			String correctIndices = "";
			for(Integer element : prevLetterIndexList) {
				correctIndices = correctIndices.concat(element.toString());
			}
			hangmanManagerSvc.registerCorrectIndices(hangmanSessionBean.getSessionId(), correctIndices);
			
			if(prevLetterIndexList.size() == guessWordMap.size()) {
				answerDto.setWinner(true);
			}
			
		}
		
		answerDto.setNewLetterIndexList(newLetterIndexList);
		answerDto.setPrevLetterIndexList(prevLetterIndexList);
		
	}
	
	private Map<Integer, String> getLetterIndexMap(String[] guessWordArr) {
		Map<Integer, String> letterIndexMap = new HashMap<>();
		for(int x = 0; x < guessWordArr.length; x++) {
			letterIndexMap.put(x, guessWordArr[x]);
		}
		return letterIndexMap;
	}

	@Override
	public HangmanDto anotherGame(HangmanDto oldHangmanDto, HttpServletRequest request) {
		updateHangman(oldHangmanDto);
		return defaultGame(request);
	}

	@Override
	public void updateHangman(HangmanDto oldHangmanDto) {
		HangmanMgmt hangmanMgmt = new HangmanMgmt();
		if(null != oldHangmanDto.getGameId()) {
			hangmanMgmt.setId(oldHangmanDto.getGameId());
			hangmanMgmt.setPlayerStatus(oldHangmanDto.getPlayerStatus());
			hangmanManagerSvc.updateStopGameStat(hangmanMgmt);
		}
	}
	
}
























