package com.cstl.erp.resources;

import com.cstl.erp.domain.Department;
import com.cstl.erp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/department")
public class DepartmentResource {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create_department")
    public HashMap createDepartments(@RequestBody Department department)
    {
        return departmentService.createDepartment(department);
    }

    @GetMapping("/find_all_department")
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

}
