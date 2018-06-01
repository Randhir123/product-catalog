package com.rks.catalog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rks.catalog.domain.Product;
import com.rks.catalog.domain.ProductType;

public class RestClient {

	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			Product product = new Product(1L, "Bata", (double) 100.0, ProductType.FOOTWEAR);
			ResponseEntity<Product> response 
				= restTemplate.postForEntity("http://localhost:8080/products", product, Product.class);
			System.out.println(response.getBody());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
