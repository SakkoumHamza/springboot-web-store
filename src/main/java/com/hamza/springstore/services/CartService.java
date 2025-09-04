package com.hamza.springstore.services;


import com.hamza.springstore.dtos.CartDto;
import com.hamza.springstore.dtos.CartItemDto;
import com.hamza.springstore.dtos.CartItemUpdateDto;
import com.hamza.springstore.entities.Cart;
import com.hamza.springstore.exceptions.CartNotFoundException;
import com.hamza.springstore.exceptions.ProductNotFoundException;
import com.hamza.springstore.mappers.CartMapper;
import com.hamza.springstore.repositories.CartRepository;
import com.hamza.springstore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto createCart(){
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

//    --------------------------
    public CartItemDto addToCart(UUID cartId, Long productId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();
        var product = productRepository.findProductById(productId);
        if (product == null)
            throw new ProductNotFoundException();
        var cartItem = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.todto(cartItem);
    }


//    --------------------------

    public List<Cart> getCarts(){
        return cartRepository.getAll();
    }

    public CartDto getCartDto(UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null)
           throw new CartNotFoundException();
        return cartMapper.toDto(cart);
    }

//    --------------------------
    public CartDto updateCart(UUID cartId, Long productId , CartItemUpdateDto request){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();

        var cartItem = cart.getItem(productId);

        if(cartItem==null)
            throw new ProductNotFoundException();

        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

//    --------------------------

    public void clearCart(UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();
        cart.clear();
        cartRepository.save(cart);
    }
    public void removeFromCart(UUID cartId,Long productId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart==null)
            throw new CartNotFoundException();
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

}
