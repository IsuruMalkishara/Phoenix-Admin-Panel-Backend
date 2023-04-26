package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeService {
    List<JobType> getAllJobTypes();
}
