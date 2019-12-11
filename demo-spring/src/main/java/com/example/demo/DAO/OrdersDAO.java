package com.example.demo.DAO;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.OrderDTO;

@Repository
@Transactional
public class OrdersDAO {
	@Autowired
	private EntityManager entityManager;
	
	public void saveOrder(OrderDTO orderDto) {
		entityManager.createQuery("Insert into Orders (name_user,adress,type_pay) Values(?,?,?)")
					.setParameter(1, orderDto.getNameUser())
					.setParameter(2, orderDto.getAddress())
					.setParameter(3, orderDto.getTypePay())
					.executeUpdate();
	}
}
