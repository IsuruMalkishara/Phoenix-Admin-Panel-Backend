package com.oxcentra.phoenix.adminpanel.service;

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
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;

    List<Admin> adminList=new ArrayList<>();

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
}
