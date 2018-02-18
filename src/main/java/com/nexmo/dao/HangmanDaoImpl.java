package com.nexmo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nexmo.entities.HangmanMgmt;
import com.nexmo.entities.HangmanWord;

@Repository
public class HangmanDaoImpl implements HangmanDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createGuessWord(HangmanWord hangmanWord) {
		entityManager.persist(hangmanWord);
	}

	@Override
	public List<HangmanWord> retrieveGuessWordListByCategory(String category) {
		String query = "Select h from HangmanWord h where h.category = :category";
		return entityManager.createQuery(query, HangmanWord.class).setParameter("category", category).getResultList();
	}

	@Override
	public HangmanWord retrieveGuessWordById(Long id) {
		return entityManager.find(HangmanWord.class, id);
	}

	@Override
	public void deleteGuessWordById(Long id) {
		HangmanWord obj = entityManager.find(HangmanWord.class, id);
		if(null != obj) {
			entityManager.remove(obj);
		}
	}

	@Override
	public void deleteGuessWordListCategory(String category) {
		String query = "Delete from HangmanWord where category = :category";
		entityManager.createQuery(query).setParameter("category", category).executeUpdate();
	}

	@Override
	public List<HangmanWord> retrieveAllGuessWordList() {
		String query = "Select h from HangmanWord h";
		return entityManager.createQuery(query, HangmanWord.class).getResultList();
	}

	@Override
	public Long createHangmanReport(HangmanMgmt hangmanMgmt) {
		entityManager.persist(hangmanMgmt);
		return hangmanMgmt.getId();
	}

	@Override
	public List<HangmanMgmt> getHangmanRepByState(String state) {
		String query = "Select hm from HangmanMgmt hm where hm.state = :state";
		return entityManager.createQuery(query, HangmanMgmt.class).setParameter("state", state).getResultList();
	}

	@Override
	public void deleteHangmanReportById(Long id) {
		HangmanMgmt obj = entityManager.find(HangmanMgmt.class, id);
		if(null != obj) {
			entityManager.remove(obj);
		}
	}

	@Override
	public List<HangmanMgmt> getAllHangmanReport() {
		String query = "Select hm from HangmanMgmt hm";
		return entityManager.createQuery(query, HangmanMgmt.class).getResultList();
	}

	@Override
	public void updateHangmanReport(HangmanMgmt hangmanMgmt) {
		HangmanMgmt retrievedHangmanMgmt = getHangmanRepById(hangmanMgmt.getId());
		retrievedHangmanMgmt.setState(hangmanMgmt.getState());
		retrievedHangmanMgmt.setPlayerStatus(hangmanMgmt.getPlayerStatus());
		entityManager.flush();
	}

	@Override
	public HangmanMgmt getHangmanRepById(Long id) {
		return entityManager.find(HangmanMgmt.class, id);
	}

	@Override
	public Long getNumberOfStartedGames(String state) {
		String queryString = "Select count(*) from HangmanMgmt hm where hm.state = :state";
		return (Long)entityManager.createQuery(queryString).setParameter("state",state).getSingleResult();
	}

}







