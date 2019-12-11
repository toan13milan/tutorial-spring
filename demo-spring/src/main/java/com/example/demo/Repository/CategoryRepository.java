package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entities.Categories;

public interface CategoryRepository extends CrudRepository<Categories, Long>{
	
}
