package com.cstl.erp.repository;

import com.cstl.erp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RegistrationRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT `user_id` FROM `user` WHERE `email`=?1 AND `password`=?2",nativeQuery = true)
    Long loginUserId(String email, String password);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `user`(`name`, `email`, `phone`, `password`) VALUES (?1, ?2 , ?3, ?4)", nativeQuery = true)
    void signupUser(String name, String email, String phone, String password);

}
