package com.example.demo.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Optional<Company>> getCompany(@RequestParam String company_link){
        return companyService.getCompany(company_link);
    }

    @PostMapping
    public void registerNewCompany(@RequestBody Company company){
        companyService.addNewCompany(company);
    }
}
