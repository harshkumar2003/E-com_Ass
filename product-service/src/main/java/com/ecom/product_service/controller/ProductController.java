package com.ecom.product_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.product_service.model.Product;
import com.ecom.product_service.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController 
{
	private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

   
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }
    
    @GetMapping
    public List<Product> getAllProducts()
    {
    	return service.getAllProducts();
    }

    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return service.getProductById(id);
    }

    
 
    @GetMapping("/checkStock/{id}")
    public boolean checkStock(@PathVariable Long id,
                              @RequestParam int quantity) {
        return service.isStockAvailable(id, quantity);
    }

    // Reserve Stock (Used by Order Service)
    @PutMapping("/reserveStock/{id}")
    public boolean reserveStock(@PathVariable Long id,
                                @RequestParam int quantity) {
        return service.reserveStock(id, quantity);
    }
}
