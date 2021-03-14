package com.simplilearn.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.entity.EProduct;
import com.simplilearn.webservice.exception.ProductNotFoundException;
import com.simplilearn.webservice.repository.ProductRepository;

@RestController
public class ProductController {

	// inject dependency
	@Autowired
	private ProductRepository productRepository;
	
	// GET all product
	@GetMapping("/products")
	public List<EProduct> getAllProducts(){
		return this.productRepository.findAll();
	}
	
	// GET One Product by id
	@GetMapping("/products/{id}")
	public EProduct getProductById(@PathVariable("id") long productId) {
		return this.productRepository.findById(productId).orElseThrow(()-> {
			throw  new ProductNotFoundException("Product Not Found with id "+productId);
		});
	}
	
	// Add a product 
	@PostMapping("/products")
	public EProduct addProduct(@RequestBody EProduct product) {
		return this.productRepository.save(product);
	}
}
