package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.CategoryDAO;
import com.example.demo.Entities.Categories;
import com.example.demo.Repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRep;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	public List<Categories> findAll() {
		return StreamSupport.stream(categoryRep.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
