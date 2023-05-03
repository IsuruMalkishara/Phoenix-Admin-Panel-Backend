package com.oxcentra.phoenix.adminpanel.service;

import com.oxcentra.phoenix.adminpanel.dto.JobCategory;
import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobCategoryService {
    List<JobCategory> getAllJobCategories();

    Boolean addCategory(JobCategory jobCategory);

    Boolean updateCategory(JobCategory jobCategory);

    Boolean deleteCategoryById(String id);
}
