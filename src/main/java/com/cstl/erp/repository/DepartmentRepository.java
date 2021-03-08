package com.cstl.erp.repository;

import com.cstl.erp.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `department`(`name`,`short_name`,`name_in_bangla`,`description`) VALUES (?1,?2,?3,?4) ",nativeQuery = true)
    void createDepartment(String name, String short_name, String name_in_bangla, String description);


}
