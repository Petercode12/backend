package com.company.catalogue.backend.dto;

import lombok.Data;

@Data
public class CompanyDTO {

    private String name;

    private String description;

    private String location;

    private String website;

    private String logo;

    private String industry;
}