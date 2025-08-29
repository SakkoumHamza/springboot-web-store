package com.hamza.springstore.services;

import com.hamza.springstore.entities.Address;
import com.hamza.springstore.entities.Category;
import com.hamza.springstore.entities.Product;
import com.hamza.springstore.entities.User;
import com.hamza.springstore.repositories.*;
import com.hamza.springstore.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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
    private final ProductCriteriaRepository productCriteriaRepository;

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

    @Transactional
    public void fetchProducts(){
        var product = new Product();
        product.setName("MacBook");

        var matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    public void fetchProductsByCriteria(){
        var products = productCriteriaRepository.findProductsByCriteria("Mac",BigDecimal.valueOf(1), BigDecimal.valueOf(12000));
        products.forEach(System.out::println);
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
    public void fetchProductsBySpecification(String name, BigDecimal lower){
        Specification<Product> spec = Specification.where(null);
        if(name!=null){
            spec.and(ProductSpec.hasName(name));
        }
        if(lower!=null){
            spec.and(ProductSpec.hasPriceGreaterThanOrEqual(lower));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchSortedProducts(){
        var sort = Sort.by( "name").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(sort).forEach(System.out::println);

    }

    public void fetchPaginatedProducts(int pageNumber, int size){
        PageRequest pageRequest = PageRequest.of(pageNumber,size);
        Page<Product> page = productRepository.findAll(pageRequest);
        var products = page.getContent();

        products.forEach(System.out::println);
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
    }


}
