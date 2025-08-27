package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}