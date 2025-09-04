package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
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


    @EntityGraph(attributePaths = "category")
    @Query("select p from Product p ")
    List<Product> findAllWithCategory();

    @EntityGraph(attributePaths = "category")
    List<Product> findByCategoryId(Byte categoryId);

    @Query("select p from Product p")
    Product findProductById(Long id);
}