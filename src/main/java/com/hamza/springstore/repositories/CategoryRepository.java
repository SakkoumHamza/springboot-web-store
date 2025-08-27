package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
    boolean existsByName(String name);
}