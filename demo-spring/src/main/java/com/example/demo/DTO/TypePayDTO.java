package com.example.demo.DTO;

public class TypePayDTO {
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TypePayDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TypePayDTO() {
		super();
	}
	
	
}
