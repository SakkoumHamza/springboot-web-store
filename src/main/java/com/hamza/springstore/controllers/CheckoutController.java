package com.hamza.springstore.controllers;


import com.hamza.springstore.dtos.CheckoutRequest;
import com.hamza.springstore.dtos.CheckoutResponse;
import com.hamza.springstore.entities.Order;
import com.hamza.springstore.entities.OrderItem;
import com.hamza.springstore.entities.OrderStatus;
import com.hamza.springstore.repositories.CartRepository;
import com.hamza.springstore.repositories.OrderRepository;
import com.hamza.springstore.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor

public class CheckoutController {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;

    public ResponseEntity<?> checkout(
            @Valid @RequestBody CheckoutRequest request
    ) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "No cart found")
            );
        }
        if (cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(authService.getCurrentUser());
        cart.getItems().forEach(item -> {
            var orderItem = new OrderItem();

            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setTotalPrice(item.getTotalPrice());
            orderItem.setUnitPrice(item.getProduct().getPrice());
            order.getItems().add(orderItem);
        });

        orderRepository.save(order);
        cartRepository.clearCart();
        return ResponseEntity.ok(new CheckoutResponse(order.getId()));
    }
}
