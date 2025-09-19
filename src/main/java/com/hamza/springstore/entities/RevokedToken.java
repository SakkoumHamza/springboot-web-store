package com.hamza.springstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "revoked_tokens")
@Getter
@Setter
public class RevokedToken {
    @Id
    @Column(unique = true, nullable = false)
    private String jti;


    private Date expiryDate;
}
