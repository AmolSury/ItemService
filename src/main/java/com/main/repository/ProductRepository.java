package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	 @Query("select p from Product p where p.name = :name")
	 Optional<List<Product>> findByName(String name);
}