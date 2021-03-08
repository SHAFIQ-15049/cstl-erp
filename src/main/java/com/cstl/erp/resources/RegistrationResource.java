package com.cstl.erp.resources;

import com.cstl.erp.domain.User;
import com.cstl.erp.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/registration")
public class RegistrationResource {

    @Autowired
    RegistrationService registrationService;


    private final Logger log = LoggerFactory.getLogger(RegistrationResource.class);

    @PostMapping("/signup")
    public HashMap signupUser(@RequestBody User user)
    {
        log.debug("user"+user);
        return registrationService.signupUser(user);
    }

    @PostMapping("/login")
    public HashMap loginUser(@RequestBody User user){
        return registrationService.loginUser(user);
    }

}
