package com.hamza.springstore;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false, name = "name")
    private String name ;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "email")
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Address> adresses = new ArrayList<>();

    public void addAddress(Address address) {
        adresses.add(address);
        address.setUser(this);
    }
    public void removeAddress(Address address) {
        adresses.remove(address);
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

    @Builder.Default
    @ManyToMany()
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

}
