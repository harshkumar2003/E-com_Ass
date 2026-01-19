package com.ecom.order_service.services;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom.order_service.Repo.OrderRepo;
import com.ecom.order_service.config.RestConfig;
import com.ecom.order_service.model.Orders;
import com.ecom.order_service.model.ProductPrice;


@Service
public class OrderServiceImp implements OrderService
{
	private final OrderRepo orderRepo;
	private final RestTemplate restTemplate;
	
	public OrderServiceImp(OrderRepo orderRepo, RestTemplate restTemplate)
	{
		this.orderRepo = orderRepo;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public Orders createOrder(Orders order) {

		Boolean reserved = restTemplate.exchange(
			    "http://product-service/products/reserveStock/"
			        + order.getProductId()
			        + "?quantity=" + order.getQuantity(),
			    HttpMethod.PUT,
			    null,
			    Boolean.class
			).getBody();

			if (!Boolean.TRUE.equals(reserved)) {
			    throw new RuntimeException("Out of stock");
			}
			
			ProductPrice product = restTemplate.getForObject(
				    "http://product-service/products/" + order.getProductId(),
				    ProductPrice.class
				);
			double total = product.getPrice() * order.getQuantity();
			order.setTotal_amt(total);
			return orderRepo.save(order);

    }
	
	@Override
	public Orders getOrderById(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
