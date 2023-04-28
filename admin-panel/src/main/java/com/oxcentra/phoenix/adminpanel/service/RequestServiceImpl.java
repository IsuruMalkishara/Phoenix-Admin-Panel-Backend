package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobseekerRequest;
import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService{

    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<JobseekerRequest> getAllRequestByVacancyId(Integer id) {
        log.info(" requests ");
        String url = projectAUrl + "/request/"+id;
        ResponseEntity<List<JobseekerRequest>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobseekerRequest>>() {}
        );
        return response.getBody();
    }

    @Override
    public Boolean deleteRequestById(Integer id) {
        String url = projectAUrl + "/request/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }
}
