package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.CartProductDAO;
import com.example.demo.DTO.CartProductDTO;
import com.example.demo.Entities.Cart;
import com.example.demo.Entities.Cart_ProductB;
import com.example.demo.Entities.Product;

@Service
public class CartProductService {
	@Autowired
	private CartProductDAO cartProductDAO;
	
	public void intoCart(CartProductDTO cartProductDTO) {
		cartProductDAO.intoCart(cartProductDTO);
	}
	
	public Cart getCartByUserId(Long userId) {
		return cartProductDAO.getCartByUserId(userId);
	}
	
	public Page<Product> getProductinCartByCartId(Pageable pageable, Long cartId) {
		List<Product> listProduct = cartProductDAO.getProductinCartByCartId(cartId);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startIndex = currentPage*pageSize;
		
		List<Product> list;
		if(listProduct.size() <startIndex) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(listProduct.size(), startIndex + pageSize);
			list = listProduct.subList(startIndex, toIndex);
		}
		
		Page<Product> pageProduct = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), listProduct.size());
		return pageProduct;
	}
	
	public List<Product> getProductinCartByCartId(Long cartId) {
		return cartProductDAO.getProductinCartByCartId(cartId);
	}
	
	public void resetCart(Long cartId) {
		cartProductDAO.resetToCart(cartId);
	}
	
	public Page<Cart_ProductB> getCartProductinCartPageByCartId(Pageable pageable, Long cartId) {
		Page<Cart_ProductB> pageCartProduct = cartProductDAO.getCartProductinCartPageByCartId(pageable, cartId);
//		int pageSize = pageable.getPageSize();
//		int currentPage = pageable.getPageNumber();
//		int startIndex = currentPage*pageSize;
//		
//		List<Cart_ProductB> list;
//		if(listCartProduct.size() <startIndex) {
//			list = Collections.emptyList();
//		} else {
//			int toIndex = Math.min(listCartProduct.size(), startIndex + pageSize);
//			list = listCartProduct.subList(startIndex, toIndex);
//		}
//		
//		Page<Cart_ProductB> pageCartProduct = new PageImpl<Cart_ProductB>(list, PageRequest.of(currentPage, pageSize), listCartProduct.size());
		return pageCartProduct;
	}
	
	public List<Cart_ProductB> getCartProductinCartByCartId(Long cartId) {
		return cartProductDAO.getCartProductinCartByCartId(cartId);
	}
}
