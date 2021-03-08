package com.cstl.erp.service;

import com.cstl.erp.domain.Company;
import com.cstl.erp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public HashMap createCompany(Company company){

        HashMap<String,String> map = new HashMap<>();

         companyRepository.createCompany(company.getName(),company.getShortName(),company.getNameInBangla()
        ,company.getDescription(),company.getAddress(),company.getPhone());

         map.put("status","success");
         map.put("response","insertion_successful");

         return map;
    }

    public List<Company> getAllCompanies()
    {
        return companyRepository.findAll();
    }
}
