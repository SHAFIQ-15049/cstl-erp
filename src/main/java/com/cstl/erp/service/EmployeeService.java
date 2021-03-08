package com.cstl.erp.service;

import com.cstl.erp.domain.Employee;
import com.cstl.erp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    public HashMap createEmployee(Employee employee)
    {
        HashMap<String,String> map = new HashMap<>();

        employeeRepository.save(employee);
        imageUploadService.imageWithMaxId(employee);

        map.put("status","success");

        return map;
    }
}
