package com.rks.catalog.repository;

import com.rks.catalog.domain.Product;
import com.rks.catalog.domain.ProductType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByType(ProductType type);
}
