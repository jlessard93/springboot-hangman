package com.nexmo.services;

import com.nexmo.dao.HangmanDao;
import com.nexmo.dao.PlayerDataRepository;
import com.nexmo.dto.HangmanMgmtDto;
import com.nexmo.entities.HangmanMgmt;
import com.nexmo.entities.HangmanWord;
import com.nexmo.entities.PlayerData;
import com.nexmo.util.HangmanConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HangmanMngrSvcImpl implements HangmanManagerSvc {
	
	private static final Logger logger = LoggerFactory.getLogger(HangmanMngrSvcImpl.class);
	
	@Autowired
	private HangmanDao hangmanDao;
	
	@Autowired
	private PlayerDataRepository playerDataRepo;
	
	@Override
	@Transactional
	public void createGuessWord(HangmanWord hangmanWord) {
		hangmanDao.createGuessWord(hangmanWord);
	}

	@Override
	public void deleteGuessWordById(Long id) {
		hangmanDao.deleteGuessWordById(id);
	}

	@Override
	public HangmanWord getGuessWordById(Long id) {
		return hangmanDao.retrieveGuessWordById(id);
	}

	@Override
	public List<HangmanWord> getGuessWordListByCategory(String category) {
		return hangmanDao.retrieveGuessWordListByCategory(category);
	}

	@Override
	public List<HangmanWord> getAllGuessWordList() {
		return hangmanDao.retrieveAllGuessWordList();
	}

	@Override
	public void deleteGuessWordByCategory(String category) {
		hangmanDao.deleteGuessWordListCategory(category);
	}

	@Override
	@Transactional
	public Long createGameStat(HangmanMgmt hangmanMgmt) {
		hangmanMgmt.setPlayerStatus(HangmanConstants.HANGMAN_UNKNOWN);
		return hangmanDao.createHangmanReport(hangmanMgmt);
	}

	@Override
	public void deleteGameStatById(Long id) {
		hangmanDao.deleteHangmanReportById(id);
	}

	@Override
	@Transactional
	public void updateStopGameStat(HangmanMgmt hangmanMgmt) {
		logger.debug(">>>>>> UPDATING GAME STATS TO STOPPED");
		hangmanMgmt.setState(HangmanConstants.HANGMAN_STOPPED);
		if(null == hangmanMgmt.getPlayerStatus()) {
			hangmanMgmt.setPlayerStatus(HangmanConstants.HANGMAN_UNKNOWN);
		}
		hangmanDao.updateHangmanReport(hangmanMgmt);
	}

	@Override
	public HangmanMgmtDto getAllStartedGames() {
		Long num = hangmanDao.getNumberOfStartedGames(HangmanConstants.HANGMAN_STARTED);
		HangmanMgmtDto hangmanMgmtDto = new HangmanMgmtDto();
		hangmanMgmtDto.setNumberOfStartedGames(num);
		return hangmanMgmtDto;
	}

	@Override
	public HangmanMgmtDto getAllGameStat() {
		HangmanMgmtDto hangmanMgmtDto = new HangmanMgmtDto();
		hangmanMgmtDto.setAllReports(hangmanDao.getAllHangmanReport());
		return hangmanMgmtDto;
	}

	@Override
	public void createPlayerData(String sessionId, Long hangmanMgmtId) {
		
		PlayerData pData = new PlayerData();
		pData.setSessionId(sessionId);
		HangmanMgmt hangmanMgmt = hangmanDao.getHangmanRepById(hangmanMgmtId);
		pData.setHangmanMgmt(hangmanMgmt);
		pData.setChances(HangmanConstants.DEFAULT_CHANCES);
		playerDataRepo.save(pData);
	}

	@Override
	public PlayerData existingActivePlayer(String sessionId) {
		return playerDataRepo.findBySessionIdAndHangmanMgmtState(sessionId, HangmanConstants.HANGMAN_STARTED);
	}

	@Override
	public void registerGuessedLetters(String sessionId, String newGuessedLetter) {
		PlayerData pData = playerDataRepo.findBySessionIdAndHangmanMgmtState(sessionId, HangmanConstants.HANGMAN_STARTED);
		String guessedLetter = pData.getGuessedLetters();
		if(guessedLetter == null)
			guessedLetter = "";
		guessedLetter = guessedLetter.concat(newGuessedLetter);
		pData.setGuessedLetters(guessedLetter);
		playerDataRepo.save(pData);
		
	}

	@Override
	public void registerUsedChances(String sessionId) {
		PlayerData pData = playerDataRepo.findBySessionIdAndHangmanMgmtState(sessionId, HangmanConstants.HANGMAN_STARTED);
		int chancesUsed = pData.getChancesUsed();
		pData.setChancesUsed(chancesUsed+1);
		playerDataRepo.save(pData);
	}

	@Override
	public void registerCorrectIndices(String sessionId, String correctIndices) {
		PlayerData pData = playerDataRepo.findBySessionIdAndHangmanMgmtState(sessionId, HangmanConstants.HANGMAN_STARTED);
		pData.setCorrectIndices(correctIndices);
		playerDataRepo.save(pData);
	}
	

}


















