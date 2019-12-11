package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.OrdersDAO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.Entities.Orders;
import com.example.demo.Repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrdersDAO orderDao;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void saveOrder(OrderDTO orderDto) {
		orderDao.saveOrder(orderDto);
	}
	
	public Orders save(Orders order) {
		return orderRepository.save(order);
	}
}
