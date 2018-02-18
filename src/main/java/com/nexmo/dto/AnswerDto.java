package com.nexmo.dto;

import java.util.List;

public class AnswerDto {
	
	private Long guessWordId;
	private String guessLetter;
	private Integer prevLetterIndex;
	private boolean correctAnswer;
	private String correctLetter;
	private List<Integer> prevLetterIndexList;
	private List<Integer> newLetterIndexList;
	private boolean winner;
	
	public Long getGuessWordId() {
		return guessWordId;
	}
	public void setGuessWordId(Long guessWordId) {
		this.guessWordId = guessWordId;
	}
	public String getGuessLetter() {
		return guessLetter;
	}
	public void setGuessLetter(String guessLetter) {
		this.guessLetter = guessLetter;
	}
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public Integer getPrevLetterIndex() {
		return prevLetterIndex;
	}
	public void setPrevLetterIndex(Integer prevLetterIndex) {
		this.prevLetterIndex = prevLetterIndex;
	}
	public String getCorrectLetter() {
		return correctLetter;
	}
	public void setCorrectLetter(String correctLetter) {
		this.correctLetter = correctLetter;
	}
	public List<Integer> getPrevLetterIndexList() {
		return prevLetterIndexList;
	}
	public void setPrevLetterIndexList(List<Integer> prevLetterIndexList) {
		this.prevLetterIndexList = prevLetterIndexList;
	}
	public List<Integer> getNewLetterIndexList() {
		return newLetterIndexList;
	}
	public void setNewLetterIndexList(List<Integer> newLetterIndexList) {
		this.newLetterIndexList = newLetterIndexList;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
