package com.hamza.springstore.repositories;

import com.hamza.springstore.dtos.ProductSummary;
import com.hamza.springstore.entities.Category;
import com.hamza.springstore.entities.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor {
//    Using native sql
    @Procedure("findProductsByPrice")
    List<Product> findByPrice(BigDecimal lower, BigDecimal upper);

//    Using JPQL
    @Query("select p from Product p where p.price between :lower and :upper order by p.name asc")
    List<Product> findByPriceBetweenOrderByNameAsc(@Param("lower") BigDecimal lower, @Param("upper") BigDecimal upper);

// Updating scenario using JPQL
    @Modifying
    @Query("update Product p set p.price=:price where p.category.id = :categoryId")
    void updatePriceByCategory(@Param("categoryId") byte categoryId, @Param("price") BigDecimal price);

    @Query("select p.name from Product p where p.id= :id")
    String findNameById(@Param("id") Long id);


    @Query("select p from Product p where p.category = :category")
    List<ProductSummary> findByCategory(@Param("category") Category category);

//    List<Product> findAll(Example<Product> example);

}