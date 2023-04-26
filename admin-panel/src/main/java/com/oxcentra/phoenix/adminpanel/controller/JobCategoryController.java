package com.oxcentra.phoenix.adminpanel.controller;

import com.oxcentra.phoenix.adminpanel.dto.JobCategory;
import com.oxcentra.phoenix.adminpanel.dto.JobModality;
import com.oxcentra.phoenix.adminpanel.service.JobCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/category")
    public @ResponseBody
    List<JobCategory> getAllJobCategories(){

        log.info(String.valueOf(jobCategoryService.getAllJobCategories()));
        return jobCategoryService.getAllJobCategories();
    }
}
