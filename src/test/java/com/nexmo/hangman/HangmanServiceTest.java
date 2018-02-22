package com.nexmo.hangman;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.nexmo.dto.AnswerDto;
import com.nexmo.dto.HangmanDto;
import com.nexmo.dto.HangmanSessionBean;
import com.nexmo.entities.HangmanWord;
import com.nexmo.entities.PlayerData;
import com.nexmo.exceptions.HangmanException;
import com.nexmo.services.HangmanManagerSvc;
import com.nexmo.services.HangmanSvcImpl;
import com.nexmo.util.HangmanConstants;

@RunWith(SpringRunner.class)
//@SpringBootTest
@TestPropertySource(locations={"classpath:application-dev-test.properties"})
public class HangmanServiceTest {
	
	@Mock
	private HangmanManagerSvc mockedHangmanManagerSvc;
	
	@Mock
	private HangmanSessionBean hangmanSessionBean;
	
	@InjectMocks
	private HangmanSvcImpl mockedHangmanSvcImpl = new HangmanSvcImpl();
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void defaultGameTest() {
		when(mockedHangmanManagerSvc.getAllGuessWordList()).thenReturn(getMockedHangmanWordList());
		MockHttpServletRequest mockReq = new MockHttpServletRequest();
		MockHttpSession mockSesh = new MockHttpSession();
		mockReq.setSession(mockSesh);
		when(mockedHangmanManagerSvc.existingActivePlayer(mockSesh.getId())).thenReturn(null);
		HangmanDto hangmanDto = mockedHangmanSvcImpl.defaultGame(mockReq);
		assertNotNull(hangmanDto);
		assertTrue(hangmanDto.getGuessWordId().longValue() == 1l || hangmanDto.getGuessWordId().longValue() == 2l
				|| hangmanDto.getGuessWordId().longValue() == 3l);
		assertTrue(hangmanDto.getChances() == HangmanConstants.DEFAULT_CHANCES);
	}
	
	@Test
	public void processAnswerTest() throws HangmanException {
		
		hangmanSessionBean.setSessionId("1");
		
		Long sampleId = new Long(1l);
		String guessLetter = "s";
		
		when(mockedHangmanManagerSvc.getGuessWordById(sampleId)).thenReturn(getMockedHangmanWordById(sampleId));
		
		AnswerDto answer = new AnswerDto();
		answer.setGuessWordId(sampleId);
		answer.setGuessLetter(guessLetter);
		
		Mockito.doNothing().when(mockedHangmanManagerSvc).registerGuessedLetters(Mockito.anyString(), Mockito.anyString());
		mockedHangmanSvcImpl.processAnswer(answer);
		
		assertTrue(answer.isCorrectAnswer());
		assertTrue(answer.getCorrectLetter().equalsIgnoreCase(guessLetter));
		assertTrue(answer.getNewLetterIndexList().size() == 2);
		
	}
	
	private HangmanWord getMockedHangmanWordById(Long id) {
		List<HangmanWord> list = getMockedHangmanWordList();
		for(HangmanWord element : list) {
			if(element.getId().longValue() == id) {
				return element;
			}
		}
		return null;
	}
	
	private List<HangmanWord> getMockedHangmanWordList() {
		List<HangmanWord> list = new ArrayList<>();
		HangmanWord obj = new HangmanWord();
		obj.setId(1l);
		obj.setValue("SAMSUNG");
		list.add(obj);
		obj = new HangmanWord();
		obj.setId(2l);
		obj.setValue("APPLE");
		list.add(obj);
		obj = new HangmanWord();
		obj.setId(3l);
		obj.setValue("MICROSOFT");
		list.add(obj);
		return list;
	}

}









