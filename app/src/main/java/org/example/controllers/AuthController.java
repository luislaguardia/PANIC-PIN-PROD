package org.example.controllers;

import org.example.DTO.AuthResponseDTO;
import org.example.Entities.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Made the response a dictionary for easy of testing
    @PostMapping("/signup")
    public AuthResponseDTO signupUser(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/signin")
    public AuthResponseDTO signinUser(@RequestBody User user) {
        return userService.verify(user);
    }

    //For testing purposes
    @GetMapping("/test")
    public String getMethodName() {
        return "success";
    }

}
