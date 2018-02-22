package com.nexmo.hangman;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nexmo.dto.AnswerDto;
import com.nexmo.dto.HangmanDto;
import com.nexmo.dto.HangmanMgmtDto;
import com.nexmo.util.HangmanConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations={"classpath:application-dev-test.properties"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class HangmanIntegrationTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HangmanIntegrationTest.class);
	
	private static final String HANGMAN_API_GUESSWORD = "/api/v1/hangman/guessword";
	private static final String HANGMAN_API_DEFAULT_NEWGAME = "/api/v1/hangman/newgame";
	private static final String HANGMAN_API_DEFAULT_ANOTHERGAME = "/api/v1/hangman/anothergame";
	
	private static final String HANGMAN_API_MNGR_STARTEDGAMES = "/api/v1/hangman/management/allstartedgames";
	private static final String HANGMAN_API_MGMT_ALLGAMES = "/api/v1/hangman/management/allstatistics";
	private static final String HANGMAN_API_MGMT_CREATE_HANGMANWORD = "/api/v1/hangman/management/addguessword";
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void addGuessWordTest() {
		
	}
	
	@Test
	public void allStatisticsTest() {
		//make new game 1
		initialNewGame(); 
		//make new game 2
		ResponseEntity<HangmanDto> responseEntity = restTemplate.getForEntity(HANGMAN_API_DEFAULT_NEWGAME, HangmanDto.class);
		//make new game 3
		responseEntity = restTemplate.getForEntity(HANGMAN_API_DEFAULT_NEWGAME, HangmanDto.class);
		
		ResponseEntity<HangmanMgmtDto> hangmanMgmtDtoEntityResp = restTemplate.getForEntity(HANGMAN_API_MGMT_ALLGAMES, HangmanMgmtDto.class);
		HangmanMgmtDto hangmanMgmtDtoBody = hangmanMgmtDtoEntityResp.getBody();
		assertTrue(hangmanMgmtDtoBody.getAllReports().size() == 3);
		
	}
	
	@Test
	public void getAllStartedGamesTest() {
		initialNewGame();
		ResponseEntity<HangmanMgmtDto> hangmanMgmtDtoResponse = restTemplate.getForEntity(HANGMAN_API_MNGR_STARTEDGAMES, HangmanMgmtDto.class);
		assertTrue(hangmanMgmtDtoResponse.getBody().getNumberOfStartedGames().longValue() == 1l);
		
	}
	
	@Test
	public void anotherGameTest() {
		Long gameId = initialNewGame();
		HangmanDto oldHangmanDto = new HangmanDto();
		oldHangmanDto.setGameId(gameId);
		
		ResponseEntity<HangmanDto> responseEntity = restTemplate.postForEntity(HANGMAN_API_DEFAULT_ANOTHERGAME, oldHangmanDto, HangmanDto.class);
		HangmanDto hangmanDtoResponse = responseEntity.getBody();
		assertNotNull(hangmanDtoResponse);
		assertTrue(hangmanDtoResponse.getGameId().longValue() != gameId.longValue());
	}
	
	private Long initialNewGame() {
		//GET NEW GAME WITH DEFAULT SETTINGS
		ResponseEntity<HangmanDto> responseEntity = restTemplate.getForEntity(HANGMAN_API_DEFAULT_NEWGAME, HangmanDto.class);
		HangmanDto hangmanDtoResponse = responseEntity.getBody();
		assertTrue(hangmanDtoResponse.getChances() == HangmanConstants.DEFAULT_CHANCES);
		assertTrue(hangmanDtoResponse.getTimeLimit() == HangmanConstants.DEFAULT_TIME_LIMIT);
		assertNotNull(hangmanDtoResponse.getGameId());
		logger.debug(">>>>>> CREATED GAME ID: " + hangmanDtoResponse.getGameId());
		return hangmanDtoResponse.getGameId();
	}
	
	@Test
	public void defaultNewGameTest() {
		initialNewGame();
	}
	

}









