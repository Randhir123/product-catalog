package com.rks.catalog.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.rks.catalog.domain.Product;
import com.rks.catalog.domain.ProductType;
import com.rks.catalog.repository.ProductRepository;
import com.rks.catalog.util.ProductAlreadyExistsException;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository repository;
	
	@Autowired
	public ProductServiceImpl(final ProductRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public Product save(@NotNull @Valid Product product) throws ProductAlreadyExistsException {
		LOGGER.debug("Creating {}", product);
		Optional<Product> existing = repository.findById(product.getId());
		if (existing.isPresent()) {
			throw new ProductAlreadyExistsException(
					String.format("There already exists a product with id=%s", product.getId()));
		}
		return repository.save(product);
	}

	@Override
	public List<Product> getProducts(@NotNull ProductType type) {
		LOGGER.debug("Retrieving list of products of type" + type.toString());
		return repository.findByType(type);
	}

	@Override
	public void deleteProduct(Long productId) {
		LOGGER.debug("deleting {}", productId);
		repository.deleteById(productId);
	}

}
