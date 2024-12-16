package com.company.catalogue.backend.service.auth;

import com.company.catalogue.backend.dto.SignupDTO;
import com.company.catalogue.backend.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
