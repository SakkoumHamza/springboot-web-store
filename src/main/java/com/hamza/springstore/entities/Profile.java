package com.hamza.springstore.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name = "profiles")
@Entity
public class Profile {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;

    @Column(nullable = false, name = "bio")
    private String bio ;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber ;

    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth ;

    @Column(nullable = false, name = "loyality_points")
    private int loyalityPoints ;


    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    private User user;

}
