package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Primary
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {

        // Step 1: Get CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Step 2: Create CriteriaQuery
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        // Step 3: Define root (FROM Product p)
        Root<Product> product = cq.from(Product.class);

        // Step 4: Build predicates dynamically
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(product.get("name"), "%" + name + "%"));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        }

        // Step 5: Set clauses
        cq.select(product).where(predicates.toArray(new Predicate[predicates.size()]));

        // Step 6: Execute query
        return entityManager.createQuery(cq).getResultList();
    }
}
