package com.hamza.springstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringStoreApplication {

    public static void main(String[] args) {


//      SpringApplication.run(SpringStoreApplication.class, args);
//      var address = Address.builder().street("street").city("city").state("state").zip("zip").build();
//      user.addAddress(address);
//      System.out.println(user);

        var user = User.builder().name("Hamza").password("spring123").email("springstore@gmail.com").build() ;
//        user.addTag("tag1");
//        var profile =  Profile.builder().id(1L).bio("This a facebook profile").build();
//        user.setProfile(profile);
//        profile.setUser(user);
//        System.out.println(user);
//        var product = Product.builder().name("Macbook Pro M3").price(new BigDecimal(30000)).category( (byte) 2).build()
//        Category category = Category.builder().id(2).name("Laptops").build();
//        System.out.println(product);


    }

}
