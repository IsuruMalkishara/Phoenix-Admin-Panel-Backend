package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.Employer;
import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import com.oxcentra.phoenix.adminpanel.service.EmployerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/employer")
    public @ResponseBody
    List<Employer> getAllEmployers()  {
        return employerService.getAllEmployers();
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @GetMapping("/employer/{id}")
    public @ResponseBody
    Optional<Employer> getEmployerById(@PathVariable Integer id)  {
        log.info(String.valueOf(id));
        return employerService.getEmployerById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @DeleteMapping("/employer/{id}")
    public @ResponseBody
    Boolean deleteEmployerById(@PathVariable Integer id) {
        log.info(String.valueOf(id));
        return employerService.deleteEmployerById(id);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PutMapping("/employer/{id}")
    public @ResponseBody
    Boolean updateEmployer(@RequestBody Employer employer) {

        log.info(String.valueOf(employer.getId()));
        return employerService.updateEmployer(employer);
    }

    @CrossOrigin(origins = {"http://localhost:4200","http://localhost:3000"})
    @PostMapping("/employer/title")
    public @ResponseBody
    List<Employer> searchEmployer(@RequestBody Map<String, String> requestData) {
        String title = requestData.get("title");
        log.info(title);
        return employerService.searchEmployer(title);
    }
}
