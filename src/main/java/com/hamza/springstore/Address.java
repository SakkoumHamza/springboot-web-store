package com.hamza.springstore;


import jakarta.persistence.*;
import lombok.*;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "addresses")
@Entity
public class Address {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;

    @Column(nullable = false, name = "street") //nullable is needed when dealing with hibernate , here we re working with flyway migrations
    private String street ;

    @Column(nullable = false, name = "city")
    private String city ;

    @Column(nullable = false, name = "state")
    private String state ;

    @Column(nullable = false, name = "zip")
    private String zip ;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id") // To specify the foreign key
    private User user ;

}
