package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    boolean existsByStreet(String street);
}