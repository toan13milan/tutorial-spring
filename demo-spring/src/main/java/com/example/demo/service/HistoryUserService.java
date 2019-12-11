package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.HistoryUserDAO;
import com.example.demo.DTO.HistoryUserDTO;
import com.example.demo.Entities.HistoryUser;

@Service
public class HistoryUserService {
	@Autowired
	private HistoryUserDAO historyUserDAO;
	public List<HistoryUser> getAllHistory() {
		return historyUserDAO.getAllHistory();
	}
	
	public void addHistory(HistoryUserDTO historyUserDTO) {
		historyUserDAO.addHistory(historyUserDTO);
	}
	
	public Page<HistoryUser> searchHistory(Pageable pageable,String search) {
		return historyUserDAO.searchHistory(pageable, search);
	}
	
	public Page<HistoryUser> pagingHistory(Pageable pageable, String search) {
		Page<HistoryUser> listHistory = searchHistory(pageable, search);
//		int pageSize = pageable.getPageSize();
//		int currentPage = pageable.getPageNumber();
//		
//		int startIndex = currentPage*pageSize;
//		
//		List<HistoryUser> listResponse;
//		
//		if(listHistory.size() < startIndex) {
//			listResponse = Collections.emptyList();
//		} else {
//			int toIndex = Math.min(startIndex + pageSize, listHistory.size());
//			listResponse = listHistory.subList(startIndex, toIndex);
//		}
//		
//		Page<HistoryUser> pageHistory = new PageImpl<HistoryUser>(listResponse, PageRequest.of(currentPage, pageSize), listHistory.size());
		
		return listHistory;
	}
}
