package com.company.catalogue.backend.controller;

import com.company.catalogue.backend.dto.SignupDTO;
import com.company.catalogue.backend.dto.UserDTO;
import com.company.catalogue.backend.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    // Autowire the AuthService to handle user sign-up
    @Autowired
    private AuthService authService;

    // Define a POST mapping for the "/sign-up" endpoint
    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupDTO signupDTO) {
        // Call the AuthService to create a new user
       UserDTO createdUser = authService.createUser(signupDTO);
        // Check if the user creation was successful
       if (createdUser == null){
           return new ResponseEntity<>("User not created, come again later!", HttpStatus.BAD_REQUEST);
       }
        // Return an appropriate ResponseEntity based on the outcome
       return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
