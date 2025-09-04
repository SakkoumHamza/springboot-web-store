package com.hamza.springstore.repositories.specifications;


import com.hamza.springstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    void deleteById(Long id);
//
//    @Query("select c.products from Cart c" )
//    List<Product> getProducts();
//
//    @Query("select c.user from Cart c ")
//    User getUser();
//
//    Cart getCartById(Long id);
//
    @Query("select c from Cart c ")
    List<Cart> getAll();

}
