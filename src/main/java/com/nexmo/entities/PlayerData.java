package com.nexmo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "PlayerData")
@Table(name="PLAYER_DATA")
public class PlayerData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;
	
	private String sessionId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HANGMANMGMT_ID")
	private HangmanMgmt hangmanMgmt;
	
	@Column(name = "CRRECT_GUESSD_LTTRS")
	private String guessedLetters;
	private int chances;
	private int chancesUsed;
	private String correctIndices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HangmanMgmt getHangmanMgmt() {
		return hangmanMgmt;
	}

	public void setHangmanMgmt(HangmanMgmt hangmanMgmt) {
		this.hangmanMgmt = hangmanMgmt;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getGuessedLetters() {
		return guessedLetters;
	}

	public void setGuessedLetters(String guessedLetters) {
		this.guessedLetters = guessedLetters;
	}

	public int getChances() {
		return chances;
	}

	public void setChances(int chances) {
		this.chances = chances;
	}

	public int getChancesUsed() {
		return chancesUsed;
	}

	public void setChancesUsed(int chancesUsed) {
		this.chancesUsed = chancesUsed;
	}

	public String getCorrectIndices() {
		return correctIndices;
	}

	public void setCorrectIndices(String correctIndices) {
		this.correctIndices = correctIndices;
	}
	
}











