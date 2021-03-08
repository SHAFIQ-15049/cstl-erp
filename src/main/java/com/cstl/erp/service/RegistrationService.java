package com.cstl.erp.service;

import com.cstl.erp.domain.User;
import com.cstl.erp.repository.RegistrationRepository;
import com.cstl.erp.resources.RegistrationResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    private final Logger log = LoggerFactory.getLogger(RegistrationResource.class);

    public HashMap signupUser(User user)
    {

        HashMap<String,String> map = new HashMap<>();

        user.setPassword(makeHash(user.getPassword()));
        registrationRepository.signupUser(user.getName(),user.getEmail(),user.getPhone(),user.getPassword());

        map.put("status","success");
        map.put("response","Signup Successful");

        return map;
    }

    public String makeHash (String pass){
        try{
            byte[] salt = new byte[16];
            KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            return enc.encodeToString(hash);
        }catch (Exception e){}
        return null;
    }

    public HashMap loginUser(User user){
        user.setPassword(makeHash(user.getPassword()));
        HashMap<String,String> map = new HashMap<>();

        List<Long> n = new ArrayList<>();

        //System.out.println("-----------"+user.getEmail() +"----"+user.getPassword());
        n.add(registrationRepository.loginUserId(user.getEmail(), user.getPassword()));
        //System.out.println("-----------"+registrationRepository.loginUserId(user.getEmail(),user.getPassword()));

        //log.debug("n = "+n);

        if(n.size()>0 && n.get(0)!=null){
            map.put("status","success");
            map.put("response","login successful");
        }
        else{
            map.put("status","failed");
            map.put("response","login failed");
        }

        log.debug("map"+map);

        return map;
    }

}
