package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import com.oxcentra.phoenix.adminpanel.dto.JobType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ModalityServiceImpl implements ModalityService{

    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<JobModality> getAllJobModalities() {
        log.info("All job modalities:");
        String url = projectAUrl + "/modalities";
        ResponseEntity<List<JobModality>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobModality>>() {}
        );
        return response.getBody();
    }

    @Override
    public Boolean addModality(JobModality jobModality) {
        log.info(jobModality.getTitle());
        String url = projectAUrl + "/modality";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JobModality> requestEntity = new HttpEntity<>(jobModality, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class, jobModality.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean updateModality(JobModality jobModality) {
        String url = projectAUrl + "/modality/"+jobModality.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JobModality> requestEntity = new HttpEntity<>(jobModality, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class, jobModality.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean deleteModalityById(String id) {
        String url = projectAUrl + "/modality/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }
}
