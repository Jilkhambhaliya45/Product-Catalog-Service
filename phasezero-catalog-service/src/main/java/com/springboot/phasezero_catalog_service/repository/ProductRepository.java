package com.springboot.phasezero_catalog_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.phasezero_catalog_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Boolean existsByPartNumber(String PartNumber);

	Boolean existsByPartName(String PartName);

	List<Product> findByPartNameContainingIgnoreCase(String partName);

	List<Product> findByCategoryIgnoreCase(String category);

	@Query("SELECT SUM(p.price * p.stock) FROM Product p")
	Double calculateInventoryValue();

}
