package com.company.catalogue.backend.controller;

import com.company.catalogue.backend.dto.CompanyDTO;
import com.company.catalogue.backend.model.Company;
import com.company.catalogue.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/company")
@RestController
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Get company by id provided as a query param e.g. <url-here>?id=3
    @GetMapping("")
    public Company getCompany(@RequestParam int id) {
        Optional<Company> company = companyService.getCompany(id);
        return (Company) company.orElse(null);
    }

    // Get all companies from the database
    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAll();
    }

    // Add company to the DB. Data is passed in through raw JSON
    @PostMapping("/add")
    public void addCompany(@RequestBody CompanyDTO company) {
        companyService.addCompany(company);
    }

    // Update company by id
    @PutMapping("/update")
    public void updateCompany(@RequestBody CompanyDTO newCompany, @RequestParam int id) {
        companyService.updateCompany(newCompany, id);
    }

    // Delete story by id
    @DeleteMapping("/delete")
    public void deleteCompany(@RequestParam int id) {
        companyService.deleteCompany(id);
    }
}