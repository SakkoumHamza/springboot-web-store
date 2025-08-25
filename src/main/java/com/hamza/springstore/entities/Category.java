package com.hamza.springstore.entities;


import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Byte id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "category")
    private Set <Product> product = new HashSet<>();
}
