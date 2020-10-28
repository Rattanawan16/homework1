package com.practice.homework.controllers;

import com.practice.homework.models.User;
import com.practice.homework.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> create(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> update(@RequestBody User user, @PathVariable String id) {

        Optional<User> existingUser = userRepository.findById(id);

        if (!existingUser.isPresent())
            return ResponseEntity.notFound().build();

        user.setId(id);

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }
}
