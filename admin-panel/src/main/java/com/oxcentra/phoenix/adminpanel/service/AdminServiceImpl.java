package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.common.PasswordUtility;
import com.oxcentra.phoenix.adminpanel.dto.AdminDto;
import com.oxcentra.phoenix.adminpanel.dto.JwtRequest;
import com.oxcentra.phoenix.adminpanel.model.Admin;
import com.oxcentra.phoenix.adminpanel.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordUtility passwordUtility;

    List<Admin> adminList=new ArrayList<>();
    Admin admin1;
    int verificationCode;
    String userEmail;

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        adminList=getAllAdmin().stream().filter(a->
                email.equals(a.getEmail())).collect(Collectors.toList());

        log.info(String.valueOf(adminList));

        if(adminList.size()>0){
            return new User(adminList.get(0).getEmail(),adminList.get(0).getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found");
        }


    }

    @Override
    public Admin getAdminById(int id) {
        Optional<Admin> admin=adminRepository.findById(id);


        if(admin.isPresent()){
            return admin.get();
        }
        throw new RuntimeException("employer not found");
    }

    @Override
    public Boolean updatePassword(int userId, String userEmail, String password) {
        String body="Your password Changed";
        String subject="Password Changed";

        Admin admin=getAdminById(userId);
        admin.setPassword(password);
        adminRepository.save(admin);
        emailService.sendEmail(userEmail,body,subject);
        return true;
    }

    @Override
    public String signup(AdminDto adminDto) {
        userEmail=adminDto.getEmail();
        String val;
        String message;
        List<Admin> admin=getAllAdmin().stream().filter(a->
                adminDto.getEmail().contains(a.getEmail())).collect(Collectors.toList());

        log.info(String.valueOf(admin));

         admin1=new Admin();
        admin1.setName(adminDto.getName());
        admin1.setEmail(adminDto.getEmail());
        admin1.setPhone(adminDto.getPhone());
        admin1.setPassword(adminDto.getPassword());
        admin1.setProfilePicture(adminDto.getProfilePicture());
        if(adminDto.getPassword().equals(adminDto.getConfirmPassword()) && admin.size()==0){
            if(sendVerificationCode().equals(true)){
                message ="Verification code sent";
                val = "1";
            }else{
                message =  " error";
                val = "4";
            }
        }else if(admin.size()>0){
            message="Already registered email";
            val="2";
        }else{
            message="Confirmed password not matching with password";
            val="3";
        }
        log.info(message);
        return val;
    }

    @Override
    public Boolean sendVerificationCode(){
        Random random = new Random();
        verificationCode = random.nextInt(90000000) + 10000000;
        String body="Verification code: "+verificationCode;
        String subject="Verification Code";
        if(emailService.sendEmail(userEmail,body,subject).equals(true)){
            log.info("verification code sent");
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean checkVerificationCode(Integer code) {
        if(code.equals(verificationCode)){
           return addNewAdmin();
        }
        return false;
    }

    @Override
    public Boolean addNewAdmin() {
        String body="Successfully registered";
        String subject="Registration Successful";

        adminRepository.save(admin1);
        return emailService.sendEmail(userEmail,body,subject);
    }

    @Override
    public int getAdminId(String email) {
        List<Admin> admin=getAllAdmin().stream().filter(a->
                email.contains(a.getEmail())).collect(Collectors.toList());

        return admin.get(0).getId();
    }


}
