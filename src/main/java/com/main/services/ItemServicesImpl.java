package com.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Product;
import com.main.repository.ProductRepository;

@Service
public class ItemServicesImpl  implements ItemServices{
	
	private ProductRepository productRepository;
	
	@Override
	public String createProducts(Product product) {
		//TODO cust
		Product prod = getProductRepository().save(product);
		return "ProductCreated";
	}
	
	@Override
	public List<Product> getCustomers() {
		return (List<Product>) getProductRepository().findAll();
	}

	@Override
	public Optional<Product> getById(Long id) {
		return getProductRepository().findById(id);
	}
	
	@Override
	public Optional<List<Product>> getByName(String name) {
		return getProductRepository().findByName(name);
	}

	public String updateCustomer(Product product) {
		if (getProductRepository().existsById(product.getId())) {
			getProductRepository().save(product);
			return "Updated";
		}
		return "NotFound";
	}

	public String deleteCustomer(Long id) {

		if (getProductRepository().existsById(id)) {
			getProductRepository().deleteById(id);
			return "Deleted";
		}
		return "NotFound";
	}
	

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

}
