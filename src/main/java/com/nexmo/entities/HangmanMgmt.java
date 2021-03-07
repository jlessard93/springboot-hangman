package com.nexmo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "HangmanMgmt")
@Table(name="HANGMAN_MGMT_TABLE")
public class HangmanMgmt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, name = "HANGMANMGMT_ID")
	private Long id;

	@Column(nullable = false)
	private String state; //STARTED | STOPPED

	private String hangmanCategory; //retrieved hangman word Category
	private String hangmanValue;

	private String playerStatus; //WINNER | LOSER
	private String playerIpAddress;

	@OneToOne
	@JoinColumn(name = "HANGMANWORD_ID")
	private HangmanWord hangmanWord;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHangmanCategory() {
		return hangmanCategory;
	}
	public void setHangmanCategory(String hangmanCategory) {
		this.hangmanCategory = hangmanCategory;
	}
	public String getHangmanValue() {
		return hangmanValue;
	}
	public void setHangmanValue(String hangmanValue) {
		this.hangmanValue = hangmanValue;
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
	public HangmanWord getHangmanWord() {
		return hangmanWord;
	}
	public void setHangmanWord(HangmanWord hangmanWord) {
		this.hangmanWord = hangmanWord;
	}

}












