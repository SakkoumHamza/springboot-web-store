package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {

    List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
