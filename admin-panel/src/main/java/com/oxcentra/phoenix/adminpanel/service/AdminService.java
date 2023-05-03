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
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    Admin getAdminById(int id);

    Admin getAdminByUserName(String userName);

    List<Admin> getAllAdmins();

    Boolean deleteAdminById(Integer id);

    Boolean updateAdmin(Admin admin);

    Boolean addAdmin(Admin admin);
}
