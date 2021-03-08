package com.cstl.erp.repository;

import com.cstl.erp.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `company`(`name`, `short_name`, `name_in_bangla`, `description`,`address`,`phone`) VALUES (?1, ?2 , ?3, ?4, ?5, ?6)", nativeQuery = true)
    void createCompany(String name, String short_name, String name_in_bangla, String description,String address,String phone);
}
