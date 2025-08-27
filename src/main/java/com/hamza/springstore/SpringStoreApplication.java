package com.hamza.springstore;

import com.hamza.springstore.entities.Address;
import com.hamza.springstore.entities.Category;
import com.hamza.springstore.entities.Product;
import com.hamza.springstore.entities.User;
import com.hamza.springstore.repositories.AddressRepository;
import com.hamza.springstore.repositories.CategoryRepository;
import com.hamza.springstore.repositories.ProductRepository;
import com.hamza.springstore.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringStoreApplication {



    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);

    }

}
