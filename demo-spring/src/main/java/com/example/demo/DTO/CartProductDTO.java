package com.example.demo.DTO;


public class CartProductDTO {
	private Long id;
	
	private Long cartId;
	
	private Long productBId;
	
	private Long amount;
	
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

	public Long getProductBId() {
		return productBId;
	}

	public void setProductBId(Long productBId) {
		this.productBId = productBId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
