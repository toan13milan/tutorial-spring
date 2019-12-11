package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entities.Orders;

public interface OrderRepository extends CrudRepository<Orders, Long>{

}
