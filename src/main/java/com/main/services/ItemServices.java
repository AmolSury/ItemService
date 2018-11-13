package com.main.services;

import java.util.List;
import java.util.Optional;

import com.main.entity.Product;

public interface ItemServices {
	
	public String createProducts(Product product);
	
	public List<Product> getCustomers();
	
	public Optional<Product> getById(Long id);
	
	public String updateCustomer(Product product);

	public Optional<List<Product>> getByName(String name);
}
