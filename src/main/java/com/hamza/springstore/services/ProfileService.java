package com.hamza.springstore.services;

import com.hamza.springstore.entities.Profile;
import com.hamza.springstore.repositories.ProductRepository;
import com.hamza.springstore.repositories.ProfileRepository;
import com.hamza.springstore.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeTypeAnnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
//    private final UserRepository userRepository;

//    public void fetchProfilesByLoyaltyPoints() {
//        var profiles = profileRepository.findByLoyalityPointsGreaterThan(2);
//        profiles.forEach(p-> {
//            var user = userRepository.findById(p.getId()).orElseThrow();
//            System.out.println("Id :  " + p.getId());
//            System.out.println("Email:  " + user.getEmail());
//        });
//    }

//    @Transactional
//    public void fetchProfilesByEmail() {
//        var profiles = profileRepository.findByUser_EmailAndLoyalityPointsGreaterThan("hamzasakkoum@gmail.com",0);
//        profiles.forEach(p-> {
//            System.out.println("Profile"+ p);
//            System.out.println("User"+p.getUser().getEmail);
//            System.out.println("Email:  " + user.getEmail());
//        });
//    }

//    public void fetchProfiles() {
//        var profiles = profileRepository.findProfiles(1);
//        profiles.forEach(p -> {
//            System.out.println(p);
//        });
//    }
}
