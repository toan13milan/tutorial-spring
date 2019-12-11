package com.example.demo.DTO;

import javax.validation.constraints.NotNull;

public class OrderDTO {
	private Long id;
	
	@NotNull
	private String nameUser;
	
	@NotNull
	private String address;
	
	@NotNull
	private Long typePay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
