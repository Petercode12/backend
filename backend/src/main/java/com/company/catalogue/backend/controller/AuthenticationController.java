package com.company.catalogue.backend.controller;
import com.company.catalogue.backend.dto.AuthenticationDTO;
import com.company.catalogue.backend.dto.AuthenticationResponse;
import com.company.catalogue.backend.service.jwt.UserDetailsServiceImpl;
import com.company.catalogue.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {

    // Autowire JwtUtil, AuthenticationManager, and UserDetailsServiceImpl
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Define a POST mapping for the "/authenticate" endpoint
    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            // Authenticate the user with the provided credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
        } catch (BadCredentialsException e) {
            // Handle bad credentials by throwing an exception
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            // Handle a disabled user by sending an error response and returning null
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        // Load user details and generate a JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Return an AuthenticationResponse containing the token
        return new AuthenticationResponse(jwt);

    }

}
