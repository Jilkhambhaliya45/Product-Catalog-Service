package com.springboot.phasezero_catalog_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.phasezero_catalog_service.dao.ProductDao;
import com.springboot.phasezero_catalog_service.dto.ResponseStructure;
import com.springboot.phasezero_catalog_service.entity.Product;
import com.springboot.phasezero_catalog_service.exception.NegativeValueException;
import com.springboot.phasezero_catalog_service.exception.NoProductRecordFoundException;
import com.springboot.phasezero_catalog_service.exception.ObjectIsNotValid;
import com.springboot.phasezero_catalog_service.exception.PartNumberAlreadyExists;
import com.springboot.phasezero_catalog_service.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<ResponseStructure<Product>> addProducts(Product product) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
		if (product.getPartNumber() != null && product.getPartName() != null && product.getCategory() != null
				&& product.getPrice() != null && product.getStock() != null) {
			String partNumber = product.getPartNumber().trim();
			String partName = product.getPartName().trim().toLowerCase();
			String category = product.getCategory().trim();
			if (!productDao.existsByPartNumber(partNumber)) {
				if (!(product.getPrice() < 0) && !(product.getStock() < 0)) {

					responseStructure.setMessage("Product details saved");
					product.setPartName(partName);
					product.setCategory(category);
					product.setPartNumber(partNumber);
					responseStructure.setData(productDao.addProduct(product));

					responseStructure.setStatusCode(HttpStatus.CREATED.value());
					return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
				} else {
					throw new NegativeValueException("The field is negative");
				}
			} else {
				// exp PartNumber already exists
				throw new PartNumberAlreadyExists(product.getPartNumber() + " this number is already exists");
			}
		} else {
			throw new ObjectIsNotValid("Must have to pass all the fields");
		}

	}

	public ResponseEntity<ResponseStructure<List<Product>>> listAllProducts() {
		List<Product> products = productDao.listAllProducts();
		if (!products.isEmpty()) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("All Product Data  Fetched Successfully");
			responseStructure.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new NoProductRecordFoundException("There Is No Any Product Record Is Found");
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> searchByPartName(String partName) {
		List<Product> products = productDao.searchByName(partName);
		if (!products.isEmpty()) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Details Found Based On Given Name");
			responseStructure.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		} else {

			throw new NoProductRecordFoundException("Not Any Product Record Found Based On Given Name");
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> searchByCategory(String category) {
		List<Product> products = productDao.searchByCategory(category);
		if (!products.isEmpty()) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Details Found Based On Given Category");
			responseStructure.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		} else {

			throw new NoProductRecordFoundException("Not Any Product Record Found Based On Given Category");
		}
	}

	public ResponseEntity<ResponseStructure<List<Product>>> sortProductsByPrice(String field) {
		List<Product> products = productDao.sortProductsByPrice(field);
		if (!products.isEmpty()) {
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Details Sort Based On Given Field");
			responseStructure.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure, HttpStatus.FOUND);
		} else {

			throw new NoProductRecordFoundException("Not Any Product Record Found");
		}
	}

	public ResponseEntity<ResponseStructure<Double>> getTotalInventory() {
		List<Product> products = productRepository.findAll();
		if (!products.isEmpty()) {
			double totalValue = 0.0;

			for (int i = 0; i < products.size(); i++) {
				Product product = products.get(i);

				double price = product.getPrice();
				int stock = product.getStock();

				double productValue = price * stock;

				totalValue += productValue;
			}
			ResponseStructure<Double> responseStructure = new ResponseStructure<Double>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Total Inventory Is Counted Successfully");
			responseStructure.setData(totalValue);
			return new ResponseEntity<ResponseStructure<Double>>(responseStructure, HttpStatus.OK);
		}

		else {
			throw new NoProductRecordFoundException("No Data Found");
		}

	}
}
