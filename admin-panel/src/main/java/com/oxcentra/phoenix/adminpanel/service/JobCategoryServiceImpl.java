package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.Employer;
import com.oxcentra.phoenix.adminpanel.dto.JobCategory;
import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import com.oxcentra.phoenix.adminpanel.dto.VacancyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class JobCategoryServiceImpl implements JobCategoryService{
    @Value("${projecta.api.url}")
    private String projectAUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<JobCategory> getAllJobCategories() {
        log.info("All job categories:");
        String url = projectAUrl + "/categories";
        ResponseEntity<List<JobCategory>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobCategory>>() {}
        );
        return response.getBody();
    }

    @Override
    public Boolean addCategory(JobCategory jobCategory) {
        log.info(jobCategory.getTitle());
        String url = projectAUrl + "/category";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JobCategory> requestEntity = new HttpEntity<>(jobCategory, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class, jobCategory.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean updateCategory(JobCategory jobCategory) {
        String url = projectAUrl + "/category/"+jobCategory.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JobCategory> requestEntity = new HttpEntity<>(jobCategory, headers);
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class, jobCategory.getId());
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }

    @Override
    public Boolean deleteCategoryById(String id) {
        String url = projectAUrl + "/category/{id}";
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class, id);
        HttpStatus status = response.getStatusCode();
        return status.is2xxSuccessful();
    }
}
