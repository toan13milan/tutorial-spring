package com.example.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="productb")
public class ProductB {
	@Id
	@GeneratedValue
	@Column(name="id", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	private AppUser appUser;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "order_id", nullable=false)
	private Orders order;
	
	@Column(name="pro_cost")
	private Long proCost;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="name_product")
	private String nameProduct;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Long getProCost() {
		return proCost;
	}

	public void setProCost(Long proCost) {
		this.proCost = proCost;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public ProductB(AppUser appUser, Orders order, Long proCost, Long amount, String nameProduct) {
		super();
		this.appUser = appUser;
		this.order = order;
		this.proCost = proCost;
		this.amount = amount;
		this.nameProduct = nameProduct;
	}

	public ProductB() {
		super();
	}


}
