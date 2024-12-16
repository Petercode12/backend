package com.company.catalogue.backend.service;

import com.company.catalogue.backend.dto.CompanyDTO;
import com.company.catalogue.backend.model.Company;
import com.company.catalogue.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private CompanyRepository repo;

    @Autowired
    public CompanyService(CompanyRepository repo) {
        this.repo = repo;
    }

    public Optional<Company> getCompany(long id) {
        return  repo.findById(id);
    }

    public List<Company> getAll() {
        return repo.findAll();
    }

    public Company addCompany(CompanyDTO company) {

        Company newCompany = new Company();

        newCompany.setName(company.getName());
        newCompany.setDescription(company.getDescription());
        newCompany.setEstablished( new Date());
        newCompany.setIndustry(company.getIndustry());
        newCompany.setLocation(company.getLocation());
        newCompany.setLogo(company.getLogo());
        newCompany.setWebsite(company.getWebsite());
        newCompany.setOther_details(newCompany.getOther_details());

        return repo.save(newCompany);
    }

    public void deleteCompany(long id) {
        repo.deleteById(id);
    }

    public Company updateCompany(CompanyDTO update, long id) {
        Optional<Company> optionalCompany = repo.findById(id);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();

            company.setName(update.getName());
            company.setDescription(update.getDescription());
            company.setIndustry(update.getIndustry());
            company.setLogo(update.getLogo());
            company.setWebsite(update.getWebsite());
            company.setLocation(update.getLocation());

            return repo.save(company);
        } else {
            return null;
        }
    }
}