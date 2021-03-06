package com.alexeiboriskin.sbtask.dataserver.controllers;

import com.alexeiboriskin.sbtask.dataserver.models.User;
import com.alexeiboriskin.sbtask.dataserver.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class RestfulController {

    private final UserService userService;

    public RestfulController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping(value = "/{id:[\\d]+}", produces = "application/json")
    public User getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/find", produces = "application/json")
    public User getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.findByUserName(username);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public User postUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping(value = "/{id:[\\d]+}", produces = "application/json", consumes = "application/json")
    public User putUser(@PathVariable("id") long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/{id:[\\d]+}", produces = "application/json")
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }
}