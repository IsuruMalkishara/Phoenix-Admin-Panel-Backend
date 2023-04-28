package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobseekerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {
    List<JobseekerRequest> getAllRequestByVacancyId(Integer id);

    Boolean deleteRequestById(Integer id);
}
