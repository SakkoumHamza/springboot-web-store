package com.hamza.springstore;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name = "tags")
@Entity
public class Tag {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;

    @Column(nullable = false, name = "name")
    private String name ;

    @ToString.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "tags")
    private Set<User> users = new HashSet<>();

}