package com.example.demo.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.Entities.AppUser;
import com.example.demo.Entities.Categories;

public class ProductDTO {
	private Long id;
	
	@Size(min=1, max=32, message="name must be between 1 and 32 characters")
	private String nameProduct;
	
	@NotNull
	private Long amount;
	
	@NotNull
	private Long cost;
	
	private AppUser appUser;
	
	@NotNull
	private Long categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Long getCategories() {
		return categories;
	}

	public void setCategories(Long categories) {
		this.categories = categories;
	}

}
