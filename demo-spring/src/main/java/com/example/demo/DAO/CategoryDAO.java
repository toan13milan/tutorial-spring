package com.example.demo.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entities.Categories;

@Repository
@Transactional
public class CategoryDAO {
	@Autowired
	private EntityManager entityManager;
	
	public List<Categories> findAll() {
		List<Categories> listCategory = entityManager.createQuery("Select c from Categories").getResultList();
		return listCategory;
	}
}
