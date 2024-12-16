package com.company.catalogue.backend.dto;

import com.company.catalogue.backend.enums.Role;
import lombok.Data;

@Data
public class SignupDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Role role;
}
