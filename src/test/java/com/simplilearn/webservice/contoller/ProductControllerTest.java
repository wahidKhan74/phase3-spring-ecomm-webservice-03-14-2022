package com.simplilearn.webservice.contoller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import static org.junit.jupiter.api.Assertions.*;

import com.simplilearn.webservice.entity.EProduct;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Test - Product Contoller")
public class ProductControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("Test :: Add Product ")
	public void testAddProduct() throws RestClientException, URISyntaxException {
		final String url = "http://localhost:"+port+"/products";
		
		// create a product object
		EProduct product = new EProduct("Lenovo Laptop Xyz series", 
				"It is a Laptop", 2020.3);
		// http entity object  -> for passing product data as request body.
		HttpEntity<EProduct> requestObj = new HttpEntity<EProduct>(product);
		
		ResponseEntity<EProduct> response = restTemplate.postForEntity(new URI(url), requestObj, EProduct.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Lenovo Laptop Xyz series", response.getBody().getName());
		assertEquals(2020.3, response.getBody().getPrice());
		
	}
	
	@Test
	@DisplayName("Test :: Add Product Null value")
	public void testAddProductForNull() throws RestClientException, URISyntaxException {
		final String url = "http://localhost:"+port+"/products";
		
		// create a product object
		EProduct product = null;
		// http entity object  -> for passing product data as request body.
		HttpEntity<EProduct> requestObj = new HttpEntity<EProduct>(product);
		
		ResponseEntity<EProduct> response = restTemplate.postForEntity(new URI(url), requestObj, EProduct.class);
		
		assertEquals(415, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Test :: List All Product")
	public void testGetAllProducts() throws RestClientException, URISyntaxException {
		final String url = "http://localhost:"+port+"/products";
	
		ResponseEntity<List> response = restTemplate.getForEntity(new URI(url), List.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(false, response.getBody().isEmpty());
	}
}
