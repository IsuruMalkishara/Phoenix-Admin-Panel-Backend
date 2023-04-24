package com.oxcentra.phoenix.adminpanel.service;


import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
@Service
public class VacancyServiceImpl implements VacancyService{

    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Vacancy> getAllVacancy()  {
       log.info("All Vacancies:");
        String url = projectAUrl + "/vacancy";
        ResponseEntity<List<Vacancy>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Vacancy>>() {}
        );
        return response.getBody();



    }
}
