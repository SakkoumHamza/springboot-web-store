package com.hamza.springstore;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Builder.Default
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category = new Category();


}