package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.AdminDto;
import com.oxcentra.phoenix.adminpanel.model.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService extends UserDetailsService {
    List<Admin> getAllAdmin();


    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    Admin getAdminById(int id);

    Boolean updatePassword(int userId, String userEmail, String password);

    String signup(AdminDto adminDto);

    Boolean sendVerificationCode();

    Boolean checkVerificationCode(Integer code);

    Boolean addNewAdmin();

    int getAdminId(String email);
}
