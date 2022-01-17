package com.babacar.covidherlper_back_2.controller;

import com.babacar.covidherlper_back_2.model.Users;
import com.babacar.covidherlper_back_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<Object> add(@RequestBody Users users) {

        return userService.add(users);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<Object> login(@RequestBody Users users) {
        return userService.login(users);
    }

    @PutMapping(value = "/users/{user_id}")
    public ResponseEntity<Object> update(@PathVariable long user_id, @RequestBody Users users) {

        return userService.update(user_id, users);
    }

    @GetMapping(value = "/users")
    public List<Users> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<Object> getOne(@PathVariable long user_id) {
       return userService.getOne(user_id);
    }
}
