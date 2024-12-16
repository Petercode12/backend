package com.company.catalogue.backend.dto;

import com.company.catalogue.backend.enums.Role;
import lombok.Data;

@Data
public class UserDTO {

    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private Role role;
}
