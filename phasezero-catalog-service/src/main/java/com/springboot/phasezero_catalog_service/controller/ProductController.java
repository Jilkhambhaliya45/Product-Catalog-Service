package com.springboot.phasezero_catalog_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.phasezero_catalog_service.dto.ResponseStructure;
import com.springboot.phasezero_catalog_service.entity.Product;
import com.springboot.phasezero_catalog_service.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product) {
		return productService.addProducts(product);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> listAllProducts() {
		return productService.listAllProducts();
	}

	@GetMapping("/name/{partName}")
	public ResponseEntity<ResponseStructure<List<Product>>> searchByPartName(@PathVariable String partName) {
		return productService.searchByPartName(partName);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> searchByCategory(@PathVariable String category) {
		return productService.searchByCategory(category);
	}

	@GetMapping("/sort/{field}")
	public ResponseEntity<ResponseStructure<List<Product>>> sortProductsByPrice(@PathVariable String field) {
		return productService.sortProductsByPrice(field);
	}

	@GetMapping("/inventory/value")
	public ResponseEntity<ResponseStructure<Double>> getTotalInventory() {
		return productService.getTotalInventory();
	}
}
