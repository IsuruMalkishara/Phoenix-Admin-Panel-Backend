package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.common.JwtUtility;
import com.oxcentra.phoenix.adminpanel.dto.AdminDto;
import com.oxcentra.phoenix.adminpanel.dto.Employer;
import com.oxcentra.phoenix.adminpanel.dto.JwtRequest;
import com.oxcentra.phoenix.adminpanel.dto.JwtResponse;
import com.oxcentra.phoenix.adminpanel.model.Admin;
import com.oxcentra.phoenix.adminpanel.service.AdminService;
import com.oxcentra.phoenix.adminpanel.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RestController
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AdminService adminService;


    @Autowired
    private AuthenticationManager authenticationManager;

    public AdminController(JwtUtility jwtUtility,AdminService adminService,AuthenticationManager authenticationManager){
        this.jwtUtility = jwtUtility;
        this.adminService=adminService;
        this.authenticationManager=authenticationManager;
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PostMapping("/login")
    @ResponseBody
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) throws Exception{
        
        log.info(jwtRequest.getUserName());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
        }catch (BadCredentialsException e){
            log.info("Error");
            throw new Exception("Invalid credential",e);

        }


        final Boolean result=true;
        final String message="SUCCESS";
        final String token=jwtUtility.generateToken(jwtRequest.getUserName());
        final Date expiresAt=jwtUtility.extractExpiration(token);
        final int expiresIn=jwtUtility.jwtExpirationInMs;

        log.info(token);

        JwtResponse jwtResponse=new JwtResponse();

        jwtResponse.setId(adminService.getAdminByUserName(jwtRequest.getUserName()).getId());
        jwtResponse.setUserType(adminService.getAdminByUserName(jwtRequest.getUserName()).getUserType());
        jwtResponse.setResult(true);
        jwtResponse.setMessage("Success");
        jwtResponse.setAccess_token(jwtUtility.generateToken(jwtRequest.getUserName()));
        jwtResponse.setExpires_at(jwtUtility.extractExpiration(token));
        jwtResponse.setExpires_in(jwtUtility.jwtExpirationInMs);

        return jwtResponse;
    }




    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/admin/{id}")
    public @ResponseBody
    Admin getAdminById(@PathVariable int id)  {

        return adminService.getAdminById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/admin")
    public @ResponseBody
    List<Admin> getAllAdmins()  {
        return adminService.getAllAdmins();
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @DeleteMapping("/admin/{id}")
    public @ResponseBody
    Boolean deleteAdminById(@PathVariable Integer id) {
        log.info(String.valueOf(id));
        return adminService.deleteAdminById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PutMapping("/admin/{id}")
    public @ResponseBody
    Boolean updateAdmin(@RequestBody Admin admin) {

        log.info(String.valueOf(admin.getId()));
        return adminService.updateAdmin(admin);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PostMapping("/admin")
    public @ResponseBody
    Boolean addAdmin(@RequestBody Admin admin) {

        log.info(admin.getUserName());
        return adminService.addAdmin(admin);
    }
}
