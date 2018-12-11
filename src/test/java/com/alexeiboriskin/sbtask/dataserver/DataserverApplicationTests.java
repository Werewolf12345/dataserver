package com.alexeiboriskin.sbtask.dataserver;

import com.alexeiboriskin.sbtask.dataserver.models.Role;
import com.alexeiboriskin.sbtask.dataserver.models.User;
import com.alexeiboriskin.sbtask.dataserver.services.RoleService;
import com.alexeiboriskin.sbtask.dataserver.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataserverApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void contextLoads() {
    }

    @Transactional
    @Test
    public void givenUserWhenSaveThenGetOk() {
        Role role_test1 = new Role("ROLE_TEST1");
        Role role_test2 = new Role("ROLE_TEST2");
        Role role_test3 = new Role("ROLE_TEST3");

        role_test1 = roleService.saveRole(role_test1);
        assertNotEquals(0L, role_test1.getId());

        role_test2 = roleService.saveRole(role_test2);
        assertNotEquals(0L, role_test2.getId());

        role_test3 = roleService.saveRole(role_test3);
        assertNotEquals(0L, role_test3.getId());

        User testUser = new User("test_username", "test_firstname", "test_lastname",
                "test_email", "test_password", new HashSet<>(Arrays.asList(role_test1,
                role_test2, role_test3)));
        testUser = userService.saveUser(testUser);
        assertNotEquals(0L, testUser.getId());

        User userFromDb = userService.getUserById(testUser.getId());
        assertEquals("First name from DB ", "test_firstname", userFromDb.getFirstName());

        assertNotNull(userService.findByUserName("test_username"));
    }
}
