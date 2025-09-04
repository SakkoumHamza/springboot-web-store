package com.hamza.springstore.controllers;


import com.hamza.springstore.entities.Cart;
import com.hamza.springstore.mappers.CartMapper;
import com.hamza.springstore.repositories.ProductRepository;
import com.hamza.springstore.repositories.specifications.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/carts")
@RestController
@AllArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;


    @PostMapping("")
    public ResponseEntity<?> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cart = new Cart();
        cartRepository.save(cart);
        var cartDto = cartMapper.toDto(cart);

        var uri = uriBuilder.path("/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @GetMapping("")
    public ResponseEntity<?> getCarts(
    ) {
        var carts = cartRepository.getAll();
        if (carts.isEmpty())
            return ResponseEntity.notFound().build();
        carts = carts.stream().toList();
        return ResponseEntity.ok(carts);
    }


//    @GetMapping("{id}")
//    public ResponseEntity<?> getCart(
//        @PathVariable(name = "id") Long cartId
//    ) {
//        var cart = cartRepository.getCartById(cartId);
//        if (cart == null)
//            return ResponseEntity.notFound().build();
//        else if(cart.getProducts().isEmpty())
//            return ResponseEntity.badRequest().body("No products in the cart yet");
//        return ResponseEntity.ok(cart);
//    }
//
//    @DeleteMapping("{id}/delete")
//    public ResponseEntity<?> deleteCart(
//            @PathVariable(name = "id") Long cartId
//    ) {
//        var cart = cartRepository.getCartById(cartId);
//        if (cart == null)
//            return ResponseEntity.notFound().build();
//        cartRepository.deleteById(cartId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping({"/{id}/add/{productId}"})
//    public ResponseEntity<?> addToCart(
//            @PathVariable(name = "id") Long cartId,
//            @PathVariable(name = "productId") Long productId
//    ) {
//        var cart = cartRepository.getCartById(cartId);
//        var product = productRepository.findById(productId).orElse(null);
//        if (cart == null)
//            return ResponseEntity.notFound().build();
//        if (product == null)
//            return ResponseEntity.notFound().build();
//        cart.addProduct(product);
//        return ResponseEntity.ok(cart);
//    }
//
//
//    @PostMapping({"/{id}/delete/{productId}"})
//    public ResponseEntity<?> deletefromCart(
//            @PathVariable(name = "id") Long cartId,
//            @PathVariable(name = "productId") Long productId
//    ) {
//        var cart = cartRepository.getCartById(cartId);
//        var product = productRepository.findById(productId).orElse(null);
//        if (cart == null)
//            return ResponseEntity.notFound().build();
//        if (product == null)
//            return ResponseEntity.notFound().build();
//        cart.removeProduct(product);
//        return ResponseEntity.ok(cart);
//    }

}
