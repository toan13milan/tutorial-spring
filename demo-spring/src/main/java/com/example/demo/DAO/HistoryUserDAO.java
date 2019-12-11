package com.example.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.HistoryUserDTO;
import com.example.demo.Entities.HistoryUser;
import com.example.demo.Entities.Product;

@Repository
@Transactional
public class HistoryUserDAO {
	@Autowired
	private EntityManager entityManager;
	
	public List<HistoryUser> getAllHistory() {
		List<HistoryUser> listHistory = new ArrayList<>();
		listHistory= entityManager.createQuery("Select hu from HistoryUser hu").getResultList();
		return listHistory;
	}
	
	public void addHistory(HistoryUserDTO history) {
		entityManager.createNativeQuery("Insert into History_User (product_name,action,date,user_id) Values (?,?,?,?)")
		.setParameter(1, history.getProductName())
		.setParameter(2, history.getAction())
		.setParameter(3, history.getDate())
		.setParameter(4, history.getAppUser().getUserId())
		.executeUpdate();
	}
	
	public Page<HistoryUser> searchHistory(Pageable pageable, String search) {
		List<HistoryUser> listHistory = entityManager.createQuery("Select hu from HistoryUser hu where hu.productName LIKE :search")
													.setFirstResult(pageable.getPageNumber()*5) // offset
											         .setMaxResults(pageable.getPageSize()) // limit
													.setParameter("search",search+'%')
													.getResultList();
		List<Long> count = entityManager.createQuery("Select count(hu) from HistoryUser hu where hu.productName LIKE :search")
				.setParameter("search", search+'%')
			    .getResultList();
		Page<HistoryUser> pageHistory = new PageImpl<>(listHistory, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), count.get(0));
		return pageHistory;
	}
}
