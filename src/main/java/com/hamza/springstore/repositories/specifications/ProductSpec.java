package com.hamza.springstore.repositories.specifications;

import com.hamza.springstore.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> cb.like(root.get("name"),"%"+name+"%");
    }
    public static Specification<Product> hasPriceGreaterThanOrEqual(BigDecimal lower) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"),lower);
    }
}
