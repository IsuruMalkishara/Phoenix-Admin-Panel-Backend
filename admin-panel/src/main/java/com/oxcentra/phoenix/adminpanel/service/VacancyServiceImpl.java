package com.oxcentra.phoenix.adminpanel.service;


import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import com.oxcentra.phoenix.adminpanel.dto.VacancyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;


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

    @Override
    public Optional<Vacancy> getVacancyById(int id) {
        String url = projectAUrl + "/vacancy/{id}";
        ResponseEntity<Vacancy> response = restTemplate.getForEntity(url, Vacancy.class, id);
        Vacancy vacancy = response.getBody();
        log.info(String.valueOf(vacancy));
        return Optional.ofNullable(vacancy);
    }

    @Override
    public Boolean deleteVacancyById(Integer id) {
        String url = projectAUrl + "/vacancy/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean updateVacancy(VacancyDto vacancy) {
        String url = projectAUrl + "/vacancy/"+vacancy.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<VacancyDto> requestEntity = new HttpEntity<>(vacancy, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class, vacancy.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
//        restTemplate.put(url, vacancy, vacancy.getId());
//        return true;
    }

    @Override
    public List<Vacancy> getVacanciesByEmployerId(Integer id) {
        log.info(" Vacancies of "+id);
        String url = projectAUrl + "/vacancies/"+id;
        ResponseEntity<List<Vacancy>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Vacancy>>() {}
        );
        return response.getBody();
    }
}
