package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import com.oxcentra.phoenix.adminpanel.dto.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VacancyService {


    List<Vacancy> getAllVacancy();

    Optional<Vacancy> getVacancyById(int id);

    Boolean deleteVacancyById(Integer id);

    Boolean updateVacancy(VacancyDto vacancy);

    List<Vacancy> getVacanciesByEmployerId(Integer id);

    Boolean addVacancy(VacancyDto vacancy);

    List<Vacancy> searchVacancy(String title);
}
