package com.rks.catalog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {

	@Id
	@NotNull
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	@NotNull
	@Size(max = 64)
	private String name;
	
	@NotNull
	@Min(value = 0)
	private Double price;
	
	@NotNull
	ProductType type;
	
	public Product(final Long id, final String name, final Double price, final ProductType type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}
}
