package com.rks.catalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rks.catalog.domain.Product;
import com.rks.catalog.domain.ProductType;
import com.rks.catalog.service.ProductService;
import com.rks.catalog.util.ProductAlreadyExistsException;

@RestController
public class CatalogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);
	private final ProductService catalogService;
	
	@Autowired
	public CatalogController(final ProductService productService) {
		this.catalogService = productService;
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST, consumes={"application/json"})
	public Product saveProduct(@RequestBody @Valid final Product product) throws ProductAlreadyExistsException {
		LOGGER.debug("Received request to create {}", product);
		return catalogService.save(product);
	}
	
	@RequestMapping(value = "/products/{type}", method = RequestMethod.GET, produces={"application/json"})
	public List<Product> listProducts(@PathVariable(value = "type") ProductType type) {
		LOGGER.debug("Received request to list products of type: " + type.toString());
		return catalogService.getProducts(type);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable Long id) {
		LOGGER.debug("Received request to delete product with id: " + id);
		catalogService.deleteProduct(id);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleException(ProductAlreadyExistsException e) {
		return e.getLocalizedMessage();
	}
}
