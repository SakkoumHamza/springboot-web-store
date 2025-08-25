package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository  extends CrudRepository<User,Long> {

    boolean existsByName(String name);
}
