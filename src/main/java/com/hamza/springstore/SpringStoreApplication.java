package com.hamza.springstore;
import com.hamza.springstore.services.ProfileService;
import com.hamza.springstore.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringStoreApplication {



    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);
        var userService = context.getBean(UserService.class);
//        Fetching products by specifications
        userService.fetchProductsBySpecification("Mac", BigDecimal.valueOf(1));
//        Sorting
        userService.fetchSortedProducts();
//        Pagination
        userService.fetchPaginatedProducts(1, 10);


    }

}
