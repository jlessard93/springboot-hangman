package com.nexmo.dto;

public class HangmanDto {
	
	private String guessWord;
	private int chances;
	private int timeLimit;
	private Long guessWordId;
	private int guessWordlength;
	
	private Long gameId;
	private String state;
	private String playerStatus;
	
	private String playerIpAddress;
	
	private String correctGuessedLetters;
	private String correctIndices;
	
	public String getGuessWord() {
		return guessWord;
	}
	public void setGuessWord(String guessWord) {
		this.guessWord = guessWord;
	}
	public int getChances() {
		return chances;
	}
	public void setChances(int chances) {
		this.chances = chances;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getGuessWordlength() {
		return guessWordlength;
	}
	public void setGuessWordlength(int guessWordlength) {
		this.guessWordlength = guessWordlength;
	}
	public Long getGuessWordId() {
		return guessWordId;
	}
	public void setGuessWordId(Long guessWordId) {
		this.guessWordId = guessWordId;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPlayerStatus() {
		return playerStatus;
	}
	public void setPlayerStatus(String playerStatus) {
		this.playerStatus = playerStatus;
	}
	public String getPlayerIpAddress() {
		return playerIpAddress;
	}
	public void setPlayerIpAddress(String playerIpAddress) {
		this.playerIpAddress = playerIpAddress;
	}
	public String getCorrectGuessedLetters() {
		return correctGuessedLetters;
	}
	public void setCorrectGuessedLetters(String correctGuessedLetters) {
		this.correctGuessedLetters = correctGuessedLetters;
	}
	public String getCorrectIndices() {
		return correctIndices;
	}
	public void setCorrectIndices(String correctIndices) {
		this.correctIndices = correctIndices;
	}

}
