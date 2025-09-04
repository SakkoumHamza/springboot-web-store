package com.hamza.springstore.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @Column(name = "date_created",insertable = false, updatable = false)
    private Date dateCreated;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.MERGE,fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CartItem> items  =new LinkedHashSet<>();






    public BigDecimal getTotalPrice() {
        return items.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public CartItem getItem(Long id) {
        return this.getItems()
                .stream()
                .filter(i->i.getProduct().getId().equals(id))
                .findFirst().orElse(null);
    }

    public CartItem addItem(Product p) {
        var cartItem =this.getItem(p.getId());
        if(cartItem !=null)
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        else {
            cartItem = new CartItem();
            cartItem.setProduct(p);
            cartItem.setQuantity(1);
            cartItem.setCart(this);
            this.items.add(cartItem);
        }
        return cartItem;
    }


    public void removeItem(Long id) {
        var cartItem =this.getItem(id);
        this.items.remove(cartItem);
    }

    public void clear(){
        this.items.clear();
    }
}
