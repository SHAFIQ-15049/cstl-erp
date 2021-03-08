package com.cstl.erp.service;

import com.cstl.erp.domain.Department;
import com.cstl.erp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public HashMap createDepartment(Department department){
        HashMap<String,String> map = new HashMap<>();

        departmentRepository.createDepartment(department.getName(),department.getShortName(),department.getNameInBangla()
        ,department.getDescription());

        map.put("status","successful");
        map.put("response","insertion successful");

        return map;

    }

    public List<Department> getAllDepartments(){
        HashMap<String,String> map = new HashMap<>();

        return departmentRepository.findAll();

//        map.put("status","success");
//        map.put("response","geting data from server");
//
//        return map;
    }
}
