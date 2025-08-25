package com.hamza.springstore;

import com.hamza.springstore.entities.Address;
import com.hamza.springstore.entities.User;
import com.hamza.springstore.repositories.AddressRepository;
import com.hamza.springstore.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringStoreApplication {

    public static void main(String[] args) {


         ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);

        var userRepository = context.getBean(UserRepository.class);

        var addressRepository = context.getBean(AddressRepository.class);

        var addressById = addressRepository.findById(1L).get();

    }

}
