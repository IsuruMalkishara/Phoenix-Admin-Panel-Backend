package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.AdminDto;
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


    List<Admin> adminList=new ArrayList<>();
    Admin admin1;
    int verificationCode;
    String userEmail;

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        adminList=getAllAdmin().stream().filter(a->
                userName.equals(a.getUserName())).collect(Collectors.toList());

        log.info(String.valueOf(adminList));

        if(adminList.size()>0){
            return new User(adminList.get(0).getUserName(),adminList.get(0).getPassword(),new ArrayList<>());
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
    public Admin getAdminByUserName(String userName) {
        List<Admin> admin=getAllAdmin().stream().filter(a->
                userName.equals(a.getUserName())).collect(Collectors.toList());
     log.info(String.valueOf(admin));
        return admin.get(0);
    }

    @Override
    public List<Admin> getAllAdmins() {
        log.info(String.valueOf(adminRepository.findAll()));
        return adminRepository.findAll();
    }

    @Override
    public Boolean deleteAdminById(Integer id) {
        log.info("Delete admin: "+id);
        adminRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        log.info("update admin "+admin.getUserName());
        admin.setPassword(getAdminById(admin.getId()).getPassword());
        adminRepository.save(admin);
        return true;
    }

    @Override
    public Boolean addAdmin(Admin admin) {
        log.info("add admin "+admin.getUserName());
        adminRepository.save(admin);
        return true;
    }


}
