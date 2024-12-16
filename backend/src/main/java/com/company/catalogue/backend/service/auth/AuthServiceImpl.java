package com.company.catalogue.backend.service.auth;

import com.company.catalogue.backend.dto.SignupDTO;
import com.company.catalogue.backend.dto.UserDTO;
import com.company.catalogue.backend.model.User;
import com.company.catalogue.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    // createUser method to register a new user
    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        // Extract user details from the provided DTO
        String email = signupDTO.getEmail();

        // Check if a user with the same email already exists
        User existingUser = userRepository.findFirstByEmail(email);

        if (existingUser != null) {
            // Throw an exception if the email is already in use
            throw new EmailAlreadyExistsException("Email already exists in our system!");
        }

        // Create a new User entity and set its properties
        User user = new User();
        user.setFirstname(signupDTO.getFirstname());
        user.setLastname(signupDTO.getLastname());
        user.setRole(signupDTO.getRole());
        user.setEmail(email);
        // Use BCryptPasswordEncoder to hash and store the user's password securely
        user.setPasswordHash(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));

        // Save the new user to the database
        User createdUser = userRepository.save(user);

        // Map the created User entity to a UserDTO for the response
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setFirstname(createdUser.getFirstname());
        userDTO.setLastname(createdUser.getLastname());
        userDTO.setRole(createdUser.getRole());

        return userDTO;
    }


}