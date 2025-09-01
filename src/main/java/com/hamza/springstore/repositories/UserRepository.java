package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    @Query("select u.id as id ,u.email as email from User u where u.profile.loyalityPoints > :min order by u.email")
    List<User> findUsers(@Param("min") int min);
}
