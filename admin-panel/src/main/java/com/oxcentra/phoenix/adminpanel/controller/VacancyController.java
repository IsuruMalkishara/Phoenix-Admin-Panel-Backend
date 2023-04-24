package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import com.oxcentra.phoenix.adminpanel.model.Admin;
import com.oxcentra.phoenix.adminpanel.service.VacancyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/vacancy")
    public @ResponseBody
    List<Vacancy> getVacancies()  {
         return vacancyService.getAllVacancy();
    }
}
