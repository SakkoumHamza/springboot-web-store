package com.hamza.springstore.services;

import com.hamza.springstore.entities.Address;
import com.hamza.springstore.entities.Category;
import com.hamza.springstore.entities.Product;
import com.hamza.springstore.entities.User;
import com.hamza.springstore.repositories.AddressRepository;
import com.hamza.springstore.repositories.CategoryRepository;
import com.hamza.springstore.repositories.ProductRepository;
import com.hamza.springstore.repositories.UserRepository;
import com.hamza.springstore.repositories.ProfileRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");


    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        user.addAddress(address);

        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
        productRepository.deleteById(4L);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory((byte) 5, BigDecimal.valueOf(10));
    }

    public String getProductNameById(){
        return productRepository.findNameById(5l);
    }

    public void fetchProducts(){
        productRepository.findByCategory(new Category((byte) 2));
    }

    @Transactional
    public List<Product> fetchProductsByPrice(){
        return productRepository.findByPrice(BigDecimal.valueOf(1),BigDecimal.valueOf(20000));
    }

    public void fetchUsers(){
        var profiles = userRepository.findUsers(1);
        profiles.forEach(u -> {
            System.out.println(u);
        });
    }


}
