package com.rahul.demoangular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.demoangular.dao.UserRepository;
import com.rahul.demoangular.entity.User;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> uList = (List<User>) userRepository.findAll();
        if (uList.size() <= 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(uList));
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.of(Optional.of(user));
    }

    @CrossOrigin
    @PostMapping("/users")
    public ResponseEntity<User> adduser(@RequestBody User user) {
        User b = null;
        try {
            b = userRepository.save(user);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> User(@PathVariable("id") String id) {
        try {
            this.userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id,
            @RequestBody User user) {
                // System.out.println();
        try {
            Optional<User> u = userRepository.findById(id);
            user.setId(id);
            userRepository.save(user);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
