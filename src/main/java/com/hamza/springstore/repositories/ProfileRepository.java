package com.hamza.springstore.repositories;

import com.hamza.springstore.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
//    @EntityGraph(attributePaths = "user")
//    List<Profile> findByLoyalityPointsGreaterThan(int min);
//
//    @EntityGraph(attributePaths = "user")
//    List<Profile> findByUser_EmailAndLoyalityPointsGreaterThan(String email, int min);
}