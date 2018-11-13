package com.main.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Product;
import com.main.services.ItemServicesImpl;
import com.main.util.ItemNotFoundException;

@RestController
@RequestMapping("/item-services")
public class ItemController {
	
	private ItemServicesImpl itemServicesImpl;
	
	@PostMapping(value = "/create")
	public ResponseEntity<String> createProduct(@RequestBody @Valid Product product) {
		String status = getItemServicesImpl().createProducts(product);
		return new ResponseEntity<String>(status ,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> getAllProduct() {
		return getItemServicesImpl().getCustomers();
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {

		Optional<Product> customer = getItemServicesImpl().getById(id);
		if (!customer.isPresent()) {
			throw new ItemNotFoundException("Not found product with Id is : " + id);
		}
		return new ResponseEntity<Product>(customer.get(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateProduct(@RequestBody Product currentProduct) {
		String status = getItemServicesImpl().updateCustomer(currentProduct);
		if (status == "NotFound") {
			throw new ItemNotFoundException("Not found prduct with Id is" + currentProduct.getId());
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}", headers = "Accept=application/json")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {

		String status = getItemServicesImpl().deleteCustomer(id);
		if (status == "NotFound") {
			throw new ItemNotFoundException("Not found product with Id is : " + id);
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<Boolean> getProductById(@RequestParam String name) {

		Optional<List<Product>> customer = getItemServicesImpl().getByName(name);
		if (!customer.isPresent()) {
			return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
	}
	

	public ItemServicesImpl getItemServicesImpl() {
		return itemServicesImpl;
	}

	@Autowired
	public void setItemServicesImpl(ItemServicesImpl itemServicesImpl) {
		this.itemServicesImpl = itemServicesImpl;
	}
	

}
