package com.nexmo.hangman;

import com.nexmo.dao.HangmanDao;
import com.nexmo.entities.HangmanMgmt;
import com.nexmo.entities.HangmanWord;
import com.nexmo.util.HangmanConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Transactional
@TestPropertySource(locations={"classpath:application-dev-test.properties"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class HangmanDaoTest {
	
	@Autowired
	private HangmanDao hangmanDao;
	
	private static final String INITIALIZED_CATEGORY = "ALCOHOL";
	private static final int INITIALIZED_ITEMS_COUNT = 6;
	

	@Test
	public void getNumberOfStartedGamesTest() {
		hangmanReportInitial();
		Long num = hangmanDao.getNumberOfStartedGames(HangmanConstants.HANGMAN_STARTED);
		assertEquals(1l, num.longValue());
	}
	
	@Test
	public void updateHangmanReportTest() {
		Long id = hangmanReportInitial();
		HangmanMgmt oldObj = hangmanDao.getHangmanRepById(id);
		assertEquals(HangmanConstants.HANGMAN_STARTED, oldObj.getState());

		HangmanMgmt updateObj = new HangmanMgmt();
		updateObj.setId(id);
		updateObj.setState(HangmanConstants.HANGMAN_STOPPED);
		updateObj.setPlayerStatus(HangmanConstants.HANGMAN_LOSER);
		hangmanDao.updateHangmanReport(updateObj);

		oldObj = new HangmanMgmt();
		oldObj = hangmanDao.getHangmanRepById(id);
		assertEquals(HangmanConstants.HANGMAN_STOPPED, oldObj.getState());
		assertEquals(HangmanConstants.HANGMAN_LOSER, oldObj.getPlayerStatus());
	}
	
	private Long hangmanReportInitial() {
		HangmanMgmt stat = new HangmanMgmt();
		stat.setHangmanCategory("SAMPLE_CATEGORY");
		stat.setHangmanValue("SAMPLE VALUE");
		stat.setState(HangmanConstants.HANGMAN_STARTED);
		Long genId = hangmanDao.createHangmanReport(stat);

		List<HangmanMgmt> list = hangmanDao.getHangmanRepByState(HangmanConstants.HANGMAN_STARTED);
		assertEquals(1, list.size());
		assertEquals(genId.longValue(), list.get(0).getId().longValue());
		return genId;
	}
	
	@Test
	public void deleteHangmanReportByIdTest() {
		Long genId = hangmanReportInitial();
		hangmanDao.deleteHangmanReportById(genId);
		List<HangmanMgmt> list = hangmanDao.getAllHangmanReport();
		assertEquals(0, list.size());

	}
	
	@Test
	public void createRetrieveHangmanReportTest() {
		hangmanReportInitial();
	}
	
	
	@Test
	public void retrieveInitializedGuessWordTest() {
		List<HangmanWord> resultList = hangmanDao.retrieveGuessWordListByCategory(INITIALIZED_CATEGORY);
		assertNotNull(resultList);
		assertEquals(INITIALIZED_ITEMS_COUNT, resultList.size());
	}
	
	@Test
	public void retrieveHangmanWordByCategoryTest() {
		
		String category = "FRUITS";
		
		HangmanWord hangmanWord = new HangmanWord();
		hangmanWord.setCategory(category);
		hangmanWord.setValue("MANGO");
		hangmanDao.createGuessWord(hangmanWord);
		
		hangmanWord = new HangmanWord();
		hangmanWord.setCategory(category);
		hangmanWord.setValue("STRAWBERRY");
		hangmanDao.createGuessWord(hangmanWord);

		hangmanWord = new HangmanWord();
		hangmanWord.setCategory(category);
		hangmanWord.setValue("PINEAPPLE");
		hangmanDao.createGuessWord(hangmanWord);

		List<HangmanWord> resultList = hangmanDao.retrieveGuessWordListByCategory(INITIALIZED_CATEGORY);
		assertNotNull(resultList);
		assertEquals(INITIALIZED_ITEMS_COUNT, resultList.size());

		resultList = hangmanDao.retrieveGuessWordListByCategory(category);
		assertNotNull(resultList);
		assertEquals(3, resultList.size());

	}
	
	@Test
	public void getHangmanWordByIdTest() {

		/*
		 * FROM data.sql:
		 * WHISKEY = 1
		 * GIN = 2
		 */

		List<HangmanWord> resultList = hangmanDao.retrieveGuessWordListByCategory(INITIALIZED_CATEGORY);
		assertNotNull(resultList);
		assertEquals(INITIALIZED_ITEMS_COUNT, resultList.size());

		HangmanWord hangmanWord = hangmanDao.retrieveGuessWordById(Long.valueOf(1l));
		assertTrue(hangmanWord.getValue().equalsIgnoreCase("WHISKEY"));

		hangmanWord = hangmanDao.retrieveGuessWordById(Long.valueOf(2l));
		assertTrue(hangmanWord.getValue().equalsIgnoreCase("GIN"));

	}
	
	@Test
	public void deleteHangmanWordByIdTest() {

		/*
		 * FROM data.sql:
		 * WHISKEY = 1
		 * GIN = 2
		 * TEQUILA = 3
		 */

		List<HangmanWord> resultList = hangmanDao.retrieveGuessWordListByCategory(INITIALIZED_CATEGORY);
		assertNotNull(resultList);
		assertEquals(INITIALIZED_ITEMS_COUNT, resultList.size());

		//delete TEQUILA
		hangmanDao.deleteGuessWordById(Long.valueOf(3l));

		resultList = hangmanDao.retrieveGuessWordListByCategory(INITIALIZED_CATEGORY);
		assertNotNull(resultList);
		assertEquals(INITIALIZED_ITEMS_COUNT - 1, resultList.size());

		//delete all under INITIALIZED CATEGORY
		hangmanDao.deleteGuessWordListCategory(INITIALIZED_CATEGORY);

		resultList = hangmanDao.retrieveGuessWordListByCategory(INITIALIZED_CATEGORY);
		assertEquals(0, resultList.size());
	}
	
	@Test
	public void retrieveAllHangmanWordTest() {
		List<HangmanWord> resultList = hangmanDao.retrieveAllGuessWordList();
		assertNotNull(resultList);
		assertEquals(INITIALIZED_ITEMS_COUNT, resultList.size());
	}

}

















