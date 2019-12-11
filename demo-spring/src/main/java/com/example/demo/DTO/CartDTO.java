package com.example.demo.DTO;

import javax.validation.constraints.NotNull;

public class CartDTO {
	private Long id;
	
	@NotNull
	private String nameUser;
	
	@NotNull
	private String address;
	
	@NotNull
	private Long typePay;
	
	private Long cartId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getTypePay() {
		return typePay;
	}

	public void setTypePay(Long typePay) {
		this.typePay = typePay;
	}

	
}
