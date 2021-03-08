package com.cstl.erp.resources;

import com.cstl.erp.domain.Company;
import com.cstl.erp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/company")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create_company")
    public HashMap createCompany(@RequestBody  Company company)
    {
        return companyService.createCompany(company);
    }

    @GetMapping("/findcompanies")
    public List<Company> findAllCompanies()
    {
        return this.companyService.getAllCompanies();
    }
}
