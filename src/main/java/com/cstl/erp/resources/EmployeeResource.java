package com.cstl.erp.resources;

import com.cstl.erp.domain.Employee;
import com.cstl.erp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @PostMapping("/create_employee")
    public HashMap createNewEmployee(@RequestBody Employee employee){

//        log.debug("employee-------info"+employee);
//        Employee emp = new Employee(employee.getEmpId(),employee.getEmpName());
//        log.debug("employee-------info"+emp);

        return employeeService.createEmployee(employee);
    }



}


