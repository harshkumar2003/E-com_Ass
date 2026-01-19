package com.ecom.order_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.order_service.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> 
{

}
