package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobSeeker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JobSeekerService {
    List<JobSeeker> getAllJobSeekers();

    Optional<JobSeeker> getJobSeekerById(Integer id);

    Boolean deleteJobSeekerById(Integer id);
}
