package com.ecom.product_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.product_service.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
