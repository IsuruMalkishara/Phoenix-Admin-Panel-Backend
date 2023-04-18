package com.oxcentra.phoenix.adminpanel.common;

import com.oxcentra.phoenix.adminpanel.model.Admin;
import com.oxcentra.phoenix.adminpanel.service.AdminService;
import com.oxcentra.phoenix.adminpanel.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PasswordUtility {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminService adminService;

    int verificationCode;
    String userEmail;
    String userType;
    int userId;

    public Boolean sendEmail(String email) {

        userEmail=email;
        List<Admin> foundAdmin=new ArrayList<>();


        foundAdmin=adminService.getAllAdmin().stream().filter(c->
                email.contains(c.getEmail())).collect(Collectors.toList());



        if(foundAdmin.size()>0 ){
            userType="employer";
            userId=foundAdmin.get(0).getId();
            return sendVerificationCode();
        }else{
            log.info("email not found");
            return false;
        }


    }

    public Boolean sendVerificationCode() {
        log.info("email found");
        Random random = new Random();
        verificationCode = random.nextInt(90000000) + 10000000;
        String body="Verification code: "+verificationCode;
        String subject="Verification Code";

        return emailService.sendEmail(userEmail,body,subject);
    }

    public Boolean checkVerificationCode(Integer code) {
        if(code.equals(verificationCode)){
            return true;
        }else{
            return false;
        }
    }

    public Boolean updatePassword(String password) {

            return adminService.updatePassword(userId,userEmail,password);


    }
}
