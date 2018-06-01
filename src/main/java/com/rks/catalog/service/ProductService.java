package com.rks.catalog.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rks.catalog.domain.Product;
import com.rks.catalog.domain.ProductType;
import com.rks.catalog.util.ProductAlreadyExistsException;

public interface ProductService {
	
	Product save(@NotNull @Valid final Product product) throws ProductAlreadyExistsException;
	List<Product> getProducts(@NotNull final ProductType type);
	void deleteProduct(final Long productId);
}
