package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.ProductB;
import com.example.demo.Repository.ProductBRepository;

@Service
public class ProductBService {
	@Autowired
	private ProductBRepository productBRep;
	
	public void saveAll(List<ProductB> listProductB) {
		productBRep.saveAll(listProductB);
	}
}
