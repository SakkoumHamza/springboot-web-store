package com.hamza.springstore;
import com.hamza.springstore.services.ProfileService;
import com.hamza.springstore.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringStoreApplication {



    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);
    }


}
