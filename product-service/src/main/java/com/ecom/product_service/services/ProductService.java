package com.ecom.product_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.product_service.model.Product;
@Service
public interface ProductService 
{
	public Product addProduct(Product product);
	public List<Product> getAllProducts();
	public Product getProductById(Long id);
	public boolean isStockAvailable(Long productId, int quantity);
	public boolean reserveStock(Long productId, int quantity);

}
