package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.common.PasswordUtility;
import com.oxcentra.phoenix.adminpanel.service.AdminService;
import com.oxcentra.phoenix.adminpanel.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class EmailController {
    @Autowired
    private AdminService adminService;


    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordUtility passwordUtility;




    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/forgot")
    public @ResponseBody
    Boolean forgotPassword(@RequestBody String email) {

        log.info(email);

        return passwordUtility.sendEmail(email);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/resend")
    public @ResponseBody
    Boolean resendVerificationCode() {
        return passwordUtility.sendVerificationCode();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/verify")
    public @ResponseBody
    Boolean checkVerificationCode(@RequestBody String value) {

        log.info(value);
        Integer code = Integer.parseInt(value);

        return passwordUtility.checkVerificationCode(code);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update")
    public @ResponseBody
    Boolean updatePassword(@RequestBody String password) {

        log.info(password);

        return passwordUtility.updatePassword(password);
    }
}
