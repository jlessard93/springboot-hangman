package com.nexmo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexmo.entities.PlayerData;

public interface PlayerDataRepository extends JpaRepository<PlayerData, Long> {

	PlayerData findBySessionIdAndHangmanMgmtState(String sessionId, String state);
	
}
