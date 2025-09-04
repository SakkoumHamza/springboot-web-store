package com.hamza.springstore.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
}
