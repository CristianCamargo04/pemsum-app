package com.ufps.ingsistemas.pensumapp.controllers;

import com.ufps.ingsistemas.pensumapp.entities.UserEntity;
import com.ufps.ingsistemas.pensumapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
}
