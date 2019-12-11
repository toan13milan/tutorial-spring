package com.example.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="App_Role",
		uniqueConstraints = {
				@UniqueConstraint(name="APP_ROLE_UK", columnNames = "Role_Name")
		})
public class AppRole {
	@Id
	@GeneratedValue
	@Column(name="role_id", nullable = false)
	private Long id;
	
	@Column(name="role_name", length = 30, nullable= false)
	private String roleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
