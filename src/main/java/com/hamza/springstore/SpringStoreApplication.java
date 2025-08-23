package com.hamza.springstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringStoreApplication {

    public static void main(String[] args) {


//      SpringApplication.run(SpringStoreApplication.class, args);
//      var address = Address.builder().street("street").city("city").state("state").zip("zip").build();
//      user.addAddress(address);
//      System.out.println(user);

        var user = User.builder().name("Hamza").password("spring123").email("springstore@gmail.com").build() ;
//        user.addTag("tag1");
        var profile =  Profile.builder().id(1L).bio("This a facebook profile").build();
        user.setProfile(profile);
        profile.setUser(user);
        System.out.println(user);


    }

}
