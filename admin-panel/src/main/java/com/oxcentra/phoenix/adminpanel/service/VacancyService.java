package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {


    List<Vacancy> getAllVacancy();
}
