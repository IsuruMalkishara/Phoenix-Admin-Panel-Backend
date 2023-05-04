package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.Employer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployerService {
    List<Employer> getAllEmployers();

    Optional<Employer> getEmployerById(Integer id);

    Boolean deleteEmployerById(Integer id);

    Boolean updateEmployer(Employer employer);

    List<Employer> searchEmployer(String title);
}
