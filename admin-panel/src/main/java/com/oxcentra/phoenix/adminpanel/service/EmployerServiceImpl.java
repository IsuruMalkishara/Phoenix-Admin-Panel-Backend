package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.Employer;
import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import com.oxcentra.phoenix.adminpanel.dto.VacancyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployerServiceImpl implements EmployerService{

    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Employer> getAllEmployers() {
        log.info("All Employers:");
        String url = projectAUrl + "/companies";
        ResponseEntity<List<Employer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employer>>() {}
        );
        return response.getBody();
    }

    @Override
    public Optional<Employer> getEmployerById(Integer id) {
        String url = projectAUrl + "/employer/{id}";
        ResponseEntity<Employer> response = restTemplate.getForEntity(url, Employer.class, id);
        Employer employer = response.getBody();
        log.info(String.valueOf(employer));
        return Optional.ofNullable(employer);
    }

    @Override
    public Boolean deleteEmployerById(Integer id) {
        String url = projectAUrl + "/employer/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean updateEmployer(Employer employer) {
        String url = projectAUrl + "/employer/"+employer.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employer> requestEntity = new HttpEntity<>(employer, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class, employer.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public List<Employer> searchEmployer(String title) {
        log.info("Searched Employers:");
        String url = projectAUrl + "/employer/title";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(title, headers);
        ResponseEntity<List<Employer>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Employer>>() {}
        );
        return response.getBody();
    }
}
