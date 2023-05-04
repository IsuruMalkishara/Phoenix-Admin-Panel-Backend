package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.Employer;
import com.oxcentra.phoenix.adminpanel.dto.JobSeeker;
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
public class JobSeekerServiceImpl implements JobSeekerService{

    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<JobSeeker> getAllJobSeekers() {
        log.info("All Jobseekers:");
        String url = projectAUrl + "/jobseeker";
        ResponseEntity<List<JobSeeker>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobSeeker>>() {}
        );
        return response.getBody();
    }

    @Override
    public Optional<JobSeeker> getJobSeekerById(Integer id) {
        String url = projectAUrl + "/jobseeker/{id}";
        ResponseEntity<JobSeeker> response = restTemplate.getForEntity(url, JobSeeker.class, id);
        JobSeeker jobSeeker = response.getBody();
        log.info(String.valueOf(jobSeeker));
        return Optional.ofNullable(jobSeeker);
    }

    @Override
    public Boolean deleteJobSeekerById(Integer id) {
        String url = projectAUrl + "/jobseeker/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public List<JobSeeker> searchJobSeekers(String title) {
        log.info("Searched Job Seekers:");
        String url = projectAUrl + "/jobseeker/title";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(title, headers);
        ResponseEntity<List<JobSeeker>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<JobSeeker>>() {}
        );
        return response.getBody();
    }
}
