package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobCategory;
import com.oxcentra.phoenix.adminpanel.dto.JobType;
import com.oxcentra.phoenix.adminpanel.dto.Vacancy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class TypeServiceImpl implements TypeService{

    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<JobType> getAllJobTypes() {
        log.info("All job types:");
        String url = projectAUrl + "/types";
        ResponseEntity<List<JobType>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobType>>() {}
        );
        return response.getBody();
    }

    @Override
    public Boolean addType(JobType jobType) {
        log.info(jobType.getTitle());
        String url = projectAUrl + "/type";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JobType> requestEntity = new HttpEntity<>(jobType, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class, jobType.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean updateType(JobType jobType) {
        String url = projectAUrl + "/type/"+jobType.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JobType> requestEntity = new HttpEntity<>(jobType, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class, jobType.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean deleteTypeById(String id) {
        String url = projectAUrl + "/type/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }
}
