package com.alexeiboriskin.sbtask.dataserver;

import com.alexeiboriskin.sbtask.dataserver.models.Role;
import com.alexeiboriskin.sbtask.dataserver.models.User;
import com.alexeiboriskin.sbtask.dataserver.services.RoleService;
import com.alexeiboriskin.sbtask.dataserver.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@SpringBootApplication
public class DataserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataserverApplication.class, args);
    }

    @Bean
    CommandLineRunner populateInitialData (UserService userService, RoleService roleService){
        return args -> {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            Role roleGuest = new Role("ROLE_GUEST");

            roleAdmin = roleService.saveRole(roleAdmin);
            roleUser = roleService.saveRole(roleUser);
            roleGuest = roleService.saveRole(roleGuest);

            User admin = new User("admin", "John", "Connor", "JConnor@gmail.com",
                    "admin", new HashSet<>(Arrays.asList(roleAdmin, roleUser, roleGuest)));

            User user1 = new User("p_ivanov", "Petr", "Ivanov", "PIvanov@gmail" +
                    ".com", "pass1", new HashSet<>(Collections.singletonList(roleAdmin)));

            User user2 = new User("i_petrov", "Ivan", "Petrov", "IPetrov@gmail" +
                    ".com", "pass2", new HashSet<>(Collections.singletonList(roleUser)));

            User user3 = new User("v_semenovich", "Vasil", "Semenovich", "VSeme" +
                    "@gmail.com", "pass3", new HashSet<>(Arrays.asList(roleGuest, roleUser)));

            userService.saveUser(admin);
            userService.saveUser(user1);
            userService.saveUser(user2);
            userService.saveUser(user3);
        };
    }
}
