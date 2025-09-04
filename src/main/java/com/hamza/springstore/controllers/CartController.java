package com.hamza.springstore.controllers;


import com.hamza.springstore.dtos.CartItemUpdateDto;
import com.hamza.springstore.exceptions.CartNotFoundException;
import com.hamza.springstore.exceptions.ProductNotFoundException;
import com.hamza.springstore.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RequestMapping("/carts")
@RestController
@AllArgsConstructor
@Tag(name = "Carts")
public class CartController {
    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<?> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cartDto = cartService.createCart();
        var uri = uriBuilder.path("/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @GetMapping("")
    public ResponseEntity<?> getCarts(
    ) {
        var carts = cartService.getCarts();
        if (carts.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carts);
    }

    @Operation(summary = "Adds items to the cart")
    @PostMapping("/{cartId}/items")
    public ResponseEntity<?> addToCart(
            @PathVariable UUID cartId,
            @Parameter(description = "Items to add")
            @RequestBody Long productId
    ){
            var cartItemDto = cartService.addToCart(cartId, productId);
            return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCart(
            @PathVariable(name = "cartId") UUID cartId,
            @PathVariable(name="productId") Long productId,
            @Valid @RequestBody CartItemUpdateDto request
    ){
        var cartDto =cartService.updateCart(cartId,productId,request);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<?> getCartItems(
            @PathVariable UUID cartId
    ){
       var cartDto = cartService.getCartDto(cartId);
       return ResponseEntity.ok(cartDto);
    }


    @DeleteMapping({"/{cartId}/delete/{productId}"})
    public ResponseEntity<?> deletefromCart(
            @PathVariable(name = "cartId") UUID cartId,
            @PathVariable(name = "productId") Long productId
    ) {
        cartService.removeFromCart(cartId, productId);
       return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{cartId}/items")
    public ResponseEntity<?> clearCart(
            @PathVariable(name = "cartId") UUID cartId
    ) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleCartNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Cart not found")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleProductNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", "Product not found")
        );
    }

}
