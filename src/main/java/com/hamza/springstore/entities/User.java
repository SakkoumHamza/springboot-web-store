package com.hamza.springstore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false, name = "name")
    private String name ;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "email")
    private String email;


    @Builder.Default
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true )
    private List<Address> addresses = new ArrayList<>();
    @Builder.Default
    @ManyToMany()
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade= CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist",
            joinColumns =@JoinColumn(name = "user_id") , // refrencing the owner of the relationShip
            inverseJoinColumns = @JoinColumn(name = "product_id") // Target side of the relationShip
    )
    private Set<Product> wishList = new HashSet<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    public void addTag(String tagName) {
        var tag = Tag.builder().name(tagName).build();
        tags.add(tag);
        tag.getUsers().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        System.out.println("Tag " + tag.getName() + " removed");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "password = " + password + ", " +
                "email = " + email + ")";
    }


}
