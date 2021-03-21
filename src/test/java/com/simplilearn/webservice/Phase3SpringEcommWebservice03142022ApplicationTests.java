package com.simplilearn.webservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.simplilearn.webservice.controller.ProductController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Test - Main Application Test")
class Phase3SpringEcommWebservice03142022ApplicationTests {

	@Autowired
	private ProductController productCtrl;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	
	@Test
	@DisplayName("Test :: Load Application Context")
	void contextLoads() {
		assertNotNull(productCtrl);
	}
	
	@Test
	@DisplayName("Test :: Server Running Test")
	void testForServerRunning() {
		
		String url = "http://localhost:"+port+"/";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		assertEquals("Server is up and running!", response.getBody() );
		assertEquals(200, response.getStatusCodeValue());
	}

}
