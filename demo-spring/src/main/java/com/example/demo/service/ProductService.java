package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.ProductDAO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Entities.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDAO productDAO;
	public List<Product> listProduct() {
		List<Product> list = new ArrayList<>();
		list = productDAO.getAllProduct();
		return list;
	}
	
	public void createProduct(ProductDTO productDTO) {
		productDAO.createProduct(productDTO);
	}
	
	public void editProduct(ProductDTO productDTO) {
		productDAO.editProduct(productDTO);
	}
	
	public void deleteProductById(Long id) {
		productDAO.deleteProductById(id);
	}
	
	public Product getProductById(Long id) {
		return productDAO.getProductById(id);
	}
	
	public Page<Product> searchProduct(Pageable pageable, String search, Long type) {
		if(type == null) {
			return productDAO.searchProduct(pageable,search);
		} 
		return productDAO.searchProductByType(pageable, search, type);
	}
	
	public Page<Product> pageableProducct(Pageable pageable) {
		List<Product> list = listProduct();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage*pageSize;
		
		List<Product> listProduct;
		
		if(list.size() < startItem) {
			listProduct = Collections.emptyList();
			
		} else {
			int toIndex  = Math.min(startItem + pageSize, list.size());
			listProduct = list.subList(startItem, toIndex);
		}
		
		Page<Product> productPage = new PageImpl<Product>(listProduct, PageRequest.of(currentPage, pageSize), list.size());
		
		return productPage;
	}
	
	public Page<Product> pageableSearchProduct(Pageable pageable, String search, Long type) {
//		List<Product> list = searchProduct(pageable,search, type);
//		int pageSize = pageable.getPageSize();
//		int currentPage = pageable.getPageNumber();
//		int startItem = currentPage*pageSize;
//		
//		List<Product> listProduct;
//		
//		if(list.size() < startItem) {
//			listProduct = Collections.emptyList();
//			
//		} else {
//			int toIndex  = Math.min(startItem + pageSize, list.size());
//			listProduct = list.subList(startItem, toIndex);
//		}
//		
//		Page<Product> productPage = new PageImpl<Product>(listProduct, PageRequest.of(currentPage, pageSize), list.size());
		
		return searchProduct(pageable, search, type);
	}
}
